package food;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

public class MyServer {
    public static void main(String[] args) throws Exception {
        System.out.println("hello world");
        Server server = new Server(8000);
        ServletContextHandler restContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        {
            restContext.setContextPath("/");

            ServletHolder servlet = new ServletHolder(new ServletContainer());
            servlet.setInitParameter(ServerProperties.PROVIDER_PACKAGES, "food/ws"); // add in packages
            servlet.setInitOrder(1);
            restContext.addServlet(servlet, "/*"); // make endpoints that don't exist return nothing
        }

        server.setHandler(restContext);

        server.start();
    }
}
