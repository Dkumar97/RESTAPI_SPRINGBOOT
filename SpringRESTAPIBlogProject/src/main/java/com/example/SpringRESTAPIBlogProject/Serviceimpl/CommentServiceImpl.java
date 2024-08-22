package com.example.SpringRESTAPIBlogProject.Serviceimpl;

import com.example.SpringRESTAPIBlogProject.Entity.Comment;
import com.example.SpringRESTAPIBlogProject.Entity.Post;
import com.example.SpringRESTAPIBlogProject.Exception.ResourceNotFoundException;
import com.example.SpringRESTAPIBlogProject.PayLoad.CommentDTO;
import com.example.SpringRESTAPIBlogProject.Repository.CommentRepository;
import com.example.SpringRESTAPIBlogProject.Repository.PostRepository;
import com.example.SpringRESTAPIBlogProject.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, long id) {
        Post post = postRepository.findById(id).get();
      Comment comment =   converToComment(commentDTO);
      comment.setPost(post);

      Comment comment1  =commentRepository.save(comment);
        return converToCommentDTO(comment1);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(long id) {

        List<Comment> comments = commentRepository.findByPostId(id);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            commentDTOS.add(converToCommentDTO(comment));
        }
        return commentDTOS;
    }

    @Override
    public CommentDTO getCommentById(long id) {
       Comment comment = commentRepository.findById(id).get();
       return converToCommentDTO(comment);
    }

    @Override
    public CommentDTO updateCommentbyPostId(Long postId, long commentId, CommentDTO commentDTO) {


        Post post = postRepository.findById(postId).get();
        Comment comment = commentRepository.findById(commentId).get();

      comment.setBody(commentDTO.getBody());
      comment.setEmail(commentDTO.getEmail());
      comment.setName(commentDTO.getName());

      Comment comment1 =commentRepository.save(comment);
      return converToCommentDTO(comment1);
    }


    public CommentDTO converToCommentDTO(Comment comment) {
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        /*new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setBody(comment.getBody());
        commentDTO.setEmail(comment.getEmail());*/
        return commentDTO;
    }

    public Comment converToComment(CommentDTO commentDTO) {
        Comment comment = modelMapper.map(commentDTO, Comment.class);

        /*new Comment();
        comment.setId(commentDTO.getId());
        comment.setBody(commentDTO.getBody());
        comment.setEmail(commentDTO.getEmail());
        comment.setName(commentDTO.getName());*/
        return comment;
    }
}
