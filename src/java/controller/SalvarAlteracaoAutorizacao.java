/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Autorizacao;
import model.DAO.AutorizacaoDAO;

/**
 *
 * @author junio
 */
@WebServlet(name = "SalvarAlteracaoAutorizacao", urlPatterns = {"/SalvarAlteracaoAutorizacao"})
public class SalvarAlteracaoAutorizacao extends HttpServlet {

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
            out.println("<title>Servlet SalvarAlteracaoAutorizacao</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SalvarAlteracaoAutorizacao at " + request.getContextPath() + "</h1>");
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
        
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String titulo = request.getParameter("titulo");
            String descricao = request.getParameter("descricao");
            String dataLimiteStr = request.getParameter("dataLimite");
            
            Date dataLimite = null;
            if (dataLimiteStr != null && !dataLimiteStr.isEmpty()) {
                dataLimite = new SimpleDateFormat("yyyy-MM-dd").parse(dataLimiteStr);
            }
            
            Autorizacao aut = new Autorizacao();
            aut.setId(id);
            aut.setTitulo(titulo);
            aut.setDescricao(descricao);
            aut.setData_limite(dataLimite);
            
            AutorizacaoDAO dao = new AutorizacaoDAO();
            boolean sucesso = dao.alterar(aut);
            
            if (sucesso) {
                response.sendRedirect(request.getContextPath() + "/escola/listar-autorizacoes.jsp"); 
            } else {
                response.sendRedirect(request.getContextPath() + "/escola/listar-autorizacoes.jsp?erro=1");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            // Em caso de erro de data ou SQL
            response.sendRedirect(request.getContextPath() + "/escola/listar-autorizacoes.jsp?erro=2");
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
