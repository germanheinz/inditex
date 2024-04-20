-- Crear esquema
CREATE SCHEMA IF NOT EXISTS inditex;

-- Crear tabla de marcas
CREATE TABLE IF NOT EXISTS inditex.brands (
                                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                name CHARACTER VARYING
    );

-- Insertar datos iniciales
INSERT INTO inditex.brands (name) VALUES ('ZARA'), ('Otra marca');

-- Crear tabla de precios
CREATE TABLE IF NOT EXISTS inditex.prices (
                                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                brand_id BIGINT,
                                                start_date CHARACTER VARYING,
                                                end_date CHARACTER VARYING,
                                                price_list INT,
                                                product_id INT,
                                                priority INT,
                                                price DECIMAL(10, 2),
    curr VARCHAR(3),
    FOREIGN KEY (brand_id) REFERENCES inditex.brands(id)
    );
