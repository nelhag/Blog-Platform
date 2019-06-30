package wcci.BlogPlatform.repos;

import org.springframework.data.repository.CrudRepository;

import wcci.BlogPlatform.models.Tag;

public interface TagCrudRepo extends CrudRepository<Tag, Long> {

	public Tag findByName(String name);

}
