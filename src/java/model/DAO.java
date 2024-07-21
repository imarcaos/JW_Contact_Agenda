package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author Marcos Melo
 */
public class DAO {

    /* M�dulo de Conex�o */
 /* Par�metros de Conex�o */
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/dbagenda";
    private String user = "root";
    private String pass = "";

    /* M�todo de Conex�o */
    private Connection conexao() {
        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
            return con;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro de Conex�o: " + e);
            return null;
        }
    }

    /* CRUD CREATE */
    public void inserirContato(JavaBeans contato) {
        PreparedStatement ps;
        try {
            String create = "INSERT INTO contatos (nome, telefone, email) VALUES (?, ? ,?)";
            // abrir a conex�o
            Connection con = conexao();

            // preparar a query para execu��o no BD
            ps = con.prepareStatement(create);

            // adicionar os par�metros (?)
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getTelefone());
            ps.setString(3, contato.getEmail());

            // executar a query
            ps.executeUpdate();

            // fechar a conex�o com a BD
            con.close();

        } catch (SQLException e) {
            System.out.println("Erro ao Inserir Dados: " + e);
        }

    }

//    // teste de conex�o - para ser removido
//    public void testeConexao() {
//        try {
//            Connection con = conexao();
//            System.out.println("Conectado " + con);
//            con.close();
//            System.out.println("Fechada " + con);
//        } catch (SQLException e) {
//            System.out.println("Erro Teste de Conex�o: " + e);
//        }
//    }
}
