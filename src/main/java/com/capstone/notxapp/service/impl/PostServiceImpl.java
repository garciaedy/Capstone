package com.capstone.notxapp.service.impl;

import com.capstone.notxapp.dto.PostDto;
import com.capstone.notxapp.entity.Post;
import com.capstone.notxapp.mapper.PostMapper;
import com.capstone.notxapp.repository.PostRepository;
import com.capstone.notxapp.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        return post.map(PostMapper::mapToPostDto).orElse(null);
    }

    @Override
    public void updatePost(PostDto postDto) {
        Post post = PostMapper.mapToPost(postDto);
        // Ensuring we're updating an existing post
        if (post.getId() != null && postRepository.existsById(post.getId())) {
            postRepository.save(post);
        }
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<PostDto> findPostsByUrl(String postUrl) {
        List<Post> posts = postRepository.findByUrl(postUrl);
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts = postRepository.searchPosts(query);
        return posts.stream().map(PostMapper::mapToPostDto).collect(Collectors.toList());
    }

    @Override
    public PostDto findPostByUrl(String postUrl) {
        return null;
    }
}
