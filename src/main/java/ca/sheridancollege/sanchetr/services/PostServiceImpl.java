package ca.sheridancollege.sanchetr.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.sanchetr.domain.Post;
import ca.sheridancollege.sanchetr.repositories.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Post> findAll() {
		// returns newest first
		return postRepository.findAllByOrderByCreatedAtDesc();
	}

	@Override
	public Optional<Post> findById(Long id) {
		return postRepository.findById(id);
	}

	@Override
	public Post save(Post post) {
		
		post.setCreatedAt(LocalDateTime.now());
		post.setUpdatedAt(LocalDateTime.now());
		return postRepository.save(post);
	}

	@Override
	public List<Post> saveAll(List<Post> postList) {
		// Setting timeStamps before saving for all post
		for (Post post : postList) {
			post.setCreatedAt(LocalDateTime.now());
			post.setUpdatedAt(LocalDateTime.now());
		}
		return postRepository.saveAll(postList);
	}

	@Override
	public Post update(Long id, Post post) {

		// Find the existing post, update its fields, set a fresh updatedAt timestamp
		return postRepository.findById(id).map(existing -> {
			existing.setTitle(post.getTitle());
			existing.setContent(post.getContent());
			existing.setAuthor(post.getAuthor());

			// Only updatedAt changes on edit - createdAt stays the same!
			existing.setUpdatedAt(LocalDateTime.now());
			return postRepository.save(existing);
		}).orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
	}

	@Override
	public void deleteById(Long id) {
		postRepository.deleteById(id);
	}

}
