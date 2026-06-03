package negocio;

import java.util.List;

import model.Paciente;

import persistencia.PacienteDAO;

public class PacienteNegocio {

    private PacienteDAO dao;

    public PacienteNegocio() {

        dao = new PacienteDAO();
    }

    public void salvar(Paciente paciente)
            throws Exception {

        validarPaciente(paciente);

        dao.salvar(paciente);
    }

    public void atualizar(Paciente paciente)
            throws Exception {

        validarPaciente(paciente);

        dao.atualizar(paciente);
    }

    public void excluir(Long id)
            throws Exception {

        dao.excluir(id);
    }

    public List<Paciente> listar()
            throws Exception {

        return dao.listar();
    }

    public Paciente buscarPorId(Long id)
            throws Exception {

        return dao.buscarPorId(id);
    }

    private void validarPaciente(Paciente p)
            throws Exception {

        if (p.getNome() == null ||
                p.getNome()
                        .trim()
                        .isEmpty()) {

            throw new Exception(
                    "Nome obrigatório"
            );
        }

        if (p.getCpf() == null ||
                p.getCpf()
                        .trim()
                        .isEmpty()) {

            throw new Exception(
                    "CPF obrigatório"
            );
        }

        String cpf =
                p.getCpf()
                        .replace(".", "")
                        .replace("-", "")
                        .trim();

        if (!cpf.matches("\\d{11}")) {

            throw new Exception(
                    "CPF deve conter 11 dígitos"
            );
        }

        if (!cpfValido(cpf)) {

            throw new Exception(
                    "CPF inválido"
            );
        }

        if (p.getDataNascimento() == null) {

            throw new Exception(
                    "Data de nascimento obrigatória"
            );
        }
    }

    private boolean cpfValido(String cpf) {

        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") ||
                cpf.equals("33333333333") ||
                cpf.equals("44444444444") ||
                cpf.equals("55555555555") ||
                cpf.equals("66666666666") ||
                cpf.equals("77777777777") ||
                cpf.equals("88888888888") ||
                cpf.equals("99999999999")) {

            return false;
        }

        try {

            int soma = 0;

            for (int i = 0; i < 9; i++) {

                soma +=
                        (cpf.charAt(i) - 48)
                                * (10 - i);
            }

            int primeiroDigito =
                    11 - (soma % 11);

            if (primeiroDigito >= 10) {

                primeiroDigito = 0;
            }

            if (primeiroDigito !=
                    (cpf.charAt(9) - 48)) {

                return false;
            }

            soma = 0;

            for (int i = 0; i < 10; i++) {

                soma +=
                        (cpf.charAt(i) - 48)
                                * (11 - i);
            }

            int segundoDigito =
                    11 - (soma % 11);

            if (segundoDigito >= 10) {

                segundoDigito = 0;
            }

            return segundoDigito ==
                    (cpf.charAt(10) - 48);

        } catch (Exception e) {

            return false;
        }
    }
}