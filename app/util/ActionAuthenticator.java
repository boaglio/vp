package util;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import type.Sessao;
import controllers.routes;

public class ActionAuthenticator extends Security.Authenticator {

	@Override
	public String getUsername(Http.Context ctx) {
		return ctx.session().get(Sessao.usuario.name());
	}

	@Override
	public Result onUnauthorized(Http.Context context) {
		return redirect(routes.Application.authenticate());
	}

}
