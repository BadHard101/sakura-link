package com.sakura.link.service;

import com.sakura.link.models.Reels;
import com.sakura.link.models.User;

import java.util.List;

public interface ReelsService {
    public Reels createReel(Reels reel, User user);

    public List<Reels> findAllReels();

    public List<Reels> findUsersReel(Integer userId) throws Exception;
}
