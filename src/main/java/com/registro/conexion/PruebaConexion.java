package com.registro.conexion;

import java.sql.Connection;

public class PruebaConexion {
    public static void main(String[] args) {
        Connection conn = ConexionMySQL.conectar();
        if (conn != null) {
            System.out.println("Conexión comprobada. Puedes continuar con el proyecto.");
        } else {
            System.out.println("Conexión fallida. Verifica tus credenciales o el servidor.");
        }
    }
}
