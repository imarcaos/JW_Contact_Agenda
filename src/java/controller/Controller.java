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
@WebServlet(name = "Controller", urlPatterns = {"/Controller", "/main", "/insert", "/select", "/update", "/delete"})
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
        } else if (action.equals("/update")) {
            editarContato(request, response);
        } else if (action.equals("/delete")) {
            apagarContato(request, response);
        } else {
            response.sendRedirect("index.html");
        }

//        // teste de conex�o
//        dao.testeConexao();
    }

    // Listar Contatos
    protected void contatos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Criando um objeto que ir� receber os dados JavaBeans
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
//        // teste de recebimento dos dados do formul�rio
//        System.out.println(request.getParameter("nome"));
//        System.out.println(request.getParameter("telefone"));
//        System.out.println(request.getParameter("email"));

        // Armazenar na vari�veis JavaBeans
        contato.setNome(request.getParameter("nome"));
        contato.setTelefone(request.getParameter("telefone"));
        contato.setEmail(request.getParameter("email"));

        //Invocar o m�todo inserirContato passando o objeto contato
        dao.inserirContato(contato);

        // redirecionar para o documento agenda.jsp
        response.sendRedirect("main");
    }

    // Editar Contato
    protected void listarContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recebe o id do contato que ser� editado
        String idcon = request.getParameter("idcon");
        //System.out.println(idcon); // teste

        // Configurar a vari�vel JavaBeans
        contato.setIdcon(idcon);

        //Executar o m�todo selecionarContato (DAO)
        dao.selecionarContato(contato);

        // Configurar os atributos do formul�rio com o conte�do do JavaBeans
        request.setAttribute("idcon", contato.getIdcon());
        request.setAttribute("nome", contato.getNome());
        request.setAttribute("telefone", contato.getTelefone());
        request.setAttribute("email", contato.getEmail());

        // Encaminhar para o documento editar.jsp
        RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
        rd.forward(request, response);

//        // teste de recebimento
//        System.out.println(contato.getIdcon());
//        System.out.println(contato.getNome());
//        System.out.println(contato.getTelefone());
//        System.out.println(contato.getEmail());
    }

    // Atualizar Contato
    protected void editarContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Configurar as vari�veis JavaBeans
        contato.setIdcon(request.getParameter("idcon"));
        contato.setNome(request.getParameter("nome"));
        contato.setTelefone(request.getParameter("telefone"));
        contato.setEmail(request.getParameter("email"));
        dao.alterarContato(contato);

        // redirecionar para o documento agenda.jsp (com as atualiza��es feitas)
        response.sendRedirect("main");
//        // teste de recebimento dos dados do formul�rio
//        System.out.println(request.getParameter("idcon"));
//        System.out.println(request.getParameter("nome"));
//        System.out.println(request.getParameter("telefone"));
//        System.out.println(request.getParameter("email"));
    }
    
    // Apagar Contato
    protected void apagarContato(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recebe o id do contato que ser� elimidado (validador.js)
        String idcon = request.getParameter("idcon");
        //System.out.println(idcon); // teste
        
        // Configurar a vari�vel idcon JavaBeans
        contato.setIdcon(idcon);

        //Executar o m�todo eliminarContato (DAO)
        dao.eliminarContato(contato);
        
        // redirecionar para o documento agenda.jsp (com as atualiza��es feitas)
        response.sendRedirect("main");

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
