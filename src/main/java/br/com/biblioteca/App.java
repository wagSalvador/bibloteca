package br.com.biblioteca;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import br.com.biblioteca.API.EntryPoint;

public class App {

    public static void main(String[] args) throws Exception{
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.setContextPath("/");
        System.out.println("AQUIIII");
        Server jettyServer = new Server(8185);
        jettyServer.setHandler(contextHandler);

        ServletHolder jerseyServlet = contextHandler.addServlet(org.glassfish.jersey.servlet.ServletContainer.class,"/*");
        jerseyServlet.setInitOrder(0);

        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", EntryPoint.class.getCanonicalName());

        try {
            jettyServer.start();
            jettyServer.join();
        }finally {
            jettyServer.destroy();
        }

    }
}
