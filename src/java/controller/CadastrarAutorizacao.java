/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Aluno;
import model.Autorizacao;
import model.DAO.AlunoDAO;
import model.DAO.AlunoResponsavelDAO;
import model.DAO.AutorizacaoDAO;
import model.DAO.DestinoAutorizacaoDAO;
import model.DAO.ResponsavelDAO;
import model.DAO.RespostaAutorizacaoDAO;
import model.DestinoAutorizacao;
import model.Responsavel;
import model.RespostaAutorizacao;

/**
 * Document : Servlet para cadastrar autorização e destino autorização
 * Created on : 26 de nov. de 2025 
 * Author : Zenalvo Junior
 */
@WebServlet(name = "CadastrarAutorizacao", urlPatterns = {"/CadastrarAutorizacao"})
public class CadastrarAutorizacao extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CadastrarAutorizacao</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CadastrarAutorizacao at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //Instanciandoa classe de autorização
        Autorizacao autorizacao = new Autorizacao();

        autorizacao.setTitulo(request.getParameter("titulo"));
        autorizacao.setDescricao(request.getParameter("descricao"));
        Date dataLimite = null;
        try {
            autorizacao.setData_limite(new SimpleDateFormat("yyyy-MM-dd")
                    .parse(request.getParameter("data-limite")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String destino = request.getParameter("destino");
        int idEscola = Integer.parseInt(request.getParameter("idEscola"));

        // Cadastrando autorização no banco de Dados e retornando o pk_id gerado dele
        AutorizacaoDAO autorizacaoDAO = new AutorizacaoDAO();

        try {
            int idAutorizacao = autorizacaoDAO.cadastrar(autorizacao, Integer.parseInt(request.getParameter("idEscola")));

            // Criando destino da autorização
            DestinoAutorizacao destinoAutorizacao = new DestinoAutorizacao();
            destinoAutorizacao.setIdAutorizacao(idAutorizacao);

            if ("todos".equals(destino)) {
                destinoAutorizacao.setEnviarParaTodos(true);
                destinoAutorizacao.setTurmaDestino(null);
            } else {
                destinoAutorizacao.setEnviarParaTodos(false);
                destinoAutorizacao.setTurmaDestino(destino);
            }

            DestinoAutorizacaoDAO destinoDAO = new DestinoAutorizacaoDAO();
            destinoDAO.cadastrar(destinoAutorizacao);
            
            // Gerando as respostas para os responsáveis
            AlunoResponsavelDAO alunoRespDAO = new AlunoResponsavelDAO();
            RespostaAutorizacaoDAO respostaDAO = new RespostaAutorizacaoDAO();

            List<int[]> listaRelacionamentos;

            if (destinoAutorizacao.isEnviarParaTodos()) {
                // todos os alunos da escola
                listaRelacionamentos = alunoRespDAO.buscarTodosPorEscola(idEscola);
            } else {
                // apenas alunos da turma dentro da mesma escola
                listaRelacionamentos = alunoRespDAO.buscarPorTurma(destinoAutorizacao.getTurmaDestino(), idEscola);
            }

            for (int[] reg : listaRelacionamentos) {
                int idAluno = reg[0];       
                int idResponsavel = reg[1];  

                // Insere o registro 'Pendente' na tabela resposta_autorizacao
                respostaDAO.criarResposta(idAutorizacao, idResponsavel, idAluno);
            }

            // Armazenando na sessão e redirecionando
            HttpSession sess = request.getSession();
            sess.setAttribute("aut_titulo", autorizacao.getTitulo());
            sess.setAttribute("aut_descricao", autorizacao.getDescricao());
            sess.setAttribute("aut_data_limite", request.getParameter("data-limite"));
            sess.setAttribute("aut_destino", request.getParameter("destino"));
            sess.setAttribute("aut_id", idAutorizacao);

            response.sendRedirect(request.getContextPath() + "/dashboard/escola/autorizacao.jsp");

        } catch (ClassNotFoundException ex) {
            System.getLogger(CadastrarAutorizacao.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
