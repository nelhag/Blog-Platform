package wcci.BlogPlatform.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	@Lob
	private String body;

	@ManyToMany
	private Collection<Author> authors = new ArrayList<Author>();

	Date publishDate = new Date();
	String category;
	String tags;

	protected Post()
		{

		}

	public Post(String title, String body, Date publishDate, String category, String tags)
		{
		this.title = title;
		this.body = body;
		this.publishDate = publishDate;
		this.category = category;
		this.tags = tags;
		}

	public String getTitle()
		{
		return title;
		}

	public String getBody()
		{
		return body;
		}

	public Collection<Author> getAuthors()
		{
		return authors;
		}

	public Date getPublishDate()
		{
		return publishDate;
		}

	public String getCategory()
		{
		return category;
		}

	public String getTags()
		{
		return tags;
		}

	@Override
	public int hashCode()
		{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authors == null) ? 0 : authors.hashCode());
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((publishDate == null) ? 0 : publishDate.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
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
		if (id == null)
			{
			if (other.id != null)
				return false;
			}
		else if (!id.equals(other.id))
			return false;
		return true;
		}

}
