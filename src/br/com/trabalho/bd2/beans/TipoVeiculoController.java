package br.com.trabalho.bd2.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.trabalho.bd2.dao.TipoVeiculoDAO;
import br.com.trabalho.bd2.jsf.util.FacesUtil;
import br.com.trabalho.bd2.model.TipoVeiculo;

@ManagedBean
@ViewScoped
public class TipoVeiculoController implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoVeiculo tipoVeiculo;

	private TipoVeiculo tipoVeiculoSelecionado;

	private List<TipoVeiculo> tipoVeiculos;
	
	private TipoVeiculoDAO tipoVeiculoDAO;
	
	@PostConstruct
	public void inicializar() {
		limpar();
		
		tipoVeiculoDAO = new TipoVeiculoDAO();
		
		try {
			tipoVeiculos = tipoVeiculoDAO.buscarTodos();
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao iniciar. " +e.getMessage());
			e.printStackTrace();
		}

	}

	public void salvar() {
		try {

			if (tipoVeiculo.getCodTv() == null) {
				tipoVeiculoDAO.salvar(tipoVeiculo);
				FacesUtil.addSuccessMessage("Tipo Veiculo Salvo com sucesso!");
			} else {
				tipoVeiculoDAO.editar(tipoVeiculo);
				FacesUtil.addSuccessMessage("Tipo Veiculo Atualizado com sucesso!");
			}

		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		this.limpar();
	}

	public void excluir() throws Exception {
		try {
			tipoVeiculoDAO.excluir(tipoVeiculoSelecionado);
			this.tipoVeiculos.remove(tipoVeiculoSelecionado);
			FacesUtil.addSuccessMessage("Tipo Veiculo excluído com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		tipoVeiculos = tipoVeiculoDAO.buscarTodos();
	}

	public void limpar() {
		this.tipoVeiculo = new TipoVeiculo();
	}

	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public TipoVeiculo getTipoVeiculoSelecionado() {
		return tipoVeiculoSelecionado;
	}

	public void setTipoVeiculoSelecionado(TipoVeiculo tipoVeiculoSelecionado) {
		this.tipoVeiculoSelecionado = tipoVeiculoSelecionado;
	}

	public List<TipoVeiculo> getTipoVeiculos() {
		return tipoVeiculos;
	}

	public void setTipoVeiculos(List<TipoVeiculo> tipoVeiculos) {
		this.tipoVeiculos = tipoVeiculos;
	}

}
