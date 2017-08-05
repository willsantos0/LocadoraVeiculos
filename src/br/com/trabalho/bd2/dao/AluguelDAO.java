package br.com.trabalho.bd2.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.trabalho.bd2.jsf.util.DataConnect;
import br.com.trabalho.bd2.model.Aluguel;
import br.com.trabalho.bd2.model.AluguelRel;

public class AluguelDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	public Aluguel buscarPorId(String id, Integer id2) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();

			ps = con.prepareStatement(
					"select veiculo, cliente, dataRetirada, dataDevolucao, tipoFranquia, nroCNH, dataVencimentoCNF "
							+ "from bd2.aluguel where veiculo like ? and cliente = ?");
			ps.setString(1, id);
			ps.setInt(2, id2);

			ResultSet rs = ps.executeQuery();

			Aluguel aluguel = new Aluguel();

			while (rs.next()) {

				aluguel.getVeiculo().setPlaca(rs.getString("veiculo"));
				aluguel.getCliente().setCodC(rs.getInt("cliente"));
				aluguel.setDataRetirada(rs.getDate("dataRetirada"));
				aluguel.setDataDevolucao(rs.getDate("dataDevolucao"));
				aluguel.setTipoFranquia(rs.getString("tipoFranquia"));
				aluguel.setNroCnh(rs.getString("nroCNH"));
				aluguel.setDataVencimentoCnf(rs.getDate("dataVencimentoCNF"));
			}
			rs.close();
			con.close();

			return aluguel;

		} catch (Exception e) {
			throw new Exception("Erro ao buscar Aluguel. " + e.getMessage());
		}

	}

	public void salvar(Aluguel aluguel) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();

			ps = con.prepareStatement(
					"insert into bd2.aluguel(veiculo, cliente, dataRetirada, dataDevolucao, tipoFranquia, nroCNH, dataVencimentoCNF) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?)");

			ps.setString(1, aluguel.getVeiculo().getPlaca());
			ps.setInt(2, aluguel.getCliente().getCodC());

			Date novaDataRet = new java.sql.Date(aluguel.getDataRetirada().getTime());
			ps.setDate(3, novaDataRet);

			Date novaDataDev = new java.sql.Date(aluguel.getDataDevolucao().getTime());
			ps.setDate(4, novaDataDev);

			ps.setString(5, aluguel.getTipoFranquia());
			ps.setString(6, aluguel.getNroCnh());

			Date novaDataVenc = new java.sql.Date(aluguel.getDataVencimentoCnf().getTime());
			ps.setDate(7, novaDataVenc);

			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao inserir aluguel. " + e.getMessage());
		}

	}

	public void editar(Aluguel aluguel) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("update bd2.aluguel set dataRetirada = ?, dataDevolucao = ?, tipoFranquia = ?, "
					+ "nroCNH = ?, dataVencimentoCNF = ? " + "where veiculo like ? and cliente = ?");

			Date novaDataRet = new java.sql.Date(aluguel.getDataRetirada().getTime());
			ps.setDate(1, novaDataRet);

			Date novaDataDev = new java.sql.Date(aluguel.getDataDevolucao().getTime());
			ps.setDate(2, novaDataDev);

			ps.setString(3, aluguel.getTipoFranquia());
			ps.setString(4, aluguel.getNroCnh());

			Date novaDataVenc = new java.sql.Date(aluguel.getDataVencimentoCnf().getTime());
			ps.setDate(5, novaDataVenc);

			ps.setString(6, aluguel.getVeiculo().getPlaca());
			ps.setInt(7, aluguel.getCliente().getCodC());

			ps.executeUpdate();

			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao atualizar Aluguel. " + e.getMessage());
		}

	}

	public void excluir(Aluguel aluguel) throws Exception {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("delete from bd2.aluguel where veiculo like ? and cliente = ?");
			ps.setString(1, aluguel.getVeiculo().getPlaca());
			ps.setInt(2, aluguel.getCliente().getCodC());

			ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			throw new Exception("Erro ao excluir Aluguel. " + e.getMessage());
		}

	}

	public List<Aluguel> buscarTodos() throws Exception {
		Connection con = null;

		try {
			con = DataConnect.getConnection();
			PreparedStatement pstmt = con.prepareStatement(
					"select a.veiculo, a.cliente, c.nome, a.dataRetirada, a.dataDevolucao, a.tipoFranquia, a.nroCNH, a.dataVencimentoCNF "
							+ "from bd2.aluguel a, bd2.cliente c " + "where a.cliente = c.codc "
							+ " order by a.dataRetirada");

			ResultSet rs = pstmt.executeQuery();

			List<Aluguel> aluguels = new ArrayList<Aluguel>();

			while (rs.next()) {
				Aluguel aluguel = new Aluguel();

				aluguel.getVeiculo().setPlaca(rs.getString("veiculo"));
				aluguel.getCliente().setCodC(rs.getInt("cliente"));
				aluguel.getCliente().setNome(rs.getString("nome"));
				aluguel.setDataRetirada(rs.getDate("dataRetirada"));
				aluguel.setDataDevolucao(rs.getDate("dataDevolucao"));
				aluguel.setTipoFranquia(rs.getString("tipoFranquia"));
				aluguel.setNroCnh(rs.getString("nroCNH"));
				aluguel.setDataVencimentoCnf(rs.getDate("dataVencimentoCNF"));

				aluguels.add(aluguel);
			}

			con.close();

			return aluguels;

		} catch (Exception e) {
			throw new Exception("Erro ao buscar lista de aluguéis. " + e.getMessage());
		}

	}

	public List<AluguelRel> relatorioMes(int mes, int ano) throws Exception {
		Connection con = null;

		try {
			con = DataConnect.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select case extract(month from dataretirada) "
					+ "when 1 then 'Janeiro' " 
					+ "when 2 then 'Fevereiro' " 
					+ "when 3 then 'Março' "
					+ "when 4 then 'Abril' " 
					+ "when 5 then 'Maio' " 
					+ "when 6 then 'Junho' " 
					+ "when 7 then 'Julho' "
					+ "when 8 then 'Agosto' " 
					+ "when 9 then 'Setembro' " 
					+ "when 10 then 'Outubro' "
					+ "when 11 then 'Novembro' " 
					+ "when 12 then 'Dezembro' "
					+ "end AS mes, COUNT(extract('month' from dataretirada)) AS qtd " 
					+ "from bd2.aluguel "
					+ "where extract('month' from dataretirada) in (?,?,?) and extract('year' from dataretirada)  = ? "
					+ "group by mes "
					+ "limit 3");
			pstmt.setInt(1, mes-1);
			pstmt.setInt(2, mes);
			pstmt.setInt(3, mes+1);	
			pstmt.setInt(4, ano);
			
			ResultSet rs = pstmt.executeQuery();

			List<AluguelRel> aluguels = new ArrayList<AluguelRel>();

			while (rs.next()) {
				AluguelRel aluguel = new AluguelRel();

				aluguel.setMes(rs.getString("mes"));
				aluguel.setQtdMes(rs.getInt("qtd"));

				aluguels.add(aluguel);
			}

			con.close();

			return aluguels;

		} catch (Exception e) {
			throw new Exception("Erro ao buscar relatorio de aluguéis do mes: " + e.getMessage());
		}

	}

	public List<AluguelRel> relatorioVeiculo(int mes, int ano) throws Exception {
		Connection con = null;
		
		try {
			con = DataConnect.getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("select veiculo, COUNT(extract('month' from dataretirada)) AS qtd "
							+ "from bd2.aluguel "
							+ "where extract('month' from dataretirada) = ? and extract('year' from dataretirada) = ? " 
							+ "group by veiculo "
							+ "limit 3");
			
			pstmt.setInt(1, mes);
			pstmt.setInt(2, ano);
			
			ResultSet rs = pstmt.executeQuery();

			List<AluguelRel> aluguels = new ArrayList<AluguelRel>();

			while (rs.next()) {
				AluguelRel aluguel = new AluguelRel();

				aluguel.setVeiculo(rs.getString("veiculo"));
				aluguel.setQtdMesVeiculo(rs.getInt("qtd"));

				aluguels.add(aluguel);
			}

			con.close();

			return aluguels;

		} catch (Exception e) {
			throw new Exception("Erro ao buscar relatorio de aluguéis do Ano: " + e.getMessage());
		}

	}
	
	public List<AluguelRel> relatorioMediaAnual(int ano) throws Exception {
		Connection con = null;

		try {
			con = DataConnect.getConnection();
			PreparedStatement pstmt = con.prepareStatement("select extract('year' from dataretirada) as ano, "
					+ "COUNT(extract('year' from dataretirada)) AS qtd " 
					+ "from bd2.aluguel "
					+ "where extract('year' from dataretirada) in (?,?,?) "
					+ "group by ano "
					+ "limit 3");
			pstmt.setInt(1, ano-1);
			pstmt.setInt(2, ano);
			pstmt.setInt(3, ano+1);	
			
			ResultSet rs = pstmt.executeQuery();

			List<AluguelRel> aluguels = new ArrayList<AluguelRel>();

			while (rs.next()) {
				AluguelRel aluguel = new AluguelRel();

				aluguel.setAno(rs.getInt("ano"));
				aluguel.setQtdAno(rs.getInt("qtd"));

				aluguels.add(aluguel);
			}

			con.close();

			return aluguels;

		} catch (Exception e) {
			throw new Exception("Erro ao buscar relatorio de aluguéis do mes: " + e.getMessage());
		}

	}

}
