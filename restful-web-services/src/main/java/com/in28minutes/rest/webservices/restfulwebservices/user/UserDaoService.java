/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * @author TapojitBhattacharya
 *
 */
@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();

	private static int userCount = 3;

	static {
		users.add(new User(1, "Tapojit", new Date()));
		users.add(new User(2, "Subhajit", new Date()));
		users.add(new User(3, "Antara", new Date()));
	}

	public List<User> findAllUsers() {
		return users;

	}

	public User findUserById(Integer id) {
		User selectedUser = null;
		if (id != null) {
			for (User user : users) {
				if (user.getId().equals(id)) {
					selectedUser = user;
				}
			}
		}
		return selectedUser;

	}

	public User saveUser(User user) {
		if (user != null && user.getId() == null) {
			user.setId(++userCount);
			users.add(user);
		}
		return user;

	}

	public User deleteUserById(Integer id) {
		User selectedUser = null;
		if (id != null) {
			Iterator<User> iterator = users.iterator();
			while (iterator.hasNext()) {
				User user = (User) iterator.next();
				if (user.getId().equals(id)) {
					iterator.remove();
					selectedUser = user;
				}
			}

		}
		return selectedUser;

	}

}
