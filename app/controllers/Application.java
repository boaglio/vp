package controllers;

import static play.data.Form.form;
import models.Usuario;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import type.Flash;
import type.Sessao;

public class Application extends Controller {

	private static final String MSG_USER_PASS_ERROR = "Usuário ou senha inválida";
	private static final String MSG_LOGOUT = "Saiu do sistema com sucesso";
	private static final String MSG_TITULO = "VP - vocabulario padrao";
	private static final Form<Usuario> usuarioForm = Form.form(Usuario.class);

	public static Result index() {
		return ok(views.html.index.render(MSG_TITULO));
	}

	public static Result login() {
		return ok(views.html.login.render(form(Usuario.class)));
	}

	public static Result logout() {
		session().clear();
		flash(Flash.sucesso.name(),MSG_LOGOUT);
		return redirect(routes.Application.index());
	}

	public static Result authenticate() {

		Form<Usuario> loginForm = usuarioForm.bindFromRequest();

		if (loginForm.hasErrors()) { return badRequest(views.html.login.render(loginForm)); }

		Usuario usuarioDoForm = loginForm.get();
		System.out.println("Validando usuario:" + usuarioDoForm.usuario);

		if (usuarioDoForm != null && usuarioDoForm.authenticate(usuarioDoForm.usuario,usuarioDoForm.senha)) {

			session().clear();
			session(Sessao.usuario.name(),usuarioDoForm.usuario);
			return redirect(routes.Application.index());

		} else {

			flash(Flash.erro.name(),MSG_USER_PASS_ERROR);
			return redirect(routes.Application.login());

		}
	}

}
