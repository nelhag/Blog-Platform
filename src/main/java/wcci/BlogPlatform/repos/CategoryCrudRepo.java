package wcci.BlogPlatform.repos;

import org.springframework.data.repository.CrudRepository;

import wcci.BlogPlatform.models.Category;

public interface CategoryCrudRepo extends CrudRepository<Category, Long> {

}
