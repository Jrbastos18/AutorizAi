<%-- 
    Document   : Sidebar da área de acesso do responsável
    Created on : 24 de nov. de 2025
    Author     : Zenalvo Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">

<%
    String current = request.getRequestURI();
%>

<div class="sidebar">
    <div class="logo-area">
        <img src="<%= request.getContextPath()%>/svg/Ativo 4Logo_AutorizAi_Vertical_Azul.svg" class="logo">
        <h4>Responsável</h4>
    </div>

    <ul class="menu">

        <li>
            <a href="<%= request.getContextPath()%>/dashboard/responsavel/index.jsp"
               class="<%= current.endsWith("index.jsp") ? "active" : ""%>">
                Meu Perfil
            </a>
        </li>

        <li>
            <a href="<%= request.getContextPath()%>/dashboard/responsavel/perfil-aluno.jsp"
               class="<%= current.endsWith("perfil-aluno.jsp") ? "active" : ""%>">
                Perfil do Aluno
            </a>
        </li>

        <li>
            <a href="<%= request.getContextPath()%>/dashboard/responsavel/autorizacoes-pendentes.jsp"
               class="<%= current.endsWith("autorizacoes-pendentes.jsp") ? "active" : ""%>">
                Autorizações Pendentes
            </a>
        </li>

        <li>
            <a href="<%= request.getContextPath()%>/dashboard/responsavel/historico.jsp"
               class="<%= current.endsWith("historico.jsp") ? "active" : ""%>">
                Histórico
            </a>
        </li>

<!--        <li>
            <a href="<%= request.getContextPath()%>/dashboard/responsavel/eventos.jsp"
               class="<%= current.endsWith("eventos.jsp") ? "active" : ""%>">
                Eventos
            </a>
        </li>-->

        <li>
            <a href="<%= request.getContextPath()%>/logout" onclick="return confirm('Tem certeza que deseja sair?')">Logout</a>
        </li>

    </ul>
</div>
