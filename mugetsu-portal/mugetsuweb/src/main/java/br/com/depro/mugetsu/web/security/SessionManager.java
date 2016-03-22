package br.com.depro.mugetsu.web.security;

import org.springframework.stereotype.Component;

import br.com.depro.fw.typezero.infrastructure.security.SessionBase;

@Component
public class SessionManager extends SessionBase {

	@Override
	public String getUsernameUsuarioLogado() {
		return SessionBase.USUARIO_DESCONHECIDO;
	}

	@Override
	public Long getIdUsuarioLogado() {
		return -1L;
	}

}
