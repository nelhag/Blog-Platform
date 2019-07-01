package wcci.BlogPlatform.IntegrationTests;



	import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import wcci.BlogPlatform.controllers.TagController;
import wcci.BlogPlatform.models.Post;
import wcci.BlogPlatform.models.Tag;
import wcci.BlogPlatform.repos.PostCrudRepo;
import wcci.BlogPlatform.repos.TagCrudRepo;



	@WebMvcTest(TagController.class)

	@RunWith(SpringRunner.class)

	public class MvcTagWebLayerTest {
		
		@Autowired
		MockMvc mockMvc;
		@MockBean
	    TagCrudRepo tagRepository;
		@MockBean
		PostCrudRepo postRepository;
		@Mock
		Post mockPost;
		@Mock
		private Tag mockTag;
		
			
		@Test
		public void shouldHitAuthorEndPoint() throws Exception{
			when(tagRepository.findAll()).thenReturn(Arrays.asList());
			this.mockMvc.perform(get("/tags/")).andDo(print()).andExpect(status().isOk());

		}
			
	}

