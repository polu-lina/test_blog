package servlets;

import models.BlogPost;
import models.Posts;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class SessionServlet extends HttpServlet {
    private Posts posts;

    public SessionServlet(Posts posts){
        this.posts = posts;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println(PageGenerator.instance().getPage("blog.ftl", posts.getPosts()));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html");
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        switch (path) {
            case "/save":
                String post = savePost(request);
                if (post != null)
                    response.getWriter().println(PageGenerator.instance()
                            .addMessage("Create new note: " + post)
                            .getPage("blog.ftl", posts.getPosts()));
                else
                    response.getWriter().println(PageGenerator.instance()
                            .addMessage("Add text")
                            .getPage("blog.ftl", posts.getPosts()));
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                break;
            case "/search":
                Map<Long, BlogPost> filterPosts = searchSubstr(request);
                if (filterPosts == null)
                    response.getWriter().println(PageGenerator.instance()
                            .addMessage("Add request")
                            .getPage("blog.ftl", posts.getPosts()));
                else if (filterPosts.isEmpty())
                    response.getWriter().println(PageGenerator.instance()
                        .addMessage("Not found")
                        .getPage("blog.ftl", filterPosts));
                else response.getWriter().println(PageGenerator.instance()
                            .addMessage(filterPosts.size() + " notes found")
                        .getPage("blog.ftl", filterPosts));
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                break;
            case "/delete":
                Long index;
                try {
                    index = Long.valueOf(request.getParameter("index"));
                }catch (NumberFormatException e) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    break;
                }
                posts.deletePost(index);
                response.getWriter().println(PageGenerator.instance()
                        .getPage("blog.ftl", posts.getPosts()));
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                break;
        }
    }

    public String savePost(HttpServletRequest request){
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        if (body == null || body.isEmpty())
            return null;
        if (title == null || title.isEmpty())
            title = "";
        posts.addPost(title, body);
        return title + ' ' + body;
    }

    public Map<Long, BlogPost> searchSubstr(HttpServletRequest request){
        String substr = request.getParameter("searchPost").toLowerCase();
        if (substr == null || substr.isEmpty())
            return null;
        return posts.getPosts().values().stream().
                filter(p -> p.getTitle().toLowerCase().contains(substr) || p.getBody().toLowerCase().contains(substr))
                .collect(Collectors.toMap(BlogPost::getId, p -> p));
    }
}
