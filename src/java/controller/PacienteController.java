package controller;

import model.Paciente;
import negocio.PacienteNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/paciente")
public class PacienteController
        extends HttpServlet {

    private PacienteNegocio negocio =
            new PacienteNegocio();

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            String acao =
                    req.getParameter("acao");

            if (acao != null &&
                    acao.equals("editar")) {

                Long id =
                        Long.parseLong(
                                req.getParameter("id")
                        );

                Paciente p =
                        negocio.buscarPorId(id);

                req.setAttribute(
                        "paciente",
                        p
                );
            }

            if (acao != null &&
                    acao.equals("excluir")) {

                Long id =
                        Long.parseLong(
                                req.getParameter("id")
                        );

                negocio.excluir(id);

                resp.sendRedirect("paciente");

                return;
            }

            req.setAttribute(
                    "lista",
                    negocio.listar()
            );

            req.getRequestDispatcher(
                    "paciente.jsp"
            ).forward(req, resp);

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        try {

            String id =
                    req.getParameter("id");

            Paciente p =
                    new Paciente();

            p.setNome(
                    req.getParameter("nome")
            );

            p.setCpf(
                    req.getParameter("cpf")
            );

            p.setAtivo(
                    Boolean.parseBoolean(
                            req.getParameter("ativo")
                    )
            );

            SimpleDateFormat sdf =
                    new SimpleDateFormat(
                            "yyyy-MM-dd"
                    );

            p.setDataNascimento(
                    sdf.parse(
                            req.getParameter(
                                    "dataNascimento"
                            )
                    )
            );

            if (id == null ||
                    id.isEmpty()) {

                negocio.salvar(p);

            } else {

                p.setId(
                        Long.parseLong(id)
                );

                negocio.atualizar(p);
            }

            resp.sendRedirect("paciente");

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}