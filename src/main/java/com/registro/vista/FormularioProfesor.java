package com.registro.vista;

import com.registro.controlador.GestorProfesores;
import com.registro.modelo.Profesor;

import javax.swing.*;
import java.awt.*;

public class FormularioProfesor extends JFrame {

    public FormularioProfesor() {
        setTitle("Registrar Profesor");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2, 10, 10));

        // Crear campos
        JTextField txtNombre = new JTextField();
        JTextField txtApellido = new JTextField();
        JTextField txtNacimiento = new JTextField();
        JTextField txtGenero = new JTextField();
        JTextField txtEstatura = new JTextField();
        JTextField txtPeso = new JTextField();
        JTextField txtMateria = new JTextField();

        JButton btnGuardar = new JButton("Guardar");

        // Agregar etiquetas y campos al formulario
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Apellido:"));
        add(txtApellido);
        add(new JLabel("Fecha Nacimiento (YYYY-MM-DD):"));
        add(txtNacimiento);
        add(new JLabel("Género:"));
        add(txtGenero);
        add(new JLabel("Estatura (ej: 1.75):"));
        add(txtEstatura);
        add(new JLabel("Peso (ej: 70.5):"));
        add(txtPeso);
        add(new JLabel("Materia:"));
        add(txtMateria);

        add(new JLabel()); // espacio vacío
        add(btnGuardar);

        // Acción del botón
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
                JOptionPane.showMessageDialog(this, "Profesor guardado correctamente");

                // Limpiar campos
                txtNombre.setText("");
                txtApellido.setText("");
                txtNacimiento.setText("");
                txtGenero.setText("");
                txtEstatura.setText("");
                txtPeso.setText("");
                txtMateria.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new FormularioProfesor();
    }
}
