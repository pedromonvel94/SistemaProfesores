package com.registro.vista;

import com.registro.conexion.ConexionMySQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class VentanaProfesores extends JFrame {

    public VentanaProfesores() {
        setTitle("Listado de Profesores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null); // Centrar ventana

        // Crear columnas
        String[] columnas = {
                "ID", "Nombre", "Apellido", "Nacimiento", "Género",
                "Estatura", "Peso", "Materia"
        };

        // Modelo de tabla
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // Obtener datos de la BD
        try {
            Connection conn = ConexionMySQL.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM profesores");

            while (rs.next()) {
                Object[] fila = {
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("genero"),
                        rs.getDouble("estatura"),
                        rs.getDouble("peso"),
                        rs.getString("materia")
                };
                modelo.addRow(fila);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar profesores: " + e.getMessage());
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaProfesores();
    }
}
