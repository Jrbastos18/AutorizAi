/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DAO.EscolaDAO;
import model.DAO.LoginDAO;
import model.DAO.ResponsavelDAO;
import model.Escola;
import model.Login;
import model.Responsavel;

/**
 * Document : Servlet de controle para validar Login e redirecionar para página de cada tipo de usuário 
 * Created on : 20 de nov. de 2025 
 * Author : Zenalvo Junior
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect(request.getContextPath() + "/index.html");
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

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String tipo = request.getParameter("tipo");

        // Instanciando as classes LoginDAO e Login como nulo
        LoginDAO loginDAO = new LoginDAO();
        Login login = null;
        try {
            login = loginDAO.autenticar(email, senha);
        } catch (ClassNotFoundException ex) {
            System.getLogger(LoginController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        // Verifica se o login é nulo, caso seja, vai voltar uma response com erro.
        if (login == null) {
            if ("escola".equals(tipo)) {
                response.sendRedirect("login_escola.html?erro=1");
            } else if ("responsavel".equals(tipo)) {
                response.sendRedirect("login_responsavel.html?erro=1");
            }
            return;
        }

        try {
            // Atualiza data do útlimo acesso do login antes de redirecionar para a dashboard
            loginDAO.atualizaUltimoLogin(login.getId());
        } catch (ClassNotFoundException ex) {
            System.getLogger(LoginController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        // Cria a sessão
        HttpSession session = request.getSession();

        // Verificando o tipo de login e setando nome do usuário, sendo escola ou responsável
        if (login.getTipo_usuario().equals("escola")) {

            EscolaDAO escolaDAO = new EscolaDAO();
            Escola escola = escolaDAO.buscarPorIdLogin(login.getId());

            session.setAttribute("escola", escola);
            session.setAttribute("nomeUsuario", escola.getNome());

            response.sendRedirect("dashboard/escola/index.jsp");

        } else if (login.getTipo_usuario().equals("responsavel")) {

            ResponsavelDAO responsavelDAO = new ResponsavelDAO();
            Responsavel responsavel = responsavelDAO.buscarPorIdLogin(login.getId());

            session.setAttribute("responsavel", responsavel);
            session.setAttribute("nomeUsuario", responsavel.getNome());

            response.sendRedirect("dashboard/responsavel/index.jsp");
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
