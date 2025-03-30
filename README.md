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

## ⚙️ Configuración de variables de entorno

Este proyecto utiliza variables de entorno para conectarse a la base de datos MySQL.

### Variables requeridas:

- `DB_URL`: URL de conexión JDBC (ej: `jdbc:mysql://localhost:3306/bazar?useSSL=false&serverTimezone=UTC`)
- `DB_USER`: Usuario de la base de datos (ej: `root`)
- `DB_PASSWORD`: Contraseña del usuario (puede estar vacía)

### 💡 ¿Cómo configurarlas en IntelliJ IDEA Community?

1. En el menú superior derecho, hacé clic en el nombre del proyecto (ej: `TrabajoBazarFApplication`)
2. Seleccioná **"Edit Configurations..."**
3. En el campo **"Environment variables"**, agregá:

```
DB_URL=jdbc:mysql://localhost:3306/bazar?useSSL=false&serverTimezone=UTC;DB_USER=root;DB_PASSWORD=
```

4. Guardá y ejecutá normalmente

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

## 📫 Colección Postman incluida

Para facilitar el testeo de esta API, se incluye una colección de Postman con todos los endpoints del proyecto.

### 📁 Archivo:
`bazar_api_collection.postman_collection.json`

### 🧪 ¿Cómo usarla?

1. Abrí Postman  
2. Hacé clic en **"Import"**  
3. Seleccioná el archivo `bazar_api_collection.postman_collection.json` ubicado en este repositorio  
4. Ejecutá las peticiones disponibles (productos, clientes, ventas, resumen diario, etc.)

> 💡 Asegurate de tener corriendo el backend en `http://localhost:8080`

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

**Jairo Calla Giron**  
📧 jairo@email.com  
🔗 [https://github.com/JNCallaGiron](https://github.com/JNCallaGiron)


