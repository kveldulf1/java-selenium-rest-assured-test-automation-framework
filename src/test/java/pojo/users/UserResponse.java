package pojo.users;

public class UserResponse {
    private final Long id;
    private final String email;
    private final String firstname;
    private final String lastname;
    private final String password;
    private final String avatar;

    public UserResponse(Long id, String email, String firstname, String lastname, String password, String avatar) {
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatar() {
        return avatar;
    }
} 