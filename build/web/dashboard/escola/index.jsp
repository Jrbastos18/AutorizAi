<%-- 
    Document   : Página JSP Index/Dashboard da escola após início da sessão no login
    Created on : 23 de nov. de 2025
    Author     : Zenalvo Junior
--%>

<%@page import="model.Escola"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dasboard - Escola</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <link href="../../css/sidebar.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@ include file="/components/sidebar-escola.jsp" %>

        <%  
            // Garantindo que a saída e entrada seja no padrão UTF-8
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
            <h1>DASHBOARD - ESCOLA!</h1>

            <div class="content">
                <h2>Bem vindo, <%= sess.getAttribute("nomeUsuario")%></h2>
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
                            <label>Nome da Escola: </label>
                            <input type="text" value="<%= escola.getNome()%>" readonly>
                        </div>

                        <div class="form-group">
                            <label>Email: </label>
                            <input type="text" value="<%= escola.getEmail()%>" readonly>
                        </div>

                        <div class="form-group">
                            <label>Telefone: </label>
                            <input type="text" value="<%= escola.getTelefone()%>" readonly>
                        </div>

                        <div class="form-group">
                            <label>CNPJ: </label>
                            <input type="text" value="<%= escola.getCnpj()%>" readonly>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
