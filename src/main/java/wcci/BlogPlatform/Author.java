package wcci.BlogPlatform;

import java.util.Collection;

public class Author {

	private Collection<Post> posts;

	private String name;

	private Long id;

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	protected Author() {
	}

	public Author(String name) {
		this.name = name;
	}

	public Collection<Post> getPosts() {
		return posts;
	}
}
