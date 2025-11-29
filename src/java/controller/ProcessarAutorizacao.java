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
import model.DAO.RespostaAutorizacaoDAO;

/**
 *
 * @author junio
 */
@WebServlet(name = "ProcessarAutorizacao", urlPatterns = {"/ProcessarAutorizacao"})
public class ProcessarAutorizacao extends HttpServlet {

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
            out.println("<title>Servlet ProcessarAutorizacao</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessarAutorizacao at " + request.getContextPath() + "</h1>");
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

        // Recebe os parâmetros do formulário
        String idRespostaStr = request.getParameter("idResposta");
        String acao = request.getParameter("acao"); // "autorizar" ou "negar"

        if (idRespostaStr != null && acao != null) {
            int idResposta = Integer.parseInt(idRespostaStr);
            String novoStatus = "";

            if (acao.equals("autorizar")) {
                novoStatus = "Autorizado";
            } else if (acao.equals("negar")) {
                novoStatus = "Negado";
            }

            if (!novoStatus.isEmpty()) {
                try {
                    RespostaAutorizacaoDAO dao = new RespostaAutorizacaoDAO();
                    dao.atualizarStatus(idResposta, novoStatus);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // Redireciona de volta para a lista de pendentes
        // Como o item foi atualizado no banco, ele não aparecerá mais na consulta "Pendente"
        response.sendRedirect(request.getContextPath() + "/dashboard/escola/autorizacoes-pendentes.jsp");
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
