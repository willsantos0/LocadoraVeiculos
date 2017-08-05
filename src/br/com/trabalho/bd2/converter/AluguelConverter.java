package br.com.trabalho.bd2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.trabalho.bd2.dao.ClienteDAO;
import br.com.trabalho.bd2.jsf.util.FacesUtil;
import br.com.trabalho.bd2.model.Cliente;

@FacesConverter(forClass = Cliente.class)
public class AluguelConverter implements Converter {
	
	private ClienteDAO clienteDAO = new ClienteDAO();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno = null;

		if (value != null) {

			try {
				retorno = this.clienteDAO.buscarPorId(new Integer(value));
			} catch (Exception e) {
				FacesUtil.addErrorMessage("Erro ao converter classe Cliente. " + e.getMessage());
				e.printStackTrace();
			}

		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		Cliente cliente = (Cliente) value;

		try {
			return String.valueOf(cliente.getCodC());
		} catch (Exception e) {
			return null;
		}
	}

}