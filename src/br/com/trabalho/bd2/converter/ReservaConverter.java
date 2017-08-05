package br.com.trabalho.bd2.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.trabalho.bd2.dao.ReservaDAO;
import br.com.trabalho.bd2.jsf.util.FacesUtil;
import br.com.trabalho.bd2.model.Reserva;

@FacesConverter(forClass = Reserva.class)
public class ReservaConverter implements Converter {
	
	private ReservaDAO reservaDAO = new ReservaDAO();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Reserva retorno = null;

		if (value != null) {

			try {
				retorno = this.reservaDAO.buscarPorId(Integer.parseInt(value));
			} catch (Exception e) {
				FacesUtil.addErrorMessage("Erro ao converter classe Reserva. " + e.getMessage());
				e.printStackTrace();
			}

		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		Reserva reserva = (Reserva) value;

		try {
			return String.valueOf(reserva.getCodR());
		} catch (Exception e) {
			return null;
		}
	}

}