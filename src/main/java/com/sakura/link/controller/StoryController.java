package com.sakura.link.controller;

import com.sakura.link.models.Story;
import com.sakura.link.models.User;
import com.sakura.link.service.StoryService;
import com.sakura.link.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StoryController {

    @Autowired
    private StoryService storyService;
    @Autowired
    private UserService userService;

    @PostMapping("/api/story")
    public Story createStory(@RequestBody Story story,
                             @RequestHeader("Authorization") String jwt) {
        User reqUser = userService.findUserJwt(jwt);
        Story createdStory = storyService.createStory(story, reqUser);
        return createdStory;
    }

    @GetMapping("/api/story/user/{userId}")
    public List<Story> findUsersStory(@PathVariable Integer userId,
                            @RequestHeader("Authorization") String jwt) throws Exception {
        User reqUser = userService.findUserJwt(jwt);
        List<Story> stories = storyService.findStoryByUserId(userId);
        return stories;
    }
}
