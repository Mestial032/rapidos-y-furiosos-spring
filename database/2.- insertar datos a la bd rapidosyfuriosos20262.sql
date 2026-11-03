-- SELECCIONAMOS LA BASE DE DATOS
USE bdrapidosyfuriosos20262;

-- DISTRITO
INSERT INTO distrito(nomdis,estdis) VALUES
('Ancón',1),
('Ate',1),
('Barranco',1),
('Breña',1),
('Carabayllo',1),
('Chaclacayo',1),
('Chorrillos',1),
('Cieneguilla',1),
('Comas',1),
('El Agustino',1),
('Independencia',1),
('Jesús María',1),
('La Molina',1),
('La Victoria',1),
('Lima',1),
('Lince',1),
('Los Olivos',1),
('Lurigancho',1),
('Lurín',1),
('Magdalena del Mar',1),
('Miraflores',1),
('Pachacámac',1),
('Pucusana',1),
('Pueblo Libre',1),
('Puente Piedra',1),
('Punta Hermosa',1),
('Punta Negra',1),
('Rímac',1),
('San Bartolo',1),
('San Borja',1),
('San Isidro',1),
('San Juan de Lurigancho',1),
('San Juan de Miraflores',1),
('San Luis',1),
('San Martín de Porres',1),
('San Miguel',1),
('Santa Anita',1),
('Santa María del Mar',1),
('Santa Rosa',1),
('Santiago de Surco',1),
('Surquillo',1),
('Villa El Salvador',1),
('Villa María del Triunfo',1),
('Callao',1),
('Bellavista',1),
('Carmen de La Legua-Reynoso',1),
('La Perla',1),
('La Punta',1),
('Ventanilla',1),
('Mi Perú',1);


-- SEXO
INSERT INTO sexo(nomsex,estsex) VALUES
('Masculino',1),
('Femenino',1),
('No Binario',1),
('Otro',1),
('Prefiere no decir',1);


-- TIPO DE DOCUMENTO
INSERT INTO tipodocumento(nomtipd,esttipd) VALUES
('DNI',1),
('Pasaporte',1),
('Carnet Extranjería',1),
('Licencia Conducir',1),
('Otro',1);


-- ESTADO CIVIL
INSERT INTO estadocivil(nomestc,estestc) VALUES
('Soltero',1),
('Casado',1),
('Divorciado',1),
('Viudo',1),
('Conviviente',1);


-- MARCA
INSERT INTO marca(nommar,estmar) VALUES
('Toyota',1),
('Honda',1),
('Ford',1),
('Chevrolet',1),
('Nissan',1);


-- COLOR
INSERT INTO color(nomcol,estcol) VALUES
('Rojo',1),
('Azul',1),
('Negro',1),
('Blanco',1),
('Gris',1);


-- ROL
INSERT INTO rol(nomrol,estrol) VALUES
('Administrador',1),
('Mecánico',1),
('Recepcionista',1),
('Supervisor',1),
('Contador',1);


-- SERVICIO
INSERT INTO servicio(nomser,desser,preser,estser) VALUES
(
    'Cambio de aceite',
    'Servicio de mantenimiento básico',
    120.00,
    1
),
(
    'Alineación',
    'Alineación de ruedas',
    80.00,
    1
),
(
    'Balanceo',
    'Balanceo de neumáticos',
    70.00,
    1
),
(
    'Lavado completo',
    'Lavado interior y exterior',
    50.00,
    1
),
(
    'Cambio de frenos',
    'Reemplazo de pastillas de freno',
    200.00,
    1
);


-- CLIENTE
INSERT INTO cliente(
    nomcli,
    apepcli,
    apemcli,
    doccli,
    feccli,
    naccli,
    dircli,
    telcli,
    celcli,
    corcli,
    estcli,
    coddis,
    codsex,
    codtipd
)
VALUES
(
    'Juan',
    'Perez',
    'Lopez',
    '12345678',
    '1990-05-10',
    'Peruana',
    'Av. Siempre Viva 123',
    '1234567',
    '987654321',
    'juan@mail.com',
    1,
    1,
    1,
    1
),
(
    'Maria',
    'Gomez',
    'Diaz',
    '87654321',
    '1985-03-15',
    'Peruana',
    'Calle Falsa 456',
    '7654321',
    '912345678',
    'maria@mail.com',
    1,
    2,
    2,
    1
),
(
    'Carlos',
    'Ramirez',
    'Torres',
    '11223344',
    '1992-07-20',
    'Peruana',
    'Jr. Las Flores 789',
    '1112223',
    '999888777',
    'carlos@mail.com',
    1,
    1,
    1,
    2
),
(
    'Lucia',
    'Fernandez',
    'Castro',
    '44332211',
    '1995-11-30',
    'Peruana',
    'Av. Primavera 321',
    '2223334',
    '988776655',
    'lucia@mail.com',
    1,
    2,
    2,
    2
),
(
    'Pedro',
    'Sanchez',
    'Vega',
    '55667788',
    '1988-01-25',
    'Peruana',
    'Calle Central 654',
    '3334445',
    '977665544',
    'pedro@mail.com',
    1,
    1,
    1,
    1
);


