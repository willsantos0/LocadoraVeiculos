package br.com.trabalho.bd2.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.trabalho.bd2.dao.ClienteDAO;
import br.com.trabalho.bd2.dao.ReservaDAO;
import br.com.trabalho.bd2.dao.TipoVeiculoDAO;
import br.com.trabalho.bd2.jsf.util.FacesUtil;
import br.com.trabalho.bd2.model.Cliente;
import br.com.trabalho.bd2.model.Reserva;
import br.com.trabalho.bd2.model.TipoVeiculo;

@ManagedBean
@ViewScoped
public class ReservaController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Reserva reserva;

	private Reserva reservaSelecionado;

	private List<Reserva> reservas = new ArrayList<Reserva>();
	
	private List<TipoVeiculo> tipoVeiculos = new ArrayList<TipoVeiculo>();
	
	private List<Cliente> clientes = new ArrayList<Cliente>();

	private ReservaDAO reservaDAO = new ReservaDAO();
	
	private TipoVeiculoDAO tipoVeiculoDAO = new TipoVeiculoDAO();
	
	private ClienteDAO clienteDAO = new ClienteDAO();

	@PostConstruct
	public void inicializar() {
		limpar();

		try {
			tipoVeiculos = tipoVeiculoDAO.buscarTodos();
		} catch (Exception e1) {
			FacesUtil.addErrorMessage("Erro ao iniciar. " + e1.getMessage());
			e1.printStackTrace();
		}

		try {
			clientes = clienteDAO.buscarTodos();
		} catch (Exception e1) {
			FacesUtil.addErrorMessage("Erro ao iniciar. " + e1.getMessage());
			e1.printStackTrace();
		}
		try {
			reservas = reservaDAO.buscarTodos();
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao iniciar. " + e.getMessage());
			e.printStackTrace();
		}

	}

	public void salvar() {
		try {

			if (reserva.getCodR() == null) {
				reservaDAO.salvar(reserva);
				FacesUtil.addSuccessMessage("Reserva salvo com sucesso!");
			} else {
				reservaDAO.editar(reserva);
				FacesUtil.addSuccessMessage("Reserva Atualizado com sucesso!");
			}

		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		this.limpar();
	}

	public void excluir() throws Exception {
		try {
			reservaDAO.excluir(reservaSelecionado);
			this.reservas.remove(reservaSelecionado);
			FacesUtil.addSuccessMessage(
					"Reserva do cliente " + reservaSelecionado.getCliente().getNome() + " excluído com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		reservas = reservaDAO.buscarTodos();
	}

	public void limpar() {
		this.reserva = new Reserva();
	}
	
	public List<SelectItem> listaTiposVeiculos() {
		List<SelectItem> lista = new ArrayList<SelectItem>();
		for (TipoVeiculo tipo : this.tipoVeiculos) {
			lista.add(new SelectItem(tipo, "T: " + tipo.getTamanho() + " | NP: "
					+ tipo.getNroPassageiros() + " | NPT: " + tipo.getNroPortas() + " | VD: " + 
					  tipo.getValorDiarioLocacao() + " | VKR: " + tipo.getValorKmRodado() + " | VFN: " +
					  tipo.getValorFranquiaNormal() + " | VFR: " +
					  tipo.getValorFranquiaReduzida() + " | AC: " + tipo.getArCondicionado()));
		}
		return lista;
	}
	
	public void buscarTipoVeiculo(Integer id) throws Exception {
		reservaSelecionado = reservaDAO.buscarTipoVeiculo(id);
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Reserva getReservaSelecionado() {
		return reservaSelecionado;
	}

	public void setReservaSelecionado(Reserva reservaSelecionado) {
		this.reservaSelecionado = reservaSelecionado;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<TipoVeiculo> getTipoVeiculos() {
		return tipoVeiculos;
	}

	public void setTipoVeiculos(List<TipoVeiculo> tipoVeiculos) {
		this.tipoVeiculos = tipoVeiculos;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	
}
