 ## 🛠 Proyecto de automatización de API 🚀

Este proyecto implementa la automatización de pruebas de API usando Java, TestNG, RestAssured y ExtentReports .
Las pruebas se ejecutan en paralelo y generan informes detallados para analizar los resultados.

## 📌 Tecnologías Usadas

Este proyecto usa las siguientes tecnologías:

- [Java 17](https://www.oracle.com/java/)
- [Maven](https://maven.apache.org/)
- [TestNG](https://testng.org/)
- [RestAssured](https://rest-assured.io/)
- [ExtentReports](https://extentreports.com/)
- [JSON Simple](https://github.com/fangyidong/json-simple)

## 📂 Estructura del proyecto
```bash
RetoKataApi/
├── .github/                     # Configuración de GitHub Actions
│   └── workflows/               # Flujos de trabajo de automatización
│       └── apiTestKata.yml      # Flujo de trabajo para ejecutar pruebas de API
├── .idea/                        # Configuración del IDE IntelliJ IDEA
├── src/                          # Código fuente del proyecto
│   └── test/                     # Código de pruebas
│       └── java/                 # Código Java de pruebas
│           └── co.com.bancodebogota/ # Paquete base de pruebas
│               ├── apis/         # Clases que definen las interacciones con los endpoints de la API
│               │   ├── Delete.java # Implementación del método DELETE
│               │   ├── Get.java    # Implementación del método GET
│               │   ├── Post.java   # Implementación del método POST
│               │   └── Put.java    # Implementación del método PUT
│               ├── apisTest/     # Clases de prueba para cada endpoint de la API
│               │   ├── DeleteTest.java # Pruebas para el método DELETE
│               │   ├── GetTest.java    # Pruebas para el método GET
│               │   ├── PostTest.java   # Pruebas para el método POST
│               │   └── PutTest.java    # Pruebas para el método PUT
│               └── Utils/              # Clases de utilidad para las pruebas
│                   ├── Configuration.java # Configuración del entorno de pruebas
│                   └── ExtentReportListener.java # Listener para generar reportes Extent Reports
│   └── resources/                # Recursos del proyecto
│       └── json/                 # Archivos JSON para datos de prueba
│           ├── cart_invalid.json       # Datos JSON inválidos para pruebas de carrito
│           ├── cart_new.json           # Datos JSON para crear un nuevo carrito
│           ├── cart_update_invalid.json # Datos JSON inválidos para actualizar un carrito
│           └── cart_update_success.json # Datos JSON para actualizar un carrito con éxito
├── target/                       # Directorio de salida de Maven
│   └── test-output/              # Reportes de prueba generados por TestNG
├── .gitignore                    # Archivo para ignorar archivos en Git
├── pom.xml                       # Archivo de configuración de Maven
├── README.md                     # Este archivo (documentación del proyecto)
└── testng.xml                    # Archivo de configuración de TestNG
```

## ⚙️ Instalación y configuración

## Clonar el repositorio
//github.com/AnerolRivera/RetoKataApi.git

## ⚙️️Instalar dependencias con Maven
```bash
mvn clean install
```
## 🚀Ejecutar todas las pruebas
```bash
mvn test
```
## Ejecutar pruebas con reportes
```bash
mvn clean verify
```
📌 Funcionalidades implementadas
✅ Consumo de APIs con los métodos:
GET → Obtener información
POST → Crear nuevos registros
PUT → Actualizar registros
DELETE → Eliminar registros

✅ Validaciones en las pruebas

Código de respuesta esperado.
Consumo exitoso y con error.
Validación de respuestas JSON.

✅ Ejecución en paralelo

Configurado en testng.xml
3 pruebas simultáneas

✅ Generación de reportes ExtentReports

Reportes detallados con logs.
Guardados en test-output/ExtentReport.html.

## Workflow de GitHub Actions

Este proyecto utiliza GitHub Actions para automatizar la ejecución de pruebas de API. El workflow se define en el 
archivo `.github/workflows/apiTestKata.yml` y se activa en los siguientes eventos:

* **`push`:** Ejecuta las pruebas en cada push a la rama `main`.
* **`schedule`:** Ejecuta las pruebas diariamente a las 6:00 AM UTC.
* **`workflow_dispatch`:** Permite ejecutar las pruebas manualmente.

El workflow realiza los siguientes pasos:

1.  Descarga el código fuente del repositorio.
2.  Configura el entorno de Java 17.
3.  Instala las dependencias y compila el proyecto.
4.  Ejecuta las pruebas de API con TestNG.
5.  Sube los reportes de TestNG y ExtentReports como artefactos.

Para más detalles, consulta el archivo [`.github/workflows/apiTestKata.yml`](.github/workflows/apiTestKata.yml).


📜 Dependencias utilizadas

📌 Listado de las principales dependencias de Maven (pom.xml)
```xml
<dependencies>
    <!-- TestNG para pruebas -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.9.0</version>
        <scope>test</scope>
    </dependency>

    <!-- RestAssured para pruebas de API -->
    <dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.4.0</version>
    </dependency>

    <!-- Manejo de JSON -->
    <dependency>
        <groupId>org.json</groupId>
        <artifactId>json</artifactId>
        <version>20210307</version>
    </dependency>

    <!-- Generación de reportes con ExtentReports -->
    <dependency>
        <groupId>com.aventstack</groupId>
        <artifactId>extentreports</artifactId>
        <version>5.0.9</version>
    </dependency>

    <!-- Lombok para reducir código repetitivo -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
        <scope>provided</scope>
    </dependency>

    <!-- Logs con SLF4J -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-simple</artifactId>
        <version>1.7.36</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```
🔄 Ejecución en paralelo (Configuración TestNG)
📌 Configuración en testng.xml para ejecutar pruebas en paralelo:
```xml
<?xml version="1.0" encoding="UTF-8"?>

<!-- Definición del tipo de documento de TestNG -->
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">

<!-- Inicio de la suite de pruebas -->
<suite name="Suite" thread-count="3" parallel="methods">

    <!-- Configuración de los listeners para generar reportes y capturar eventos -->
    <listeners>
        <!-- ExtentReportListener se encarga de generar reportes detallados de ejecución -->
        <listener class-name="co.com.bancodebogota.Utils.ExtentReportListener"/>
    </listeners>

    <!-- Definición de un grupo de pruebas llamado "API Tests" -->
    <test name="API Tests">
        <classes>
            <!-- Se definen las clases de prueba que se ejecutarán -->
            <class name="co.com.bancodebogota.apisTest.GetTest"/>
            <class name="co.com.bancodebogota.apisTest.PostTest"/>
            <class name="co.com.bancodebogota.apisTest.PutTest"/>
            <class name="co.com.bancodebogota.apisTest.DeleteTest"/>
        </classes>
    </test>

</suite>

```

📌 Ubicación de reportes generados
Los reportes ExtentReports se guardan en la carpeta:

📂 test-output/ExtentReport
Para abrir el reporte, simplemente haz doble clic en el archivo o ábrelo en un navegador.

🔥 Beneficios de esta estructura
✅ Modularidad: Separa la lógica de las APIs, los casos de prueba y las configuraciones.
✅ Facilidad de mantenimiento: Puedes modificar las pruebas sin tocar la lógica de las APIs.
✅ Ejecución en paralelo: Las pruebas se ejecutan de manera más rápida gracias a testng.xml.
✅ Reportes automatizados: Los resultados quedan documentados en ExtentReports.
