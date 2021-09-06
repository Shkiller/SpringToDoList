package main.controller;

import main.EntityNotFoundException;
import main.model.Case;
import main.model.ToDoListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ToDoListController {

    private final ToDoListService toDoListService;

    public ToDoListController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @PostMapping("/cases/")
    public Case add(Case newCase) {
        return toDoListService.add(newCase);
    }


    @GetMapping("/cases")
    public Iterable<Case> list() {
        return toDoListService.list();
    }


    @GetMapping("/cases/{id}")
    public ResponseEntity get(@PathVariable int id) throws EntityNotFoundException {
        return toDoListService.get(id);

    }

    @DeleteMapping("/cases")
    public void removeAll() {
        toDoListService.removeAll();
    }

    @DeleteMapping("/cases/{id}")
    public ResponseEntity remove(@PathVariable int id) throws EntityNotFoundException {
        return toDoListService.remove(id);
    }

    @PutMapping("/cases/{id}")
    public ResponseEntity updateCase(@PathVariable int id, Case newCase) throws EntityNotFoundException {
        return toDoListService.updateCase(id, newCase);
    }

}
