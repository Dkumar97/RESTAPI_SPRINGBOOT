package com.example.SpringRESTAPIBlogProject.Repository;

import com.example.SpringRESTAPIBlogProject.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long id);


}
