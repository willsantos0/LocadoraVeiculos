package br.com.trabalho.bd2.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.trabalho.bd2.jsf.util.DataConnect;
import br.com.trabalho.bd2.model.TipoVeiculo;
import br.com.trabalho.bd2.model.Veiculo;

public class VeiculoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public Veiculo buscarPorId(String id) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();

			ps = con.prepareStatement("select placa, cor, nro_chassi, nro_motor, km_atual, tipo "
					+ "from bd2.veiculo where placa like ?");
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			Veiculo veiculo = new Veiculo();

			while (rs.next()) {

				veiculo.setPlaca(rs.getString("placa"));
				veiculo.setCor(rs.getString("cor"));
				veiculo.setNroChassi(rs.getString("nro_chassi"));
				veiculo.setNroMotor(rs.getString("nro_motor"));
				veiculo.setKmAtual(rs.getDouble("km_atual"));
				veiculo.getTipo().setCodTv(rs.getInt("tipo"));
			}
			rs.close();
			con.close();

			return veiculo;

		} catch (Exception e) {
			throw new Exception("Erro ao buscar Veiculo. " + e.getMessage());
		}

	}

	public void salvar(Veiculo veiculo) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("insert into bd2.veiculo(placa, cor, nro_chassi, nro_motor, km_atual, tipo) "
					+ "VALUES (?, ?, ?, ?, ?, ?)");

			ps.setString(1, veiculo.getPlaca());
			ps.setString(2, veiculo.getCor());
			ps.setString(3, veiculo.getNroChassi());
			ps.setString(4, veiculo.getNroMotor());
			ps.setDouble(5, veiculo.getKmAtual());
			ps.setInt(6, veiculo.getTipo().getCodTv());

			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao inserir Veiculo. " + e.getMessage());
		}

	}

	public void editar(Veiculo veiculo) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement(
					"update bd2.veiculo set cor = ?, nro_chassi = ?, nro_motor = ?, km_atual = ?, tipo = ? "
							+ "where placa like ?");

			ps.setString(1, veiculo.getCor());
			ps.setString(2, veiculo.getNroChassi());
			ps.setString(3, veiculo.getNroMotor());
			ps.setDouble(4, veiculo.getKmAtual());
			ps.setInt(5, veiculo.getTipo().getCodTv());
			ps.setString(6, veiculo.getPlaca());

			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar Veiculo. " + e.getMessage());
		}

	}

	public void excluir(Veiculo veiculo) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("delete from bd2.veiculo where placa like ?");
			ps.setString(1, veiculo.getPlaca());

			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao excluir Veiculo. " + e.getMessage());
		}

	}

	public List<Veiculo> buscarTodos() throws Exception {
		Connection con = null;

		try {
			con = DataConnect.getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("select placa, cor, nro_chassi, nro_motor, km_atual, tipo "
							+ "from bd2.veiculo order by placa");

			ResultSet rs = pstmt.executeQuery();

			List<Veiculo> veiculos = new ArrayList<Veiculo>();

			while (rs.next()) {
				Veiculo veiculo = new Veiculo();
				veiculo.setPlaca(rs.getString("placa"));
				veiculo.setCor(rs.getString("cor"));
				veiculo.setNroChassi(rs.getString("nro_chassi"));
				veiculo.setNroMotor(rs.getString("nro_motor"));
				veiculo.setKmAtual(rs.getDouble("km_atual"));
				veiculo.getTipo().setCodTv(rs.getInt("tipo"));

				veiculos.add(veiculo);
			}

			con.close();

			return veiculos;

		} catch (Exception e) {
			throw new Exception("Erro ao buscar lista de Veiculos. " + e.getMessage());
		}

	}
	
	
	
	public Veiculo buscarTipoVeiculo(Integer id) throws Exception {
		Connection con = null;

		try {
			con = DataConnect.getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("select codTv, tamanho, nroPassageiros, nroPortas, valorDiarioLocacao, valorKmRodado,"
					+ " valorFranquiaNormal, valorFranquiaReduzida, arCondicionado "
					+ "from bd2.tipoVeiculo where codTv = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			Veiculo veiculo = new Veiculo();
			
			while(rs.next()){
				
				veiculo.getTipo().setCodTv(rs.getInt("codTv"));
				veiculo.getTipo().setTamanho(rs.getString("tamanho"));
				veiculo.getTipo().setNroPassageiros(rs.getInt("nroPassageiros"));
				veiculo.getTipo().setNroPortas(rs.getInt("nroPortas"));
				veiculo.getTipo().setValorDiarioLocacao(rs.getDouble("valorDiarioLocacao"));
				veiculo.getTipo().setValorKmRodado(rs.getDouble("valorKmRodado"));
				veiculo.getTipo().setValorFranquiaNormal(rs.getDouble("valorFranquiaNormal"));
				veiculo.getTipo().setValorFranquiaReduzida(rs.getDouble("valorFranquiaReduzida"));
				veiculo.getTipo().setArCondicionado(rs.getString("arCondicionado"));
				
			}

			con.close();

			return veiculo;

		} catch (Exception e) {
			throw new Exception("Erro ao Tipo Veiculo. " + e.getMessage());
		}

	}


}
