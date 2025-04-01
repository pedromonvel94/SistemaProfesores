package com.registro.modelo;

public class Profesor extends Persona {
    private String materia;
    private static Profesor[] profesores = new Profesor[50];
    private static int contador = 0;

    public Profesor(String nombre, String apellido, String fechaNacimiento, String genero,
                    double estatura, double peso, String materia) {
        super(nombre, apellido, fechaNacimiento, genero, estatura, peso);
        this.materia = materia;
        if (contador < profesores.length) {
            profesores[contador++] = this;
        }
    }

    public String getMateria() {
        return materia;
    }

    public static Profesor[] getProfesores() {
        return profesores;
    }
}
