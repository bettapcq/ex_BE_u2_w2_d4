package bettapcq.exu2w2d4.repositories;

import bettapcq.exu2w2d4.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostsRepository extends JpaRepository<BlogPost, Long> {
}
