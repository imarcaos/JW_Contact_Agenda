package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.DAO;
import model.JavaBeans;

/**
 *
 * @author Marcos Melo
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller", "/main", "/insert", "/select"})
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
        System.out.println(action);
        if (action.equals("/main")) {
            contatos(request, response);
        } else if (action.equals("/insert")) {
            novoContato(request, response);
        } else if (action.equals("/select")) {
            listarContato(request, response);
        } else {
            response.sendRedirect("index.html");
        }

//        // teste de conexão
//        dao.testeConexao();
    }

    // Listar Contatos
    protected void contatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Criando um objeto que irá receber os dados JavaBeans
        ArrayList<JavaBeans> lista = dao.listarContatos();
        
        // Encaminhar a lista ao documento agenda.jsp
        request.setAttribute("contatos", lista);
        RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
        rd.forward(request, response);

//        // teste de recebimento da lista - apagar
//        for (int i = 0; i < lista.size(); i++) {
//            System.out.println(lista.get(i).getIdcon());
//            System.out.println(lista.get(i).getNome());
//            System.out.println(lista.get(i).getTelefone());
//            System.out.println(lista.get(i).getEmail());
//        }
    }

    // Novo Contato
    protected void novoContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        // teste de recebimento dos dados do formulário
//        System.out.println(request.getParameter("nome"));
//        System.out.println(request.getParameter("telefone"));
//        System.out.println(request.getParameter("email"));

        // Armazenar na variáveis JavaBeans
        contato.setNome(request.getParameter("nome"));
        contato.setTelefone(request.getParameter("telefone"));
        contato.setEmail(request.getParameter("email"));

        //Invocar o método inserirContato passando o objeto contato
        dao.inserirContato(contato);

        // redirecionar para o documento agenda.jsp
        response.sendRedirect("main");
    }
    
    // Editar Contato
    protected void listarContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recebe o id do contato que será editado
        String idcon = request.getParameter("idcon");
        //System.out.println(idcon); // teste
        
        // Configurar a variável JavaBeans
        contato.setIdcon(idcon);
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
