package controllers;

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

}
