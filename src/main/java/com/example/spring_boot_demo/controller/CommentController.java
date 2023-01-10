package com.example.spring_boot_demo.controller;

import com.example.spring_boot_demo.dto.CommentDto;
import com.example.spring_boot_demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create-comment/{userId}/{postId}")
    public String createComment(@ModelAttribute CommentDto commentDto,
                             @PathVariable Long userId, @PathVariable Long postId) {
        commentService.createComment(commentDto, userId, postId);
        return "redirect:/";
    }

    @RequestMapping("/delete") //Validation required
    public String deletePost(@RequestParam Long userId, @RequestParam Long commentId) {
        commentService.deleteComment(userId, commentId);
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String updatePost(@RequestParam Long userId, @RequestParam Long commentId,
                             @RequestParam String commentMessage, HttpSession session) {
        session.setAttribute("showInputField", false);
        commentService.updateComment(commentId, userId, commentMessage);
        return "redirect:/";
    }
}
