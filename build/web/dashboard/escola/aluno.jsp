<%-- 
    Document   : Página JSP para informar dados do aluno cadastrado
    Created on : 26 de nov. de 2025
    Author     : Zenalvo Junior
--%>

<%@page import="model.Escola"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Aluno</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/sidebar.css" rel="stylesheet">
    </head>
    <body>
        <%@ include file="/components/sidebar-escola.jsp" %>

        <%            // Garantindo que a saída e entrada seja no padrão UTF-8
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

            // Prevenindo para que o usuário não acesse essa página sem estar logado
            HttpSession sess = request.getSession(false);
            if (sess == null || sess.getAttribute("escola") == null) {
                response.sendRedirect(request.getContextPath() + "/index.html");
                return;
            }

            Escola escola = (Escola) sess.getAttribute("escola");

            String nome = (String) sess.getAttribute("alunoNome");
            String cpf = (String) sess.getAttribute("alunoCpf");
            String turma = (String) sess.getAttribute("alunoTurma");
            String ra = (String) sess.getAttribute("alunoRA");
        %>

        <div class="main">
            <div class="content">
                <% if (nome != null && turma != null && ra != null) {%>

                <h2>Aluno cadastrado com sucesso!</h2>

                <p><strong>Nome:</strong> <%= nome%></p>
                <p><strong>CPF:</strong> <%= cpf%></p>
                <p><strong>Turma:</strong> <%= turma%></p>
                <p><strong>RA:</strong> <%= ra%></p>

                <button class="btn-default"><a href="${pageContext.request.contextPath}/dashboard/escola/cadastrar-aluno.jsp">Cadastrar novo aluno</a></button>
                
                <% } else { %>

                <h3>Nenhuma informação de cadastro encontrada!</h3>
                <button onclick="history.back()" class="btn-default">Voltar</button>
            </div>
            <% }%>

            <%
                // limpa depois de exibir
                sess.removeAttribute("alunoNome");
                sess.removeAttribute("alunoCpf");
                sess.removeAttribute("alunoTurma");
                sess.removeAttribute("alunoRA");
            %>
        </div>


    </body>
</html>
