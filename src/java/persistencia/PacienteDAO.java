package persistencia;

import model.Paciente;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public void salvar(Paciente p)
            throws Exception {

        Connection con = Conexao.conectar();

        String sql =
                "INSERT INTO paciente " +
                "(nome, cpf, ativo, data_nascimento) " +
                "VALUES (?, ?, ?, ?)";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(1, p.getNome());
        ps.setString(2, p.getCpf());
        ps.setBoolean(3, p.getAtivo());

        ps.setDate(
                4,
                new java.sql.Date(
                        p.getDataNascimento().getTime()
                )
        );

        ps.execute();

        ps.close();
        con.close();
    }

    public List<Paciente> listar()
            throws Exception {

        Connection con = Conexao.conectar();

        String sql =
                "SELECT * FROM paciente";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        List<Paciente> lista =
                new ArrayList<>();

        while (rs.next()) {

            Paciente p =
                    new Paciente();

            p.setId(rs.getLong("id"));
            p.setNome(rs.getString("nome"));
            p.setCpf(rs.getString("cpf"));
            p.setAtivo(rs.getBoolean("ativo"));

            p.setDataNascimento(
                    rs.getDate("data_nascimento")
            );

            lista.add(p);
        }

        rs.close();
        ps.close();
        con.close();

        return lista;
    }

    public Paciente buscarPorId(Long id)
        throws Exception {

        Connection con =
                Conexao.conectar();

        String sql =
                "SELECT * FROM paciente " +
                "WHERE id = ?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setLong(1, id);

        ResultSet rs =
                ps.executeQuery();

        Paciente p = null;

        if (rs.next()) {

            p = new Paciente();

            p.setId(rs.getLong("id"));
            p.setNome(rs.getString("nome"));
            p.setCpf(rs.getString("cpf"));

            p.setAtivo(
                    rs.getBoolean("ativo")
            );

            p.setDataNascimento(
                    rs.getDate(
                            "data_nascimento"
                    )
            );
        }

        rs.close();
        ps.close();
        con.close();

        return p;
    }
    
    public void atualizar(Paciente p)
            throws Exception {

        Connection con = Conexao.conectar();

        String sql =
                "UPDATE paciente SET " +
                "nome = ?, " +
                "cpf = ?, " +
                "ativo = ?, " +
                "data_nascimento = ? " +
                "WHERE id = ?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(1, p.getNome());
        ps.setString(2, p.getCpf());
        ps.setBoolean(3, p.getAtivo());

        ps.setDate(
                4,
                new java.sql.Date(
                        p.getDataNascimento().getTime()
                )
        );

        ps.setLong(5, p.getId());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    public void excluir(Long id)
            throws Exception {

        Connection con = Conexao.conectar();

        String sql =
                "DELETE FROM paciente " +
                "WHERE id = ?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setLong(1, id);

        ps.executeUpdate();

        ps.close();
        con.close();
    }
}