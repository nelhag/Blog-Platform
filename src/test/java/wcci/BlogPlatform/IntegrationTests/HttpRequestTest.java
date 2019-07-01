package wcci.BlogPlatform.IntegrationTests;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
	@Resource
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	@Test
	public void categoriesEndPointIsOk() {
		ResponseEntity<String> response = restTemplate.getForEntity("/home", String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status, is(HttpStatus.OK));
	}
	@Test
	public void reviewsEndPointIsOk() {
		ResponseEntity<String> response = restTemplate.getForEntity("/posts", String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status, is(HttpStatus.OK));
	}
	@Test
	public void categoriessEndPointIsOk() {
		ResponseEntity<String> response = restTemplate.getForEntity("/categories", String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status, is(HttpStatus.OK));
	}
	@Test
	public void authorsEndPointIsOk() {
		ResponseEntity<String> response = restTemplate.getForEntity("/authors", String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status, is(HttpStatus.OK));
	}
	@Test
	public void tagsEndPointIsOk() {
		ResponseEntity<String> response = restTemplate.getForEntity("/tags", String.class);
		HttpStatus status = response.getStatusCode();
		assertThat(status, is(HttpStatus.OK));
	}
}
