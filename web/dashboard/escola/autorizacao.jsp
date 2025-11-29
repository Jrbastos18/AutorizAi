<%-- 
    Document   : Página JSP para visualizar autorização criada
    Created on : 27 de nov. de 2025
    Author     : Zenalvo Junior
--%>

<%@page import="model.Escola"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autorização Criada</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/sidebar.css" rel="stylesheet">
    </head>
    <body>

        <%@ include file="../../components/sidebar-escola.jsp" %>

        <%            // Garantindo UTF-8
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

            // Verificando login
            HttpSession sess = request.getSession(false);
            if (sess == null || sess.getAttribute("escola") == null) {
                response.sendRedirect(request.getContextPath() + "/index.html");
                return;
            }

            Escola escola = (Escola) sess.getAttribute("escola");

            // Pegando informações da autorização (gravadas no Servlet após criar)
            String titulo = (String) sess.getAttribute("aut_titulo");
            String descricao = (String) sess.getAttribute("aut_descricao");
            String dataLimite = (String) sess.getAttribute("aut_data_limite");
            String destino = (String) sess.getAttribute("aut_destino");
            Integer idAutorizacao = (Integer) sess.getAttribute("aut_id");
        %>

        <div class="main">
            <h2>Autorização Criada com Sucesso!</h2>

            <div class="form-default">

                <div class="form-group">
                    <label>Título:</label>
                    <p><%= titulo%></p>
                </div>

                <div class="form-group">
                    <label>Descrição:</label>
                    <p><%= descricao%></p>
                </div>

                <div class="form-group">
                    <label>Data Limite:</label>
                    <p><%= dataLimite%></p>
                </div>

                <div class="form-group">
                    <label>Destino:</label>
                    <p><%= destino.equals("todos") ? "Todos os alunos" : destino%></p>
                </div>

                <div class="form-group">
                    <label>ID da Autorização (BD):</label>
                    <p><%= idAutorizacao%></p>
                </div>

                <hr>

                <form method="get" action="${pageContext.request.contextPath}/GerarPDF" target="_blank">
                    <input type="hidden" name="idAutorizacao" value="<%= idAutorizacao%>">
                    <button type="submit" class="btn-default">Baixar PDF</button>
                </form>

                <br>

                <a href="${pageContext.request.contextPath}/dashboard/escola/criar-autorizacao.jsp" 
                   class="btn-default">Criar outra autorização</a>

            </div>
        </div>

    </body>
</html>
