package bettapcq.exu2w2d4.repositories;

import bettapcq.exu2w2d4.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Long> {
    public Author findByNomeAndCognome(String nome, String cognome);
}
