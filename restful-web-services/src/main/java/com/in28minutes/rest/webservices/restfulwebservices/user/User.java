/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Past;

import org.springframework.util.StringUtils;

/**
 * @author TapojitBhattacharya
 *
 */
@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Past(message = "DOB cannot be in past")
	@Column(name = "DOB")
	private Date dob;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Post> posts;

	/**
	 * 
	 */
	public User() {

	}

	/**
	 * @param id
	 * @param name
	 * @param dob
	 */
	public User(Integer id, String name, Date dob) {
		this.id = id;
		this.name = name;
		this.dob = dob;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public void addPost(Post post) {
		if (StringUtils.isEmpty(this.posts)) {
			this.posts = new ArrayList<Post>();
		}
		if (post.getUser() != this) {
			post.setUser(this);
		}

		this.posts.add(post);
	}

	/**
	 * @return the posts
	 */
	public List<Post> getPosts() {
		return posts;
	}

	/**
	 * @param posts
	 *            the posts to set
	 */
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + ", posts=" + posts + "]";
	}

}
