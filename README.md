# 🛒 BazarApp - Sistema de Gestión de Ventas

Aplicación backend desarrollada con **Java + Spring Boot** para la gestión de ventas en un bazar. Incluye ABM de productos y clientes, control de stock automático, y generación de reportes diarios.

---

## ⚙️ Tecnologías utilizadas

- Java 17  
- Spring Boot  
- Spring Data JPA  
- Hibernate  
- Maven  
- Lombok  
- H2 / MySQL (según config)  
- Postman (para pruebas)  

---

## 📦 Funcionalidades principales

- ✅ CRUD de Productos, Clientes y Ventas  
- ✅ Gestión automática de stock (descuento y reversión)  
- ✅ Relaciones entre entidades: Cliente ↔️ Venta ↔️ Producto  
- ✅ Endpoints REST organizados en arquitectura **multicapa**  
- ✅ Reporte diario de ventas con DTO personalizado  
- ✅ Probado completamente con Postman  

---

## 🗂️ Estructura del proyecto

```
com.jairo.trabajoBazarF
├── controller
├── service
│   ├── I*Service.java
│   └── *Service.java
├── repository
├── model
├── dto
└── TrabajoBazarFApplication.java
```

---

## 🚀 ¿Cómo ejecutar este proyecto?

1. Cloná el repositorio  
2. Abrilo con IntelliJ IDEA o Eclipse  
3. Asegurate de tener:
   - Java 17
   - Maven  
4. Ejecutá la clase `TrabajoBazarFApplication`  
5. La API se ejecuta en: `http://localhost:8080`  

---

## 🔁 Endpoints disponibles

### 📁 Productos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET    | `/productos` | Listar todos los productos |
| GET    | `/productos/{id}` | Buscar producto por ID |
| POST   | `/productos/crear` | Crear producto |
| PUT    | `/productos/editar/{id}` | Editar producto |
| DELETE | `/productos/eliminar/{id}` | Eliminar producto |

### 👤 Clientes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET    | `/clientes` | Listar todos los clientes |
| GET    | `/clientes/{id}` | Buscar cliente por ID |
| POST   | `/clientes/crear` | Crear cliente |
| PUT    | `/clientes/editar/{id}` | Editar cliente |
| DELETE | `/clientes/eliminar/{id}` | Eliminar cliente |

### 💸 Ventas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET    | `/venta/lista` | Listar todas las ventas |
| GET    | `/venta/buscar/{id}` | Buscar venta por ID |
| POST   | `/venta/crear` | Crear nueva venta |
| PUT    | `/venta/editar/{id}` | Editar venta |
| DELETE | `/venta/eliminar/{id}` | Eliminar venta |

---

### 📊 Reporte de ventas diarias

| Endpoint | Descripción |
|----------|-------------|
| GET `/venta/resumen/{fecha}` | Devuelve la cantidad de ventas, total facturado y los códigos de venta en una fecha determinada |

#### Ejemplo de respuesta:

```json
{
  "fecha": "2025-03-30",
  "cantidadVentas": 3,
  "totalFacturado": 47.75,
  "codigosVentas": [1, 2, 3]
}
```

---

## 🧪 Pruebas

La aplicación fue testeada manualmente utilizando Postman. Se incluyen:

- Alta y baja de productos y clientes  
- Registro de ventas  
- Actualización de stock  
- Reportes de ventas por fecha  

---

## 🔐 Seguridad

🔓 Este proyecto **no incluye autenticación**. Está pensado como un backend funcional de práctica para portfolio.  
Puede ampliarse fácilmente con **Spring Security y JWT** en el futuro.

---

## 🧠 Mejoras futuras

- Validaciones con DTOs y anotaciones `@NotBlank`  
- Autenticación y autorización con JWT  
- Documentación con Swagger  
- Paginación y filtros por parámetros  
- Despliegue en la nube (Docker + Render / DonWeb)  

---

## 👨‍💻 Autor

**Jairo **  

