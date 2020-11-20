package com.techtalentsouth.javablog.BlogPosts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class BlogPostController {
   
    @Autowired
    private BlogPostRepository blogPostRepository;
    private static ArrayList<BlogPost> posts = new ArrayList<>();

    @GetMapping(value="/")
    public String index (BlogPost blogPost, Model model) {
        posts.removeAll(posts);

        for(BlogPost postFromDB : blogPostRepository.findAll()) {
            posts.add(postFromDB);
        }

        model.addAttribute("posts", posts);
        return "myBlog/index";
    }

    @PostMapping(value = "/donuts")
    public String create(BlogPost blogPost, Model model) {
        
        System.out.println(blogPost);
        ArrayList<String> tagsList = new ArrayList<String>(Arrays.asList(blogPost.getTags().split(",")));
        System.out.println(tagsList);
        System.out.println(tagsList.get(1));
        blogPostRepository.save(blogPost);
        posts.add(blogPost);

        // all effects HTML page
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());
        model.addAttribute("tags", blogPost.getTags());
            return "myBlog/result";
    }


    @GetMapping(value = "/blogpost/new")
    public String newBlog(BlogPost blogPost) {
        return "myBlog/new";
    }

    @RequestMapping(value = "blogpost/delete/{id}")
    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost) {
        blogPostRepository.deleteById(id);
        return "myBlog/deleted";
    }

    @RequestMapping(value = "/blogpost/edit/{id}")
    public String updateBlogPost(@PathVariable Long id, Model model) {
    	Optional<BlogPost> result = blogPostRepository.findById(id);
		BlogPost blogEdited = null;

		if(result.isPresent()) {
			
			blogEdited = result.get();
			
		} else {
			
            return "error";
        
		}
		
		model.addAttribute("blogPost", blogEdited);
		return "myBlog/new";
    }

    @GetMapping(value = "/tags/{tag}")
    public String myTag(@PathVariable(value = "tag") String tag, Model model) {
        
        List<BlogPost> blogPostsTagged = blogPostRepository.findByTags(tag);
        model.addAttribute("blogPostsTagged", blogPostsTagged);
        model.addAttribute("myAwesomeTag", tag);
        return "tags";
    }
    
}
