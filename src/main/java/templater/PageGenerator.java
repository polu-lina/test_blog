package templater;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import models.BlogPost;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class PageGenerator {
    private static final String HTML_DIR = "src/templates";

    private static PageGenerator pageGenerator;
    private final Configuration cfg;
    private final Map<String, Object> notes;

    public static PageGenerator instance() {
        pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public PageGenerator addMessage(String message){
        notes.put("message", message);
        return this;
    }

    public String getPage(String filename, Map<Long, BlogPost> data) {
        notes.put("blog", data.values());
        Writer stream = new StringWriter();
        try {
            Template template = cfg.getTemplate(HTML_DIR + File.separator + filename);
            template.process(notes, stream);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
        return stream.toString();
    }

    private PageGenerator() {
        cfg = new Configuration();
        notes = new HashMap<>();
    }
}
