package wcci.BlogPlatform.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Post {

	@Id
	@GeneratedValue
	@Column(name = "post_id")
	private Long id;

	private String title;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "publish_date")
	Date publishDate = new Date();

	@Lob
	private String body;

	@ManyToOne
	Category category;

	@ManyToMany(mappedBy = "posts")
	private Collection<Author> authors = new ArrayList<Author>();

	// @ManyToMany(cascade = { CascadeType.PERSIST })
	// @ManyToMany(cascade = { CascadeType.ALL })
	@ManyToMany
	@JoinTable(name = "JOIN_POST_TAG", joinColumns = { @JoinColumn(name = "post_id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id") })
	private Collection<Tag> tags = new ArrayList<Tag>();

	protected Post()
		{
		}

	public void addTag(Tag tag)
		{
		tags.add(tag);
		}

	public Post(String title, String body, Category category)
		{
		super();
		this.title = title;
		this.body = body;
		this.category = category;
		}

	public Long getId()
		{
		return id;
		}

	public String getTitle()
		{
		return title;
		}

	public Date getPublishDate()
		{
		return publishDate;
		}

	public String getBody()
		{
		return body;
		}

	public Category getCategory()
		{
		return category;
		}

	public Collection<Author> getAuthors()
		{
		return authors;
		}

	public Collection<Tag> getTags()
		{
		return tags;
		}

	@Override
	public int hashCode()
		{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Post other = (Post) obj;
		if (body == null)
			{
			if (other.body != null)
				return false;
			}
		else if (!body.equals(other.body))
			return false;
		if (id == null)
			{
			if (other.id != null)
				return false;
			}
		else if (!id.equals(other.id))
			return false;
		if (publishDate == null)
			{
			if (other.publishDate != null)
				return false;
			}
		else if (!publishDate.equals(other.publishDate))
			return false;
		if (title == null)
			{
			if (other.title != null)
				return false;
			}
		else if (!title.equals(other.title))
			return false;
		return true;
		}

}
