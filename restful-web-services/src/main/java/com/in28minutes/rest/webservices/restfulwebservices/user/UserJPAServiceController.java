/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author TapojitBhattacharya
 *
 */
@RestController
public class UserJPAServiceController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@GetMapping(path = "/jpa/find-all-users")
	public List<User> findAllUsers() {
		return userRepository.findAll();

	}

	@GetMapping(path = "/jpa/find-user-by-id/{id}")
	public User findUserById(@PathVariable Integer id) {
		Optional<User> savedUser = userRepository.findById(id);
		if (savedUser.isPresent()) {

			return savedUser.get();

		} else {
			throw new UserNotFoundException("id=" + id);

		}

	}

	@GetMapping(path = "/jpa/find-user-by-id-hateoas/{id}")
	public Resource<User> findUserByIdHateoas(@PathVariable Integer id) {
		Optional<User> savedUser = userRepository.findById(id);
		if (savedUser.isPresent()) {

			// HATEOS
			// findAllUsers
			/**
			 * In the example below we are not only returning the desired User object , but
			 * also returning a link to access all the users using HATEOAS. The returned
			 * Resource object contains the User Object and the link.
			 * 
			 **/
			// Creating the Resource object from the savedUser object
			Resource<User> resource = new Resource<User>(savedUser.get());

			// Creating a link builder to access the method findAllUsers
			ControllerLinkBuilder linkBuilder1 = ControllerLinkBuilder
					.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAllUsers());

			ControllerLinkBuilder linkBuilder2 = ControllerLinkBuilder
					.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findUserById(id));

			// Creating a link to the service /find-all-users
			Link link = linkBuilder1.withRel("all-users");
			Link link2 = linkBuilder2.withRel("self");

			resource.add(link);
			resource.add(link2);

			return resource;

		} else {
			throw new UserNotFoundException("id=" + id);

		}

	}

	@PostMapping(path = "/jpa/save-user")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/jpa/find-user-by-id/{id}")
				.buildAndExpand(savedUser.getId()).toUri();

		/*
		 * URI location =
		 * ServletUriComponentsBuilder.fromUriString("/find-user-by-id/{id}")
		 * .buildAndExpand(savedUser.getId()).toUri();
		 */
		/*
		 * URI location =
		 * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
		 * (savedUser.getId()).toUri();
		 */

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(path = "/jpa/delete-user-by-id/{id}")
	public void deleteUserById(@PathVariable Integer id) {
		userRepository.deleteById(id);

	}

	@GetMapping(path = "/jpa/find-all-users/{id}/posts")
	public List<Post> findAllPostsOfAUser(@PathVariable Integer id) {
		Optional<User> savedUser = userRepository.findById(id);
		if (!savedUser.isPresent()) {
			throw new UserNotFoundException("id=" + id);
		}

		return savedUser.get().getPosts();

	}

	@PostMapping(path = "/jpa/save-post-for-user/{id}")
	public ResponseEntity<Object> saveUserPost(@PathVariable Integer id, @RequestBody Post post) {

		Optional<User> savedUser = userRepository.findById(id);
		if (!savedUser.isPresent()) {
			throw new UserNotFoundException("id=" + id);
		}

		post.setUser(savedUser.get());

		postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/jpa/find-all-users/{id}")
				.buildAndExpand(savedUser.get().getId() + "/posts").toUri();

		return ResponseEntity.created(location).build();
	}

}
