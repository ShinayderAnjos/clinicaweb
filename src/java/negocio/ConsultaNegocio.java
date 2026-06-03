package negocio;

import model.Consulta;
import persistencia.ConsultaDAO;

import java.util.List;
import model.Paciente;

public class ConsultaNegocio {

    private ConsultaDAO dao =
            new ConsultaDAO();

    public void salvar(Consulta c)
            throws Exception {

        validarConsulta(c);

        dao.salvar(c);
    }

    public List<Consulta> listar()
            throws Exception {

        return dao.listar();
    }

    public Consulta buscarPorId(Long id)
            throws Exception {

        return dao.buscarPorId(id);
    }

    public void atualizar(Consulta c)
            throws Exception {

        validarConsulta(c);

        dao.atualizar(c);
    }

    public void excluir(Long id)
            throws Exception {

        dao.excluir(id);
    }

    private void validarConsulta(Consulta c)
        throws Exception {

        if (c.getData() == null) {

            throw new Exception(
                    "Data obrigatória"
            );
        }

        java.util.Date hoje =
                new java.util.Date();

        if (c.getData().before(hoje)) {

            throw new Exception(
                    "Não é permitido agendar consultas em datas passadas"
            );
        }

        if (c.getDescricao() == null ||
                c.getDescricao()
                        .trim()
                        .isEmpty()) {

            throw new Exception(
                    "Descrição obrigatória"
            );
        }

        if (c.getValor() == null ||
                c.getValor() <= 0) {

            throw new Exception(
                    "Valor inválido"
            );
        }

        if (c.getPaciente() == null ||
                c.getPaciente().getId() == null) {

            throw new Exception(
                    "Paciente obrigatório"
            );
        }

        if (c.getMedico() == null ||
                c.getMedico().getId() == null) {

            throw new Exception(
                    "Médico obrigatório"
            );
        }

        PacienteNegocio pacienteNegocio =
                new PacienteNegocio();

        Paciente paciente =
                pacienteNegocio.buscarPorId(
                        c.getPaciente().getId()
                );

        if (!paciente.getAtivo()) {

            throw new Exception(
                    "Paciente inativo não pode agendar consultas"
            );
        }
    }
}