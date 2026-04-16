package controllers;

import java.util.List;

import models.Pessoa;
import play.mvc.Controller;

public class Pessoas extends Controller {
	
	public static void form() {
		render();
	}
	
	public static void listar() {
		List<Pessoa> pessoas = Pessoa.findAll();
		render(pessoas);
	}
	
	public static void detalhar(Pessoa pessoa) {
		render(pessoa);
	}
	
	public static void salvar(Pessoa pessoa) {
		pessoa.nome = pessoa.nome.toUpperCase();
		pessoa.email = pessoa.email.toLowerCase();
		pessoa.save();
		detalhar(pessoa);
	}

}
