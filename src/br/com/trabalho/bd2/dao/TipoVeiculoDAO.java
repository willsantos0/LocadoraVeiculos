package br.com.trabalho.bd2.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.trabalho.bd2.jsf.util.DataConnect;
import br.com.trabalho.bd2.model.TipoVeiculo;

public class TipoVeiculoDAO implements Serializable{

	private static final long serialVersionUID = 1L;

	public TipoVeiculo buscarPorId(Integer id) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();

			ps = con.prepareStatement("select codTv, tamanho, nroPassageiros, nroPortas, valorDiarioLocacao, valorKmRodado,"
					+ " valorFranquiaNormal, valorFranquiaReduzida, arCondicionado "
								+ "from bd2.tipoVeiculo where codTv = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			
			TipoVeiculo tipoVeiculo = new TipoVeiculo();
			
			while(rs.next()){
			
			tipoVeiculo.setCodTv(rs.getInt("codTv"));
			tipoVeiculo.setTamanho(rs.getString("tamanho"));
			tipoVeiculo.setNroPassageiros(rs.getInt("nroPassageiros"));
			tipoVeiculo.setNroPortas(rs.getInt("nroPortas"));
			tipoVeiculo.setValorDiarioLocacao(rs.getDouble("valorDiarioLocacao"));
			tipoVeiculo.setValorKmRodado(rs.getDouble("valorKmRodado"));
			tipoVeiculo.setValorFranquiaNormal(rs.getDouble("valorFranquiaNormal"));
			tipoVeiculo.setValorFranquiaReduzida(rs.getDouble("valorFranquiaReduzida"));
			tipoVeiculo.setArCondicionado(rs.getString("arCondicionado"));
			}
			rs.close();
			con.close();
			
			return tipoVeiculo;

		} catch (Exception e) {
			throw new Exception("Erro ao buscar Tipo Veiculo. "+e.getMessage());
		}
		
	}

	public void salvar(TipoVeiculo tipoVeiculo) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement(
					"insert into bd2.tipoVeiculo(tamanho, nroPassageiros, nroPortas, valorDiarioLocacao, valorKmRodado, "
					+ "valorFranquiaNormal, valorFranquiaReduzida, arCondicionado) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			
			ps.setString(1, tipoVeiculo.getTamanho());
			ps.setInt(2, tipoVeiculo.getNroPassageiros());
			ps.setInt(3, tipoVeiculo.getNroPortas());
			ps.setDouble(4, tipoVeiculo.getValorDiarioLocacao());
			ps.setDouble(5, tipoVeiculo.getValorKmRodado());
			ps.setDouble(6, tipoVeiculo.getValorFranquiaNormal());
			ps.setDouble(7, tipoVeiculo.getValorFranquiaReduzida());
			ps.setString(8, tipoVeiculo.getArCondicionado());

			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao inserir Tipo Veiculo. " + e.getMessage());
		}

	}

	public void editar(TipoVeiculo tipoVeiculo) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("update bd2.tipoVeiculo set tamanho = ?, nroPassageiros = ?, nroPortas = ?, "
					+ "valorDiarioLocacao = ?, valorKmRodado = ?, valorFranquiaNormal = ?, valorFranquiaReduzida = ?, "
					+ "arCondicionado = ? " + "where codTv = ?");
			
			ps.setString(1, tipoVeiculo.getTamanho());
			ps.setInt(2, tipoVeiculo.getNroPassageiros());
			ps.setInt(3, tipoVeiculo.getNroPortas());
			ps.setDouble(4, tipoVeiculo.getValorDiarioLocacao());
			ps.setDouble(5, tipoVeiculo.getValorKmRodado());
			ps.setDouble(6, tipoVeiculo.getValorFranquiaNormal());
			ps.setDouble(7, tipoVeiculo.getValorFranquiaReduzida());
			ps.setString(8, tipoVeiculo.getArCondicionado());
			ps.setInt(9, tipoVeiculo.getCodTv());

			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar Tipo Veiculo. " + e.getMessage());
		}

	}

	public void excluir(TipoVeiculo tipoVeiculo) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("delete from bd2.tipoVeiculo where codTv = ?");
			ps.setInt(1, tipoVeiculo.getCodTv());

			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao excluir Tipo Veiculo. " + e.getMessage());
		}

	}

	public List<TipoVeiculo> buscarTodos() throws Exception {
		Connection con = null;

		try {
			con = DataConnect.getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("select codTv, tamanho, nroPassageiros, nroPortas, valorDiarioLocacao, valorKmRodado,"
					+ " valorFranquiaNormal, valorFranquiaReduzida, arCondicionado "
					+ "from bd2.tipoVeiculo");

			ResultSet rs = pstmt.executeQuery();

			List<TipoVeiculo> tipoVeiculos = new ArrayList<TipoVeiculo>();

			while(rs.next()){
				TipoVeiculo tipoVeiculo = new TipoVeiculo();
				tipoVeiculo.setCodTv(rs.getInt("codTv"));
				tipoVeiculo.setTamanho(rs.getString("tamanho"));
				tipoVeiculo.setNroPassageiros(rs.getInt("nroPassageiros"));
				tipoVeiculo.setNroPortas(rs.getInt("nroPortas"));
				tipoVeiculo.setValorDiarioLocacao(rs.getDouble("valorDiarioLocacao"));
				tipoVeiculo.setValorKmRodado(rs.getDouble("valorKmRodado"));
				tipoVeiculo.setValorFranquiaNormal(rs.getDouble("valorFranquiaNormal"));
				tipoVeiculo.setValorFranquiaReduzida(rs.getDouble("valorFranquiaReduzida"));
				tipoVeiculo.setArCondicionado(rs.getString("arCondicionado"));
				
				tipoVeiculos.add(tipoVeiculo);
			}

			con.close();

			return tipoVeiculos;

		} catch (Exception e) {
			throw new Exception("Erro ao buscar lista de Tipo Veiculos. " + e.getMessage());
		}

	}

}
