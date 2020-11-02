package com.techtalentsouth.javablog.BlogPosts;
import java.util.ArrayList;
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
        blogPostRepository.save(blogPost);
        posts.add(blogPost);
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
		BlogPost blogPost = null;

		if(result.isPresent()) {
			
			blogPost = result.get();
			
		} else {
			
			return "Error";
		}
		
		model.addAttribute("blogPost", blogPost);
		return "myBlog/new";
    }
}
