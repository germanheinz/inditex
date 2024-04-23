# Inditex App Demo - Germán Heinz 

Proyecto demo para Inditex

## Tabla de Contenidos

1. [Introducción](#introducción)
2. [Tecnologías Utilizadas](#tecnologías-utilizadas)
3. [Ejecución del Proyecto](#ejecución-del-proyecto)
4. [Endpoint](#endpoints)

## Introducción

Breve descripción de tu proyecto y su propósito.

## Tecnologías Utilizadas

- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database
- Lombok
- MapStruct
- OpenAPI Generator

## Ejecución del Proyecto


1. Clona este repositorio.
2. Abre el proyecto en tu IDE favorito.
3. Configura el entorno de desarrollo según sea necesario.
4. Ejecuta la aplicación Spring Boot.

## Endpoints
A continuación se muestra una lista de los endpoints disponibles en la API:

1. `GET /price`: Obtiene todos los precios según un criterio de búsqueda.
    - Parámetros de entrada:
        - `brandId`: ID de la marca.
        - `startDate`: Fecha de inicio.
        - `productId`: ID del producto.
    - Ejemplo de solicitud:
      ```json
      {
        "brandId": "1",
        "startDate": "2022-01-01T00:00:00",
        "productId": 35455
      }
      ```
    - Ejemplo de respuesta:
      ```json
      [
        {
          "id": 1,
          "brandId": "1",
          "startDate": "2022-01-01T00:00:00",
          "endDate": "2022-06-30T23:59:59",
          "priceList": 1,
          "productId": 35455,
          "priority": 0,
          "price": 35.50,
          "curr": "EUR"
        },
        {
          "id": 2,
          "brandId": "1",
          "startDate": "2022-07-01T00:00:00",
          "endDate": "2022-12-31T23:59:59",
          "priceList": 2,
          "productId": 35455,
          "priority": 0,
          "price": 25.45,
          "curr": "EUR"
        }
      ]
      ```
