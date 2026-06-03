<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="model.Medico"%>

<%
    Medico medico =
            (Medico)
                    request.getAttribute(
                            "medico"
                    );

    List<Medico> lista =
            (List<Medico>)
                    request.getAttribute(
                            "lista"
                    );
%>

<!DOCTYPE html>

<html>

<head>

    <title>Médicos</title>

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
            Cadastro de Médicos
        </h1>

        <form action="medico" method="post">

            <input
                    type="hidden"
                    name="id"
                    value="<%= medico != null
                            ? medico.getId()
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
                            value="<%= medico != null
                                    ? medico.getNome()
                                    : "" %>"
                    >

                </div>

                <div class="form-group">

                    <label>
                        Especialidade
                    </label>

                    <input
                            type="text"
                            name="especialidade"
                            value="<%= medico != null
                                    ? medico.getEspecialidade()
                                    : "" %>"
                    >

                </div>

                <div class="form-group">

                    <label>
                        CRM
                    </label>

                    <input
                            type="text"
                            name="crm"
                            value="<%= medico != null
                                    ? medico.getCrm()
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

                <%= medico != null
                        ? "Atualizar"
                        : "Salvar" %>

            </button>

        </form>

    </div>

    <div class="card">

        <h2>
            Lista de Médicos
        </h2>

        <div class="table-container">

            <table class="table">

                <tr>

                    <th>ID</th>
                    <th>Nome</th>
                    <th>Especialidade</th>
                    <th>CRM</th>
                    <th>Status</th>
                    <th>Ações</th>

                </tr>

                <%
                    if (lista != null) {

                        for (Medico m : lista) {
                %>

                <tr>

                    <td>
                        <%= m.getId() %>
                    </td>

                    <td>
                        <%= m.getNome() %>
                    </td>

                    <td>
                        <%= m.getEspecialidade() %>
                    </td>

                    <td>
                        <%= m.getCrm() %>
                    </td>

                    <td>

                        <span class="<%= m.getAtivo()
                                ? "badge badge-ativo"
                                : "badge badge-inativo" %>">

                            <%= m.getAtivo()
                                    ? "Ativo"
                                    : "Inativo" %>

                        </span>

                    </td>

                    <td>

                        <div class="actions">

                            <a
                                    class="btn btn-warning"
                                    href="medico?acao=editar&id=<%= m.getId() %>"
                            >
                                Editar
                            </a>

                            <a
                                    class="btn btn-danger"
                                    href="medico?acao=excluir&id=<%= m.getId() %>"
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