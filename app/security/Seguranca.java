package security;

import controllers.Logins;
import models.Perfil;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {

	@Before
	static void auth() {
		if (!session.contains("usuarioLogado")) {
			flash.error("Restrito para usuários autenticados!");
			Logins.form();
		}
	}

	@Before
	static void verificarAdministrador() {
		String perfil = session.get("perfilUsuario");
		Administrador possuiAnotacaoAdministrador = getActionAnnotation(Administrador.class);
		if (possuiAnotacaoAdministrador != null && !Perfil.ADMIN.name().equals(perfil)) {
			forbidden("Acesso restrito aos administradores do sistema");
		}
	}

}
