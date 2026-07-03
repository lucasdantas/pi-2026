package controllers;

import java.util.List;

import models.Departamento;
import models.Pessoa;
import models.Status;
import play.db.jpa.JPABase;
import play.mvc.Controller;

public class Pessoas extends Controller {
	
	public static void form() {
		Pessoa p = new Pessoa();
		List<Departamento> departamentos = Departamento.findAll();
		render(p, departamentos);
	}
	
	public static void editar(Long id) {
		Pessoa p = Pessoa.findById(id);
		List<Departamento> departamentos = Departamento.findAll();
		renderTemplate("Pessoas/form.html", p, departamentos);
	}
	
	public static void listar(String termo) {
		List<Pessoa> pessoas = null;
		if (termo == null) {
			pessoas = Pessoa.find("status != ?1", Status.INATIVO).fetch();			
		} else {
			pessoas = Pessoa.find("status != ?1 and (lower(nome) like ?2 or lower(email) like ?2)", Status.INATIVO, "%"+termo.toLowerCase()+"%").fetch();
		}
		render(pessoas, termo);
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
		listar(null);
	}
	
	public static void remover(Long id) {
		Pessoa qualquerNome = Pessoa.findById(id);
		qualquerNome.status = Status.INATIVO;
		qualquerNome.save();
		
		flash.success("Pessoa removida com sucesso!");
		listar(null);
	}

}
