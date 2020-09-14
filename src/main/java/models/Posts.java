package models;

import java.util.HashMap;
import java.util.Map;

public class Posts {
    private Map<Long, BlogPost> posts;
    private Long id;

    public Posts(){
        posts = new HashMap<>();
        id = 1L;
        posts.put(0L, new BlogPost("First", "Try writing something", 0L));
    }

    public void addPost(String title, String body){
        posts.put(id, new BlogPost(title, body, id));
        id += 1;
    }

    public void deletePost(Long id){
        posts.remove(id);
    }

    public Map<Long, BlogPost> getPosts() {
        return posts;
    }

    public BlogPost getPostById(Integer id) {
        return posts.get(id);
    }
}
