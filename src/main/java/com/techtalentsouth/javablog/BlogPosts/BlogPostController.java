package com.techtalentsouth.javablog.BlogPosts;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import antlr.collections.List;

@Controller
public class BlogPostController {
   
    @Autowired
    private BlogPostRepository blogPostRepository;
    // private static ArrayList<BlogPost> posts = new ArrayList<>();

    @GetMapping(value="/")
    public String index (BlogPost blogPost, Model model) {
        model.addAttribute("repo", blogPostRepository.findAll());
        return "blogpost/index";
    }

    @GetMapping(value = "/blog_posts/new")
    public String newBlog (BlogPost blogPost) {
        return "blogpost/new";
    }

    @PostMapping(value = "/blog_posts/new")
    public String create(BlogPost blogPost, Model model) {
        blogPostRepository.save(blogPost);
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());
            return "blogpost/result";
    }

    @GetMapping(value = "/blog_posts/update/{id}")
    public String updateBlogPost(@PathVariable Long id, Model model) {
    	Optional<BlogPost> result = blogPostRepository.findById(id);
		BlogPost blogPost = null;

		if(result.isPresent()) {
			
			blogPost = result.get();
			
		} else {
			
			throw new RuntimeException("Did not find post id" + id);
		}
		
		//Set the post as a model attribute
		model.addAttribute("blogPost", blogPost);
		//send it across to form
		return "blogpost/new";
    }

    @RequestMapping(value = "blog_posts/delete/{id}")
    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost) {

        blogPostRepository.deleteById(id);
        return "blogpost/deleted";

    }

}
