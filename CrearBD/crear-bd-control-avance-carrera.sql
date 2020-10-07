DROP DATABASE control_avance_carrera;

CREATE DATABASE control_avance_carrera;
use control_avance_carrera;
/**********TABLAS**********/

CREATE TABLE carrera (
	nombre VARCHAR(80) PRIMARY KEY,
    cantCreditosMin INTEGER,
    cantCreditosMax INTEGER
);

CREATE TABLE materia (
	PRIMARY KEY (nombre, nombreCarrera),
    FOREIGN KEY (nombreCarrera) REFERENCES carrera (nombre) ON UPDATE CASCADE,
	nombre VARCHAR(80),
    nombreCarrera VARCHAR(80),
    cantCreditos INTEGER
);

CREATE TABLE asignatura (
	PRIMARY KEY (nombre, nombreCarrera),
    FOREIGN KEY (nombreMateria, nombreCarrera) REFERENCES materia (nombre, nombreCarrera)  ON UPDATE CASCADE,
	nombre VARCHAR(80),
    nombreCarrera VARCHAR(80),
    nombreMateria VARCHAR(80),
    cantCreditos INTEGER,
    tienePrevias BOOLEAN
);

CREATE TABLE asignatura_previa (
	PRIMARY KEY (nombreCarrera, nombreAsignatura, nombrePrevia),
    FOREIGN KEY (nombreCarrera) REFERENCES carrera (nombre)  ON UPDATE CASCADE,
    FOREIGN KEY (nombreAsignatura) REFERENCES asignatura (nombre)  ON UPDATE CASCADE,
    FOREIGN KEY (nombrePrevia) REFERENCES asignatura (nombre)  ON UPDATE CASCADE,
    nombreCarrera VARCHAR(80),
	nombreAsignatura VARCHAR(80),
    nombrePrevia VARCHAR(80)
);

/********************* DATOS DE PRUEBA ******************/

/**************************CARRERAS**************************/
INSERT INTO carrera VALUES ("Tecnologo en informatica", 150, 252);
INSERT INTO carrera VALUES ("Ingenieria en sistemas", 210, 350);
INSERT INTO carrera VALUES ("Tecnicatura en redes", 230, 360);
/**************************MATERIAS**************************/
/******TECNOLOGO EN INFORMATICA******/
INSERT INTO materia VALUES ("Matematica", "Tecnologo en informatica", 26);
INSERT INTO materia VALUES ("Programacion", "Tecnologo en informatica", 44);
INSERT INTO materia VALUES ("Arquitectura Sistemas Operativos y Redes de Computadoras", "Tecnologo en informatica", 32);
INSERT INTO materia VALUES ("Bases de Datos y Sistemas de Información", "Tecnologo en informatica", 24);
INSERT INTO materia VALUES ("Desarrollo de Software", "Tecnologo en informatica", 12);
INSERT INTO materia VALUES ("Ciencias Humanas y Sociales", "Tecnologo en informatica", 28);
INSERT INTO materia VALUES ("Proyecto y Pasantía", "Tecnologo en informatica", 30);
/******INGENIERIA EN SISTEMAS******/
INSERT INTO materia VALUES ("Programacion", "Ingenieria en sistemas", 100);
INSERT INTO materia VALUES ("Ciencias humanas", "Ingenieria en sistemas", 100);
INSERT INTO materia VALUES ("Bases de datos", "Ingenieria en sistemas", 110);
/*************************ASIGNATURAS************************/
INSERT INTO asignatura VALUES("Arquitectura del computador", "Tecnologo en informatica", "Arquitectura Sistemas Operativos y Redes de Computadoras", 0, false);
INSERT INTO asignatura VALUES("Inglés Técnico I", "Tecnologo en informatica", "Ciencias Humanas y Sociales", 8, false);
INSERT INTO asignatura VALUES("Matemática", "Tecnologo en informatica", "Matematica", 0, false);
INSERT INTO asignatura VALUES("Matematicas discretas y logica I", "Tecnologo en informatica", "Matematica", 12, false);
INSERT INTO asignatura VALUES("Principios de programacion", "Tecnologo en informatica", "Programacion", 0, false);
INSERT INTO asignatura VALUES("Bases de datos I", "Tecnologo en informatica", "Programacion", 12, true);
INSERT INTO asignatura VALUES("Estructuras de datos y algoritmos", "Tecnologo en informatica", "Programacion", 16, true);
INSERT INTO asignatura VALUES("Inglés Técnico II", "Tecnologo en informatica", "Ciencias Humanas y Sociales", 4, false);
INSERT INTO asignatura VALUES("Matematicas discretas y logica II", "Tecnologo en informatica", "Matematica", 6, true);
INSERT INTO asignatura VALUES("Sistemas Operativos", "Tecnologo en informatica", "Arquitectura Sistemas Operativos y Redes de Computadoras", 12, true);


/*****************PREVIAS DE UNA ASIGNATURA******************/
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Estructuras de datos y algoritmos", "Principios de programacion");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Estructuras de datos y algoritmos", "Matematicas discretas y logica I");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Bases de datos I", "Principios de programacion");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Matematicas discretas y logica II", "Matematicas discretas y logica I");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Sistemas Operativos", "Arquitectura del computador");