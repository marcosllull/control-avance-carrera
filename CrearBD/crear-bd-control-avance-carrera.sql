DROP DATABASE IF EXISTS control_avance_carrera;

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
INSERT INTO asignatura VALUES("Bases de datos I", "Tecnologo en informatica", "Bases de Datos y Sistemas de Información", 12, true);
INSERT INTO asignatura VALUES("Estructuras de datos y algoritmos", "Tecnologo en informatica", "Programacion", 16, true);
INSERT INTO asignatura VALUES("Inglés Técnico II", "Tecnologo en informatica", "Ciencias Humanas y Sociales", 4, false);
INSERT INTO asignatura VALUES("Matematicas discretas y logica II", "Tecnologo en informatica", "Matematica", 6, true);
INSERT INTO asignatura VALUES("Sistemas Operativos", "Tecnologo en informatica", "Arquitectura Sistemas Operativos y Redes de Computadoras", 12, true);
INSERT INTO asignatura VALUES("Bases de datos II", "Tecnologo en informatica", "Bases de Datos y Sistemas de Información", 12, true);
INSERT INTO asignatura VALUES("Comunicación Oral y Escrita", "Tecnologo en informatica", "Ciencias Humanas y Sociales", 4, false);
INSERT INTO asignatura VALUES("Contabilidad", "Tecnologo en informatica", "Ciencias Humanas y Sociales", 8, false);
INSERT INTO asignatura VALUES("Redes de Computadoras", "Tecnologo en informatica", "Arquitectura Sistemas Operativos y Redes de Computadoras", 12, true);
INSERT INTO asignatura VALUES("Programación Avanzada", "Tecnologo en informatica", "Programacion", 12, true); /*ME LA INVENTE. NO HAY DATOS*/
INSERT INTO asignatura VALUES("Administración de Infraestructuras", "Tecnologo en informatica", "Arquitectura Sistemas Operativos y Redes de Computadoras", 8, true);
INSERT INTO asignatura VALUES("Ingeniería de Software", "Tecnologo en informatica", "Desarrollo de Software", 12, true);
INSERT INTO asignatura VALUES("Probabilidad y Estadística", "Tecnologo en informatica", "Matematica", 8, true);
INSERT INTO asignatura VALUES("Programación de Aplicaciones", "Tecnologo en informatica", "Programacion", 12, true); /*ME LA INVENTE. NO HAY DATOS*/
INSERT INTO asignatura VALUES("Relaciones Personales y Laborales", "Tecnologo en informatica", "Ciencias Humanas y Sociales", 4, false);
INSERT INTO asignatura VALUES("Taller de Aplicaciones de Internet Ricas", "Tecnologo en informatica", "Programacion", 4, true);
INSERT INTO asignatura VALUES("Taller de Sistemas de Información .NET", "Tecnologo en informatica", "Programacion", 12, true);
INSERT INTO asignatura VALUES("Taller de Sistemas de Información Geográfica", "Tecnologo en informatica", "Programacion", 12, true);
INSERT INTO asignatura VALUES("Calidad de Datos", "Tecnologo en informatica", "Bases de Datos y Sistemas de Información", 12, true);
INSERT INTO asignatura VALUES("Pasantía Laboral", "Tecnologo en informatica", "Proyecto y Pasantía", 10, true);
/*Butiá: Robótica Educativa     NO HAY DATOS       */
INSERT INTO asignatura VALUES("Proyecto", "Tecnologo en informatica", "Proyecto y Pasantía", 20, true); /*ME LA INVENTE. NO HAY DATOS*/


/*****************PREVIAS DE UNA ASIGNATURA******************/
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Estructuras de datos y algoritmos", "Principios de programacion");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Estructuras de datos y algoritmos", "Matematicas discretas y logica I");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Bases de datos I", "Principios de programacion");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Matematicas discretas y logica II", "Matematicas discretas y logica I");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Sistemas Operativos", "Arquitectura del computador");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Bases de datos II", "Bases de datos I");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Redes de Computadoras", "Arquitectura del computador");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Programación Avanzada", "Bases de datos I");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Programación Avanzada", "Estructuras de datos y algoritmos");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Administración de Infraestructuras", "Sistemas Operativos");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Administración de Infraestructuras", "Redes de Computadoras");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Ingeniería de Software", "Estructuras de datos y algoritmos");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Ingeniería de Software", "Bases de datos I");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Ingeniería de Software", "Programación Avanzada");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Ingeniería de Software", "Bases de datos II");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Probabilidad y Estadística", "Matematicas discretas y logica I");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Probabilidad y Estadística", "Matematicas discretas y logica II");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Programación de Aplicaciones", "Programación Avanzada");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Programación de Aplicaciones", "Bases de datos II");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Taller de Aplicaciones de Internet Ricas", "Programación de Aplicaciones");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Taller de Sistemas de Información .NET", "Programación de Aplicaciones");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Taller de Sistemas de Información .NET", "Bases de datos I");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Taller de Sistemas de Información .NET", "Bases de datos II");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Taller de Sistemas de Información Geográfica", "Bases de datos II");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Taller de Sistemas de Información Geográfica", "Programación de Aplicaciones");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Taller de Sistemas de Información Geográfica", "Ingeniería de Software");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Calidad de Datos", "Bases de datos II");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Calidad de Datos", "Programación de Aplicaciones");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Pasantía Laboral", "Principios de programacion");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Pasantía Laboral", "Estructuras de datos y algoritmos");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Pasantía Laboral", "Programación Avanzada");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Pasantía Laboral", "Arquitectura del computador");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Pasantía Laboral", "Sistemas Operativos");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Pasantía Laboral", "Redes de Computadoras");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Pasantía Laboral", "Bases de datos I");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Pasantía Laboral", "Bases de datos II");
INSERT INTO asignatura_previa VALUES("Tecnologo en informatica", "Proyecto", "Programación Avanzada");