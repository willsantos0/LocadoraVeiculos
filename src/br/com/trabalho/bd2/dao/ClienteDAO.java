package br.com.trabalho.bd2.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.trabalho.bd2.jsf.util.DataConnect;
import br.com.trabalho.bd2.model.Cliente;

public class ClienteDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	public Cliente buscarPorId(Integer id) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();

			ps = con.prepareStatement("select codc, cpf, nome, endereco, datanasc, sexo, telefonefixo, telefonecelular "
								+ "from bd2.cliente where codc = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			
			Cliente cliente = new Cliente();
			
			while(rs.next()){
			
			cliente.setCodC(rs.getInt("codc"));
			cliente.setCpf(rs.getString("cpf"));
			cliente.setNome(rs.getString("nome"));
			cliente.setEndereco(rs.getString("endereco"));
			cliente.setDataNasc(rs.getDate("datanasc"));
			cliente.setSexo(rs.getString("sexo").charAt(0));
			cliente.setTelefoneFixo(rs.getString("telefonefixo"));
			cliente.setTelefoneCelular(rs.getString("telefoneCelular"));
			}
			rs.close();
			con.close();
			
			return cliente;

		} catch (Exception e) {
			throw new Exception("Erro ao buscar Cliente. "+e.getMessage());
		}
		
	}

	public void salvar(Cliente cliente) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			// ps = con.prepareStatement("insert into bd2.cliente(cpf, nome,
			// endereco, datanasc, sexo, telefonefixo, telefonecelular) "
			// + "VALUES ('"+cliente.getCpf()+"',
			// '"+cliente.getNome()+"','"+cliente.getEndereco()+"', '"+
			// cliente.getDataNasc()+"','"+cliente.getSexo()+"','"+cliente.getTelefoneFixo()+"','"+cliente.getTelefoneCelular()+"')");
			ps = con.prepareStatement(
					"insert into bd2.cliente(cpf, nome, endereco, datanasc, sexo, telefonefixo, telefonecelular) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, cliente.getCpf());
			ps.setString(2, cliente.getNome());
			ps.setString(3, cliente.getEndereco());

			Date novaData = new java.sql.Date(cliente.getDataNasc().getTime());
			ps.setDate(4, novaData);

			ps.setString(5, String.valueOf(cliente.getSexo()));
			ps.setString(6, cliente.getTelefoneFixo());
			ps.setString(7, cliente.getTelefoneCelular());

			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao inserir cliente. " + e.getMessage());
		}

	}

	public void editar(Cliente cliente) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("update bd2.cliente set cpf = ?, nome = ?, endereco = ?, datanasc = ?,"
					+ " sexo = ?, telefonefixo = ?, telefonecelular = ? " + "where codc = ?");
			ps.setString(1, cliente.getCpf());
			ps.setString(2, cliente.getNome());
			ps.setString(3, cliente.getEndereco());

			Date novaData = new java.sql.Date(cliente.getDataNasc().getTime());
			ps.setDate(4, novaData);

			ps.setDate(4, novaData);
			ps.setString(5, String.valueOf(cliente.getSexo()));
			ps.setString(6, cliente.getTelefoneFixo());
			ps.setString(7, cliente.getTelefoneCelular());
			ps.setInt(8, cliente.getCodC());

			ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar Cliente. " + e.getMessage());
		}

	}

	public void excluir(Cliente cliente) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("delete from bd2.cliente where codc = ?");
			ps.setInt(1, cliente.getCodC());

			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao excluir. " + e.getMessage());
		}

	}

	public List<Cliente> buscarTodos() throws Exception {
		Connection con = null;

		try {
			con = DataConnect.getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("select codc, cpf, nome, endereco, datanasc, sexo, telefonefixo, telefonecelular "
							+ "from bd2.cliente order by nome");

			ResultSet rs = pstmt.executeQuery();

			List<Cliente> clientes = new ArrayList<Cliente>();

			while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setCodC(rs.getInt("codc"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setDataNasc(rs.getDate("datanasc"));
				cliente.setSexo(rs.getString("sexo").charAt(0));
				cliente.setTelefoneFixo(rs.getString("telefonefixo"));
				cliente.setTelefoneCelular(rs.getString("telefonecelular"));

				clientes.add(cliente);
			}

			con.close();

			return clientes;

		} catch (Exception e) {
			throw new Exception("Erro ao buscar lista de clientes. " + e.getMessage());
		}

	}

}
