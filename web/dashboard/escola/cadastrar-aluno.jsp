<%-- 
    Document   : Página JSP para cadastrar aluno
    Created on : 24 de nov. de 2025
    Author     : Zenalvo Junior
--%>

<%@page import="model.Escola"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Aluno</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <link href="../../css/sidebar.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@ include file="../../components/sidebar-escola.jsp" %>

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
        %>

        <div class="main">

            <div class="detalhes-container">
                <h2> Cadastrar Aluno </h2>
                <form class="detalhes-form" method="post" action="${pageContext.request.contextPath}/CadastrarAluno">
                    <input type="hidden" name="idEscola" value="<%= escola.getId()%>">

                    <div class="form-group">
                        <label for="nome">Nome do Aluno:</label>
                        <input id="nome" name="nome" type="text" required>
                    </div>

                    <div class="form-group">
                        <label for="cpf">CPF:</label>
                        <input id="cpf" name="cpf" type="text" maxlength="11" pattern="\d{11}" required>
                    </div>

                    <div class="form-group">
                        <label for="turma">Turma</label>
                        <select name="turma" id="turma" class="form-control" required>
                            <option value="">Selecione a turma</option>

                            <optgroup label="Educação Infantil">
                                <option value="Infantil I">Infantil I</option>
                                <option value="Infantil II">Infantil II</option>
                            </optgroup>

                            <optgroup label="Ensino Fundamental - Anos Iniciais">
                                <option value="1º Ano A">1º Ano A</option>
                                <option value="1º Ano B">1º Ano B</option>
                                <option value="2º Ano A">2º Ano A</option>
                                <option value="3º Ano A">3º Ano A</option>
                            </optgroup>

                            <optgroup label="Ensino Fundamental - Anos Finais">
                                <option value="6º Ano A">6º Ano A</option>
                                <option value="7º Ano A">7º Ano A</option>
                                <option value="8º Ano A">8º Ano A</option>
                                <option value="9º Ano A">9º Ano A</option>
                            </optgroup>
                        </select>
                    </div>


                    <button type="submit" class="btn-default">Registrar</button>
                </form>
            </div
        </div>

    </body>
</html>
