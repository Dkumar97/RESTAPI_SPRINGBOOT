package com.example.SpringRESTAPIBlogProject.Service;

import com.example.SpringRESTAPIBlogProject.Entity.Comment;
import com.example.SpringRESTAPIBlogProject.PayLoad.CommentDTO;
import com.example.SpringRESTAPIBlogProject.Repository.CommentRepository;

import java.util.List;

public interface CommentService  {

    CommentDTO createComment(CommentDTO commentDTO , long id);

    List<CommentDTO> getCommentsByPostId(long id);

    CommentDTO getCommentById(long id);

    CommentDTO updateCommentbyPostId(Long postId , long commentId , CommentDTO commentDTO);
}
