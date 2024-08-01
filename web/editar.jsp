<%-- 
    Document   : editar
    Created on : 01/08/2024, 19:46:03
    Author     : Marcos Melo
--%>

<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Agenda de Contatos</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="assets/images/favicon.ico"/>
        <link rel="stylesheet" href="assets/css/style.css"/>
    </head>
    <body>
        <h1>Editar Contato</h1>
        <form name="frmContato" action="">
            <table>
                <tr>
                    <td>
                        <input type="text" name="idcon" id="cx3" readonly value="<% out.print(request.getAttribute("idcon")); %>" >
                    </td>	
                </tr>
                <tr>
                    <td>
                        <input type="text" name="nome" class="cx1" value="<% out.print(request.getAttribute("nome")); %>" >
                    </td>	
                </tr>
                <tr>
                    <td>
                        <input type="text" name="telefone" class="cx1" value="<% out.print(request.getAttribute("telefone")); %>" >
                    </td>	
                </tr>
                <tr>
                    <td>
                        <input type="text" name="email" class="cx1" value="<% out.print(request.getAttribute("email")); %>" >
                    </td>	
                </tr>
            </table>
            <input type="button" value="Salvar" class="btn1" onclick="validar()" >
        </form>
        <script type="text/javascript" src="assets/js/validar.js"></script>
    </body>
</html>