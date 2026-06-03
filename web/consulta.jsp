<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@page import="model.Consulta"%>
<%@page import="model.Paciente"%>
<%@page import="model.Medico"%>

<%
    Consulta consulta =
            (Consulta)
                    request.getAttribute(
                            "consulta"
                    );

    List<Consulta> lista =
            (List<Consulta>)
                    request.getAttribute(
                            "lista"
                    );

    List<Paciente> pacientes =
            (List<Paciente>)
                    request.getAttribute(
                            "pacientes"
                    );

    List<Medico> medicos =
            (List<Medico>)
                    request.getAttribute(
                            "medicos"
                    );

    String erro =
            (String)
                    request.getAttribute(
                            "erro"
                    );

    SimpleDateFormat sdf =
            new SimpleDateFormat(
                    "yyyy-MM-dd"
            );
%>

<!DOCTYPE html>

<html>

<head>

    <title>Consultas</title>

    <link
            rel="stylesheet"
            href="css/estilo.css"
    >

</head>

<body>

<div class="topbar">

    <a href="index.jsp" class="logo">

        <div class="logo-icon">
            <div class="cross-horizontal"></div>
            <div class="cross-vertical"></div>
        </div>

        <div class="logo-text">
            <span class="logo-clinica">Clínica</span><span class="logo-web">Web</span>
        </div>

    </a>

</div>

<div class="sidebar">

    <a href="paciente">
        Pacientes
    </a>

    <a href="medico">
        Médicos
    </a>

    <a href="consulta">
        Consultas
    </a>

</div>

<div class="content">

    <div class="card">

        <h1 class="page-title">
            Cadastro de Consultas
        </h1>

        <%
            if (erro != null) {
        %>

        <div class="erro-box">

            <%= erro %>

        </div>

        <br>

        <%
            }
        %>

        <form action="consulta" method="post">

            <input
                    type="hidden"
                    name="id"
                    value="<%= consulta != null
                            ? consulta.getId()
                            : "" %>"
            >

            <div class="form-grid">

                <div class="form-group">

                    <label>
                        Data da Consulta
                    </label>

                    <input
                            type="date"
                            name="data"
                            min="<%= sdf.format(
                                    new java.util.Date()
                            ) %>"
                            value="<%= consulta != null &&
                                    consulta.getData() != null
                                    ? sdf.format(
                                            consulta.getData()
                                    )
                                    : "" %>"
                    >

                </div>

                <div class="form-group">

                    <label>
                        Valor
                    </label>

                    <input
                            type="number"
                            step="0.01"
                            min="0"
                            name="valor"
                            value="<%= consulta != null
                                    ? consulta.getValor()
                                    : "" %>"
                    >

                </div>

                <div class="form-group">

                    <label>
                        Paciente
                    </label>

                    <select name="pacienteId">

                        <%
                            if (pacientes != null) {

                                for (Paciente p : pacientes) {
                        %>

                        <option
                                value="<%= p.getId() %>"

                                <%= consulta != null &&
                                        consulta.getPaciente() != null &&
                                        consulta.getPaciente().getId()
                                                .equals(p.getId())
                                        ? "selected"
                                        : "" %>
                        >

                            <%= p.getNome() %>

                            <%= !p.getAtivo()
                                    ? " (INATIVO)"
                                    : "" %>

                        </option>

                        <%
                                }
                            }
                        %>

                    </select>

                </div>

                <div class="form-group">

                    <label>
                        Médico
                    </label>

                    <select name="medicoId">

                        <%
                            if (medicos != null) {

                                for (Medico m : medicos) {
                        %>

                        <option
                                value="<%= m.getId() %>"

                                <%= consulta != null &&
                                        consulta.getMedico() != null &&
                                        consulta.getMedico().getId()
                                                .equals(m.getId())
                                        ? "selected"
                                        : "" %>
                        >

                            <%= m.getNome() %>

                        </option>

                        <%
                                }
                            }
                        %>

                    </select>

                </div>

                <div class="form-group">

                    <label>
                        Consulta Realizada
                    </label>

                    <select name="realizada">

                        <option
                                value="false"

                                <%= consulta != null &&
                                        !consulta.getRealizada()
                                        ? "selected"
                                        : "" %>
                        >
                            Não
                        </option>

                        <option
                                value="true"

                                <%= consulta != null &&
                                        consulta.getRealizada()
                                        ? "selected"
                                        : "" %>
                        >
                            Sim
                        </option>

                    </select>

                </div>

            </div>

            <br>

            <div class="form-group">

                <label>
                    Descrição
                </label>

                <textarea
                        name="descricao"
                        rows="4"
                ><%= consulta != null
                        ? consulta.getDescricao()
                        : "" %></textarea>

            </div>

            <br>

            <button
                    type="submit"
                    class="btn btn-primary"
            >

                <%= consulta != null
                        ? "Atualizar"
                        : "Salvar" %>

            </button>

        </form>

    </div>

    <div class="card">

        <h2>
            Lista de Consultas
        </h2>

        <div class="table-container">

            <table class="table">

                <tr>

                    <th>ID</th>
                    <th>Data</th>
                    <th>Paciente</th>
                    <th>Médico</th>
                    <th>Valor</th>
                    <th>Status</th>
                    <th>Ações</th>

                </tr>

                <%
                    if (lista != null) {

                        for (Consulta c : lista) {
                %>

                <tr>

                    <td>
                        <%= c.getId() %>
                    </td>

                    <td>
                        <%= c.getData() %>
                    </td>

                    <td>
                        <%= c.getPaciente() != null
                                ? c.getPaciente().getNome()
                                : "" %>
                    </td>

                    <td>
                        <%= c.getMedico() != null
                                ? c.getMedico().getNome()
                                : "" %>
                    </td>

                    <td>
                        R$ <%= c.getValor() %>
                    </td>

                    <td>

                        <span class="<%= c.getRealizada()
                                ? "badge badge-ativo"
                                : "badge badge-inativo" %>">

                            <%= c.getRealizada()
                                    ? "Realizada"
                                    : "Pendente" %>

                        </span>

                    </td>

                    <td>

                        <div class="actions">

                            <a
                                    class="btn btn-warning"
                                    href="consulta?acao=editar&id=<%= c.getId() %>"
                            >
                                Editar
                            </a>

                            <a
                                    class="btn btn-danger"
                                    href="consulta?acao=excluir&id=<%= c.getId() %>"
                            >
                                Excluir
                            </a>

                        </div>

                    </td>

                </tr>

                <%
                        }
                    }
                %>

            </table>

        </div>

    </div>

</div>

</body>

</html>