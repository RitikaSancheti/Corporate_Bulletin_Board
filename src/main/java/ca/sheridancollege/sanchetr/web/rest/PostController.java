package ca.sheridancollege.sanchetr.web.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.sanchetr.domain.Post;
import ca.sheridancollege.sanchetr.services.PostService;

//Extra functionality included:
//- GET /api/v1/posts/{id}        : Retrieve a single post by ID
//- PUT /api/v1/posts/{id}        : Edit/update an existing post (sets fresh updatedAt timestamp via service layer)
//- DELETE /api/v1/posts/{id}     : Delete a post by ID
//- All timestamps are handled in the service layer, never here in the controller
//- Posts are always returned newest first via our custom repository keyword query


@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
	
	@Autowired
    private PostService postService;

    // GET all posts - newest first
    @GetMapping
    public List<Post> findAll() {
        return postService.findAll();
    }

    // GET a single post by ID
    @GetMapping("/{id}")
    public Optional<Post> findById(@PathVariable Long id) {
        return postService.findById(id);
    }

    // POST a new post - timestamps set in service layer
    @PostMapping
    public Post save(@RequestBody Post post) {
        return postService.save(post);
    }

    // PUT - update an existing post by ID
    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post post) {
        return postService.update(id, post);
    }

    // DELETE a post by ID
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        postService.deleteById(id);
    }

}


