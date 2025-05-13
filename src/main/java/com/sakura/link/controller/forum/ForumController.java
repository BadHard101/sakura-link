// src/main/java/com/sakura/link/controller/forum/ForumController.java
package com.sakura.link.controller.forum;

import com.sakura.link.dto.forum.*;
import com.sakura.link.models.User;
import com.sakura.link.service.UserService;
import com.sakura.link.service.forum.ForumService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forum")
@CrossOrigin(origins = "*")                 // при желании сузьте список доменов
public class ForumController {

    private final ForumService forumService;
    private final UserService  userService;

    public ForumController(ForumService forumService, UserService userService) {
        this.forumService = forumService;
        this.userService  = userService;
    }

    /* ---------- THREADS ---------- */

    @GetMapping("/threads")
    public List<ThreadDto> listThreads() {
        return forumService.findAllThreads();
    }

    @PostMapping("/threads")
    public ThreadDto createThread(@RequestHeader("Authorization") String jwt,
                                  @RequestBody ThreadRequest req) {

        User user = userService.findUserJwt(jwt);       // достаём пользователя из JWT
        return forumService.createThread(req, user.getId());
    }

    @GetMapping("/threads/{id}")
    public ThreadDto getThread(@PathVariable Long id) {
        return forumService.findThread(id);
    }

    /* ---------- POSTS ---------- */

    @PostMapping("/threads/{id}/posts")
    public PostDto createPost(@RequestHeader("Authorization") String jwt,
                              @PathVariable Long id,
                              @RequestBody PostRequest req) {

        User user = userService.findUserJwt(jwt);
        return forumService.addPost(id, req, user.getId());
    }

    @PatchMapping("/threads/{id}/status")
    public ThreadDto changeStatus(@RequestHeader("Authorization") String jwt,
                                  @PathVariable Long id,
                                  @RequestParam boolean solved) {

        User u = userService.findUserJwt(jwt);
        return forumService.setSolved(id, solved, u.getId());
    }

    @PatchMapping("/posts/{id}/solution")
    public ThreadDto markSolution(@RequestHeader("Authorization") String jwt,
                                  @PathVariable Long id) {
        User u = userService.findUserJwt(jwt);
        return forumService.acceptSolution(id, u.getId());
    }

}
