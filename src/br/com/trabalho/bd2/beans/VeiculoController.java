package br.com.trabalho.bd2.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.trabalho.bd2.dao.TipoVeiculoDAO;
import br.com.trabalho.bd2.dao.VeiculoDAO;
import br.com.trabalho.bd2.jsf.util.FacesUtil;
import br.com.trabalho.bd2.model.TipoVeiculo;
import br.com.trabalho.bd2.model.Veiculo;

@ManagedBean
@ViewScoped
public class VeiculoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Veiculo veiculo;

	private TipoVeiculo tipoVeiculo;
	private TipoVeiculo tipoVeiculoSelecionado;

	private Veiculo veiculoSelecionado;

	private List<Veiculo> veiculos;

	private List<TipoVeiculo> tipoVeiculos = new ArrayList<>();

	private VeiculoDAO veiculoDAO = new VeiculoDAO();

	private TipoVeiculoDAO tipoVeiculoDAO = new TipoVeiculoDAO();;

	@PostConstruct
	public void inicializar() {
		limpar();
		
		try {
			tipoVeiculos = tipoVeiculoDAO.buscarTodos();
		} catch (Exception e1) {
			FacesUtil.addErrorMessage("Erro ao iniciar Tipo Veiculos. " + e1.getMessage());
			e1.printStackTrace();
		}
		
		try {
			veiculos = veiculoDAO.buscarTodos();
			
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao iniciar Veiculos. " + e.getMessage());
			e.printStackTrace();
		}

	}

	public void salvar() {
		try {
			
			String s = null;
			s = veiculoDAO.buscarPorId(veiculo.getPlaca()).getPlaca();
			
			if (s == null) {
				veiculoDAO.salvar(veiculo);
				FacesUtil.addSuccessMessage("Veiculo salvo com sucesso!");
			} else {
				veiculoDAO.editar(veiculo);
				FacesUtil.addSuccessMessage("Veiculo Atualizado com sucesso!");
			}

		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		this.limpar();
	}

	public void excluir() throws Exception {
		try {
			veiculoDAO.excluir(veiculoSelecionado);
			this.veiculos.remove(veiculoSelecionado);
			FacesUtil.addSuccessMessage("Veiculo com placa: " + veiculoSelecionado.getPlaca() + " excluído com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		veiculos = veiculoDAO.buscarTodos();
	}

	public void limpar() {
		this.veiculo = new Veiculo();
		this.tipoVeiculo = new TipoVeiculo();
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
		veiculoSelecionado = veiculoDAO.buscarTipoVeiculo(id);
	}
	

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Veiculo getVeiculoSelecionado() {
		return veiculoSelecionado;
	}

	public void setVeiculoSelecionado(Veiculo veiculoSelecionado) {
		this.veiculoSelecionado = veiculoSelecionado;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public List<TipoVeiculo> getTipoVeiculos() {
		return tipoVeiculos;
	}
	
	

	public TipoVeiculo getTipoVeiculoSelecionado() {
		return tipoVeiculoSelecionado;
	}

	public void setTipoVeiculoSelecionado(TipoVeiculo tipoVeiculoSelecionado) {
		this.tipoVeiculoSelecionado = tipoVeiculoSelecionado;
	}

	public void setTipoVeiculos(List<TipoVeiculo> tipoVeiculos) {
		this.tipoVeiculos = tipoVeiculos;
	}
	
	
}
