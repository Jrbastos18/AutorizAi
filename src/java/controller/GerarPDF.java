/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Autorizacao;
import model.DAO.AutorizacaoDAO;

/**
 * Document : Servlet para gerar PDF usando a library OpenPDF 
 * Created on : 27 de nov. de 2025 
 * Author : Zenalvo Junior
 */
@WebServlet(name = "GerarPDF", urlPatterns = {"/GerarPDF"})
public class GerarPDF extends HttpServlet {

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
            out.println("<title>Servlet GerarPDF</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GerarPDF at " + request.getContextPath() + "</h1>");
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
        // Pegando o parâmetro
        String idParam = request.getParameter("idAutorizacao");
        if (idParam == null) {
            response.sendError(400, "ID da autorização não informado.");
            return;
        }

        try {
            int idAutorizacao = Integer.parseInt(idParam);

            // Buscar a autorização no banco
            AutorizacaoDAO autorizacaoDAO = new AutorizacaoDAO();
            Autorizacao autorizacao = autorizacaoDAO.buscarPorId(idAutorizacao);

            if (autorizacao == null) {
                response.sendError(404, "Autorização não encontrada.");
                return;
            }

            // Configurar resposta HTTP
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=autorizacao_" + idAutorizacao + ".pdf");

            OutputStream out = response.getOutputStream();

            // Criar documento PDF
            Document pdf = new Document(PageSize.A4, 40, 40, 40, 40);
            PdfWriter.getInstance(pdf, out);

            pdf.open();

            // ----------- CABEÇALHO -----------
            Paragraph cabecalho = new Paragraph("AUTORIZAÇÃO ESCOLAR", new Font(Font.HELVETICA, 18, Font.BOLD));
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            pdf.add(cabecalho);

            pdf.add(new Paragraph("\n")); // Espaço

            // Linha horizontal
            pdf.add(new Paragraph("_____________________________________________________________\n\n"));

            // ----------- INFORMAÇÕES DA AUTORIZAÇÃO -----------
            pdf.add(new Paragraph("Título:", new Font(Font.HELVETICA, 12, Font.BOLD)));
            pdf.add(new Paragraph(autorizacao.getTitulo() + "\n\n"));

            pdf.add(new Paragraph("Descrição:", new Font(Font.HELVETICA, 12, Font.BOLD)));
            pdf.add(new Paragraph(autorizacao.getDescricao() + "\n\n"));

            pdf.add(new Paragraph("Data de Envio:", new Font(Font.HELVETICA, 12, Font.BOLD)));
            pdf.add(new Paragraph(String.valueOf(autorizacao.getData_envio()) + "\n\n"));

            pdf.add(new Paragraph("Data Limite:", new Font(Font.HELVETICA, 12, Font.BOLD)));
            pdf.add(new Paragraph(String.valueOf(autorizacao.getData_limite()) + "\n\n"));

            // ----------- ÁREA DE ASSINATURA -----------
            pdf.add(new Paragraph("\n\n\n__________________________________________"));
            pdf.add(new Paragraph("Assinatura do Responsável", new Font(Font.HELVETICA, 12)));

            pdf.add(new Paragraph("\nDocumento gerado automaticamente pelo sistema.\n",
                    new Font(Font.HELVETICA, 9, Font.ITALIC)));

            pdf.close();

        } catch (Exception ex) {
            response.sendError(500, "Erro ao gerar PDF: " + ex.getMessage());
        }
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
        processRequest(request, response);
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
