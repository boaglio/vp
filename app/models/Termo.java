package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.data.validation.Constraints;
import play.db.ebean.Model;

@Entity
@Table(name = "termo")
public class Termo extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_termo")
	public Long id;

	@Constraints.Required
	@Column(name = "ds_original")
	public String original;

	@Constraints.Required
	@Column(name = "ds_traduzido")
	public String traduzido;

	@Column(name = "ds_contexto")
	public String contexto;

	public static Model.Finder<Long,Termo> find = new Model.Finder<Long,Termo>(Long.class,Termo.class);

	@Override
	public String toString() {
		return original;
	}

}
