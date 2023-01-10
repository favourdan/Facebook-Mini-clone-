package com.example.spring_boot_demo.service.serviceImpl;

import com.example.spring_boot_demo.dto.CommentDto;
import com.example.spring_boot_demo.model.Comment;
import com.example.spring_boot_demo.model.Post;
import com.example.spring_boot_demo.model.User;
import com.example.spring_boot_demo.repository.CommentRepository;
import com.example.spring_boot_demo.repository.PostRepository;
import com.example.spring_boot_demo.repository.UserRepository;
import com.example.spring_boot_demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Override
    public void createComment(CommentDto commentDto, Long userId, Long postId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Post> postOptional = postRepository.findById(postId);

        User user = userOptional.orElseThrow(() ->
                new NoSuchElementException("User id: " + userId + " does not exist."));
        Post post = postOptional.orElseThrow(() ->
                new NoSuchElementException("Post with id: " + postId + " does not exist."));

        Comment comment = Comment.builder()
                .message(commentDto.getMessage())
                .user(user)
                .post(post)
                .build();
        commentRepository.save(comment);

    }

    @Override
    public void deleteComment(Long userId, Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(userId);
        Comment comment = commentOptional.orElseThrow(() ->
                new NoSuchElementException("User id: " + userId + " does not exist."));

        if(!comment.getUser().getId().equals(userId))
            throw new NoSuchElementException("You are not authorized to delete comment");

        commentRepository.deleteById(commentId);
    }

    @Override
    public List<Comment> getAllComment() {
        List<Comment> comments = commentRepository.findAll();
        return comments;
    }

    @Override
    public void updateComment(Long commentId, Long userId, String message) {
        Optional<Comment> commentOptional = commentRepository.findById(userId);
        Comment comment = commentOptional.orElseThrow(() ->
                new NoSuchElementException("User id: " + userId + " does not exist."));

        if(!comment.getUser().getId().equals(userId))
            throw new NoSuchElementException("You are not authorized to delete comment");
        comment.setMessage(message);
        commentRepository.save(comment);
    }
}
