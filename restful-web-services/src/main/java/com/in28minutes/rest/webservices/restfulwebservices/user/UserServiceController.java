/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

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
public class UserServiceController {

	@Autowired
	private UserDaoService userDaoService;

	@GetMapping(path = "/find-all-users")
	public List<User> findAllUsers() {
		return userDaoService.findAllUsers();

	}

	@GetMapping(path = "/find-user-by-id/{id}")
	public User findUserById(@PathVariable Integer id) {
		User savedUser = userDaoService.findUserById(id);
		if (savedUser != null) {

			return savedUser;

		} else {
			throw new UserNotFoundException("id=" + id);

		}

	}

	@GetMapping(path = "/find-user-by-id-hateoas/{id}")
	public Resource<User> findUserByIdHateoas(@PathVariable Integer id) {
		User savedUser = userDaoService.findUserById(id);
		if (savedUser != null) {

			// HATEOS
			// findAllUsers
			/**
			 * In the example below we are not only returning the desired User object , but
			 * also returning a link to access all the users using HATEOAS. The returned
			 * Resource object contains the User Object and the link.
			 * 
			 **/
			// Creating the Resource object from the savedUser object
			Resource<User> resource = new Resource<User>(savedUser);

			// Creating a link builder to access the method findAllUsers
			ControllerLinkBuilder linkBuilder1 = ControllerLinkBuilder
					.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findAllUsers());
			
			ControllerLinkBuilder linkBuilder2 = ControllerLinkBuilder
					.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findUserById(2));

			
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

	@PostMapping(path = "/save-user")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.saveUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/find-user-by-id/{id}")
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

	@DeleteMapping(path = "/delete-user-by-id/{id}")
	public void deleteUserById(@PathVariable Integer id) {
		User deletedUser = userDaoService.deleteUserById(id);

		if (deletedUser == null) {
			throw new UserNotFoundException("id=" + id);
		}

	}

}
