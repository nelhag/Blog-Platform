package wcci.BlogPlatform;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class PostTest {
	
	@Test
	public void PostShouldHaveAuthorObject() {
		Post postUnderTest = new Post("post1", "Post1 Body", testauthors, "6/27/2019", "category1", "tags1");
		String title = postUnderTest.getTitle();
		String body = postUnderTest.getBody();
		Date publishedDate = postUnderTest.getPublishDate();
		String category = postUnderTest.getCategory();
		String tags = postUnderTest.getTags();
		List <Author> authors  =postUnderTest.getAuthors();
		assertThat(title, is("post1"));
		assertThat(body, is("Post1 Body"));
		assertThat(publishedDate, is("6/27/2019"));
		assertThat(category, is("category1"));
		assertThat(tags, is("tags1"));
		assertNotNull(authors);
		
	}
}
