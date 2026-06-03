package negocio;

import model.Medico;
import persistencia.MedicoDAO;

import java.util.List;

public class MedicoNegocio {

    private MedicoDAO dao =
            new MedicoDAO();

    public void salvar(Medico m)
            throws Exception {

        validarMedico(m);

        dao.salvar(m);
    }

    public List<Medico> listar()
            throws Exception {

        return dao.listar();
    }

    public Medico buscarPorId(Long id)
            throws Exception {

        return dao.buscarPorId(id);
    }

    public void atualizar(Medico m)
            throws Exception {

        validarMedico(m);

        dao.atualizar(m);
    }

    public void excluir(Long id)
            throws Exception {

        dao.excluir(id);
    }

    private void validarMedico(Medico m)
            throws Exception {

        if (m.getNome() == null ||
                m.getNome().trim().isEmpty()) {

            throw new Exception(
                    "Nome obrigatório"
            );
        }

        if (m.getEspecialidade() == null ||
                m.getEspecialidade()
                        .trim()
                        .isEmpty()) {

            throw new Exception(
                    "Especialidade obrigatória"
            );
        }

        if (m.getCrm() == null ||
                m.getCrm().trim().isEmpty()) {

            throw new Exception(
                    "CRM obrigatório"
            );
        }

        String crm =
                m.getCrm()
                        .replaceAll(
                                "[^0-9]",
                                ""
                        );

        if (!crm.matches("\\d{4,10}")) {

            throw new Exception(
                    "CRM inválido"
            );
        }

        m.setCrm(crm);
    }
}