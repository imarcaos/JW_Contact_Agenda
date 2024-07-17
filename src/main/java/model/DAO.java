package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	
	/* Módulo de Conexão */
	
	/* Parâmetros de Conexão */
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/contact_javap1";
	private String user = "root";
	private String pass = "";
	
	/* Método de Conexão */
	private Connection conexao() {
		Connection con = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, pass);
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Erro de Conexão: " + e);
			return null;
		}
	}
	
	// teste de conexão
	public void testeConexao() {
		try {
			Connection con = conexao();
			System.out.println("Conectado " + con);
			con.close();
			System.out.println("Fechada " + con);
		} catch (Exception e) {
			System.out.println("Erro Teste de Conexão: " + e);
		}
	}

}
