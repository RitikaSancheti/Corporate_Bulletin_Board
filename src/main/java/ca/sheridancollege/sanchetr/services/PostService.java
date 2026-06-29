package ca.sheridancollege.sanchetr.services;

import java.util.List;
import java.util.Optional;

import ca.sheridancollege.sanchetr.domain.Post;

public interface PostService {
	
	public List<Post> findAll();

    public Optional<Post> findById(Long id);

    public Post save(Post post);

    public List<Post> saveAll(List<Post> postList);

    public Post update(Long id, Post post);

    public void deleteById(Long id);

}
