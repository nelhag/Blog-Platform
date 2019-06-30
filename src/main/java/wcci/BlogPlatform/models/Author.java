package wcci.BlogPlatform.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Author {

	@Id
	@GeneratedValue
	@Column(name = "author_id")
	private Long id;

	@Column(name = "author_name", unique = true, nullable = false)
	private String name;

	@ManyToMany
	@JoinTable(name = "author_posts", joinColumns = { @JoinColumn(name = "author_id") }, inverseJoinColumns = { @JoinColumn(name = "post_id") })
	private Set<Post> posts = new HashSet<Post>();

	protected Author()
		{
		}

	public void addPost(Post post)
		{
		getPosts().add(post);
		post.getAuthors().add(this);
		}

	public Author(String name)
		{
		this.name = name;
		}

	public Long getId()
		{
		return id;
		}

	public String getName()
		{
		return name;
		}

	public Set<Post> getPosts()
		{
		return posts;
		}

	@Override
	public int hashCode()
		{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
		}

	@Override
	public boolean equals(Object obj)
		{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (id == null)
			{
			if (other.id != null)
				return false;
			}
		else if (!id.equals(other.id))
			return false;
		if (name == null)
			{
			if (other.name != null)
				return false;
			}
		else if (!name.equals(other.name))
			return false;
		return true;
		}
}
