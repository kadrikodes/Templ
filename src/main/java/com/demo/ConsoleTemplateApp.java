package com.demo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleTemplateApp {
    public static void main(String[] args) {
        // Configure FreeMarker
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(ConsoleTemplateApp.class, "/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);

        // Data model
        Map<String, Object> dataModel = new HashMap<>();

        // Input from console
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Task Name:");
        dataModel.put("taskName", scanner.nextLine());

        System.out.println("Enter Description:");
        dataModel.put("taskDescription", scanner.nextLine());

        System.out.println("Enter Due Date:");
        dataModel.put("dueDate", scanner.nextLine());

        System.out.println("Enter Priority:");
        dataModel.put("priority", scanner.nextLine());

        System.out.println("Enter Title (Mr., Ms., Dr., etc.):");
        dataModel.put("title", scanner.nextLine());

        System.out.println("Enter Name:");
        dataModel.put("name", scanner.nextLine());

        // Process template
        try {
            Template template = cfg.getTemplate("taskTemplate2.ftl");

            Writer consoleWriter = new OutputStreamWriter(System.out);
            template.process(dataModel, consoleWriter);
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
