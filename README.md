Sistema de Gestión de Almacén by // RONALDO SOLANO (Let's keep the vibes up) //


Aplicación de escritorio desarrollada en Java Swing para la administración de usuarios y productos de un almacén.  
Incluye inicio de sesión, registro de usuarios, gestión de inventario y paneles visuales con diseño moderno.

---

Características

- **Inicio de sesión** seguro para acceder al sistema.
- **Registro de usuarios** con validación de datos y confirmación de contraseña.
- **Gestión de usuarios**: listado, alta y mantenimiento.
- **Gestión de productos**: visualización, creación, edición y eliminación.
- **Diseño moderno** con paleta de colores y fuentes personalizadas.
- **Base de datos en memoria** (sin necesidad de configuración externa).
- Paneles dinámicos con **CardLayout** para navegación fluida.

---

Tecnologías utilizadas

- **Java SE** (versión 8+)
- **Swing** para la interfaz gráfica.
- **Java Collections Framework** (`Map`, `List`, `ArrayList`, `HashMap`).
- **CardLayout** para la navegación entre pantallas.

---

Estructura del código

El proyecto está contenido en una sola clase principal:

- **SistemaGestionAlmacen**: Contiene toda la lógica de interfaz, manejo de eventos y almacenamiento temporal de datos.
- **Usuario** (clase interna): Representa a un usuario del sistema.
- **Producto** (clase interna): Representa a un producto en inventario.

---

Funcionalidades principales

Autenticación
- **Login** con validación de usuario y contraseña.
- Usuario **admin** por defecto:
  - Usuario: `admin`
  - Contraseña: `admin123`

Gestión de Usuarios
- Registro de nuevos usuarios con datos obligatorios:
  - Usuario
  - Nombre
  - Apellido
  - Teléfono
  - Email
  - Contraseña (con confirmación)
- Listado de usuarios registrados.

Gestión de Productos
- Creación de nuevos productos con:
  - Nombre
  - Marca
  - Categoría
  - Precio
  - Cantidad disponible
- Listado de productos existentes.
- Visualización y modificación de detalles.

---

Instalación y ejecución

1. **Clonar** el repositorio o copiar el código fuente.
2. Abrir el proyecto en un **IDE Java** (NetBeans, IntelliJ, Eclipse).
3. Compilar y ejecutar el archivo:
   ```bash
   SistemaGestionAlmacen.java
