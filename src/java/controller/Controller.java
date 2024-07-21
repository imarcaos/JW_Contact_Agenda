package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.DAO;
import model.JavaBeans;

/**
 *
 * @author Marcos Melo
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller", "/main", "/insert"})
public class Controller extends HttpServlet {

    DAO dao = new DAO();
    JavaBeans contato = new JavaBeans();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String action = request.getServletPath();
        if (action.equals("/main")) {
            contatos(request, response);
        } else if (action.equals("/insert")) {
            novoContato(request, response);
        } else {
            response.sendRedirect("index.html");
        }

//        // teste de conex�o
//        dao.testeConexao();
    }

    // Listar Contatos
    protected void contatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("agenda.jsp");
    }
    
    // Novo Contato
    protected void novoContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // teste de recebimento dos dados do formul�rio
        System.out.println(request.getParameter("nome"));
        System.out.println(request.getParameter("telefone"));
        System.out.println(request.getParameter("email"));
        // Armazenar na vari�veis JavaBeans
        contato.setNome(request.getParameter("nome"));
        contato.setTelefone(request.getParameter("telefone"));
        contato.setEmail(request.getParameter("email"));
    }

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
