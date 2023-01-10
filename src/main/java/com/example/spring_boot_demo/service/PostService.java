package com.example.spring_boot_demo.service;

import com.example.spring_boot_demo.dto.PostDto;
import com.example.spring_boot_demo.model.Post;

import java.util.List;

public interface PostService {
    void createPost(PostDto postDto, Long userId);
    void deletePost(Long userId, Long postId);

    List<Post> getAllPost();

    void updatePost(Long postId, String message);
}
