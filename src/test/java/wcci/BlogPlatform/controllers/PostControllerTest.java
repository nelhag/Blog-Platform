package wcci.BlogPlatform.controllers;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import wcci.BlogPlatform.models.Post;
import wcci.BlogPlatform.repos.PostCrudRepo;

import wcci.BlogPlatform.models.Category;

public class PostControllerTest {

	@InjectMocks
	private PostController underTest;
	@Mock
	PostCrudRepo mockRepo;
	@Mock
	Model model;

	@Mock
	private Post postAuthor;
	@Mock
	private PostCrudRepo postRepo;
	@Mock
	Model mockModel;
	@Mock
	private Post mockPost;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	Category cat = new Category();

	@Test
	public void shouldReturnOnepost() {

		Post testPost = new Post("Title1", "This a body1", cat);
		List<Post> testPosts = Collections.singletonList(testPost);
		Mockito.when(mockRepo.findAll()).thenReturn(testPosts);
		underTest.renderAllPosts(mockModel);
		Mockito.verify(mockModel).addAttribute("posts", testPosts);

	}

	@Test
	public void shouldReturnAllPosts() {
		Category cat1 = new Category();
		Category cat2 = new Category();
		Category cat3 = new Category();
		Post testPost1 = new Post("Title1", "body1", cat1);
		Post testPost2 = new Post("Title2", "body2", cat2);
		Post testPost3 = new Post("Title3", "body3", cat3);
		List<Post> testPosts = Arrays.asList(testPost1, testPost2, testPost3);
		Mockito.when(mockRepo.findAll()).thenReturn(testPosts);
		underTest.renderAllPosts(mockModel);
		Mockito.verify(mockModel).addAttribute("posts", testPosts);

	}

	@Test
	public void displayPostsAddNewPostToModel() {
		when(postRepo.findAll()).thenReturn(Collections.singletonList(mockPost));
		underTest.renderNewPost(model);
		verify(model).addAttribute("posts", Collections.singletonList(mockPost));
	}

}
