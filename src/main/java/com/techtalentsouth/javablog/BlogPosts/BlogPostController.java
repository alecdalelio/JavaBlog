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
import org.springframework.web.bind.annotation.RequestMethod;
import antlr.collections.List;

@Controller
public class BlogPostController {
   
    @Autowired
    private BlogPostRepository blogPostRepository;
    private static ArrayList<BlogPost> posts = new ArrayList<>();

    @GetMapping(value="/")
    public String index (BlogPost blogPost, Model model) {
        model.addAttribute("repo", blogPostRepository.findAll());
        return "blogpost/index";
    }

    @GetMapping(value = "/blogposts/new")
    public String newBlog (BlogPost blogPost) {
        return "blogpost/new";
    }

    private BlogPost blogPost;

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

    @RequestMapping(value = "/blogpost/{id}", method = RequestMethod.GET)
    public String editPostWithId(@PathVariable Long id, BlogPost blogPost, Model model){
        Optional<BlogPost> post = blogPostRepository.findById(id);
        if (post.isPresent()) {
            BlogPost actualPost = post.get();
            model.addAttribute("blogPost", actualPost);
        }
        return "blogpost/edit";
    }

        
    @RequestMapping(value = "/blogposts/update/{id}", method = RequestMethod.POST)
    public String updateExistingPost(@PathVariable Long id, BlogPost blogPost, Model model) {
        Optional<BlogPost> post = blogPostRepository.findById(id);
        if (post.isPresent()) {
            BlogPost actualPost = post.get();
            actualPost.setTitle(blogPost.getTitle());
            actualPost.setAuthor(blogPost.getAuthor());
            actualPost.setBlogEntry(blogPost.getBlogEntry());
            blogPostRepository.save(actualPost);
            model.addAttribute("blogPost", actualPost);
        }

        return "blogpost/result";
    }

    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.DELETE) public String deletePostWithId(@PathVariable Long id, BlogPost blogPost) {
    blogPostRepository.deleteById(id);
    return "blogpost/index";
}

}
