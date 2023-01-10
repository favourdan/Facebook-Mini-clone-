package com.example.spring_boot_demo.service.serviceImpl;

import com.example.spring_boot_demo.dto.PostDto;
import com.example.spring_boot_demo.model.Post;
import com.example.spring_boot_demo.model.User;
import com.example.spring_boot_demo.repository.PostRepository;
import com.example.spring_boot_demo.repository.UserRepository;
import com.example.spring_boot_demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void createPost(PostDto postDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() ->
                new NoSuchElementException("User id: " + userId + " does not exist."));

        Post post = Post.builder()
                .message(postDto.getMessage())
                .user(user)
                .build();
            postRepository.save(post);
    }

    @Override
    public void deletePost(Long userId, Long postId) {
//        postRepository.deleteById(postId);
        User user = userRepository.findById(userId).get();
        Post post = postRepository.findById(postId).get();
        user.removePost(post);
    }

    @Override
    public List<Post> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    @Override
    public void updatePost(Long postId, String message) {
        Post post = postRepository.findById(postId).get();
        post.setMessage(message);
        postRepository.save(post);
    }


    public Post getAllPost(Long postId) {
        Post post = postRepository.findById(postId).get();
        return post;
    }


}

