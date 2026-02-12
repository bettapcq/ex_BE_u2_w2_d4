package bettapcq.exu2w2d4.services;

import bettapcq.exu2w2d4.entities.Author;
import bettapcq.exu2w2d4.entities.BlogPost;
import bettapcq.exu2w2d4.exceptions.BadReqException;
import bettapcq.exu2w2d4.exceptions.NotFoundException;
import bettapcq.exu2w2d4.payloads.NewBlogPostDTO;
import bettapcq.exu2w2d4.payloads.UpdateBlogPostDTO;
import bettapcq.exu2w2d4.repositories.AuthorsRepository;
import bettapcq.exu2w2d4.repositories.BlogPostsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BlogPostsService {

    private final BlogPostsRepository blogPostsRepository;
    private final AuthorsRepository authorsRepository;

    public BlogPostsService(BlogPostsRepository blogPostsRepository, AuthorsRepository authorsRepository) {
        this.blogPostsRepository = blogPostsRepository;
        this.authorsRepository = authorsRepository;
    }

    public Page<BlogPost> findAll(int page, int size, String orderBy) {
        if (page < 0) page = 0;
        if (size > 100 || size < 0) size = 10;

        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.blogPostsRepository.findAll(pageable);
    }


    public BlogPost findById(Long blogPostId) {
        return this.blogPostsRepository.findById(blogPostId)
                .orElseThrow(() -> new NotFoundException(blogPostId));
    }

    public BlogPost addBlogPost(NewBlogPostDTO payload) {
        if (payload == null) throw new BadReqException("AuthorId non può essere null!");
        Author found = this.authorsRepository.findById(payload.authorId()).orElseThrow(() -> new NotFoundException(payload.authorId()));
        BlogPost newBlog = new BlogPost(payload.categoria(), payload.titolo(), payload.contenuto(), payload.minLettura(), found);

        BlogPost newBlogSaved = this.blogPostsRepository.save(newBlog);

        log.info("il Blog Post " + newBlogSaved.getTitolo() + " è stato aggiunto");

        return newBlogSaved;
    }

    public BlogPost findByIdAndEdit(Long blogPostId, UpdateBlogPostDTO payload) {
        BlogPost foundBlog = this.blogPostsRepository.findById(blogPostId).orElseThrow(() -> new NotFoundException(blogPostId));

        foundBlog.setTitolo(payload.titolo());
        foundBlog.setCategoria(payload.categoria());
        foundBlog.setMinLettura(payload.minLettura());

        this.blogPostsRepository.save(foundBlog);

        log.info("il blog con id " + blogPostId + " è stato modificato");
        return foundBlog;

    }

    public void findByIdAndDelete(Long blogPostId) {
        BlogPost found = this.blogPostsRepository.findById(blogPostId).orElseThrow(() -> new NotFoundException(blogPostId));
        this.blogPostsRepository.delete(found);
    }


}
