--DROP SCHEMA IF EXISTS inditex CASCADE;

-- Crear esquema
--CREATE SCHEMA IF NOT EXISTS inditex;

-- Crear tabla de marcas
CREATE TABLE IF NOT EXISTS brands (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

-- Crear tabla de precios
CREATE TABLE IF NOT EXISTS prices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand_id BIGINT,
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    price_list INT,
    product_id INT,
    priority INT,
    price DECIMAL(10, 2),
    curr VARCHAR(3),
    FOREIGN KEY (brand_id) REFERENCES brands(id)
);