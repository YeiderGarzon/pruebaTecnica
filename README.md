# 🚀 Prueba Técnica - Sistema de Gestión de Franquicias

Este proyecto es una aplicación **Spring Boot 3** diseñada para gestionar el inventario de productos en sucursales de diferentes franquicias. Se ha puesto especial énfasis en la seguridad, escalabilidad e Infraestructura como Código (IaC).

---

## 🔹 Tecnologías Utilizadas

* **Java 21**
* **Spring Boot 3.2.5**
* **Maven** (Gestión de dependencias)
* **Azure SQL / SQL Server** (Base de Datos Relacional)
* **Hibernate/JPA** (Persistencia de datos)
* **Terraform** (Infraestructura como Código)
* **JUnit 5 / Mockito** (Pruebas unitarias y de integración)

---

## 🔹 Funcionalidades Principales

* **Gestión de Franquicias:** Creación y listado.
* **Gestión de Sucursales:** Añadir sucursales a franquicias existentes.
* **Control de Inventario:**
* Agregar productos a sucursales.
* Eliminar productos.
* Modificar stock de productos.
* Consultar el producto con más stock por sucursal para una franquicia determinada.


* **Arquitectura:** Implementación de patrones **Service**, **Repository** y manejo global de excepciones.

---

## 🔹 Configuración Obligatoria (Local)

Por motivos de seguridad, las credenciales de la base de datos no están incluidas en el código. Para ejecutar el proyecto localmente, **debe crear el archivo de configuración personal**:

1. En la raíz del proyecto (donde está el archivo `pom.xml`), cree un archivo llamado `local.properties`.
2. Pegue el contenido enviado por mensaje privado con las credenciales de acceso:

```properties
# Configuración de Base de Datos
SPRING_DATASOURCE_URL=jdbc:sqlserver://tu-servidor.database.windows.net:1433;database=tu-db
SPRING_DATASOURCE_USERNAME=tu_usuario
SPRING_DATASOURCE_PASSWORD=tu_password_seguro

```

> **Nota:** Este archivo está ignorado por Git para prevenir la fuga de secretos.

---

## 🔹 Infraestructura (IaC)

El proyecto incluye una carpeta `/terraform` con los archivos necesarios para desplegar automáticamente los recursos en **Azure Cloud** (son opcionales, puesto que ya están desplegados):

* Recurso de **Azure SQL Server**.
* Base de Datos SQL.
* Reglas de Firewall para permitir acceso desde la aplicación.

---

## 🔹 Ejecución del Proyecto

### 1. Pruebas Unitarias y de Integración

Para validar la lógica de negocio y la conexión, ejecute:

```bash
mvn clean test

```

### 2. Ejecutar Localmente

```bash
mvn spring-boot:run

```

### 3. Ejecutar mediante JAR

```bash
mvn clean package
java -jar target/pruebaTecnica-0.0.1-SNAPSHOT.jar

```

---

## 🔹 Uso de Docker

1. **Construir la imagen:**
```bash
docker build -t franquicia-app:latest .

```


2. **Ejecutar el contenedor:**
Para que Docker reconozca las variables, pase el archivo de propiedades:
```bash
docker run -p 8080:8080 --env-file .env franquicia-app:latest

```

## 🚀 Despliegue en la Nube

La aplicación se encuentra **actualmente desplegada** y lista para ser probada sin necesidad de configuración local.

* **URL Base:** [https://pruebatecnica-qyb8.onrender.com](https://pruebatecnica-qyb8.onrender.com)
* **Documentación Interactiva (Swagger):** Puedes probar todos los endpoints directamente desde el navegador aquí:
👉 **[https://pruebatecnica-qyb8.onrender.com/swagger-ui.html](https://www.google.com/search?q=https://pruebatecnica-qyb8.onrender.com/swagger-ui.html)**

---

### 💡 Nota para el evaluador:

Desde el **Swagger UI**, puedes interactuar con las entidades (Franquicias, Sucursales y Productos) en tiempo real, ya que la API está conectada a la base de datos de **Azure SQL** configurada mediante Terraform.

---

## 🔹 Contacto

* **Autor:** Yeider Garzón
* **GitHub:** [https://github.com/YeiderGarzon](https://github.com/YeiderGarzon)

---