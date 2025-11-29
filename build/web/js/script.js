/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById("cep").addEventListener("blur", function () {

        const cep = this.value.replace(/\D/g, "");

        if (cep.length !== 8) {
            alert("CEP inválido!");
            return;
        }

        fetch(`https://viacep.com.br/ws/${cep}/json/`)
                .then(response => response.json())
                .then(data => {

                    if (data.erro) {
                        alert("CEP não encontrado!");
                        document.getElementById("cep").value = "";
                        return;
                    }

                    document.getElementById("logradouro").value = data.logradouro;
                    document.getElementById("bairro").value = data.bairro;
                    document.getElementById("cidade").value = data.localidade;
                    document.getElementById("uf").value = data.uf;
                    document.getElementById("estado").value = data.estado;
                })
                .catch(err => {
                    console.error("Erro ao buscar CEP:", err);
                    alert("Erro ao buscar CEP");
                });
    });
});


function validarSenha() {
    const senha = document.getElementById("senha").value;
    const confirma = document.getElementById("confirmaSenha").value;

    if (senha !== confirma) {
        document.getElementById("senha").value = "";
        document.getElementById("confirmaSenha").value = "";
        alert("As senhas não coincidem!");
        return false;
    }

    return true;
}


