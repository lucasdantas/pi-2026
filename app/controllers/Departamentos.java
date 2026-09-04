package controllers;

import java.util.List;

import models.Departamento;
import models.Pessoa;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Departamentos extends Controller {
	
	public static void listar() {
		List<Departamento> departamentos = Departamento.findAll();
		render(departamentos);
	}
	
	public static void form() {
		Departamento d = new Departamento();
		List<Pessoa> pessoas = Pessoa.findAll();
		render(d, pessoas);
	}
	
	public static void editar(Long id) {
		Departamento d = Departamento.findById(id);
		List<Pessoa> pessoas = Pessoa.findAll();
		renderTemplate("Departamentos/form.html", d, pessoas);
	}
	
	public static void salvar(Departamento departamento) {
		departamento.save();
		flash.success("Departamento cadastrado com sucesso.");
		listar();
	}

}
