<%-- 
    Document   : Página JSP para Listar Alunos cadastrados pela Escola
    Created on : 27 de nov. de 2025
    Author     : Zenalvo Junior
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Aluno"%>
<%@page import="model.DAO.AlunoDAO"%>
<%@page import="model.Escola"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alunos</title>
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
            <div class="container">
                <h2>Alunos - Lista Completa</h2>                                                   

                <%
                    //Instância do Objeto
                    AlunoDAO alunoDAO = new AlunoDAO();

                    List<Aluno> lista = new ArrayList<>();
                    lista = alunoDAO.consultaGeral(escola.getId());
                %>
                <table border="1" style="width: 100%; border-collapse: collapse; margin-top: 20px;">
                    <tr> 
                        <th>RA</th>
                        <th>Nome</th>
                        <th>CPF</th>
                        <th>Turma</th>
                        <th>Alterar</th>
                        <th>Consultar</th>
                        <th>Apagar</th>                    
                    </tr>
                    <%
                        int n_reg = 0;
                        for (int i = 0; i <= lista.size() - 1; i++) {
                    %>        
                    <tr> 
                        <td><%=lista.get(i).getRa()%></td>
                        <td><%=lista.get(i).getNome()%></td>
                        <td><%=lista.get(i).getCpf()%></td>
                        <td><%=lista.get(i).getTurma()%></td>                  
                        <td><a href="alterarAluno.jsp?id=<%=lista.get(i).getId()%>"><img src="../../svg/Edit_Icone.svg" alt="Editar"></a></td>                    
                        <td><a href="#"><img src="../../svg/Search_Icone.svg"></a></td>                    
                        <td>️<a href="#"><img src="../../svg/Delete_Icone.svg"></a></td>                                            
                    </tr>                                               
                    <%
                            n_reg++;
                        }
                    %>
                    <tfoot>
                        <tr>
                            <th>Registros</th>
                            <th><%=n_reg%></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>    
                    </tfoot>
                </table>

            </div>
        </div>

    </body>
</html>
