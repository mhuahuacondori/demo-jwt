-- Crear secuencias
CREATE SEQUENCE IF NOT EXISTS table_empleados_id_seq;
CREATE SEQUENCE IF NOT EXISTS table_usuarios_id_seq;

-- Crear tabla EMPLEADOS con restricciones
CREATE TABLE IF NOT EXISTS EMPLEADOS(
    ID integer NOT NULL DEFAULT nextval('table_empleados_id_seq'),
    NOMBRE varchar(30) NOT NULL,
    APELLIDO varchar(30) NOT NULL,
    FECHA_NAC date NOT NULL CHECK (FECHA_NAC <= CURRENT_DATE),
    REPORTA_A int NULL,
    EXTENSION int NULL CHECK (EXTENSION >= 1000 AND EXTENSION <= 9999),
    FECHA_CREACION TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_EMPLEADOS PRIMARY KEY (ID)
);

-- Crear tabla USUARIO con restricciones y seguridad
CREATE TABLE IF NOT EXISTS USUARIO(
    ID integer NOT NULL DEFAULT nextval('table_usuarios_id_seq'),
    NOMBRE varchar(50) NOT NULL,
    EMAIL varchar(50) NOT NULL UNIQUE,
    PASSWORD varchar(100) NOT NULL,
    FECHA_CREACION TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT PK_USUARIOS PRIMARY KEY (ID)
);

-- Crear relación REPORTA_A entre empleados
ALTER TABLE EMPLEADOS ADD CONSTRAINT FK_REPORTA_A FOREIGN KEY (REPORTA_A) REFERENCES EMPLEADOS(ID) ON DELETE SET NULL;

-- Crear índices para mejorar rendimiento
CREATE INDEX idx_empleados_reporta ON EMPLEADOS(REPORTA_A);
CREATE INDEX idx_usuario_email ON USUARIO(EMAIL);

-- Insertar datos en EMPLEADOS
INSERT INTO EMPLEADOS (NOMBRE, APELLIDO, FECHA_NAC, REPORTA_A, EXTENSION) VALUES
    ('Carlos', 'Gómez', '1985-06-15', NULL, 1234),
    ('Ana', 'Martínez', '1990-02-20', 1, 5678),
    ('Luis', 'Fernández', '1988-09-10', 2, 9012),
    ('Sofía', 'Rodríguez', '1995-07-25', 1, 3456),
    ('Miguel', 'Pérez', '1992-12-30', 3, 7890);

-- Insertar datos en USUARIO
INSERT INTO USUARIO (NOMBRE, EMAIL, PASSWORD) VALUES
    ('admin', 'admin@example.com', '$2a$12$jDKB00EBOsdL4ykiVDGapuAdFDcOxBSVlAPM569GJSmacjBw/1rHG'),
    ('usuario1', 'user1@example.com', '$2a$12$2hQueZ.0LTlbWGi3hLw74.YdDc5NUT898agsmz3Na7jTUtbwGOZZq'),
    ('usuario2', 'user2@example.com', '$2a$12$k2yQGEE6zrX9pyGeLMWRZ.ORK4B1MtvPC0UlusBcyebgrqDhGm1Fi'),
    ('usuario3', 'user3@example.com', '$2a$12$3fZcuKEDslOb/2LCtzlSiukihzQy/lDtC81E327QYz4agmRSnNXqK'),
    ('usuario4', 'user4@example.com', '$2a$12$a9QO4Y73NDd7eXbkYCIoau3cBiE9IQFD0upTFRxgT8iV07s/lpY3W');

-- Consultas útiles
SELECT ID, NOMBRE, APELLIDO, FECHA_NAC, REPORTA_A, EXTENSION FROM EMPLEADOS;
SELECT ID, NOMBRE, EMAIL FROM USUARIO;
SELECT ID, NOMBRE, APELLIDO FROM EMPLEADOS WHERE APELLIDO = 'Martínez';
SELECT ID, NOMBRE, APELLIDO FROM EMPLEADOS WHERE REPORTA_A = 1;
SELECT COUNT(ID) AS total_empleados FROM EMPLEADOS;
SELECT COUNT(ID) AS total_usuarios FROM USUARIO;
SELECT ID, NOMBRE, EMAIL FROM USUARIO WHERE EMAIL = 'user1@example.com';
SELECT ID, NOMBRE FROM USUARIO WHERE PASSWORD IS NULL OR PASSWORD = '';


-- Credenciales BCrypt
-- Contrasenia: Cafe@1975
-- Hash: $2a$12$jDKB00EBOsdL4ykiVDGapuAdFDcOxBSVlAPM569GJSmacjBw/1rHG
----------------------------
-- Contrasenia: Sol&1989
-- Hash: $2a$12$2hQueZ.0LTlbWGi3hLw74.YdDc5NUT898agsmz3Na7jTUtbwGOZZq
----------------------------
-- Contrasenia: Rio!2001!
-- Hash: $2a$12$k2yQGEE6zrX9pyGeLMWRZ.ORK4B1MtvPC0UlusBcyebgrqDhGm1Fi
----------------------------
-- Contrasenia: Luna_1993
-- Hash: $2a$12$3fZcuKEDslOb/2LCtzlSiukihzQy/lDtC81E327QYz4agmRSnNXqK
----------------------------
-- Contrasenia: , "Luna_1993"
-- Hash: $2a$12$a9QO4Y73NDd7eXbkYCIoau3cBiE9IQFD0upTFRxgT8iV07s/lpY3W
