package com.sakura.link.controller;

import com.sakura.link.models.Comment;
import com.sakura.link.models.User;
import com.sakura.link.service.CommentService;
import com.sakura.link.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/comments/post/{postId}")
    public Comment createCOmment(@RequestBody Comment comment,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable("postId") Integer postId) throws Exception {
        User user = userService.findUserJwt(jwt);
        Comment createdComment = commentService.createComment(comment, postId, user.getId());
        return createdComment;
    }

    @PutMapping("/api/comments/like/{commentId}")
    public Comment likeComment(@RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer commentId) throws Exception {
        User user = userService.findUserJwt(jwt);
        Comment likedComment = commentService.likeComment(commentId, user.getId());
        return likedComment;
    }
}
