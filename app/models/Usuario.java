package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name = "usuario")
public class Usuario extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_usuario")
	public Long id;

	@Constraints.Required
	@Column(name = "nm_usuario")
	public String usuario;

	@Constraints.Required
	@Column(name = "ds_senha")
	public String senha;

	public static Model.Finder<Long,Usuario> find = new Model.Finder<Long,Usuario>(Long.class,Usuario.class);

	public boolean authenticate(String usuario,String senha) {
		System.out.println("Usuario tentando entrar:" + usuario);
		boolean resultado = false;
		List<Usuario> usuarios = find.where().ilike("usuario",usuario).ilike("senha",senha).findList();
		if (usuarios != null && usuarios.size() > 0) {
			System.out.println("senha ok! ");
			resultado = true;
		} else {
			System.out.println("senha errada! ");
		}

		return resultado;
	}

	@Override
	public String toString() {
		return usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
