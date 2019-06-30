package wcci.BlogPlatform.repos;

import org.springframework.data.repository.CrudRepository;

import wcci.BlogPlatform.models.Author;
import wcci.BlogPlatform.models.Category;

public interface AuthorCrudRepo extends CrudRepository<Author, Long> {

	public Author findByName(String name);

}
