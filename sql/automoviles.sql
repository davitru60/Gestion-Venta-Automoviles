DROP TABLE VENTAS;
DROP TABLE AUTOMOVILES;
DROP TABLE CLIENTES;


CREATE TABLE automoviles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    marca VARCHAR(255) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    ano INT NOT NULL,
    color VARCHAR(255) NOT NULL,
    precio DECIMAL(10,2) NOT NULL
);

CREATE TABLE clientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    telefono VARCHAR(20)
);

CREATE TABLE ventas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    automovil_id INT NOT NULL,
    cliente_id INT NOT NULL,
    fecha VARCHAR(10) NOT NULL,
    precio_venta DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (automovil_id) REFERENCES automoviles(id) ON DELETE CASCADE,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);

INSERT INTO automoviles (marca, modelo, ano, color, precio) VALUES ('Chevrolet', 'Spark', 2020, 'Rojo', 10000.00);
INSERT INTO automoviles (marca, modelo, ano, color, precio) VALUES ('Toyota', 'Corolla', 2021, 'Blanco', 20000.00);
INSERT INTO automoviles (marca, modelo, ano, color, precio) VALUES ('Ford', 'Mustang', 2019, 'Negro', 30000.00);
INSERT INTO automoviles (marca, modelo, ano, color, precio) VALUES ('Honda', 'Civic', 2018, 'Gris', 15000.00);
INSERT INTO automoviles (marca, modelo, ano, color, precio) VALUES ('Nissan', 'Versa', 2022, 'Azul',18000.00);

INSERT INTO clientes (nombre, apellido, email, telefono) VALUES ('Juan', 'Pérez', 'juanperez@gmail.com', '555-1234');
INSERT INTO clientes (nombre, apellido, email, telefono) VALUES ('María', 'González', 'mariagonzalez@yahoo.com', '555-5678');
INSERT INTO clientes (nombre, apellido, email, telefono) VALUES ('Carlos', 'García', 'carlosgarcia@gmail.com','555-3456');

INSERT INTO ventas (automovil_id, cliente_id, fecha, precio_venta) VALUES (1, 1, '2022-01-05', 11000.00);
INSERT INTO ventas (automovil_id, cliente_id, fecha, precio_venta) VALUES (2, 2, '2022-02-10', 22000.00);
INSERT INTO ventas (automovil_id, cliente_id, fecha, precio_venta) VALUES (3, 3, '2022-03-15', 32000.00);
INSERT INTO ventas (automovil_id, cliente_id, fecha, precio_venta) VALUES (4, 4, '2022-04-20', 17000.00);
INSERT INTO ventas (automovil_id, cliente_id, fecha, precio_venta) VALUES (5, 5, '2022-05-25',20000.00);