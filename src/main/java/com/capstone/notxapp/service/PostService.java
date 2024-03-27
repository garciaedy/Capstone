package com.capstone.notxapp.service;
import com.capstone.notxapp.dto.PostDto;
import java.util.List;


public interface PostService {
    List<PostDto> findAllPosts();

    void createPost(PostDto postDto);

    PostDto findPostById(Long postId);

    void updatePost(PostDto postDto);

    void deletePost(Long postId);

    // Updated method signature to reflect that it returns a list of PostDto objects
    List<PostDto> findPostsByUrl(String postUrl);

    List<PostDto> searchPosts(String query);


    PostDto findPostByUrl(String postUrl);
}