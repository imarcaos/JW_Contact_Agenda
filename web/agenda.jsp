<%-- 
    Document   : agenda
    Created on : 20/07/2024, 20:07:08
    Author     : Marcos Melo
--%>

<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@page import="model.JavaBeans" %>
<%@page import="java.util.ArrayList" %>
<%
    ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
//    for (int i = 0; i < lista.size(); i++) {
//        out.println(lista.get(i).getIdcon());
//        out.println(lista.get(i).getNome());
//        out.println(lista.get(i).getTelefone());
//        out.println(lista.get(i).getEmail());
//    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Agenda de Contatos</title>
        <link rel="icon" href="assets/images/favicon.ico"/>
        <link rel="stylesheet" href="assets/css/style.css"/>
    </head>
    <body>
        <h1>Agenda de Contatos</h1>
        <a href="novo.html" class="btn1">Novo Contato</a>
        <table id="tabela">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Nome</th>
                    <th>Telefone</th>
                    <th>Email</th>
                    <th>Opções</th>
                </tr>               
            </thead>
            <tbody>
                <% for(int i = 0; i < lista.size(); i++) { %>
                <tr>
                    <td><%=lista.get(i).getIdcon()%></td>
                    <td><%=lista.get(i).getNome()%></td>
                    <td><%=lista.get(i).getTelefone()%></td>
                    <td><%=lista.get(i).getEmail()%></td>
                    <td>
                        <a href="select?idcon=<%=lista.get(i).getIdcon()%>" class="btn1">Editar</a>
                        <a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)" class="btn2">Apagar</a>
                    </td>
                </tr>                
                <% } %>                
            </tbody>            
        </table>
        <script type="text/javascript" src="assets/js/confirmador.js"></script>
    </body>
</html>
