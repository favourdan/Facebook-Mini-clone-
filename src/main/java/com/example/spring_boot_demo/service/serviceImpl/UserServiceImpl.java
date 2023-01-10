package com.example.spring_boot_demo.service.serviceImpl;

import com.example.spring_boot_demo.dto.UserDto;
import com.example.spring_boot_demo.model.Post;
import com.example.spring_boot_demo.model.PostLike;
import com.example.spring_boot_demo.model.User;
import com.example.spring_boot_demo.repository.PostLikeRepository;
import com.example.spring_boot_demo.repository.PostRepository;
import com.example.spring_boot_demo.repository.UserRepository;
import com.example.spring_boot_demo.response.ApiResponse;
import com.example.spring_boot_demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

   private final UserRepository userRepository;
   private final PostLikeRepository postLikeRepository;
   private final PostRepository  postRepository;

   public UserServiceImpl(UserRepository userRepository, PostLikeRepository postLikeRepository, PostRepository postRepository) {
       this.userRepository = userRepository;
       this.postLikeRepository = postLikeRepository;
       this.postRepository = postRepository;
   }

    @Override
    public ApiResponse<String> signupNewUser(UserDto userDto) {
       //Verify user details
        User user = User.builder()
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .build();
        userRepository.save(user);
        return new ApiResponse<>("Registration successful", true, "success");
    }

    @Override
    public Long likePost(Long userId, Long postId) {
        Optional<PostLike> postsLikes = postLikeRepository.findAllByUserIdAndPostId(userId, postId);
        if (postsLikes.isPresent()){
            postLikeRepository.delete(postsLikes.get());
            return   postLikeRepository.count();
        }
        Post post = postRepository.findById(postId).get();
        User user = userRepository.findById(userId).get();
        PostLike postLike = new PostLike();
        postLike.setPost(post);
        postLike.setUser(user);
        postLikeRepository.save(postLike);
        return postLikeRepository.count();

    }
}
