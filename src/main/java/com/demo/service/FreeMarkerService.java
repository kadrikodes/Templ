package com.demo.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



@Service
public class FreeMarkerService {

    private final Configuration freeMarkerConfiguration;

    @Autowired
    public FreeMarkerService(Configuration freeMarkerConfiguration) {
        this.freeMarkerConfiguration = freeMarkerConfiguration;
    }

    public String generateContent(Map<String, Object> model, String templateName) throws Exception {
        //In this method, you load the FreeMarker template using the provided templateName,
        // process it with the model (which contains the data from the form), and return the generated content as a String.

        //Load the template from the configuration
        Template template = freeMarkerConfiguration.getTemplate(templateName);

        //Use a StringWriter to output the processed template
        StringWriter writer = new StringWriter();
        template.process(model, writer);

        //Convert the writer's output to a String and return it
        return writer.toString();
    }

    public String getTemplateContentOnly(Map<String, Object> model, String templateName) throws Exception {
        // Load the template from the configuration
        Template template = freeMarkerConfiguration.getTemplate(templateName);

        // Use a StringWriter to output the processed template without HTML structure
        StringWriter writer = new StringWriter();
        template.process(model, writer);

        // Convert the writer's output to a String
        String content = writer.toString();

        // Use Jsoup to clean the HTML, preserving line breaks
        String cleanedContent = Jsoup.clean(content, Whitelist.none().addTags("br", "p", "h1"));

        // Replace specific tags with newline characters
        cleanedContent = cleanedContent.replace("<br>", "\n").replace("<p>", "\n").replace("</p>", "\n").replace("<h1>", "\n").replace("</h1>", "\n");

        return cleanedContent;
    }


}
