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
import model.DAO.AlunoDAO;

/**
 *  Document   : Servlet para validar RA do aluno e seguir com o cadastro do responsável
 *  Created on : 20 de nov. de 2025
 *  Author     : Zenalvo Junior
 */
@WebServlet(name = "ValidarRA", urlPatterns = {"/ValidarRA"})
public class ValidarRA extends HttpServlet {

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
            out.println("<title>Valida RA</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Valida RA at " + request.getContextPath() + "</h1>");
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

        // Recebe dados do input da página validaRA.html
        String ra = request.getParameter("ra");

        // Instanciando o AlunoDAO para chamar o método de verificar RA
        AlunoDAO alunoDAO = new AlunoDAO();

        try {
            boolean existe = alunoDAO.verificaRA(ra);

            if (existe) {
                request.getSession().setAttribute("raValidado", ra);
                response.sendRedirect(request.getContextPath() + "/cadastrarResponsavel/index.jsp"); // Redireciona para a página de cadastro do responsável
            } else {
                response.sendRedirect(request.getContextPath() + "/cadastrarResponsavel/validaRA.html?erro=1"); // Retornando o erro
            }

        } catch (Exception ex) {
            throw new ServletException(ex);
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
