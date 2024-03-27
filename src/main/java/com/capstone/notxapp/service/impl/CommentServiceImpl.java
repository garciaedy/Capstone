package com.capstone.notxapp.service.impl;

import com.capstone.notxapp.dto.CommentDto;
import com.capstone.notxapp.entity.Comment;
import com.capstone.notxapp.entity.Post;
import com.capstone.notxapp.mapper.CommentMapper;
import com.capstone.notxapp.repository.CommentRepository;
import com.capstone.notxapp.repository.PostRepository;
import com.capstone.notxapp.service.CommentService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto) {
        List<Post> posts = postRepository.findByUrl(postUrl);
        if (!posts.isEmpty()) {
            // Assuming we want to attach the comment to the first post found
            Post post = posts.get(0);
            Comment comment = CommentMapper.mapToComment(commentDto);
            comment.setPost(post);
            commentRepository.save(comment);
        } else {
            throw new IllegalStateException("No post found with URL: " + postUrl);
        }
    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(CommentMapper::mapToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
