package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import play.data.validation.Email;
import play.data.validation.InPast;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class Pessoa extends Model {
	
	public String login;
	public String senha;
	
	@Required
	@MinSize(5)
	public String nome;
	
	@Email
	@Required
	public String email;
	
	@InPast
	public Date nascimento;
	
	@ManyToOne
	public Departamento departamento;
	
	@Enumerated(EnumType.STRING)
	public Perfil perfil;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	public Pessoa() {
		this.status = Status.ATIVO;
	}
	
	public static Pessoa obterUsuario(String login, String senha) {
		Pessoa pessoa = Pessoa.find("login = ?1 and senha = ?2", login, senha).first();
		return pessoa;
	}
	
}
