# Prueba Técnica - Spring Boot

Este es un proyecto de ejemplo creado con **Maven** y **Spring Boot**, diseñado para servir como prueba técnica o plantilla inicial.  

---

## 🔹 Tecnologías utilizadas

- Java 21
- Spring Boot
- Maven
- PostgreSQL (puede usarse con Supabase u otro servicio)
- Docker (opcional para despliegue)

---

## 🔹 Funcionalidades

- Conexión a base de datos PostgreSQL
- Configuración de datasource vía `application.properties`
- Proyecto listo para empaquetar como JAR y ejecutar
- Dockerfile incluido para ejecutar la aplicación en contenedor

---

## 🔹 Requisitos

- Java 21 instalado
- Maven
- PostgreSQL o base de datos remota disponible
- (Opcional) Docker para contenedor

---

## 🔹 Cómo descargar y ejecutar localmente

1. Clona el repositorio:

```bash
git clone https://github.com/YeiderGarzon/pruebaTecnica.git
````

2. Entra a la carpeta del proyecto:

```bash
cd pruebaTecnica
```

3. Compila y empaqueta el proyecto con Maven:

```bash
mvn clean package
```

Esto generará un JAR dentro de la carpeta `target/`.

4. Ejecuta la aplicación:

```bash
java -jar target/pruebaTecnica.jar
```

> Asegúrate de tener configurada la conexión a la base de datos en `src/main/resources/application.properties`.

---

## 🔹 Ejecutar con Docker (opcional)

1. Construye la imagen:

```bash
docker build -t pruebaTecnica:latest .
```

2. Corre el contenedor:

```bash
docker run -p 8080:8080 pruebaTecnica:latest
```

* La aplicación estará disponible en `http://localhost:8080`.

---

## 🔹 Contribuir

Si deseas contribuir:

1. Haz un fork del repositorio.
2. Crea una rama para tu feature: `git checkout -b feature/nueva-funcionalidad`
3. Haz commit de tus cambios: `git commit -m "Agrega nueva funcionalidad"`
4. Sube tus cambios: `git push origin feature/nueva-funcionalidad`
5. Abre un Pull Request en GitHub.

---

## 🔹 Contacto

* Autor: Yeider Garzón
* GitHub: [https://github.com/YeiderGarzon](https://github.com/YeiderGarzon)

