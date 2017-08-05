package br.com.trabalho.bd2.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.trabalho.bd2.dao.ClienteDAO;
import br.com.trabalho.bd2.jsf.util.FacesUtil;
import br.com.trabalho.bd2.model.Cliente;

@ManagedBean
@ViewScoped
public class ClienteController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;

	private Cliente clienteSelecionado;

	private List<Cliente> clientes;
	
	private ClienteDAO clienteDAO;
	
	@PostConstruct
	public void inicializar() {
		limpar();
		
		clienteDAO = new ClienteDAO();
		
		try {
			clientes = clienteDAO.buscarTodos();
		} catch (Exception e) {
			FacesUtil.addErrorMessage("Erro ao iniciar. " +e.getMessage());
			e.printStackTrace();
		}

	}

	public void salvar() {
		try {

			if (cliente.getCodC() == null) {
				clienteDAO.salvar(cliente);
				FacesUtil.addSuccessMessage("Cliente salvo com sucesso!");
			} else {
				clienteDAO.editar(cliente);
				FacesUtil.addSuccessMessage("Cliente Atualizado com sucesso!");
			}

		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		this.limpar();
	}

	public void excluir() throws Exception {
		try {
			clienteDAO.excluir(clienteSelecionado);
			this.clientes.remove(clienteSelecionado);
			FacesUtil.addSuccessMessage("Cliente " + clienteSelecionado.getNome() + " excluído com sucesso.");
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}

		clientes = clienteDAO.buscarTodos();
	}

	public void limpar() {
		this.cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

}
