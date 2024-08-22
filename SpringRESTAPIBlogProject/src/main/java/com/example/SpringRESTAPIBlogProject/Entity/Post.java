package com.example.SpringRESTAPIBlogProject.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title", nullable = false,unique = true)
    private String title;
    @Column(name = "content", nullable = false,unique = true)
    private String content;
    @Column(name = "description", nullable = false,unique = true)
    private String description;

    @OneToMany(mappedBy = "post" ,cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Post() {
    }

    public Post(long id, String title, String content, String description) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
