CREATE TABLE Pacientes (
    uuid varchar2 (50),
    nombre VARCHAR2(50),
    apellido VARCHAR2(50),
    edad int,
    enfermedad VARCHAR2(100),
    habitacion_numero int,
    cama_numero int,
    medicamento varchar(100),
    horaAplicacion VARCHAR2(50)   
);
INSERT INTO Pacientes (nombre, apellido, edad, enfermedad, habitacion_numero, cama_numero, medicamento, horaAplicacion)
VALUES ( 'Juan', 'Perez', 45, 'Diabetes', 101, 1, 'Acetaminofen', '8:00pm');

select * from Pacientes