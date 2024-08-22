package com.example.SpringRESTAPIBlogProject.Controller;

import com.example.SpringRESTAPIBlogProject.PayLoad.CommentDTO;
import com.example.SpringRESTAPIBlogProject.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts/")
public class CommentCotroller {
    @Autowired
    private CommentService commentService;

    @PostMapping("createComment/{id}")
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO, @PathVariable ("id") long id) {
       return commentService.createComment(commentDTO,id);
    }

   @GetMapping("comments/{post_Id}")
    public List<CommentDTO> getAllCommentsByPostId(@PathVariable (value = "post_Id") long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/commentById/{comment_id}")
    public CommentDTO getCommentById(@PathVariable (value = "comment_id") long id) {
        return commentService.getCommentById(id);
    }

    @PutMapping("{post_id}/comments/{coment_id}")
    public CommentDTO updateComment(
                                    @PathVariable (value = "post_id") long id,@RequestBody CommentDTO commentDTO, @PathVariable(value = "coment_id") long commentId) {
       return commentService.updateCommentbyPostId(id,commentId,commentDTO);
    }
}
