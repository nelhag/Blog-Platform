package wcci.BlogPlatform.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Author {
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;

	@ManyToMany(mappedBy = "authors")
	private Collection<Post> posts = new ArrayList<Post>();

	protected Author()
		{

		}

	public String getFirstName()
		{
		return firstName;
		}

	public String getLastName()
		{
		return lastName;
		}

	public Long getId()
		{
		return id;
		}

	public Author(String firstName, String lastName, Post... posts)
		{
		this.firstName = firstName;
		this.lastName = lastName;
		this.posts = Arrays.asList(posts);
		}

	public Collection<Post> getPosts()
		{
		return posts;
		}

	@Override
	public int hashCode()
		{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((posts == null) ? 0 : posts.hashCode());
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
		if (firstName == null)
			{
			if (other.firstName != null)
				return false;
			}
		else if (!firstName.equals(other.firstName))
			return false;
		if (id == null)
			{
			if (other.id != null)
				return false;
			}
		else if (!id.equals(other.id))
			return false;
		if (lastName == null)
			{
			if (other.lastName != null)
				return false;
			}
		else if (!lastName.equals(other.lastName))
			return false;
		if (posts == null)
			{
			if (other.posts != null)
				return false;
			}
		else if (!posts.equals(other.posts))
			return false;
		return true;
		}

}
