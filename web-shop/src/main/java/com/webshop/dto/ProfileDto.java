package com.webshop.dto;

import com.webshop.model.User;

public class ProfileDto {

    Long id;

    private String name;

    private String lastname;

    private String role;

    private String profilePicture;

    public ProfileDto() {
    }

    public ProfileDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.role = user.getUserRole();
        this.profilePicture = user.getProfilePicture();
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
