package controller;

import model.Consulta;
import model.Medico;
import model.Paciente;

import negocio.ConsultaNegocio;
import negocio.MedicoNegocio;
import negocio.PacienteNegocio;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/consulta")
public class ConsultaController
        extends HttpServlet {

    private ConsultaNegocio negocio =
            new ConsultaNegocio();

    private PacienteNegocio pacienteNegocio =
            new PacienteNegocio();

    private MedicoNegocio medicoNegocio =
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

                Consulta c =
                        negocio.buscarPorId(id);

                req.setAttribute(
                        "consulta",
                        c
                );
            }

            if (acao != null &&
                    acao.equals("excluir")) {

                Long id =
                        Long.parseLong(
                                req.getParameter("id")
                        );

                negocio.excluir(id);

                resp.sendRedirect("consulta");

                return;
            }

            req.setAttribute(
                    "lista",
                    negocio.listar()
            );

            req.setAttribute(
                    "pacientes",
                    pacienteNegocio.listar()
            );

            req.setAttribute(
                    "medicos",
                    medicoNegocio.listar()
            );

            req.getRequestDispatcher(
                    "consulta.jsp"
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

            Consulta c =
                    new Consulta();

            SimpleDateFormat sdf =
                    new SimpleDateFormat(
                            "yyyy-MM-dd"
                    );

            c.setData(
                    sdf.parse(
                            req.getParameter("data")
                    )
            );

            c.setDescricao(
                    req.getParameter(
                            "descricao"
                    )
            );

            c.setRealizada(
                    Boolean.parseBoolean(
                            req.getParameter(
                                    "realizada"
                            )
                    )
            );

            c.setValor(
                    Double.parseDouble(
                            req.getParameter(
                                    "valor"
                            )
                    )
            );

            Paciente p =
                    new Paciente();

            p.setId(
                    Long.parseLong(
                            req.getParameter(
                                    "pacienteId"
                            )
                    )
            );

            Medico m =
                    new Medico();

            m.setId(
                    Long.parseLong(
                            req.getParameter(
                                    "medicoId"
                            )
                    )
            );

            c.setPaciente(p);
            c.setMedico(m);

            if (id == null ||
                    id.isEmpty()) {

                negocio.salvar(c);

            } else {

                c.setId(
                        Long.parseLong(id)
                );

                negocio.atualizar(c);
            }

            resp.sendRedirect("consulta");

        } catch (Exception e) {

            throw new ServletException(e);
        }
    }
}