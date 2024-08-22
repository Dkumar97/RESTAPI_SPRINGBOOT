package com.example.SpringRESTAPIBlogProject.Service;

import com.example.SpringRESTAPIBlogProject.PayLoad.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> getAllPosts(int pageNo , int pageSize);


    PostDTO getPostById(Long id);

    PostDTO updatePostById(Long id, PostDTO postDTO);

    String deletePostById(Long id);
}
