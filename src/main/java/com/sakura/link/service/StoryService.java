package com.sakura.link.service;

import com.sakura.link.models.Story;
import com.sakura.link.models.User;

import java.util.List;

public interface StoryService {

    public Story createStory (Story story, User user);

    public List<Story> findStoryByUserId(Integer userId) throws Exception;

}
