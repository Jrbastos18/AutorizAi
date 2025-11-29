<%-- 
    Document   : Página para Alterar Autorização
    Author     : Zenalvo Junior
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.Autorizacao"%>
<%@page import="model.DAO.AutorizacaoDAO"%>
<%@page import="model.Escola"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Autorização</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <link href="../../css/sidebar.css" rel="stylesheet" type="text/css"/>
        
        <style>
            .detalhes-container {
                background-color: #ffffff;
                border-radius: 16px;
                box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
                padding: 30px;
                width: 100%;
                max-width: 700px;
                margin: 0 auto;
            }

            .detalhes-container h2 {
                color: #333;
                margin-bottom: 20px;
                border-bottom: 2px solid #f0f0f0;
                padding-bottom: 10px;
            }

            .detalhes-form {
                display: flex;
                flex-direction: column;
                gap: 15px;
            }

            .form-group {
                display: flex;
                flex-direction: column;
                text-align: start;
            }

            .form-group label {
                font-size: 0.9rem;
                font-weight: 600;
                color: #666;
                margin-bottom: 5px;
                margin-left: 4px;
            }

            .detalhes-form input, 
            .detalhes-form textarea {
                background-color: #f8f9fa; 
                border: 1px solid #e0e0e0; 
                border-radius: 8px; 
                padding: 12px 15px; 
                font-size: 1rem;
                color: #333; 
                width: 100%;
                box-sizing: border-box;
                transition: border-color 0.3s ease;
                font-family: inherit;
            }
            
            .detalhes-form textarea {
                resize: vertical;
                min-height: 100px;
            }

            .detalhes-form input:focus,
            .detalhes-form textarea:focus {
                outline: none;
                border-color: #bbb;
                background-color: #fff;
            }

            .btn-salvar {
                background-color: #28a745;
                color: white;
                border: none;
                padding: 12px;
                border-radius: 8px;
                font-size: 1rem;
                font-weight: bold;
                cursor: pointer;
                margin-top: 10px;
                transition: background 0.3s;
            }
            .btn-salvar:hover { background-color: #218838; }
            
            .btn-cancelar {
                background-color: #dc3545;
                text-align: center;
                text-decoration: none;
                color: white;
                padding: 12px;
                border-radius: 8px;
                font-size: 1rem;
                font-weight: bold;
                margin-top: 5px;
            }
        </style>
    </head>
    <body>
        <%@ include file="../../components/sidebar-escola.jsp" %>

        <%            
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

            HttpSession sess = request.getSession(false);
            if (sess == null || sess.getAttribute("escola") == null) {
                response.sendRedirect(request.getContextPath() + "/index.html");
                return;
            }

            Escola escola = (Escola) sess.getAttribute("escola");
            
            String idStr = request.getParameter("id");
            Autorizacao autorizacao = null;
            String dataFormatada = ""; 
            
            if (idStr != null && !idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    AutorizacaoDAO dao = new AutorizacaoDAO();
                    autorizacao = dao.buscarPorId(id);
                    
                    if(autorizacao != null && autorizacao.getData_limite() != null) {
                        // Formata a data para yyyy-MM-dd (padrão HTML5)
                        dataFormatada = new SimpleDateFormat("yyyy-MM-dd").format(autorizacao.getData_limite());
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao buscar: " + e);
                }
            }
            
            if (autorizacao == null) {
                response.sendRedirect("listar-autorizacoes.jsp");
                return;
            }
        %>

        <div class="main">
            <div class="container">
                
                <div class="detalhes-container">
                    <h2>Alterar Autorização</h2>
                    
                    <form class="detalhes-form" action="<%= request.getContextPath() %>/SalvarAlteracaoAutorizacao" method="POST">
                        
                        <input type="hidden" name="id" value="<%= autorizacao.getId() %>">

                        <div class="form-group">
                            <label>Título: </label>
                            <input type="text" name="titulo" value="<%= autorizacao.getTitulo() %>" required>
                        </div>

                        <div class="form-group">
                            <label>Descrição: </label>
                            <textarea name="descricao" required><%= autorizacao.getDescricao() %></textarea>
                        </div>

                        <div class="form-group">
                            <label>Data Limite: </label>
                            <input type="date" name="dataLimite" required>
                        </div>

                        <div class="form-group">
                            <label>Status Atual (Apenas leitura): </label>
                            <input type="text" value="<%= autorizacao.getStatus_global() %>" readonly style="background-color: #e9ecef; cursor: not-allowed;">
                        </div>
                        
                        <button type="submit" class="btn-salvar">Salvar Alterações</button>
                        <a href="listar-autorizacoes.jsp" class="btn-cancelar">Cancelar</a>
                    </form>
                </div>

            </div>
        </div>
    </body>
</html>