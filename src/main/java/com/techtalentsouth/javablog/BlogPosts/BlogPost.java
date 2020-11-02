package com.techtalentsouth.javablog.BlogPosts;

import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="blogpost")
public class BlogPost {
    // sets the Id as the Primary Key
    @Id

    //allows the Id to be generated by the underlying database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="blogid")
        private Long id;
    @Column(name="title")
        private String title;
    @Column(name="author")
        private String author;
    @Column(name="blogentry")
        private String blogEntry;
    @Column(name="tags")
        private String tags;

    /* STRIPPED DOWN VERSION LOOKS LIKE THIS:
    
    public class BlogPost {
    
        private Long id; 
        private String title;
        private String author;
        private String blogEntry;

    */

     // THIS non-argument constructor is needed for JPA
    public BlogPost() {
    }

    public ArrayList<String> hashTags;

    public BlogPost(String title, String author, String blogEntry, ArrayList<String> hashTags) {
        this.title = title;
        this.author = author;
        this.blogEntry = blogEntry;
        this.hashTags = hashTags;
        hashTags = new ArrayList<String>(Arrays.asList(tags.split(",")));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBlogEntry() {
        return blogEntry;
    }

    public void setBlogEntry(String blogEntry) {
        this.blogEntry = blogEntry;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public ArrayList<String> getHashTags() {
        return hashTags;
    }

    public void setHashTags(ArrayList<String> hashTags) {
        this.hashTags = hashTags;
    }

    @Override
    public String toString() {
        return "BlogPost [author=" + author + ", blogEntry=" + blogEntry + ", hashTags=" + hashTags + ", id=" + id
                + ", tags=" + tags + ", title=" + title + "]";
    }

}
