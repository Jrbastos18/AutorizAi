/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Aluno;
import model.DAO.AlunoDAO;

/**
 *
 * @author Zenalvo Junior
 */
@WebServlet(name = "CadastrarAluno", urlPatterns = {"/CadastrarAluno"})
public class CadastrarAluno extends HttpServlet {

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
            out.println("<title>Servlet CadastrarAluno</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CadastrarAluno at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html; charset=UTF-8");

        // Pegando dados do formulário
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String turma = request.getParameter("turma");
        int idEscola = Integer.parseInt(request.getParameter("idEscola"));

        // Gerando RA automaticamente
        AlunoDAO alunoDAO = new AlunoDAO();
        String ra = alunoDAO.gerarRAUnico();

        // Criando objeto aluno
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setTurma(turma);
        aluno.setRa(ra);

        boolean resultado = false;
        // Efetuando o cadastro do aluno e passando como parâmetro o id da escola recebido da session
        try {
            resultado = alunoDAO.cadastrar(aluno, idEscola);
        } catch (ClassNotFoundException ex) {
            System.getLogger(CadastrarAluno.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        if (resultado == true) {

            HttpSession sess = request.getSession();
            sess.setAttribute("alunoNome", aluno.getNome());
            sess.setAttribute("alunoCpf", aluno.getCpf());
            sess.setAttribute("alunoTurma", aluno.getTurma());
            sess.setAttribute("alunoRA", aluno.getRa());

            response.sendRedirect(request.getContextPath() + "/dashboard/escola/aluno.jsp");

        } else {
            response.sendRedirect(request.getContextPath() + "/dashboard/escola/cadastrar-aluno.jsp?msg=erro");
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
