package com.registro.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySQL {

    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sistema_profesores",
                    "root", // tu usuario
                    "Js1006793030*" // tu contraseña real
            );
            System.out.println("✅ Conexión exitosa a la base de datos.");
        } catch (Exception e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }
        return conexion;
    }
}
