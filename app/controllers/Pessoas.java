package controllers;

import java.util.List;

import models.Pessoa;
import play.db.jpa.JPABase;
import play.mvc.Controller;

public class Pessoas extends Controller {
	
	public static void form() {
		Pessoa p = new Pessoa();
		render(p);
	}
	
	public static void editar(Long id) {
		Pessoa p = Pessoa.findById(id);
		renderTemplate("Pessoas/form.html", p);
	}
	
	public static void listar() {
		List<Pessoa> pessoas = Pessoa.findAll();
		render(pessoas);
	}
	
	public static void detalhar(Long id) {
		Pessoa pessoa = Pessoa.findById(id);
		render(pessoa);
	}
	
	public static void salvar(Pessoa pessoa) {
		pessoa.nome = pessoa.nome.toUpperCase();
		pessoa.email = pessoa.email.toLowerCase();
		pessoa.save();
		flash.success("Pessoa cadastrada com sucesso!");
		listar();
	}
	
	public static void remover(Long id) {
		Pessoa qualquerNome = Pessoa.findById(id);
		qualquerNome.delete();
		flash.success("Pessoa removida com sucesso!");
		listar();
	}

}
