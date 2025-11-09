CREATE TABLE estudiantes(
                            id BIGSERIAL PRIMARY KEY,
                            nombre VARCHAR(100) NOT NULL,
                            apellido VARCHAR(100) NOT NULL,
                            correo VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE materias(
                         id BIGSERIAL PRIMARY KEY,
                         nombre VARCHAR(100) UNIQUE NOT NULL,
                         creditos INT NOT NULL CHECK ( creditos > 0)
);

CREATE TABLE notas(
                      id BIGSERIAL PRIMARY KEY,
                      materia_id BIGINT NOT NULL,
                      estudiante_id BIGINT NOT NULL,
                      valor NUMERIC(3,2) NOT NULL CHECK ( valor >= 0.0 AND valor <= 5.0 ),
                      porcentaje NUMERIC(5,2) NOT NULL CHECK ( porcentaje >= 0.0 AND porcentaje <= 100.0 ),
                      observacion TEXT,
                      CONSTRAINT fk_nota_materia FOREIGN KEY (materia_id) REFERENCES materias(id) ON DELETE CASCADE,
                      CONSTRAINT fk_nota_estudiante FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id) ON DELETE CASCADE
);

--------

-- Datos Mock
insert into estudiantes (nombre, apellido, correo) values ('Danny', 'Charlotte', 'dcharlotte0@seesaa.net');
insert into estudiantes (nombre, apellido, correo) values ('Estell', 'Hearl', 'ehearl1@thetimes.co.uk');
insert into estudiantes (nombre, apellido, correo) values ('Delmore', 'Fearey', 'dfearey2@spiegel.de');
insert into estudiantes (nombre, apellido, correo) values ('Lavinie', 'Pedron', 'lpedron3@acquirethisname.com');
insert into estudiantes (nombre, apellido, correo) values ('Noel', 'Coronado', 'ncoronado4@columbia.edu');
insert into estudiantes (nombre, apellido, correo) values ('Melva', 'Rodenborch', 'mrodenborch5@rediff.com');
insert into estudiantes (nombre, apellido, correo) values ('Rollo', 'Gilding', 'rgilding6@facebook.com');
insert into estudiantes (nombre, apellido, correo) values ('Hodge', 'Wilshire', 'hwilshire7@squidoo.com');
insert into estudiantes (nombre, apellido, correo) values ('Norrie', 'Olsen', 'nolsen8@irs.gov');
insert into estudiantes (nombre, apellido, correo) values ('Denni', 'Jersh', 'djersh9@cornell.edu');


insert into materias (nombre, creditos) values ('Ingeniería de Software', 2);
insert into materias (nombre, creditos) values ('Estructuras de Datos', 3);
insert into materias (nombre, creditos) values ('Comunicaciones y Redes', 4);
insert into materias (nombre, creditos) values ('Sistemas Operativos', 3);
insert into materias (nombre, creditos) values ('Bases de Datos', 4);
insert into materias (nombre, creditos) values ('Significación Teológica', 2);

insert into notas (materia_id, estudiante_id, valor, porcentaje, observacion) values (1, 1, 4.5, 50.0, 'Buen desempeño en la materia');
insert into notas (materia_id, estudiante_id, valor, porcentaje, observacion) values (2, 1, 3.8, 50.0, 'Necesita mejorar en estructuras de datos');
insert into notas (materia_id, estudiante_id, valor, porcentaje, observacion) values (3, 2, 4.2, 40.0, 'Excelente comprensión de redes');
insert into notas (materia_id, estudiante_id, valor, porcentaje, observacion) values (4, 3, 2.9, 60.0, 'Dificultades con sistemas operativos');
insert into notas (materia_id, estudiante_id, valor, porcentaje, observacion) values (5, 4, 4.7, 70.0, 'Destacado en bases de datos');
insert into notas (materia_id, estudiante_id, valor, porcentaje, observacion) values (6, 5, 3.5, 30.0, 'Buen inicio en teología');
insert into notas (materia_id, estudiante_id, valor, porcentaje, observacion) values (1, 5, 4.0, 70.0, 'Consistente en ingeniería de software');