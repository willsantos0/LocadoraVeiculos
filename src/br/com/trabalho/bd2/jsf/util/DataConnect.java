package br.com.trabalho.bd2.jsf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnect {
	
	static String user;
	static String senha;
	
	public static void login(String user, String senha){
		DataConnect.user = user;
		DataConnect.senha = senha;
	}

	public static Connection getConnection() {
		try {
			String url = "jdbc:postgresql://localhost/postgres?user="+user+"&password="+senha;

			Connection conexao = null;

			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(url);

			return conexao;

		} catch (SQLException ex) {
			System.out.println("Erro. " + ex.getMessage());
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			System.out.println("Não foi possível encontrar a Classe!");
			ex.printStackTrace();
		}
		return null;

	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception ex) {
		}
	}

}