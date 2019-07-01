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

import wcci.BlogPlatform.controllers.CategoryController;
import wcci.BlogPlatform.models.Category;
import wcci.BlogPlatform.models.Post;
import wcci.BlogPlatform.repos.CategoryCrudRepo;
import wcci.BlogPlatform.repos.PostCrudRepo;



	@WebMvcTest(CategoryController.class)

	@RunWith(SpringRunner.class)

	public class MvcCategoryWebLayerTest {
		
		@Autowired
		MockMvc mockMvc;
		@MockBean
		PostCrudRepo reviewRepository;
		@MockBean
		CategoryCrudRepo categoryRepository;
		@Mock
		Post mockPost;
		@Mock
		private Category mockCategory;
		
			
		@Test
		public void shouldHitCategoryEndPoint() throws Exception{
			when(categoryRepository.findAll()).thenReturn(Arrays.asList());
			this.mockMvc.perform(get("/categories/")).andDo(print()).andExpect(status().isOk());

		}
			
	}

