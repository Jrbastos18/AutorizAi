<%-- 
    Document   : Página JSP para Cadastrar Responsável
    Created on : 21 de nov. de 2025
    Author     : Zenalvo Junior
--%>

<%@page import="model.DAO.ResponsavelDAO"%>
<%@page import="model.DAO.EnderecoDAO"%>
<%@page import="model.DAO.LoginDAO"%>
<%@page import="model.Login"%>
<%@page import="model.Endereco"%>
<%@page import="model.Responsavel"%>
<%@page import="java.security.*"%>
<%@page import="java.math.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro - Escola</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"> 
        <link href="../css/index_style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <h2>Responsável</h2>
            <%
                // Garantindo que a saída e entrada seja no padrão UTF-8
                response.setContentType("text/html; charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                request.setCharacterEncoding("UTF-8");
                
                //Instância do Objeto Escola, Endereço e Login
                Responsavel responsavel = new Responsavel();
                Endereco endereco = new Endereco();
                Login login = new Login();
                
                // Gravando RA do aluno
                String ra = request.getParameter("ra");

                // Entrada / Atrib. valores
                // Cadastrando Responsavel, Endereço e Login
                responsavel.setNome(request.getParameter("nome") );
                responsavel.setCpf(request.getParameter("cpf") );
                responsavel.setTelefone(request.getParameter("telefone") );
                responsavel.setEmail(request.getParameter("email") );
                
                endereco.setCep(request.getParameter("cep"));
                endereco.setLogradouro(request.getParameter("logradouro"));
                endereco.setNumero(request.getParameter("numero"));
                // verificando se está vazio
                if (!request.getParameter("complemento").isEmpty()){
                    endereco.setComplemento(request.getParameter("complemento"));
                } else {
                    endereco.setComplemento("");
                }              
                endereco.setBairro(request.getParameter("bairro"));
                endereco.setCidade(request.getParameter("cidade"));
                endereco.setUf(request.getParameter("uf").trim());
                endereco.setEstado(request.getParameter("estado"));
                
                login.setEmail(responsavel.getEmail());
                String senha = request.getParameter("senha");
            
                //Rotina de criptografia - segurança
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update(senha.getBytes(), 0, senha.length());
                String senha_cript = new BigInteger(1, md5.digest()).toString(16);
                
                login.setSenha(senha_cript); 
                login.setTipo_usuario("responsavel");
                login.setStatus_conta("ATIVO");

                //Salvar
                LoginDAO loginDAO = new LoginDAO();
                EnderecoDAO enderecoDAO = new EnderecoDAO();
                ResponsavelDAO responsavelDAO = new ResponsavelDAO();
                
                int id_login = loginDAO.cadastrar(login);
                
                if (id_login != -1) {
                    int id_endereco = enderecoDAO.cadastrar(endereco);
                
                    if (responsavelDAO.cadastrar(responsavel, id_login, id_endereco, ra)==true){
                        out.println("<br><br> Responsável cadastrado com sucesso!!!"); 
                        %>
                        <button type="button" onclick="document.location = '../login_responsavel.html'" class="btn-default">Fazer Login</button>  
                        <%
                    }else{
                        out.println("<br><br> Responsável não cadastrado ou já está cadastrado!!!");
                        %>
                        <button type="button" onclick="document.location = '../login_responsavel.html'" class="btn-default">Voltar ao Login</button>                   
                        <%
                    }
                }else{
                    out.println("<br><br> Responsável não cadastrado ou já está cadastrado!!!");
                    %>
                    <button type="button" onclick="document.location = '../login_responsavel.html'" class="btn-default">Voltar ao Login</button>                   
                    <%
                }                      
            %>
        </div>
    </body>
</html>

