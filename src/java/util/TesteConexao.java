package util;

import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        try {

            Connection con =
                    Conexao.conectar();

            if (con != null) {

                System.out.println(
                        "Conectado com sucesso!"
                );

                con.close();
            }

        } catch (Exception e) {

            System.out.println(
                    "Erro ao conectar!"
            );

            e.printStackTrace();
        }
    }
}