package com.example.spring_boot_demo.controller;

import com.example.spring_boot_demo.dto.CommentDto;
import com.example.spring_boot_demo.dto.PostDto;
import com.example.spring_boot_demo.model.Post;
import com.example.spring_boot_demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/", "/posts"})
public class PostController {
    private final PostService postService;

    @GetMapping
    public ModelAndView getPosts(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return new ModelAndView("indexPage/login");
        }
        ModelAndView indexPage = new ModelAndView("indexPage/home");
        List<Post> posts = postService.getAllPost();
        indexPage.addObject("posts", posts);
        PostDto postDto = new PostDto();
        indexPage.addObject("postDto", postDto);

        CommentDto commentDto = new CommentDto();
        indexPage.addObject("commentDto", commentDto);
        return indexPage;
    }

    @PostMapping("/create-post/{userId}")
    public String createPost(@ModelAttribute PostDto postDto, @PathVariable Long userId) {
        postService.createPost(postDto, userId);
        return "redirect:/";
    }


    @RequestMapping("/delete")
    public String deletePost(@RequestParam Long Id, @RequestParam Long userId) {
        postService.deletePost(Id, userId);
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String updatePost(@RequestParam Long Id, @RequestParam Long userId, @RequestParam String postMassage, HttpSession session) {
        session.setAttribute("showInputField", false);
        postService.updatePost(Id, postMassage);
        return "redirect:/";
    }}





