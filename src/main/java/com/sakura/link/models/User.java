package com.sakura.link.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String gender;
    @Column(unique = true, nullable = false)
    private String username;

    private String avatar;          // URL до аватарки
    private String coverImage;      // URL до фоновой картинки профиля

    private List<Integer> followers = new ArrayList<>();
    private List<Integer> followings = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<Post> savedPost = new ArrayList<>();

    public User() {
    }

    public User(Integer id, String firstName, String lastName, String email,
                String password, String gender, String username, String avatar, String coverImage,
                List<Integer> followers, List<Integer> followings) {
        this.id         = id;
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.email      = email;
        this.password   = password;
        this.gender     = gender;
        this.username   = username;
        this.avatar     = avatar;
        this.coverImage = coverImage;
        this.followers  = followers;
        this.followings = followings;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public List<Integer> getFollowings() {
        return followings;
    }

    public void setFollowings(List<Integer> followings) {
        this.followings = followings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Post> getSavedPost() {
        return savedPost;
    }

    public void setSavedPost(List<Post> savedPost) {
        this.savedPost = savedPost;
    }

    public String getAvatar()          { return avatar; }
    public void   setAvatar(String a)  { this.avatar = a; }

    public String getCoverImage()              { return coverImage; }
    public void   setCoverImage(String cover)  { this.coverImage = cover; }

    public String  getUsername()       { return username; }
    public void    setUsername(String u){ this.username = u; }
}
