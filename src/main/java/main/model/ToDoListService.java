package main.model;

import main.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ToDoListService {

    private final ToDoListRepository toDoListRepository;

    private ToDoListService(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    public Iterable<Case> list() {

        return toDoListRepository.findAll();
    }

    public Case add(Case newCase) {
        return toDoListRepository.save(newCase);
    }

    public ResponseEntity get(int id) throws EntityNotFoundException {

        return toDoListRepository.findById(id)
                .map(cCase -> new ResponseEntity(cCase, HttpStatus.OK))
                .orElseThrow(() -> new EntityNotFoundException("No such case"));
    }

    public void removeAll() {
        toDoListRepository.deleteAll();
    }

    public ResponseEntity remove(int id) throws EntityNotFoundException {
        return toDoListRepository.findById(id)
                .map(cCase -> {
                    toDoListRepository.delete(cCase);
                    return new ResponseEntity(HttpStatus.OK);
                }).orElseThrow(() -> new EntityNotFoundException("No such case"));
    }

    public ResponseEntity updateCase(int id, Case newCase) throws EntityNotFoundException {
        return toDoListRepository.findById(id)
                .map(cCase -> new ResponseEntity(toDoListRepository
                        .save(newCase), HttpStatus.OK))
                .orElseThrow(() -> new EntityNotFoundException("No such case"));
    }
}
