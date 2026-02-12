package bettapcq.exu2w2d4.controllers;

import bettapcq.exu2w2d4.entities.Author;
import bettapcq.exu2w2d4.payloads.AuthorsDTO;
import bettapcq.exu2w2d4.services.AuthorsService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    private final AuthorsService authorsService;

    @Autowired
    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    //get all:
    @GetMapping
    public Page<Author> findAll(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(defaultValue = "cognome") String orderBy) {

        return this.authorsService.findAll(page, size, orderBy);
    }

    //post author
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@RequestBody @Validated AuthorsDTO payload, BindingResult valRes) {
        return this.authorsService.addAuthor(payload);

        if (valRes.hasErrors()) {
            List<String> errorsList = valRes.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errorsList);
        } else {
            return this.authorsService.addAuthor(payload);

        }
    }

    //get by id
    @GetMapping("/{authorId}")
    public Author findById(@PathVariable Long authorId) {
        return this.authorsService.findById(authorId);
    }

    //put
    @PutMapping("/{authorId}")
    public Author findByIdAndEdit(@RequestBody @Validated AuthorsDTO payload, @PathVariable Long authorId, BindingResult valRes) {

        if (valRes.hasErrors()) {

        } else {
            return authorsService.findByIdAndEdit(authorId, payload);
        }
    }

    // delete by id
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long authorId) {
    }
}
