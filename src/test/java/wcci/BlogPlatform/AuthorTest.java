package wcci.BlogPlatform;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class AuthorTest {
@Test
public void shouldListAuthorFirstAndLastNameAndPosts(){
	Author authorUnderTest=new Author("Dawn", "Wolf");
	String firstName=authorUnderTest.getFirstName();
	String lastName=authorUnderTest.getLastName();
	
	assertThat(firstName,is("Dawn"));
	assertThat(lastName, is("Wolf"));
	
}
}
