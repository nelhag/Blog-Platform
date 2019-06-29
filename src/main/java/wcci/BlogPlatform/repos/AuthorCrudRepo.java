package wcci.BlogPlatform.repos;

import org.springframework.data.repository.CrudRepository;

import wcci.BlogPlatform.models.Author;

public interface AuthorCrudRepo extends CrudRepository<Author, Long> {

}
