package com.example.spring_boot_demo.repository;

import com.example.spring_boot_demo.model.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    @Query(value = "SELECT * FROM post_like WHERE id=? AND user_id=?", nativeQuery = true)
    Optional<PostLike> findAllByUserIdAndPostId(Long userId, Long postId);
}
