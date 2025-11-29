<%-- 
    Document   : Página JSP para listar histórico de autorizações
    Created on : 26 de nov. de 2025
    Author     : Zenalvo Junior
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.DAO.RespostaAutorizacaoDAO"%>
<%@page import="model.DTO.HistoricoDTO"%>
<%@page import="model.Responsavel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Histórico de Autorizações</title>

        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/css/sidebar.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@ include file="../../components/sidebar-responsavel.jsp" %>

        <%  // Garantindo que a saída e entrada seja no padrão UTF-8
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
        %>

        <div class="main">
            <div class="container">
                <h2>Histórico de Autorizações</h2>                                            

                <%
                    // Instância do DAO e Busca
                    RespostaAutorizacaoDAO respDao = new RespostaAutorizacaoDAO();
                    List<HistoricoDTO> lista = new ArrayList<>();

                    try {
                        lista = respDao.buscarHistoricoCompleto(responsavel.getId());
                    } catch (Exception e) {
                        out.println("<p>Erro ao carregar histórico.</p>");
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                %>

                <table border="1" style="width: 100%; border-collapse: collapse; margin-top: 20px;">
                    <thead>
                        <tr> 
                            <th>Título</th>
                            <th>Aluno</th>
                            <th>Turma</th>
                            <th>Data Limite</th>
                            <th>Data Resposta</th>
                            <th>Status</th>
                            <th>Documento PDF</th>                   
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int n_reg = 0;
                            if (lista.isEmpty()) {
                        %>
                        <tr>
                            <td colspan="7" style="text-align: center;">Nenhuma autorização encontrada.</td>
                        </tr>
                        <%
                        } else {
                            for (HistoricoDTO item : lista) {
                                // Define a classe CSS baseada no status
                                String classeStatus = "";
                                if ("Autorizado".equalsIgnoreCase(item.getStatusResposta()))
                                    classeStatus = "status-autorizado";
                                else if ("Negado".equalsIgnoreCase(item.getStatusResposta()))
                                    classeStatus = "status-negado";
                                else
                                    classeStatus = "status-pendente";
                        %>        
                        <tr> 
                            <td><%= item.getTitulo()%></td>
                            <td><%= item.getNomeAluno()%></td>
                            <td><%= item.getTurma()%></td>

                            <td><%= sdf.format(item.getDataLimite())%></td>

                            <td>
                                <%= (item.getDataResposta() != null) ? sdf.format(item.getDataResposta()) : "---"%>
                            </td>

                            <td class="<%= classeStatus%>">
                                <%= item.getStatusResposta()%>
                            </td>

                            <td style="text-align: center;">
                                <a href="#">
                                    <img src="../../svg/Download_Icone.svg" alt="PDF" width="20" onerror="this.src='../../img/pdf_placeholder.png'">
                                </a>
                            </td>                     
                        </tr>                                      
                        <%
                                    n_reg++;
                                }
                            }
                        %>
                    </tbody>
                    <tfoot>
                        <tr>
                            <th colspan="7" style="text-align: left; padding: 10px;">
                                Total de Registros: <%= n_reg%>
                            </th>
                        </tr>    
                    </tfoot>
                </table>

            </div>
        </div>
    </body>
</html>
