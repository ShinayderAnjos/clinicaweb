package persistencia;

import model.Consulta;
import model.Medico;
import model.Paciente;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public void salvar(Consulta c)
            throws Exception {

        Connection con =
                Conexao.conectar();

        String sql =
                "INSERT INTO consulta " +
                "(data, descricao, realizada, valor, paciente_id, medico_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setDate(
                1,
                new java.sql.Date(
                        c.getData().getTime()
                )
        );

        ps.setString(2, c.getDescricao());
        ps.setBoolean(3, c.getRealizada());
        ps.setDouble(4, c.getValor());

        ps.setLong(
                5,
                c.getPaciente().getId()
        );

        ps.setLong(
                6,
                c.getMedico().getId()
        );

        ps.execute();

        ps.close();
        con.close();
    }

    public List<Consulta> listar()
            throws Exception {

        Connection con =
                Conexao.conectar();

        String sql =
                "SELECT c.*, " +
                "p.nome AS paciente_nome, " +
                "m.nome AS medico_nome " +
                "FROM consulta c " +
                "INNER JOIN paciente p " +
                "ON c.paciente_id = p.id " +
                "INNER JOIN medico m " +
                "ON c.medico_id = m.id";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        List<Consulta> lista =
                new ArrayList<>();

        while (rs.next()) {

            Consulta c =
                    new Consulta();

            Paciente p =
                    new Paciente();

            Medico m =
                    new Medico();

            c.setId(rs.getLong("id"));
            c.setData(rs.getDate("data"));
            c.setDescricao(
                    rs.getString("descricao")
            );

            c.setRealizada(
                    rs.getBoolean("realizada")
            );

            c.setValor(
                    rs.getDouble("valor")
            );

            p.setId(rs.getLong("paciente_id"));

            p.setNome(
                    rs.getString("paciente_nome")
            );

            m.setId(rs.getLong("medico_id"));

            m.setNome(
                    rs.getString("medico_nome")
            );

            c.setPaciente(p);
            c.setMedico(m);

            lista.add(c);
        }

        rs.close();
        ps.close();
        con.close();

        return lista;
    }

    public Consulta buscarPorId(Long id)
            throws Exception {

        Connection con =
                Conexao.conectar();

        String sql =
                "SELECT * FROM consulta " +
                "WHERE id = ?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setLong(1, id);

        ResultSet rs =
                ps.executeQuery();

        Consulta c = null;

        if (rs.next()) {

            c = new Consulta();

            Paciente p =
                    new Paciente();

            Medico m =
                    new Medico();

            c.setId(rs.getLong("id"));
            c.setData(rs.getDate("data"));

            c.setDescricao(
                    rs.getString("descricao")
            );

            c.setRealizada(
                    rs.getBoolean("realizada")
            );

            c.setValor(
                    rs.getDouble("valor")
            );

            p.setId(rs.getLong("paciente_id"));
            m.setId(rs.getLong("medico_id"));

            c.setPaciente(p);
            c.setMedico(m);
        }

        rs.close();
        ps.close();
        con.close();

        return c;
    }

    public void atualizar(Consulta c)
            throws Exception {

        Connection con =
                Conexao.conectar();

        String sql =
                "UPDATE consulta SET " +
                "data = ?, " +
                "descricao = ?, " +
                "realizada = ?, " +
                "valor = ?, " +
                "paciente_id = ?, " +
                "medico_id = ? " +
                "WHERE id = ?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setDate(
                1,
                new java.sql.Date(
                        c.getData().getTime()
                )
        );

        ps.setString(2, c.getDescricao());
        ps.setBoolean(3, c.getRealizada());
        ps.setDouble(4, c.getValor());

        ps.setLong(
                5,
                c.getPaciente().getId()
        );

        ps.setLong(
                6,
                c.getMedico().getId()
        );

        ps.setLong(7, c.getId());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    public void excluir(Long id)
            throws Exception {

        Connection con =
                Conexao.conectar();

        String sql =
                "DELETE FROM consulta " +
                "WHERE id = ?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setLong(1, id);

        ps.executeUpdate();

        ps.close();
        con.close();
    }
}