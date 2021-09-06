package main.controller;

import main.model.Case;
import main.model.ToDoListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController {
    private final ToDoListService toDoListService;

    public DefaultController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }


    @RequestMapping("/")
    public String index(Model model) {
        Iterable<Case> caseList = toDoListService.list();
        List<Case> cases = new ArrayList<>();
        for (var currentCase : caseList)
            cases.add(currentCase);
        model.addAttribute("cases", cases);
        return "index";
    }

    @GetMapping("/newCase")
    public String caseForm(Model model) {
        model.addAttribute("newCase", new Case());
        return "newCase";
    }

    @PostMapping("/newCase")
    public String add(@ModelAttribute Case newCase, Model model) {
        model.addAttribute("newCase", newCase);
        toDoListService.add(newCase);
        return "result";
    }
}
