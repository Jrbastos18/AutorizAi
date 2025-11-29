<%-- 
    Document   : Página JSP com autorizações pendentes para o Responsável aprovar ou não
    Created on : 26 de nov. de 2025
    Author     : Zenalvo Junior
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.DTO.AutorizacaoPendenteDTO"%>
<%@page import="java.util.List"%>
<%@page import="model.DAO.RespostaAutorizacaoDAO"%>
<%@page import="model.Responsavel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Autorizações Pendentes</title>
        
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/sidebar.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <%@ include file="/components/sidebar-responsavel.jsp" %>

        <div class="main">
            <%                // Validação de Sessão
                HttpSession sess = request.getSession(false);
                if (sess == null || sess.getAttribute("responsavel") == null) {
                    response.sendRedirect(request.getContextPath() + "/index.html");
                    return;
                }
                Responsavel responsavel = (Responsavel) sess.getAttribute("responsavel");

                // Buscando a lista de pendências
                RespostaAutorizacaoDAO respostaDAO = new RespostaAutorizacaoDAO();
                List<AutorizacaoPendenteDTO> listaPendentes = null;
                try {
                    listaPendentes = respostaDAO.buscarPendentesPorResponsavel(responsavel.getId());
                } catch (Exception e) {
                    out.println("Erro ao carregar dados.");
                }
            %>

            <h1>Autorizações Pendentes</h1>
            <p>Abaixo estão as autorizações que aguardam sua resposta.</p>

            <div class="card-container">
                <%
                    if (listaPendentes == null || listaPendentes.isEmpty()) {
                %>
                <div style="width: 100%; text-align: center; margin-top: 50px;">
                    <h3>Nenhuma pendência encontrada! 🎉</h3>
                    <p>Você respondeu todas as autorizações.</p>
                </div>
                <%
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    for (AutorizacaoPendenteDTO item : listaPendentes) {
                %>
                <div class="card">
                    <h3><%= item.getTituloAutorizacao()%></h3>
                    <div class="aluno">Aluno: <%= item.getNomeAluno()%></div>

                    <div class="desc">
                        <%= item.getDescricaoAutorizacao()%>
                    </div>

                    <div class="data">
                        Data Limite: <%= sdf.format(item.getDataLimite())%>
                    </div>

                    <form action="<%= request.getContextPath()%>/ProcessarAutorizacao" method="POST" class="btn-group">
                        <input type="hidden" name="idResposta" value="<%= item.getIdResposta()%>">

                        <button type="submit" name="acao" value="autorizar" class="btn btn-approve">Autorizar</button>
                        <button type="submit" name="acao" value="negar" class="btn btn-deny">Negar</button>
                    </form>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </body>
</html>
