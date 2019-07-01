package wcci.BlogPlatform.IntegrationTests;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import wcci.BlogPlatform.controllers.PostController;
import wcci.BlogPlatform.models.Author;
import wcci.BlogPlatform.models.Category;
import wcci.BlogPlatform.models.Post;
import wcci.BlogPlatform.models.Tag;
import wcci.BlogPlatform.repos.AuthorCrudRepo;
import wcci.BlogPlatform.repos.CategoryCrudRepo;
import wcci.BlogPlatform.repos.PostCrudRepo;
import wcci.BlogPlatform.repos.TagCrudRepo;


@WebMvcTest(PostController.class)

@RunWith(SpringRunner.class)
public class MvcPostWebLayerTest {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	PostCrudRepo postRepository;
	@MockBean
	CategoryCrudRepo categoryRepository;
	@MockBean
	TagCrudRepo tagRepository;
	@MockBean
	AuthorCrudRepo authorRepository;
	
	@Mock
	Post mockPost;
	@Mock
	private Category mockCategory;
	@Mock
	private Collection<Tag> mockTag;
	@Mock
	private Collection<Author> mockAuthor;
	
	@Test
	public void shouldReturnReview() throws Exception{
		Optional<Post> mockPostOptional = Optional.of(mockPost);
		when(postRepository.findById(1L)).thenReturn(mockPostOptional);
		when(mockPost.getCategory()).thenReturn(mockCategory);
		when(mockPost.getTags()).thenReturn(mockTag);
		when(mockPost.getAuthors()).thenReturn(mockAuthor);
		this.mockMvc.perform(get("/posts/1")).andDo(print()).andExpect(status().isOk());
	}	
}
