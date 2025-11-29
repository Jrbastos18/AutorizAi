<%-- 
    Document   : Página JSP para criar bilhete a ser autorizado
    Created on : 27 de nov. de 2025
    Author     : Zenalvo Junior
--%>

<%@page import="model.Escola"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Criar Autorização</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/sidebar.css" rel="stylesheet">
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
                <h2> Criar Autorização </h2>
                <form class="detalhes-form" method="post" action="${pageContext.request.contextPath}/CadastrarAutorizacao">
                    <input type="hidden" name="idEscola" value="<%= escola.getId()%>">

                    <div class="form-group">
                        <label for="titulo">Título da Autorização</label>
                        <input type="text" id="titulo" name="titulo" required>
                    </div>

                    <div class="form-group">
                        <label for="descricao">Descrição</label>
                        <textarea id="descricao" name="descricao" required></textarea>
                    </div>

                    <div class="form-group">
                        <label for="data-limite">Data Limite</label>
                        <input type="date" id="data-limite" name="data-limite" required>
                    </div>

                    <div class="form-group">
                        <label for="destino">Enviar para:</label>
                        <select id="destino" name="destino" required>
                            <option value="">Selecione</option>
                            <option value="todos">Enviar para todos os alunos</option>

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

                    <button type="submit" class="btn-default">Criar Autorização</button>
                </form>
            </div>
        </div>
    </body>
</html>
