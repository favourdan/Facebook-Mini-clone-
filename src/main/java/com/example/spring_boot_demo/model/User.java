package com.example.spring_boot_demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @JsonIgnore
    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Comment> comments = new ArrayList<>();


    public void addPost(Post post) {
        if (!post.getMessage().isEmpty()) {
            this.posts.add(post);
//            post.setUser(this);
        }
    }

    public void removePost(Post post) {
        if (this.posts.contains(post)) {
            this.posts.remove(post);
//            post.setUser(null);
        }
    }
}
