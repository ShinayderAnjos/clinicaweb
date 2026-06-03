package controller;

import model.Medico;
import negocio.MedicoNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/medico")
public class MedicoController
        extends HttpServlet {

    private MedicoNegocio negocio =
            new MedicoNegocio();

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

                Medico m =
                        negocio.buscarPorId(id);

                req.setAttribute(
                        "medico",
                        m
                );
            }

            if (acao != null &&
                    acao.equals("excluir")) {

                Long id =
                        Long.parseLong(
                                req.getParameter("id")
                        );

                negocio.excluir(id);

                resp.sendRedirect("medico");

                return;
            }

            req.setAttribute(
                    "lista",
                    negocio.listar()
            );

            req.getRequestDispatcher(
                    "medico.jsp"
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

            Medico m =
                    new Medico();

            m.setNome(
                    req.getParameter("nome")
            );

            m.setEspecialidade(
                    req.getParameter(
                            "especialidade"
                    )
            );

            m.setCrm(
                    req.getParameter("crm")
            );

            m.setAtivo(
                    Boolean.parseBoolean(
                            req.getParameter("ativo")
                    )
            );

            if (id == null ||
                    id.isEmpty()) {

                negocio.salvar(m);

            } else {

                m.setId(
                        Long.parseLong(id)
                );

                negocio.atualizar(m);
            }

            resp.sendRedirect("medico");

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}