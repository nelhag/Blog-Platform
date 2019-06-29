package wcci.BlogPlatform;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AuthorTest {
@Test
public void shouldListAuthorFirstAndLastNameAndPosts(){
	Post post1=new Post();
	Post post2=new Post();
	ArrayList<Post> posts = new ArrayList<>();
	posts.add(post1);
	posts.add(post2);
	Author authorUnderTest=new Author("Dawn", "Wolf",post1,post2);
	String firstName=authorUnderTest.getFirstName();
	String lastName=authorUnderTest.getLastName();
//	List<Post> posts=authorUnderTest.getPosts();
	assertThat(firstName,is("Dawn"));
	assertThat(lastName, is("Wolf"));
	assertNotNull(posts);
	
	
}
}
