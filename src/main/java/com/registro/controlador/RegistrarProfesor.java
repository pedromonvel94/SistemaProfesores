package com.registro.controlador;

import com.registro.modelo.Profesor;

public class RegistrarProfesor {
    public static void main(String[] args) {

        // Crear un profesor de ejemplo
        Profesor profe = new Profesor(
                "Luis",
                "Martínez",
                "1990-08-15",
                "Masculino",
                1.75,
                70.5,
                "Matemáticas"
        );

        // Guardarlo en la base de datos
        GestorProfesores.guardar(profe);
    }
}
