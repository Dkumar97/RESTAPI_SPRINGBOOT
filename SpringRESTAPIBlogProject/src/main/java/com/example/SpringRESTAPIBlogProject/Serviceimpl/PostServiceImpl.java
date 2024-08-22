package com.example.SpringRESTAPIBlogProject.Serviceimpl;

import com.example.SpringRESTAPIBlogProject.Entity.Post;
import com.example.SpringRESTAPIBlogProject.Exception.ResourceNotFoundException;
import com.example.SpringRESTAPIBlogProject.PayLoad.PostDTO;
import com.example.SpringRESTAPIBlogProject.Repository.PostRepository;
import com.example.SpringRESTAPIBlogProject.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
private ModelMapper modelMapper;
    public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
     Post post1 =   postRepository.save(post);
     PostDTO postDTO1 = new PostDTO();
     postDTO1.setTitle(post1.getTitle());
     postDTO1.setDescription(post1.getDescription());
     postDTO1.setContent(post1.getContent());
     postDTO1.setId(post1.getId());
        return postDTO1;
    }

    @Override
    public List<PostDTO> getAllPosts(int page, int size) {
        //adding Paging Support
        Pageable pageable = PageRequest.of(page, size);
        List<Post> posts = postRepository.findAll(pageable).getContent();
        List<PostDTO> postDTOs = new ArrayList<>();
       for (Post post : posts) {
         PostDTO dto =   convertToDTO(post);
         postDTOs.add(dto);
       }

        return postDTOs;
    }

    @Override
    public PostDTO getPostById(Long id) {

    Post post =     postRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("post", "id",id));
    PostDTO dto =   convertToDTO(post);
    return dto;
          }

    @Override
    public PostDTO updatePostById(Long id, PostDTO postDTO) {

       Post post = postRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("post", "id",id));
       post.setTitle(postDTO.getTitle());
       post.setDescription(postDTO.getDescription());
       post.setContent(postDTO.getContent());
       Post post1 =   postRepository.save(post);
       PostDTO postDTO1 = convertToDTO(post1);

        return postDTO1;
    }

    @Override
    public String deletePostById(Long id) {

        Post post = postRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("post", "id",id));
        postRepository.delete(post);

        return "Post deleted successfully";
    }


    public PostDTO convertToDTO(Post post) {
        //using model mapper
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);
      /*  PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setContent(post.getContent());*/
        return postDTO;
    }
    public Post convertToPost(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        /*Post post = new Post();
        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());*/
        return post;
    }
}
