DROP DATABASE control_avance_carrera;

CREATE DATABASE control_avance_carrera;
use control_avance_carrera;
/**********TABLAS**********/

CREATE TABLE carrera (
	nombre VARCHAR(40) PRIMARY KEY,
    cantCreditosMin INTEGER,
    cantCreditosMax INTEGER
);

CREATE TABLE materia (
	PRIMARY KEY (nombre, nombreCarrera),
    FOREIGN KEY (nombreCarrera) REFERENCES carrera (nombre) ON UPDATE CASCADE,
	nombre VARCHAR(40),
    nombreCarrera VARCHAR(40),
    cantCreditos INTEGER
);

CREATE TABLE asignatura (
	PRIMARY KEY (nombre, nombreCarrera),
    FOREIGN KEY (nombreMateria, nombreCarrera) REFERENCES materia (nombre, nombreCarrera)  ON UPDATE CASCADE,
	nombre VARCHAR(40),
    nombreCarrera VARCHAR(40),
    nombreMateria VARCHAR(40),
    cantCreditos INTEGER,
    tienePrevias BOOLEAN
);

CREATE TABLE asignatura_previa (
	PRIMARY KEY (nombreCarrera, nombreAsignatura, nombrePrevia),
    FOREIGN KEY (nombreCarrera) REFERENCES carrera (nombre)  ON UPDATE CASCADE,
    FOREIGN KEY (nombreAsignatura) REFERENCES asignatura (nombre)  ON UPDATE CASCADE,
    FOREIGN KEY (nombrePrevia) REFERENCES asignatura (nombre)  ON UPDATE CASCADE,
    nombreCarrera VARCHAR(40),
	nombreAsignatura VARCHAR(40),
    nombrePrevia VARCHAR(40)
);

/********************* DATOS DE PRUEBA ******************/

/**************************CARRERAS**************************/
INSERT INTO carrera VALUES ("Tecnologo en informatica", 150, 250);
INSERT INTO carrera VALUES ("Ingenieria en sistemas", 210, 350);
INSERT INTO carrera VALUES ("Tecnicatura en redes", 230, 360);
/**************************MATERIAS**************************/
INSERT INTO materia VALUES ("Programacion", "Tecnologo en informatica", 60);
INSERT INTO materia VALUES ("Matematicas", "Tecnologo en informatica", 30);
INSERT INTO materia VALUES ("Programacion", "Ingenieria en sistemas", 100);
INSERT INTO materia VALUES ("Ciencias humanas", "Ingenieria en sistemas", 100);
INSERT INTO materia VALUES ("Bases de datos", "Ingenieria en sistemas", 110);
/*************************ASIGNATURAS************************/
INSERT INTO asignatura VALUES("Principios de programacion", "Tecnologo en informatica", "Programacion", 0, false);
INSERT INTO asignatura VALUES("Estructuras de datos y algoritmos", "Tecnologo en informatica", "Programacion", 6, true);
INSERT INTO asignatura VALUES("Bases de datos I", "Tecnologo en informatica", "Programacion", 12, true);
INSERT INTO asignatura VALUES("Matematicas discretas y logica I", "Tecnologo en informatica", "Matematicas", 12, false);
/*****************PREVIAS DE UNA ASIGNATURA******************/
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Estructuras de datos y algoritmos", "Principios de programacion");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Bases de datos I", "Principios de programacion");