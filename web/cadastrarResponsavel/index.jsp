<%-- 
    Document   : Página JSP de Cadastro do Responsável
    Created on : 23 de nov. de 2025, 13:59:50
    Author     : Zenalvo Junior
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Cadastro - Responsável</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet"> 
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index_style.css">
    </head>
    <body>
        <%
                // Garantindo que a saída e entrada seja no padrão UTF-8
                response.setContentType("text/html; charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                request.setCharacterEncoding("UTF-8");
        %>
        
        <div class="container">
            <form method="post" action="cadastrarResponsavel.jsp">

                <div class="form-group">
                    <label for="ra">RA do Aluno:</label>
                    <!-- Pegando RA enviado via request -->
                    <input id="ra" name="ra" type="text" readonly value="${sessionScope.raValidado}">
                </div>

                <div class="form-group">
                    <label for="nome">Nome do Responsável:</label>
                    <input id="nome" name="nome" type="text" required>
                </div>

                <div class="form-group">
                    <label for="cpf">CPF:</label>
                    <input id="cpf" name="cpf" type="text" maxlength="11" pattern="\d{11}" required>
                </div>

                <div class="form-group">
                    <label for="telefone">Telefone:</label>
                    <input id="telefone" name="telefone" type="text" required>
                </div>

                <div class="form-group">
                    <label for="email">E-mail:</label>
                    <input id="email" name="email" type="email" required>
                </div>

                <div class="form-group">
                    <label for="cep">CEP:</label>
                    <input id="cep" name="cep" type="text" maxlength="8" pattern="\d{8}" required>
                </div>

                <div class="form-group">
                    <label for="logradouro">Logradouro:</label>
                    <input id="logradouro" name="logradouro" type="text" required readonly>
                </div>

                <div class="form-group">
                    <label for="numero">Número:</label>
                    <input id="numero" name="numero" type="text" maxlength="8" required>
                </div>

                <div class="form-group">
                    <label for="complemento">Complemento:</label>
                    <input id="complemento" name="complemento" type="text" placeholder="Opcional">
                </div>

                <div class="form-group">
                    <label for="bairro">Bairro:</label>
                    <input id="bairro" name="bairro" type="text" required readonly>
                </div>

                <div class="form-group">
                    <label for="cidade">Cidade:</label>
                    <input id="cidade" name="cidade" type="text" required readonly>
                </div>

                <div class="form-group">
                    <label for="uf">UF:</label>
                    <input id="uf" name="uf" type="text" required readonly>
                </div>

                <div class="form-group">
                    <label for="estado">Estado:</label>
                    <input id="estado" name="estado" type="text" required readonly>
                </div>

                <div class="form-group">
                    <label for="senha">Senha: </label>
                    <input id="senha" name="senha" type="password" placeholder="Insira senha" required>
                </div>

                <div class="form-group">
                    <label for="confirmaSenha">Confirme a senha: </label>
                    <input id="confirmaSenha" name="confirmaSenha" type="password" placeholder="Confirmar senha" required>
                </div>

                <button type="submit" onclick="return validarSenha()" class="btn-default">
                    Cadastrar Responsável
                </button>
            </form>
        </div>


        <!-- Script CEP via API ViaCEP e preencher formulário dinamicamente + Validação de Senha -->
        <script src="../js/script.js" type="text/javascript"></script>
    </body>
</html>
