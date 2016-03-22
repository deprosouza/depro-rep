package br.com.depro.videoconverter;

import java.awt.Desktop;
import java.net.URI;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Main {

	public static void main(String[] args) {
		final Server server = new Server(8080);
		final ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.setErrorHandler(null);
		servletContextHandler.setContextPath("/videoconverter");

		final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("br.com.depro.videoconverter.web.config.ApplicationConfig");
		context.getEnvironment().setDefaultProfiles("dev");

		servletContextHandler.addServlet(new ServletHolder(new DispatcherServlet(context)), "/");
		servletContextHandler.addEventListener(new ContextLoaderListener(context));

		try {
			servletContextHandler.setResourceBase(new ClassPathResource("webapp").getURI().toString());
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
		

		server.setHandler(servletContextHandler);
		try {
			server.start();
			server.join();
			
			Desktop.getDesktop().browse(new URI("http://localhost:8080/videoconverter/youtube/playlisttomp3"));
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
}



