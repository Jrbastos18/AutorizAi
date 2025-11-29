<%-- 
    Document   : Página JSP Index/Dashboard do responsável após início da sessão no login
    Created on : 23 de nov. de 2025
    Author     : Zenalvo Junior
--%>

<%@page import="model.DAO.RespostaAutorizacaoDAO"%>
<%@page import="model.Responsavel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard - Responsável</title>

        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/sidebar.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@ include file="/components/sidebar-responsavel.jsp" %>

        <div class="main">
            <%                // Garantindo que a saída e entrada seja no padrão UTF-8
                response.setContentType("text/html; charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                request.setCharacterEncoding("UTF-8");

                // Prevenindo para que o usuário não acesse essa página sem estar logado
                HttpSession sess = request.getSession(false);
                if (sess == null || sess.getAttribute("responsavel") == null) {
                    response.sendRedirect(request.getContextPath() + "/index.html");
                    return;
                }

                Responsavel responsavel = (Responsavel) sess.getAttribute("responsavel");

                // Contador para exibir quantidade de autorizações pendentes
                int qtdPendentes = 0;
                try {
                    RespostaAutorizacaoDAO autDao = new RespostaAutorizacaoDAO();
                    qtdPendentes = autDao.contarPendentes(responsavel.getId());
                } catch (Exception e) {
                    System.out.println("Erro ao carregar contador: " + e.getMessage());
                }
            %>


            <h1>DASHBOARD - RESPONSÁVEL!</h1>

            <div class="bem-vindo">
                <h2>Bem vindo, <%= sess.getAttribute("nomeUsuario")%></h2>

                <div class="content">
                    <img src="../../svg/Ativo 5Logo_AutorizAi_Icone.svg" alt="Logo Ícone AutorizAi" width="60" height="60"/>

                    <div class="info">
                        <p>Autorizações Pendentes</p>

                        <% if (qtdPendentes > 0) {%>
                            <span class="badge-count"><%= qtdPendentes%></span>
                        <% } else { %>
                            <span style="color: green; font-weight: bold;">✔</span>
                        <% }%>
                    </div>
                </div>
            </div>

            <div class="perfil">
                <!--Detalhes do perfil do responsável-->
                <div class="usuario">
                    <img class="foto-perfil" src="../../img/perfil_default.png" alt="Imagem padrão para perfil da escola/responsável"/>

                    <h2><%= sess.getAttribute("nomeUsuario")%></h2>
                </div>

                <div class="detalhes-perfil">
                    <h2>Detalhes</h2>
                    <form class="detalhes-form">
                        <div class="form-group">
                            <label>Nome Completo: </label>
                            <input type="text" value="<%= responsavel.getNome()%>" readonly>
                        </div>

                        <div class="form-group">
                            <label>Email: </label>
                            <input type="text" value="<%= responsavel.getEmail()%>" readonly>
                        </div>

                        <div class="form-group">
                            <label>Telefone: </label>
                            <input type="text" value="<%= responsavel.getTelefone()%>" readonly>
                        </div>

                        <div class="form-group">
                            <label>CPF: </label>
                            <input type="text" value="<%= responsavel.getCpf()%>" readonly>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
