package models;

public class BlogPost {
    private Long id;
    private String title;
    private String body;


    public BlogPost(String title, String body, Long id) {
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public String getBody(){
        return body;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setBody(String body){
        this.body = body;
    }

    public Long getId(){
        return id;
    }
}
