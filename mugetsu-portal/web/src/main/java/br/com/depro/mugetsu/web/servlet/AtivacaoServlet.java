package br.com.depro.mugetsu.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import br.com.depro.fw.typezero.infrastructure.exception.ApplicationException;
import br.com.depro.mugetsu.negocio.service.security.AtivacaoService;
import br.com.depro.mugetsu.web.utils.MugetsuSpringUtils;

/**
 *
 * @author rsouza
 * @version 1.0 - Versao Inicial - 16.09.2012
 */
public class AtivacaoServlet extends HttpServlet {

    /**
     * Numero de serie da classe
     */
    private static final long serialVersionUID = 7200117510685549841L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest, HttpServletResponse)
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigoAtivacao = request.getParameter("CA");
        if (StringUtils.isNotBlank(codigoAtivacao)) {
            AtivacaoService ativacaoService = (AtivacaoService) MugetsuSpringUtils.getBean(AtivacaoService.class);
            try {
                ativacaoService.ativarContaUsuario(codigoAtivacao);
                response.sendRedirect(request.getContextPath() + "/");
            } catch (ApplicationException e) {
                throw new ServletException();
            }
        } else {
            throw new ServletException();
        }
    }
}
