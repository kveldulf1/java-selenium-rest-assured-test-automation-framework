package pojo.users;

public class CreateUserResponse {
    private final Long id;
    private final String email;
    
    public CreateUserResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
} 