-- EMPLEADO
INSERT INTO empleado(
    nomemp,
    apepemp,
    apememp,
    docemp,
    fecemp,
    nacemp,
    diremp,
    telemp,
    celemp,
    coremp,
    usuemp,
    claemp,
    sueemp,
    estemp,
    coddis,
    codrol,
    codsex,
    codtipd,
    codestc
)
VALUES
(
    'Luis',
    'Martinez',
    'Rojas',
    '99887766',
    '1980-02-12',
    'Peruana',
    'Av. Industrial 100',
    '4445556',
    '955443322',
    'luis@mail.com',
    'lmartinez',
    '$2a$10$WWHt98ylkU7S9.xljqeqbOmzc22gMvsYYuyaOROFay38PibvQMwgG',
    1850.00,
    1,
    2,
    1,
    1,
    1,
    1
),
(
    'Ana',
    'Lopez',
    'Garcia',
    '88776655',
    '1990-06-18',
    'Peruana',
    'Calle Comercio 200',
    '5556667',
    '944332211',
    'ana@mail.com',
    'alopez',
    '$2a$10$WWHt98ylkU7S9.xljqeqbOmzc22gMvsYYuyaOROFay38PibvQMwgG',
    2500.00,
    1,
    3,
    2,
    2,
    1,
    2
),
(
    'Jose',
    'Diaz',
    'Mendoza',
    '77665544',
    '1985-09-25',
    'Peruana',
    'Jr. Progreso 300',
    '6667778',
    '933221100',
    'jose@mail.com',
    'jdiaz',
    '$2a$10$WWHt98ylkU7S9.xljqeqbOmzc22gMvsYYuyaOROFay38PibvQMwgG',
    1900.00,
    1,
    1,
    2,
    1,
    1,
    2
),
(
    'Elena',
    'Castillo',
    'Ruiz',
    '66554433',
    '1992-12-05',
    'Peruana',
    'Av. Libertad 400',
    '7778889',
    '922110099',
    'elena@mail.com',
    'ecastillo',
    '$2a$10$WWHt98ylkU7S9.xljqeqbOmzc22gMvsYYuyaOROFay38PibvQMwgG',
    3500.00,
    1,
    4,
    1,
    2,
    2,
    1
),
(
    'Miguel',
    'Torres',
    'Silva',
    '55443322',
    '1988-04-14',
    'Peruana',
    'Calle Norte 500',
    '8889990',
    '911009988',
    'miguel@mail.com',
    'mtorres',
    '$2a$10$WWHt98ylkU7S9.xljqeqbOmzc22gMvsYYuyaOROFay38PibvQMwgG',
    4800.00,
    1,
    5,
    2,
    1,
    2,
    2
);


-- VEHICULO
INSERT INTO vehiculo(
    plaveh,
    modveh,
    anioveh,
    codmar,
    codcol,
    codcli,
    estveh
)
VALUES
('ABC123','Corolla',2015,1,1,1,1),
('XYZ789','Civic',2018,2,2,2,1),
('LMN456','Focus',2017,3,3,3,1),
('JKL321','Aveo',2016,4,4,4,1),
('QWE654','Sentra',2019,5,5,5,1);


-- LOG DEL SISTEMA
INSERT INTO log_sistema(
    codemp,
    accion,
    tabla_afectada,
    fecha
)
VALUES
(
    1,
    'Registro de cliente',
    'cliente',
    '2025-01-08 09:15:00'
),
(
    2,
    'Registro de vehículo',
    'vehiculo',
    '2025-01-09 10:20:00'
),
(
    3,
    'Registro de historial',
    'historialvehiculo',
    '2025-01-10 11:00:00'
),
(
    4,
    'Actualización de servicio',
    'servicio',
    '2025-01-11 14:30:00'
),
(
    5,
    'Consulta de historial',
    'historialvehiculo',
    '2025-01-12 16:45:00'
);


-- HISTORIAL DE VEHICULO
INSERT INTO historialvehiculo(
    codveh,
    fecing,
    obshis,
    codemp,
    esthis
)
VALUES
(
    1,
    '2025-01-10',
    'Cambio de aceite y revisión general',
    1,
    1
),
(
    2,
    '2025-01-12',
    'Alineación y balanceo',
    2,
    1
),
(
    3,
    '2025-01-15',
    'Lavado completo',
    3,
    1
),
(
    4,
    '2025-01-18',
    'Cambio de frenos',
    4,
    1
),
(
    5,
    '2025-01-20',
    'Revisión técnica anual',
    5,
    1
);


-- DETALLE DEL HISTORIAL
INSERT INTO detallehistorial(
    numhis,
    codser,
    predet,
    candet
)
VALUES
(1,1,120.00,1),
(1,4,50.00,1),

(2,2,80.00,1),
(2,3,70.00,1),

(3,4,50.00,1),

(4,5,200.00,1),
(4,2,80.00,1),

(5,1,120.00,1),
(5,2,80.00,1),
(5,3,70.00,1);


-- CONSULTAS
SELECT * FROM distrito;
SELECT * FROM sexo;
SELECT * FROM tipodocumento;
SELECT * FROM estadocivil;
SELECT * FROM marca;
SELECT * FROM color;
SELECT * FROM rol;
SELECT * FROM servicio;
SELECT * FROM cliente;
SELECT * FROM empleado;
SELECT * FROM vehiculo;
SELECT * FROM log_sistema;
SELECT * FROM historialvehiculo;
SELECT * FROM detallehistorial;