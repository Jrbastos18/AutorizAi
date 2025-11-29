<%-- 
    Document   : Página JSP do Perfil do aluno no acesso do responsável
    Created on : 26 de nov. de 2025
    Author     : Zenalvo Junior
--%>

<%@page import="model.DAO.AlunoDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.Aluno"%>
<%@page import="model.Responsavel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Perfil do Aluno - Responsável</title>

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

                int idResponsavel = responsavel.getId();

                List<Aluno> listaAlunos = null;
                try {
                    AlunoDAO alunoDAO = new AlunoDAO();
                    listaAlunos = alunoDAO.buscarPorResponsavel(idResponsavel);
                } catch (Exception e) {
                    out.println("<p style='color: red;'>Erro ao carregar alunos: " + e.getMessage() + "</p>");
                    e.printStackTrace();
                    return;
                }
            %>


            <h1>Perfil de Alunos Vinculados ao Responsável: <%= sess.getAttribute("nomeUsuario")%></h1>

            <%
                if (listaAlunos == null || listaAlunos.isEmpty()) {
            %>
            <p>Nenhum aluno encontrado vinculado à sua conta.</p>
            <%
            } else {
                for (Aluno aluno : listaAlunos) {
            %>

            <div class="perfil">

                <div class="usuario">
                    <h2>Aluno: <%= aluno.getNome()%></h2>
                    <img class="foto-perfil" src="../../img/perfil_default.png" alt="Imagem padrão para perfil do aluno"/>                  
                </div>

                <div class="detalhes-perfil">
                    <h3>Detalhes de <%= aluno.getNome()%></h3>
                    <form class="detalhes-form">
                        <div class="form-group">
                            <label>Nome Completo: </label>
                            <input type="text" value="<%= aluno.getNome()%>" readonly>
                        </div>

                        <div class="form-group">
                            <label>RA: </label>
                            <input type="text" value="<%= aluno.getRa()%>" readonly>
                        </div>

                        <div class="form-group">
                            <label>CPF: </label>
                            <input type="text" value="<%= aluno.getCpf()%>" readonly> 
                        </div>

                        <div class="form-group">
                            <label>Turma: </label>
                            <input type="text" value="<%= aluno.getTurma()%>" readonly> 
                        </div>

                    </form>
                </div>
            </div>
            <br><br><hr> <%
                    }
                }
            %>

        </div>
    </body>
</html>
