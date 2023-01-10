package com.example.spring_boot_demo.service;

import com.example.spring_boot_demo.dto.CommentDto;
import com.example.spring_boot_demo.model.Comment;

import java.util.List;

public interface CommentService {

    void createComment(CommentDto commentDto, Long userId, Long postId);

    void deleteComment(Long userId, Long commentId);

    List<Comment> getAllComment();

    void updateComment(Long commentId, Long userId, String message);
}

