package br.com.trabalho.bd2.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.trabalho.bd2.jsf.util.DataConnect;
import br.com.trabalho.bd2.model.Reserva;

public class ReservaDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public Reserva buscarPorId(Integer id) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();

			ps = con.prepareStatement("select r.codR, r.dataPrevistaRetirada, r.local, r.cliente, c.nome, r.tipoVeiculo, t.tamanho "
					+ "from bd2.reserva r, bd2.cliente c, bd2.tipoVeiculo t "
					+ "where r.cliente = c.codc and r.tipoVeiculo = t.codTv and codr = ?");
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			Reserva reserva = new Reserva();

			while (rs.next()) {
				
				reserva.setCodR(rs.getInt("codR"));
				reserva.setDataPrevistaRetirada(rs.getDate("dataPrevistaRetirada"));
				reserva.setLocal(rs.getString("local"));
				reserva.getCliente().setCodC(rs.getInt("cliente"));
				reserva.getCliente().setNome(rs.getString("nome"));
				reserva.getTipoVeiculo().setCodTv(rs.getInt("tipoVeiculo"));

			}
			rs.close();
			con.close();

			return reserva;

		} catch (Exception e) {
			throw new Exception("Erro ao buscar Reserva. " + e.getMessage());
		}

	}

	public void salvar(Reserva reserva) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("insert into bd2.reserva(dataPrevistaRetirada, local, cliente, tipoVeiculo) "
					+ "VALUES (?, ?, ?, ?)");

			Date novaData = new java.sql.Date(reserva.getDataPrevistaRetirada().getTime());
			ps.setDate(1, novaData);

			ps.setString(2, reserva.getLocal());
			ps.setInt(3, reserva.getCliente().getCodC());
			ps.setInt(4, reserva.getTipoVeiculo().getCodTv());

			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao inserir reserva. " + e.getMessage());
		}

	}

	public void editar(Reserva reserva) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("update bd2.reserva set dataPrevistaRetirada = ?, local = ?, cliente = ?, "
					+ "tipoVeiculo = ? where codr = ?");

			Date novaData = new java.sql.Date(reserva.getDataPrevistaRetirada().getTime());
			ps.setDate(1, novaData);

			ps.setString(2, reserva.getLocal());
			ps.setInt(3, reserva.getCliente().getCodC());
			ps.setInt(4, reserva.getTipoVeiculo().getCodTv());
			ps.setInt(5, reserva.getCodR());

			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar Reserva. " + e.getMessage());
		}

	}

	public void excluir(Reserva reserva) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("delete from bd2.reserva where codr = ?");
			ps.setInt(1, reserva.getCodR());

			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao excluir Reserva. " + e.getMessage());
		}

	}

	public List<Reserva> buscarTodos() throws Exception {
		Connection con = null;

		try {
			con = DataConnect.getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("select r.codR, r.dataPrevistaRetirada, r.local, r.cliente, c.nome, r.tipoVeiculo "
							+ "from bd2.reserva r, bd2.cliente c where r.cliente = c.codc order by c.nome");

			ResultSet rs = pstmt.executeQuery();

			List<Reserva> reservas = new ArrayList<Reserva>();

			while (rs.next()) {
				Reserva reserva = new Reserva();
				
				reserva.setCodR(rs.getInt("codR"));
				reserva.setDataPrevistaRetirada(rs.getDate("dataPrevistaRetirada"));
				reserva.setLocal(rs.getString("local"));
				reserva.getCliente().setCodC(rs.getInt("cliente"));
				reserva.getCliente().setNome(rs.getString("nome"));
				reserva.getTipoVeiculo().setCodTv(rs.getInt("tipoVeiculo"));

				reservas.add(reserva);
			}

			con.close();

			return reservas;

		} catch (Exception e) {
			throw new Exception("Erro ao buscar lista de reservas. " + e.getMessage());
		}

	}
	
	public Reserva buscarTipoVeiculo(Integer id) throws Exception {
		Connection con = null;

		try {
			con = DataConnect.getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("select codTv, tamanho, nroPassageiros, nroPortas, valorDiarioLocacao, valorKmRodado,"
					+ " valorFranquiaNormal, valorFranquiaReduzida, arCondicionado "
					+ "from bd2.tipoVeiculo where codTv = ?");
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			Reserva reserva = new Reserva();
			
			while(rs.next()){
				
				reserva.getTipoVeiculo().setCodTv(rs.getInt("codTv"));
				reserva.getTipoVeiculo().setTamanho(rs.getString("tamanho"));
				reserva.getTipoVeiculo().setNroPassageiros(rs.getInt("nroPassageiros"));
				reserva.getTipoVeiculo().setNroPortas(rs.getInt("nroPortas"));
				reserva.getTipoVeiculo().setValorDiarioLocacao(rs.getDouble("valorDiarioLocacao"));
				reserva.getTipoVeiculo().setValorKmRodado(rs.getDouble("valorKmRodado"));
				reserva.getTipoVeiculo().setValorFranquiaNormal(rs.getDouble("valorFranquiaNormal"));
				reserva.getTipoVeiculo().setValorFranquiaReduzida(rs.getDouble("valorFranquiaReduzida"));
				reserva.getTipoVeiculo().setArCondicionado(rs.getString("arCondicionado"));
				
			}

			con.close();

			return reserva;

		} catch (Exception e) {
			throw new Exception("Erro ao Tipo Veiculo. " + e.getMessage());
		}

	}

}
