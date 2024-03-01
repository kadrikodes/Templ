package com.demo.controller;

import com.demo.service.FreeMarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TaskController {

    private final FreeMarkerService freeMarkerService;

    @Autowired
    public TaskController(FreeMarkerService freeMarkerService) {
        this.freeMarkerService = freeMarkerService;
    }

//    @GetMapping(value = {"/", "/index"})
//    public String index(Model model) {
//        model.addAttribute("title", "title");
//        return "taskTemplate";
//    }
    @GetMapping("/showForm")
    public String showForm() {
        System.out.println("showing form...");
        return "taskForm";
    }

    @PostMapping("/submitTask")
    public String submitTask(@RequestParam String taskName, @RequestParam String taskDescription, @RequestParam String dueDate, @RequestParam String priority, Model model) {
        //We create a dataModel Map containing the form data, then use FreeMarkerService to generate the content.
        //The generated content is added to the model under the attribute "content".

        //Prepare the model data
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("taskName", taskName);
        dataModel.put("taskDescription", taskDescription);
        dataModel.put("dueDate", dueDate);
        dataModel.put("priority", priority);

        //Generate content using FreeMarker
        try {
            String content = freeMarkerService.generateContent(dataModel, "taskTemplate2.ftl");

            // Set the generated content on the clipboard
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable templateTransferable = new StringSelection(content);
            clipboard.setContents(templateTransferable, new MyClipboardOwner());

            // Add the generated content to the model
            model.addAttribute("content", content);
        } catch (Exception e) {
            e.printStackTrace();
            return "errorPage";
        }
        return "generatedTemplate";
    }

    private static class MyClipboardOwner implements ClipboardOwner {
        @Override
        public void lostOwnership(Clipboard clipboard, Transferable transferable) {
            // Handle ownership loss, if needed
        }
    }
}
