package ca.sheridancollege.sanchetr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.sanchetr.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	// Returns all posts ordered by createdAt descending (newest first)
    List<Post> findAllByOrderByCreatedAtDesc();

}
