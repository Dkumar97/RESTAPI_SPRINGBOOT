package com.example.SpringRESTAPIBlogProject.Controller;


import com.example.SpringRESTAPIBlogProject.Entity.Post;
import com.example.SpringRESTAPIBlogProject.PayLoad.PostDTO;
import com.example.SpringRESTAPIBlogProject.Service.PostService;
import com.example.SpringRESTAPIBlogProject.Serviceimpl.PostServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("posts/")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/createPost")
    public ResponseEntity<PostDTO> CreatePost(@Valid @RequestBody PostDTO postDTO) {
 PostDTO createdPost = postService.createPost(postDTO);

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/getAllPosts")
    public ResponseEntity<List<PostDTO>> getAllPosts(@RequestParam (value = "pageNo", defaultValue = "0", required = false) int pageNo ,
                                                     @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize) {
        List<PostDTO> posts = postService.getAllPosts(pageNo, pageSize);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<PostDTO> findPostById(@PathVariable long id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @PutMapping("/updatePostById/{id}")
    public ResponseEntity<PostDTO> updatePostById(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.updatePostById(id, postDTO), HttpStatus.OK);
    }

    @DeleteMapping("/deletePostByID/{id}")
    public void deletePostById(@PathVariable long id) {
        postService.deletePostById(id);
    }
}
