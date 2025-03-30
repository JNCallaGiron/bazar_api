package com.jairo.trabajoBazarF.service;

import com.jairo.trabajoBazarF.dto.ResumenVentasDiariasDTO;
import com.jairo.trabajoBazarF.model.Cliente;
import com.jairo.trabajoBazarF.model.DetalleVenta;
import com.jairo.trabajoBazarF.model.Producto;
import com.jairo.trabajoBazarF.model.Venta;
import com.jairo.trabajoBazarF.repository.IClienteRepository;
import com.jairo.trabajoBazarF.repository.IDetalleVentaRepository;
import com.jairo.trabajoBazarF.repository.IProductoRepository;
import com.jairo.trabajoBazarF.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepository;

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private IDetalleVentaRepository detalleVentaRepository;

    @Override
    @Transactional // para poder revertir en caso de algun error
    public Venta saveVenta(Venta venta) {
        // 🔹 Verificar si el cliente existe
        Optional<Cliente> clienteOpt = clienteRepository.findById(venta.getUnCliente().getIdCliente());
        if (clienteOpt.isEmpty()) {
            return null; // Cliente no encontrado
        }
        venta.setUnCliente(clienteOpt.get());

        // 🔹 Procesar los detalles de la venta
        for (DetalleVenta detalle : venta.getDetallesVenta()) {
            Producto producto = productoRepository.findById(detalle.getProducto().getIdProducto()).orElse(null);

            if (producto == null) {
                return null; // Producto no encontrado
            }

            // Verificar si hay stock suficiente antes de vender
            //recorre cada producto que forma para de la  venta
            if (producto.getCantidadDisponible() < detalle.getCantidad()) {
                return null; // Stock insuficiente
            }

            // ✅ Descontar stock del producto
            producto.setCantidadDisponible(producto.getCantidadDisponible() - detalle.getCantidad());
            productoRepository.save(producto);

            // ✅ Asociar la venta al detalle
            detalle.setVenta(venta);
        }

        // ✅ Guardar la venta y los detalles
        Venta nuevaVenta = ventaRepository.save(venta);
        detalleVentaRepository.saveAll(venta.getDetallesVenta());

        return nuevaVenta;
    }


    @Override
    public List<Venta> getVentas() {

        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> findVenta(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    @Transactional
    public Venta editVenta(Long id, Venta ventaEditada) {
        System.out.println("🟡 [editVenta] Iniciando edición para ID venta: " + id);

        Venta venta = this.findVenta(id).orElse(null);
        if (venta == null) {
            System.out.println("❌ Venta no encontrada con ID: " + id);
            return null;
        }

        if (ventaEditada.getDetallesVenta() == null || ventaEditada.getDetallesVenta().isEmpty()) {
            System.out.println("❌ La venta editada no contiene detalles de productos.");
            return null;
        }

        System.out.println("🟢 Venta actual recuperada. Validando productos modificados...");
        boolean productosModificados = !venta.getDetallesVenta().equals(ventaEditada.getDetallesVenta());

        if (productosModificados) {
            // 🔄 Restaurar stock de productos anteriores
            for (DetalleVenta detalle : venta.getDetallesVenta()) {
                Producto producto = detalle.getProducto();
                producto.setCantidadDisponible(producto.getCantidadDisponible() + detalle.getCantidad());
                productoRepository.save(producto);
                System.out.println("🔄 Stock restaurado para producto ID: " + producto.getIdProducto());
            }

            // 🗑️ Eliminar detalles antiguos
            detalleVentaRepository.deleteAll(venta.getDetallesVenta());
            System.out.println("🗑️ Detalles de venta anteriores eliminados.");

            // ✅ Nueva lista de detalles para guardar luego
            List<DetalleVenta> nuevosDetalles = new ArrayList<>();

            for (DetalleVenta nuevoDetalle : ventaEditada.getDetallesVenta()) {
                Long idProducto = nuevoDetalle.getProducto().getIdProducto();
                Producto producto = productoRepository.findById(idProducto).orElse(null);

                if (producto == null) {
                    System.out.println("❌ Producto no encontrado con ID: " + idProducto);
                    return null;
                }

                if (producto.getCantidadDisponible() < nuevoDetalle.getCantidad()) {
                    System.out.println("❌ Stock insuficiente para producto ID: " + idProducto);
                    return null;
                }

                producto.setCantidadDisponible(producto.getCantidadDisponible() - nuevoDetalle.getCantidad());
                productoRepository.save(producto);
                System.out.println("📦 Stock actualizado para producto ID: " + idProducto);

                nuevoDetalle.setVenta(venta); // vincular detalle a la venta
                nuevosDetalles.add(nuevoDetalle);
            }

            venta.getDetallesVenta().clear();
            venta.getDetallesVenta().addAll(nuevosDetalles);


            // Guardar nuevos detalles
            detalleVentaRepository.saveAll(nuevosDetalles);
            System.out.println("✅ Nuevos detalles guardados");
        }

        // 🔄 Actualizar fecha y total
        venta.setFechaVenta(ventaEditada.getFechaVenta());
        venta.setTotal(ventaEditada.getTotal());

        // 💾 Guardar la venta actualizada
        Venta ventaActualizada = ventaRepository.save(venta);
        System.out.println("✅ Venta editada correctamente: ID " + id);

        return ventaActualizada;
    }





    @Override
    @Transactional
    public boolean deleteVenta(Long id) {
        Venta venta = this.findVenta(id).orElse(null);
        if (venta != null) {
            // ✅ Restaurar stock de los productos antes de eliminar la venta
            for (DetalleVenta detalle : venta.getDetallesVenta()) {
                Producto producto = detalle.getProducto();
                producto.setCantidadDisponible(producto.getCantidadDisponible() + detalle.getCantidad());
                productoRepository.save(producto);
            }

            // ✅ Eliminar los detalles de venta y la venta
            detalleVentaRepository.deleteAll(venta.getDetallesVenta());
            ventaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ResumenVentasDiariasDTO obtenerResumenDeVentasPorFecha(LocalDate fecha) {
        List<Venta> ventasDelDia = ventaRepository.findByFechaVenta(fecha);

        if (ventasDelDia.isEmpty()) return null;

        double totalFacturado = ventasDelDia.stream()
                .mapToDouble(Venta::getTotal)
                .sum();

        List<Long> codigos = ventasDelDia.stream()
                .map(Venta::getCodigoVenta)
                .toList();

        return new ResumenVentasDiariasDTO(
                fecha,
                ventasDelDia.size(),
                totalFacturado,
                codigos
        );
    }
}
