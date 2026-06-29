package ca.sheridancollege.sanchetr.bootstrapData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.sanchetr.domain.Post;
import ca.sheridancollege.sanchetr.services.PostService;

@Component
public class PostBootstrap implements CommandLineRunner {

	@Autowired
	private PostService postService;

	@Override
	public void run(String... args) throws Exception {

		if (postService.findAll().size() > 0)
			return;

		List<Post> posts = List.of(

			    new Post(null, "Alice Chen", "Spring Boot 3.x Performance Improvements",
			            "GraalVM Native Image support in Spring Boot 3.x reduces startup times to "
			            + "milliseconds and slashes memory consumption. It's a game-changer for "
			            + "serverless deployments and container efficiency.",
			            null, null),

			    new Post(null, "Marcus Webb", "Angular Signals Are Changing Everything",
			            "By replacing Zone.js with fine-grained reactivity, Angular Signals "
			            + "eliminate the need for global change detection. The new @if and @for "
			            + "syntax also makes templates much more readable and performant.",
			            null, null),

			    new Post(null, "Priya Nair", "Docker Compose for Local Development",
			            "We've moved beyond simple 'up' and 'down'. By utilizing profiles and "
			            + "health checks, our team can now orchestrate complex microservice "
			            + "dependencies locally with zero manual configuration.",
			            null, null),

			    new Post(null, "Jordan Blake", "Why We Chose H2 for Development and Testing",
			            "The ability to run in-memory with automatic schema generation makes H2 "
			            + "indispensable for TDD. We use it to ensure our JUnit tests stay fast "
			            + "and completely isolated from the production database.",
			            null, null),

			    new Post(null, "Ritika Sancheti", "RESTful Best Practices with Spring Boot",
			            "Scaling our API meant strictly adhering to HATEOAS and proper HTTP "
			            + "status codes. Using @RestControllerAdvice for global exception "
			            + "handling ensures our clients always get consistent, helpful responses.",
			            null, null)

			);

		postService.saveAll(posts);
		System.out.println(">>> PostBootstrap: Loaded " + posts.size() + " sample posts!");
	}

}
