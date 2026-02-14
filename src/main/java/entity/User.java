package entity;

import java.sql.Timestamp;

public class User {
    private Integer id;
    private String username;
    private String passwordHash;
    private String email;
    private String image;
    private String role;
    private Timestamp createdAt;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User(Integer id, String username, String passwordHash, String email, String image, String role, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.image = image;
        this.role = role;
        this.createdAt = createdAt;
    }

    public User(String username, String passwordHash, String email, String role) {
        this.id = null;
        this.image = "https://res.cloudinary.com/dyvlt50yh/image/upload/v1762360400/kjbwe231nk325j32a_q8g1qe.png";
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.role = role;

    }

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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
