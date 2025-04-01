package com.registro.vista;

import com.registro.controlador.GestorProfesores;
import com.registro.modelo.Profesor;
import com.registro.conexion.ConexionMySQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PanelProfesores extends JFrame {

    private DefaultTableModel modeloTabla;
    private JTable tablaProfesores;

    // Campos del formulario
    private JTextField txtNombre, txtApellido, txtNacimiento, txtGenero, txtEstatura, txtPeso, txtMateria;

    public PanelProfesores() {
        setTitle("Gestión de Profesores");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior (formulario)
        JPanel panelFormulario = new JPanel(new GridLayout(8, 2, 10, 5));
        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtNacimiento = new JTextField();
        txtGenero = new JTextField();
        txtEstatura = new JTextField();
        txtPeso = new JTextField();
        txtMateria = new JTextField();
        JButton btnGuardar = new JButton("Guardar Profesor");

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Apellido:"));
        panelFormulario.add(txtApellido);
        panelFormulario.add(new JLabel("Fecha de Nacimiento (YYYY-MM-DD):"));
        panelFormulario.add(txtNacimiento);
        panelFormulario.add(new JLabel("Género:"));
        panelFormulario.add(txtGenero);
        panelFormulario.add(new JLabel("Estatura (ej: 1.75):"));
        panelFormulario.add(txtEstatura);
        panelFormulario.add(new JLabel("Peso (ej: 70.5):"));
        panelFormulario.add(txtPeso);
        panelFormulario.add(new JLabel("Materia:"));
        panelFormulario.add(txtMateria);
        panelFormulario.add(new JLabel());
        panelFormulario.add(btnGuardar);

        add(panelFormulario, BorderLayout.NORTH);

        // Panel inferior (tabla)
        String[] columnas = {"ID", "Nombre", "Apellido", "Nacimiento", "Género", "Estatura", "Peso", "Materia"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProfesores = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaProfesores);
        add(scroll, BorderLayout.CENTER);

        // Cargar datos al iniciar
        cargarProfesores();

        // Acción botón guardar
        btnGuardar.addActionListener(e -> {
            try {
                Profesor profe = new Profesor(
                        txtNombre.getText(),
                        txtApellido.getText(),
                        txtNacimiento.getText(),
                        txtGenero.getText(),
                        Double.parseDouble(txtEstatura.getText()),
                        Double.parseDouble(txtPeso.getText()),
                        txtMateria.getText()
                );
                GestorProfesores.guardar(profe);
                limpiarFormulario();
                cargarProfesores();
                JOptionPane.showMessageDialog(this, "Profesor guardado y tabla actualizada.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }

    private void limpiarFormulario() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtNacimiento.setText("");
        txtGenero.setText("");
        txtEstatura.setText("");
        txtPeso.setText("");
        txtMateria.setText("");
    }

    private void cargarProfesores() {
        modeloTabla.setRowCount(0); // limpiar tabla
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
                modeloTabla.addRow(fila);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar profesores: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new PanelProfesores();
    }
}

