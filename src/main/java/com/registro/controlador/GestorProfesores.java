package com.registro.controlador;

import com.registro.modelo.Profesor;
import com.registro.conexion.ConexionMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class GestorProfesores {

    public static void guardar(Profesor profesor) {
        try {
            Connection conn = ConexionMySQL.conectar();
            String sql = "INSERT INTO profesores (nombre, apellido, fecha_nacimiento, genero, estatura, peso, materia) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, profesor.getNombre());
            ps.setString(2, profesor.getApellido());
            ps.setString(3, profesor.getFechaNacimiento());
            ps.setString(4, profesor.getGenero());
            ps.setDouble(5, profesor.getEstatura());
            ps.setDouble(6, profesor.getPeso());
            ps.setString(7, profesor.getMateria());
            ps.executeUpdate();
            System.out.println("✅ Profesor guardado correctamente.");
        } catch (Exception e) {
            System.out.println("❌ Error al guardar el profesor: " + e.getMessage());
        }
    }
}
