package wcci.BlogPlatform.models;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Tag {

	@Id
	@GeneratedValue
	@Column(name = "tag_id")
	private Long id;

	@Column(name = "tag_name", unique = true, nullable = false)
	private String name;

	@ManyToMany
	@JoinTable(name = "JOIN_POST_TAG", joinColumns = { @JoinColumn(name = "tag_id") }, inverseJoinColumns = { @JoinColumn(name = "post_id") })
	private Collection<Post> posts = new ArrayList<Post>();

	protected Tag()
		{
		}

	public Tag(String name)
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

	public Collection<Post> getPosts()
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
		Tag other = (Tag) obj;
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
