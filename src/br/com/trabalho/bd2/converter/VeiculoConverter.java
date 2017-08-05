package br.com.trabalho.bd2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.trabalho.bd2.dao.VeiculoDAO;
import br.com.trabalho.bd2.jsf.util.FacesUtil;
import br.com.trabalho.bd2.model.Veiculo;

@FacesConverter(forClass = Veiculo.class)
public class VeiculoConverter implements Converter {
	
	private VeiculoDAO veiculoDAO = new VeiculoDAO();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Veiculo retorno = null;

		if (value != null) {

			try {
				retorno = this.veiculoDAO.buscarPorId(value);
			} catch (Exception e) {
				FacesUtil.addErrorMessage("Erro ao converter classe Veiculo. " + e.getMessage());
				e.printStackTrace();
			}

		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		Veiculo veiculo = (Veiculo) value;

		try {
			return veiculo.getPlaca();
		} catch (Exception e) {
			return null;
		}
	}

}