# Prueba Técnica Inditex

Este proyecto contiene una API rest para crear y listar tareas

Ofrece la siguientes funcionalidades:

- Buscar precios en función de parámetros

## Puesta en marcha

Este es un ejemplo de cómo ejecutar el proyecto localmente.

### Prerequisitos

Se necesita tener instalado lo siguiente:

- JDK de java 17 o superior

### Instalación

1. Compilar el proyecto

```sh
mvn clean install -DskipTest
```

2. Para ejecutar el proyecto localmente, podemos usar una de las siguientes formas:
    1. Ejecutar el siguiente comando:
   ```sh
   cd boot/target
   java -jar boot-1.0.0.jar
   ```

    2. Ejecutar manualmente la clase 'InditexApplication.java' que se encuentra en el módulo 'boot'

4. Para ejecutar los test unitarios bastas con el siguiente comando:

```sh
mvn clean install
```

5. Para ejecutar los test de integración basta con el siguiente comando:

```sh
mvn surefire:test
```

## Desarrollo

### Estructura del proyecto

El proyecto está organizado en una arquitecture hexagonal, con los siguientes componentes:

- **api**: Contiene la definición y controladores de la API.
    - **openapi**: Contiene la definicion y la documentación de la API.
    - **rest**: Contiene controladores de la API REST.
- **boot**: Contiene la aplicación principal y los test de integración.
- **domain**: Contiene los entidades y la lógica de negocio del proyecto.
- **infrastructure**: Contiene la partsistencia de los datos en la base de datos.

### Estructura de los casos de aceptación o test de integración

Los casos de aceptación para las funcionalidades de la API REST son los siguientes:

- **GetPriceIT.getPrice_Test1**: Obtener el precio para la marca 1 en la fecha 2020-06-14 00:00:00
- **GetPriceIT.getPrice_Test2**: Obtener el precio para la marca 1 en la fecha 2020-06-14 15:00:00
- **GetPriceIT.getPrice_Test3**: Obtener el precio para la marca 1 en la fecha 2020-06-14 21:00:00
- **GetPriceIT.getPrice_Test4**: Obtener el precio para la marca 1 en la fecha 2020-06-15 10:00:00
- **GetPriceIT.getPrice_Test5**: Obtener el precio para la marca 1 en la fecha 2020-06-16 21:00:00

    