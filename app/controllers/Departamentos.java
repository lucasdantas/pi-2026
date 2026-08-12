package controllers;

import java.util.List;

import models.Departamento;
import play.mvc.Controller;

public class Departamentos extends Controller {
	
	public static void listar() {
		List<Departamento> departamentos = Departamento.findAll();
		render(departamentos);
	}
	
	public static void form() {
		Departamento d = new Departamento();
		render(d);
	}
	
	public static void editar(Long id) {
		Departamento d = Departamento.findById(id);
		renderTemplate("Departamentos/form.html", d);
	}
	
	public static void salvar(Departamento departamento) {
		departamento.save();
		flash.success("Departamento cadastrado com sucesso.");
		listar();
	}

}
