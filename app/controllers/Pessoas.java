package controllers;

import java.util.List;

import models.Departamento;
import models.Pessoa;
import models.Status;
import play.data.validation.Valid;
import play.data.validation.Validation;
import play.db.jpa.JPABase;
import play.mvc.Controller;
import play.mvc.With;
import security.Administrador;
import security.Seguranca;

@With(Seguranca.class)
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
		
		List<Departamento> diretoriasPessoa = Departamento.find("diretor.id = ?1", pessoa.id).fetch();
		int quantidadeDiretorias = diretoriasPessoa.size();
		
		int quantidadeColaboradores = 0;
		for (Departamento d: diretoriasPessoa) {
			quantidadeColaboradores += d.colaboradores;
		}
		
		render(pessoa, diretoriasPessoa, 
				quantidadeDiretorias,
				quantidadeColaboradores);
	}
	
	public static void verFoto(Long id) {
		Pessoa pessoa = Pessoa.findById(id);
		response.setContentTypeIfNotSet(pessoa.foto.type());
		renderBinary(pessoa.foto.get());
	}
	
	public static void salvar(@Valid Pessoa pessoa) {
		if (validation.hasErrors()) {
			Pessoa p = pessoa;
			List<Departamento> departamentos = Departamento.findAll();
			renderTemplate("Pessoas/form.html", p, departamentos);
		}
		
		pessoa.nome = pessoa.nome.toUpperCase();
		pessoa.email = pessoa.email.toLowerCase();
		pessoa.save();
		flash.success("Pessoa cadastrada com sucesso!");

		listar(null);
	}
	
	@Administrador
	public static void remover(Long id) {
		Pessoa qualquerNome = Pessoa.findById(id);
		qualquerNome.status = Status.INATIVO;
		qualquerNome.save();
		
		flash.success("Pessoa removida com sucesso!");
		listar(null);
	}

}
