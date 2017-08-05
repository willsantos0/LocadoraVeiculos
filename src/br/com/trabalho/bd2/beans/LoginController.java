package br.com.trabalho.bd2.beans;

import java.io.Serializable;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.trabalho.bd2.dao.LoginDAO;
import br.com.trabalho.bd2.jsf.util.SessionUtils;
import br.com.trabalho.bd2.model.Usuario;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1094801825228386363L;
	
	private Usuario usuario;
	
	private LoginDAO loginDAO;
	
	@PostConstruct
	public void init(){
		this.usuario = new Usuario();
		
		this.loginDAO = new LoginDAO();
	}
	
	//validate login
	public String validateUsernamePassword() throws SQLException {
		boolean valid = loginDAO.validate(usuario.getUser(), usuario.getPwd());
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", usuario.getUser());
			return "index";
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Nome ou senha Incorreto",
							"Tente novamente"));
			return "login";
		}
	}

	//logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
