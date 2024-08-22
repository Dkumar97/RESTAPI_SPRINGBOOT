package com.example.SpringRESTAPIBlogProject.Repository;

import com.example.SpringRESTAPIBlogProject.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
