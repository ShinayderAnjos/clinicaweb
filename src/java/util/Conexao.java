package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/clinica";

    private static final String USUARIO = "postgres";
    private static final String SENHA = "";

    public static Connection conectar() throws Exception {

        Class.forName("org.postgresql.Driver");

        return DriverManager.getConnection(
                URL,
                USUARIO,
                SENHA
        );
    }
}
