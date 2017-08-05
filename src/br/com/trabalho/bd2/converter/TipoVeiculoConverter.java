package br.com.trabalho.bd2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.trabalho.bd2.dao.TipoVeiculoDAO;
import br.com.trabalho.bd2.jsf.util.FacesUtil;
import br.com.trabalho.bd2.model.TipoVeiculo;

@FacesConverter(forClass = TipoVeiculo.class)
public class TipoVeiculoConverter implements Converter {
	
	private TipoVeiculoDAO tipoVeiculoDAO = new TipoVeiculoDAO();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		TipoVeiculo retorno = null;

		if (value != null) {

			try {
				retorno = this.tipoVeiculoDAO.buscarPorId(new Integer(value));
			} catch (Exception e) {
				FacesUtil.addErrorMessage("Erro ao converter classe TipoVeiculo. " + e.getMessage());
				e.printStackTrace();
			}

		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		TipoVeiculo tipoVeiculo = (TipoVeiculo) value;

		try {
			return String.valueOf(tipoVeiculo.getCodTv());
		} catch (Exception e) {
			return null;
		}
	}

}