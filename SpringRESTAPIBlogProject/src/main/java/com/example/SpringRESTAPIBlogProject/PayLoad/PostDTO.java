package com.example.SpringRESTAPIBlogProject.PayLoad;

import com.example.SpringRESTAPIBlogProject.Entity.Comment;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PostDTO {

    private long id;

    @NotEmpty
    @Size(min = 2, message = "Post title Should have at least Two characters")
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    @Size(min = 10, message = "post description should be at least 10 Characters")
    private String description;

    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PostDTO() {
    }
}
