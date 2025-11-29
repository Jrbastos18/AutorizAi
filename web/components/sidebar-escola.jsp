<%-- 
    Document   : Sidebar da área de acesso da escola
    Created on : 24 de nov. de 2025
    Author     : Zenalvo Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String current = request.getRequestURI();
%>
<link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">

<div class="sidebar">
    <div class="logo-area">
        <img src="<%= request.getContextPath()%>/svg/Ativo 4Logo_AutorizAi_Vertical_Azul.svg" class="logo">
        <h4>Escola</h4>
    </div>

    <ul class="menu">

        <li>
            <a href="<%= request.getContextPath()%>/dashboard/escola/index.jsp"
               class="<%= current.endsWith("index.jsp") ? "active" : ""%>">
                Meu Perfil
            </a>
        </li>

        <li>
            <a href="<%= request.getContextPath()%>/dashboard/escola/cadastrar-aluno.jsp"
               class="<%= (current.endsWith("cadastrar-aluno.jsp") || current.endsWith("aluno.jsp")) ? "active" : ""%>">
                Cadastrar Aluno
            </a>
        </li>

        <li>
            <a href="<%= request.getContextPath()%>/dashboard/escola/criar-autorizacao.jsp"
               class="<%= current.endsWith("criar-autorizacao.jsp") ? "active" : ""%>">
                Criar Autorização
            </a>
        </li>

        <li>
            <a href="<%= request.getContextPath()%>/dashboard/escola/listar-autorizacoes.jsp"
               class="<%= current.endsWith("listar-autorizacoes.jsp") ? "active" : ""%>">
                Listar Autorizações
            </a>
        </li>

        <li>
            <a href="<%= request.getContextPath()%>/dashboard/escola/listar-alunos.jsp"
               class="<%= current.endsWith("listar-alunos.jsp") ? "active" : ""%>">
                Listar Alunos
            </a>
        </li>

        <li>
            <a href="<%= request.getContextPath()%>/logout" onclick="return confirm('Tem certeza que deseja sair?')">Logout</a>
        </li>

    </ul>
</div>

