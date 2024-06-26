package com.capstone.notxapp.controller;
//
//import com.capstone.notxapp.dto.PostDto;
//import com.capstone.notxapp.service.PostService;
//import jakarta.validation.Valid;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@Controller
//public class PostController {
//
//    private PostService postService;
//
//    public PostController(PostService postService) {
//        this.postService = postService;
//    }
//
//    // create handler method, GET request and return model and view
//    @GetMapping("/admin/posts")
//    public String posts(Model model){
//        List<PostDto> posts = postService.findAllPosts();
//        model.addAttribute("posts", posts);
//        return "/admin/posts";
//    }
//
//    // handler method to handle new post request
//    @GetMapping("admin/posts/newpost")
//    public String newPostForm(Model model){
//        PostDto postDto = new PostDto();
//        model.addAttribute("post", postDto);
//        return "admin/create_post";
//    }
//
//    // handler method to handle form submit request
//    @PostMapping("/admin/posts")
//    public String createPost(@Valid @ModelAttribute("post") PostDto postDto,
//                             BindingResult result,
//                             Model model){
//        if(result.hasErrors()){
//            model.addAttribute("post", postDto);
//            return "admin/create_post";
//        }
//        postDto.setUrl(getUrl(postDto.getTitle()));
//        postService.createPost(postDto);
//        return "redirect:/admin/posts";
//    }
//
//    // handler method to handle edit post request
//    @GetMapping("/admin/posts/{postId}/edit")
//    public String editPostForm(@PathVariable("postId") Long postId,
//                               Model model){
//
//        PostDto postDto = postService.findPostById(postId);
//        model.addAttribute("post", postDto);
//        return "admin/edit_post";
//    }
//
//    // handler method to handle edit post form submit request
//    @PostMapping("/admin/posts/{postId}")
//    public String updatePost(@PathVariable("postId") Long postId,
//                             @Valid @ModelAttribute("post") PostDto post,
//                             BindingResult result,
//                             Model model){
//        if(result.hasErrors()){
//            model.addAttribute("post", post);
//            return "admin/edit_post";
//        }
//
//        post.setId(postId);
//        postService.updatePost(post);
//        return "redirect:/admin/posts";
//    }
//
//    // handler method to handle delete post request
//    @GetMapping("/admin/posts/{postId}/delete")
//    public String deletePost(@PathVariable("postId") Long postId){
//        postService.deletePost(postId);
//        return "redirect:/admin/posts";
//    }
//
////    // handler method to handle view post request
////    @GetMapping("/admin/posts/{postUrl}/view")
////    public String viewPost(@PathVariable("postUrl") String postUrl,
////                           Model model){
////        PostDto postDto = postService.findPostByUrl(postUrl);
////        model.addAttribute("post", postDto);
////        return "admin/view_post";
////
////    }
//
//    // handler method to handle search blog posts request
//    // localhost:8080/admin/posts/search?query=java
//    @GetMapping("/admin/posts/search")
//    public String searchPosts(@RequestParam(value = "query") String query,
//                              Model model){
//        List<PostDto> posts = postService.searchPosts(query);
//        model.addAttribute("posts", posts);
//        return "admin/posts";
//    }
//
//    // Updated handler method to handle view post request for multiple posts with the same URL
//    @GetMapping("/admin/posts/{postUrl}/view")
//    public String viewPosts(@PathVariable("postUrl") String postUrl, Model model) {
//        List<PostDto> postDtos = postService.findPostsByUrl(postUrl); // Adjusted to the new method
//        model.addAttribute("posts", postDtos); // Adds the list of PostDto objects to the model
//        return "admin/view_posts"; // Make sure to adjust the view to iterate over multiple posts
//    }
//
//    // The rest of your controller methods remain unchanged...
//
//    private static String getUrl(String postTitle) {
//        // Your URL generation logic remains unchanged...
//        String title = postTitle.trim().toLowerCase();
//        String url = title.replaceAll("\\s+", "-");
//        url = url.replaceAll("[^A-Za-z0-9]", "-");
//        return url;
//    }
//}

import jakarta.validation.Valid;
import com.capstone.notxapp.dto.CommentDto;
import com.capstone.notxapp.dto.PostDto;
import com.capstone.notxapp.service.CommentService;
import com.capstone.notxapp.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private PostService postService;
    private CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    // create handler method, GET request and return model and view
    @GetMapping("/admin/posts")
    public String posts(Model model){
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "/admin/posts";
    }

    // handler method to handle list comments request
    @GetMapping("/admin/posts/comments")
    public String postComments(Model model){
        List<CommentDto> comments = commentService.findAllComments();
        model.addAttribute("comments", comments);
        return "admin/comments";
    }

    // handler method to handle delete comment request
    @GetMapping("/admin/posts/comments/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/admin/posts/comments";
    }

    // handler method to handle new post request
    @GetMapping("admin/posts/newpost")
    public String newPostForm(Model model){
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);
        return "admin/create_post";
    }

    // handler method to handle form submit request
    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("post", postDto);
            return "admin/create_post";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    // handler method to handle edit post request
    @GetMapping("/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId,
                               Model model){

        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("post", postDto);
        return "admin/edit_post";
    }

    // handler method to handle edit post form submit request
    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId,
                             @Valid @ModelAttribute("post") PostDto post,
                             BindingResult result,
                             Model model){
        if(result.hasErrors()){
            model.addAttribute("post", post);
            return "admin/edit_post";
        }

        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/admin/posts";
    }

    // handler method to handle delete post request
    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

    // handler method to handle view post request
    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl,
                           Model model){
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post", postDto);
        return "admin/view_post";

    }

    // handler method to handle search blog posts request
    // localhost:8080/admin/posts/search?query=java
    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam(value = "query") String query,
                              Model model){
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts", posts);
        return "admin/posts";
    }

    private static String getUrl(String postTitle){
        // OOPS Concepts Explained in Java
        // oops-concepts-explained-in-java
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;
    }
}
