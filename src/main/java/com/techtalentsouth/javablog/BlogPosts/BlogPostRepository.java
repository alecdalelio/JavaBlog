package com.techtalentsouth.javablog.BlogPosts;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long>{

	List<BlogPost> findByTags(String tag);
    
}
