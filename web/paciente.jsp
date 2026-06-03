<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="model.Paciente"%>

<%
    Paciente paciente =
            (Paciente)
                    request.getAttribute(
                            "paciente"
                    );

    List<Paciente> lista =
            (List<Paciente>)
                    request.getAttribute(
                            "lista"
                    );

    String erro =
            (String)
                    request.getAttribute(
                            "erro"
                    );
%>

<!DOCTYPE html>

<html>

<head>

    <title>Pacientes</title>

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
            Cadastro de Pacientes
        </h1>

        <%
            if (erro != null) {
        %>

        <div class="erro-box">
            <%= erro %>
        </div>

        <%
            }
        %>

        <form action="paciente" method="post">

            <input
                    type="hidden"
                    name="id"
                    value="<%= paciente != null
                            ? paciente.getId()
                            : "" %>"
            >

            <div class="form-grid">

                <div class="form-group">

                    <label>
                        Nome
                    </label>

                    <input
                            type="text"
                            name="nome"
                            value="<%= paciente != null
                                    ? paciente.getNome()
                                    : "" %>"
                    >

                </div>

                <div class="form-group">

                    <label>
                        CPF
                    </label>

                    <input
                            type="text"
                            name="cpf"
                            value="<%= paciente != null
                                    ? paciente.getCpf()
                                    : "" %>"
                    >

                </div>

                <div class="form-group">

                    <label>
                        Data de Nascimento
                    </label>

                    <input
                            type="date"
                            name="dataNascimento"
                            value="<%= paciente != null &&
                                    paciente.getDataNascimento() != null
                                    ? new java.text.SimpleDateFormat(
                                            "yyyy-MM-dd"
                                    ).format(
                                            paciente.getDataNascimento()
                                    )
                                    : "" %>"
                    >

                </div>

                <div class="form-group">

                    <label>
                        Status
                    </label>

                    <select name="ativo">

                        <option value="true">
                            Ativo
                        </option>

                        <option value="false">
                            Inativo
                        </option>

                    </select>

                </div>

            </div>

            <br>

            <button
                    type="submit"
                    class="btn btn-primary"
            >

                <%= paciente != null
                        ? "Atualizar"
                        : "Salvar" %>

            </button>

        </form>

    </div>

    <div class="card">

        <h2>
            Lista de Pacientes
        </h2>

        <div class="table-container">

            <table class="table">

                <tr>

                    <th>ID</th>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Status</th>
                    <th>Ações</th>

                </tr>

                <%
                    if (lista != null) {

                        for (Paciente p : lista) {
                %>

                <tr>

                    <td>
                        <%= p.getId() %>
                    </td>

                    <td>
                        <%= p.getNome() %>
                    </td>

                    <td>
                        <%= p.getCpf() %>
                    </td>

                    <td>

                        <span class="<%= p.getAtivo()
                                ? "badge badge-ativo"
                                : "badge badge-inativo" %>">

                            <%= p.getAtivo()
                                    ? "Ativo"
                                    : "Inativo" %>

                        </span>

                    </td>

                    <td>

                        <div class="actions">

                            <a
                                    class="btn btn-warning"
                                    href="paciente?acao=editar&id=<%= p.getId() %>"
                            >
                                Editar
                            </a>

                            <a
                                    class="btn btn-danger"
                                    href="paciente?acao=excluir&id=<%= p.getId() %>"
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