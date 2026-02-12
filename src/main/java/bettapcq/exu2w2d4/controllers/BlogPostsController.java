package bettapcq.exu2w2d4.controllers;

import bettapcq.exu2w2d4.entities.BlogPost;
import bettapcq.exu2w2d4.exceptions.ValidationException;
import bettapcq.exu2w2d4.payloads.NewBlogPostDTO;
import bettapcq.exu2w2d4.payloads.UpdateBlogPostDTO;
import bettapcq.exu2w2d4.services.BlogPostsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/blogposts")
@Slf4j
public class BlogPostsController {

    private final BlogPostsService blogPostsService;

    @Autowired
    public BlogPostsController(BlogPostsService blogPostsService) {
        this.blogPostsService = blogPostsService;
    }

    // get all
    @GetMapping
    public Page<BlogPost> findAll(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  @RequestParam(defaultValue = "titolo") String orderBy) {
        return this.blogPostsService.findAll(page, size, orderBy);
    }


    // post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost addBlogPost(@RequestBody @Validated NewBlogPostDTO payload, BindingResult valRes) {

        if (valRes.hasErrors()) {
            List<String> errorsList = valRes.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errorsList);
        }

        return this.blogPostsService.addBlogPost(payload);
    }

    // put
    @PutMapping("/{blogPostId}")
    public BlogPost editBlogPost(@PathVariable Long blogPostId, @RequestBody @Validated UpdateBlogPostDTO payload, BindingResult valRes) {

        if (valRes.hasErrors()) {
            List<String> errorsList = valRes.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();

            throw new ValidationException(errorsList);
        }

        return this.blogPostsService.findByIdAndEdit(blogPostId, payload);
    }

    // get con id
    @GetMapping("/{blogPostId}")
    public BlogPost findById(@PathVariable Long blogPostId) {
        return this.blogPostsService.findById(blogPostId);
    }

    // delete
    @DeleteMapping("/{blogPostId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long blogPostId) {

    }

    //carica foto
    @PatchMapping("/{blogPostId}/cover")
    public BlogPost uploadImage(@RequestParam("cover_pic") MultipartFile file, @PathVariable Long blogPostId) {

        return this.blogPostsService.uploadBlogPostCover(file, blogPostId);
    }

}
