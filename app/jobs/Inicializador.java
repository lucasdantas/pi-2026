package jobs;

import java.util.Date;

import models.Departamento;
import models.Pessoa;
import models.Projeto;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {
	
	@Override
	public void doJob() throws Exception {
		
		if (Departamento.count() != 0) {
			return;
		}
		
		Departamento d1 = new Departamento();
		d1.nome = "TI";
		d1.ramal = 100;
		d1.save();
		
		Departamento d2 = new Departamento();
		d2.nome = "RH";
		d2.ramal = 200;
		d2.save();
		
		Pessoa p1 = new Pessoa();
		p1.nome = "Joao da Silva";
		p1.departamento = d2;
		p1.nascimento = new Date();
		p1.email = "joaosilva@gmail.com";
		p1.save();
		
		Pessoa p2 = new Pessoa();
		p2.nome = "Maria Joaquina";
		p2.departamento = d2;
		p2.nascimento = new Date();
		p2.email = "mj@gmail.com";
		p2.save();
		
		Projeto suap = new Projeto();
		suap.nome = "Acadêmico SUAP";
		suap.inicio = new Date();
		suap.save();
		
		Projeto apoioFardamento = new Projeto();
		apoioFardamento.nome = "Alunos devem estar fardados";
		apoioFardamento.inicio = new Date();
		apoioFardamento.save();
		
		System.out.println("Inicializador executado com sucesso!");
	}

}
