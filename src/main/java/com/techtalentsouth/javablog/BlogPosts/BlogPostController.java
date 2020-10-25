package com.techtalentsouth.javablog.BlogPosts;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import antlr.collections.List;

@Controller
public class BlogPostController {
   
    @GetMapping(value="/")
    public String index (BlogPost blogPost, Model model) {
        model.addAttribute("posts", posts);
        return "blogpost/index";
    }

    @Autowired
    private BlogPostRepository blogPostRepository;
    private static ArrayList<BlogPost> posts = new ArrayList<>();

    // new ArrayList posts = new ArrayList<String>;

    @GetMapping(value = "/blogposts/new")
    public String newBlog (BlogPost blogPost) {
        return "blogpost/new";
    }


    private BlogPost blogPost;
    @PostMapping(value = "/blogposts")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
        blogPostRepository.save(new BlogPost(blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry()));
        posts.add(blogPost);
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());
        return "blogpost/result";
    }
}
