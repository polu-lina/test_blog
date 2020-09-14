package main;

import models.Posts;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SessionServlet;

public class Main {
    public static void main(String args[]) {
        int port = 8080;
        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        Posts posts = new Posts();
        context.addServlet(new ServletHolder(new SessionServlet(posts)), "/*");
        server.setHandler(context);

//        String url = "jdbc:mysql://localhost:3306/mydb";
//        String user = "user";
//        String password = "password";

        try {
//            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
//            try (Connection conn = DriverManager.getConnection(url, user, password)) {
//                System.out.println("Connection to bd");
//            }
            server.start();
            System.out.println("Listening port : " + port );

            server.join();

        } catch (Exception e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }
}
