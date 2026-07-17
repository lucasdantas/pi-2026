package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Pessoa;
import models.Projeto;
import play.mvc.Controller;

public class Projetos extends Controller {
	
	public static void listar() {
		List<Projeto> projetos = Projeto.findAll();
		render(projetos);
	}
	
	public static void formMembro(Long idProjeto) {
		Projeto projeto = Projeto.findById(idProjeto);
		List<Pessoa> pessoas = Pessoa.findAll();
		render(projeto, pessoas);
	}
	
	public static void removerMembro(Long idProjeto, Long idPessoa) {
		Projeto projeto = Projeto.findById(idProjeto);
		Pessoa pessoa = Pessoa.findById(idPessoa);
		
		projeto.membros.remove(pessoa);
		projeto.save();
		formMembro(idProjeto);
	}
	
	public static void salvarMembro(Long idProjeto, Long idPessoa) {
		Projeto projeto = Projeto.findById(idProjeto);
		Pessoa pessoa = Pessoa.findById(idPessoa);
		
		if (projeto.membros == null) {
			projeto.membros = new ArrayList<Pessoa>();
		}
		
		if (projeto.membros.contains(pessoa)) {
			flash.error("Essa pessoa já faz parte do projeto.");
			formMembro(idProjeto);
		}
		
		projeto.membros.add(pessoa);
		projeto.save();
		
		formMembro(idProjeto);
	}

}
