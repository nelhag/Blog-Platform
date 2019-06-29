package wcci.BlogPlatform.repos;

import org.springframework.data.repository.CrudRepository;

import wcci.BlogPlatform.models.Post;

public interface PostCrudRepo extends CrudRepository<Post, Long> {

}
