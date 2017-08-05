package br.com.trabalho.bd2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.trabalho.bd2.jsf.util.DataConnect;
import br.com.trabalho.bd2.model.Usuario;

public class LoginDAO{
	
	public boolean validate(String user, String password) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			DataConnect.login(user, password);
			con = DataConnect.getConnection();
			ps = con.prepareStatement("select rolname, rolpassword from pg_authid where rolname like ? and rolpassword like ?");
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Erro no Login -->" + ex.getMessage());
			return false;
		} finally {
			DataConnect.close(con);
		}
		return false;
	}
	
	public Usuario retornarUsuarioLogado(String usuario, String senha){
		Usuario us = new Usuario();
		
		us.setUser(usuario);
		us.setPwd(senha);
		
		return us;
	}
		
}