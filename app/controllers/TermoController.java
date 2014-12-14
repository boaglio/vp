package controllers;

import java.util.List;

import models.Termo;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import type.Flash;
import util.ActionAuthenticator;

public class TermoController extends Controller {

	private static final String MSG_DEL_OK = "Termo removido com sucesso";
	private static final String MSG_ADD_OK = "Termo gravado com sucesso";
	private static final String MSG_ERRO_NO_FORM = "Foram identificados problemas no cadastro";
	private static final Form<Termo> termoForm = Form.form(Termo.class);
	private static final String ATUALIZAR = "upd";
	private static final String ADICIONAR = "add";

	public static Result lista(String filtro) {

		List<Termo> termos = Termo.find.where("lower(original) like '%" + filtro.toLowerCase() + "%' or  lower(traduzido)  like '%" + filtro.toLowerCase() + "%' or lower(contexto)  like '%" + filtro.toLowerCase() + "%'").orderBy("original").findList();

		return ok(views.html.termos.render(termos,filtro));

	}

	@Security.Authenticated(ActionAuthenticator.class)
	public static Result detalhar(Long id) {

		Termo termo = Termo.find.byId(id);
		termoForm.fill(termo);
		return ok(views.html.alterarTermo.render(termoForm,ATUALIZAR,termo));

	}

	@Security.Authenticated(ActionAuthenticator.class)
	public static Result atualizar(Termo termo) {

		return ok(views.html.detalhar.render(termo));

	}

	@Security.Authenticated(ActionAuthenticator.class)
	public static Result novo() {

		return ok(views.html.alterarTermo.render(termoForm,ADICIONAR,new Termo()));

	}

	@Security.Authenticated(ActionAuthenticator.class)
	public static Result gravar() {

		Form<Termo> form = termoForm.bindFromRequest();

		if (form.hasErrors()) {
			flash(Flash.erro.name(),MSG_ERRO_NO_FORM);
			return badRequest(views.html.alterarTermo.render(termoForm,ADICIONAR,new Termo()));
		}

		System.out.println("gravando id = " + form.get().id);
		Termo termo = form.get();
		if (form.get().id != null) {
			termo.update();
		} else {
			termo.save();
		}

		flash(Flash.sucesso.name(),MSG_ADD_OK);

		return redirect(routes.Application.index());

	}

	@Security.Authenticated(ActionAuthenticator.class)
	public static Result remover(Long id) {

		Termo.find.ref(id).delete();

		flash(Flash.sucesso.name(),MSG_DEL_OK);

		return redirect(routes.Application.index());

	}

}
