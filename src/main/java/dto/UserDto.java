package dto;

import utils.DateFormatterUtil;

import java.sql.Timestamp;

public class UserDto {
    private Integer id;
    private String username;
    private String email;
    private String image;
    private String role;
    private String createdAt;

    public UserDto(Integer id, String username, String email, String image, String role, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.image = image;
        this.role = role;
        this.createdAt = DateFormatterUtil.formatTimestamp(createdAt);
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public UserDto() {}

    // Геттеры и сеттеры


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return role;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}