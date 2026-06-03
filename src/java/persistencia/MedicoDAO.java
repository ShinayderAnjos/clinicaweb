package persistencia;

import model.Medico;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    public void salvar(Medico m)
            throws Exception {

        Connection con =
                Conexao.conectar();

        String sql =
                "INSERT INTO medico " +
                "(nome, especialidade, crm, ativo) " +
                "VALUES (?, ?, ?, ?)";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(1, m.getNome());
        ps.setString(2, m.getEspecialidade());
        ps.setString(3, m.getCrm());
        ps.setBoolean(4, m.getAtivo());

        ps.execute();

        ps.close();
        con.close();
    }

    public List<Medico> listar()
            throws Exception {

        Connection con =
                Conexao.conectar();

        String sql =
                "SELECT * FROM medico";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        List<Medico> lista =
                new ArrayList<>();

        while (rs.next()) {

            Medico m =
                    new Medico();

            m.setId(rs.getLong("id"));
            m.setNome(rs.getString("nome"));
            m.setEspecialidade(
                    rs.getString("especialidade")
            );
            m.setCrm(rs.getString("crm"));
            m.setAtivo(rs.getBoolean("ativo"));

            lista.add(m);
        }

        rs.close();
        ps.close();
        con.close();

        return lista;
    }

    public Medico buscarPorId(Long id)
            throws Exception {

        Connection con =
                Conexao.conectar();

        String sql =
                "SELECT * FROM medico " +
                "WHERE id = ?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setLong(1, id);

        ResultSet rs =
                ps.executeQuery();

        Medico m = null;

        if (rs.next()) {

            m = new Medico();

            m.setId(rs.getLong("id"));
            m.setNome(rs.getString("nome"));

            m.setEspecialidade(
                    rs.getString("especialidade")
            );

            m.setCrm(rs.getString("crm"));
            m.setAtivo(rs.getBoolean("ativo"));
        }

        rs.close();
        ps.close();
        con.close();

        return m;
    }

    public void atualizar(Medico m)
            throws Exception {

        Connection con =
                Conexao.conectar();

        String sql =
                "UPDATE medico SET " +
                "nome = ?, " +
                "especialidade = ?, " +
                "crm = ?, " +
                "ativo = ? " +
                "WHERE id = ?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setString(1, m.getNome());

        ps.setString(
                2,
                m.getEspecialidade()
        );

        ps.setString(3, m.getCrm());
        ps.setBoolean(4, m.getAtivo());

        ps.setLong(5, m.getId());

        ps.executeUpdate();

        ps.close();
        con.close();
    }

    public void excluir(Long id)
            throws Exception {

        Connection con =
                Conexao.conectar();

        String sql =
                "DELETE FROM medico " +
                "WHERE id = ?";

        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setLong(1, id);

        ps.executeUpdate();

        ps.close();
        con.close();
    }
}