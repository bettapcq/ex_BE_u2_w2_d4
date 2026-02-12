package bettapcq.exu2w2d4.services;

import bettapcq.exu2w2d4.entities.Author;
import bettapcq.exu2w2d4.exceptions.BadReqException;
import bettapcq.exu2w2d4.exceptions.NotFoundException;
import bettapcq.exu2w2d4.payloads.AuthorsDTO;
import bettapcq.exu2w2d4.repositories.AuthorsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorsService {

    private final AuthorsRepository authorsRepository;

    public AuthorsService(AuthorsRepository auhtorRepository) {
        this.authorsRepository = auhtorRepository;
    }

    public Page<Author> findAll(int page, int size, String orderBy) {
        if (page < 0) page = 0;
        if (size > 100 || size < 0) size = 10;

        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.authorsRepository.findAll(pageable);
    }


    public Author findById(Long authorId) {
        return this.authorsRepository.findById(authorId)
                .orElseThrow(() -> new NotFoundException(authorId));
    }

    public Author addAuthor(AuthorsDTO payload) {
        Author found = this.authorsRepository.findByNomeAndCognome(payload.nome(), payload.cognome());

        if (found != null)
            throw new BadReqException("L'autore " + payload.nome() + " " + payload.cognome() + " è già presente!");

        Author authorToSave = new Author(payload.nome(), payload.cognome(), payload.dataDiNascita());

        Author newAuthor = this.authorsRepository.save(authorToSave);

        log.info("Il nuovo autore " + newAuthor.getNome() + " " + newAuthor.getCognome() + " è stato salvato");


        return newAuthor;
    }

    public Author findByIdAndEdit(Long authorId, AuthorsDTO payload) {
        Author found = this.authorsRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));

        found.setNome(payload.nome());
        found.setCognome(payload.cognome());
        found.setAvatar("https://ui-avatars.com/api/?name=" + payload.nome() + "+" + payload.cognome());
        found.setDataDiNascita(payload.dataDiNascita());

        this.authorsRepository.save(found);

        log.info("L'autore con id " + found.getAuthor_id() + " è stato modificato correttamente");

        return found;
    }

    public void findByIdAndDelete(Long authorId) {
        Author found = this.authorsRepository.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
        this.authorsRepository.delete(found);
    }

}
