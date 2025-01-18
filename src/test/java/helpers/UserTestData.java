package helpers;

import com.google.gson.JsonObject;

import ch.qos.logback.classic.Logger;

import com.google.gson.JsonArray;
import java.util.Optional;

import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;


/**
 * UserTestData class provides methods to retrieve test user data from the JSON file.
 * It supports both API tests and UI tests.
 */
public class UserTestData {
    private static final JsonObject usersData = TestDataReader.getTestData("users");
    private static final Logger logger = LoggerManager.getLogger(UserTestData.class);

    
    public static JsonObject getDynamicUser() {
        return usersData.getAsJsonArray("dynamicUserData")
            .get(0)
            .getAsJsonObject();
    }
    
    public static JsonObject getPojoTestUser() {
        return usersData.getAsJsonObject("pojoTestUser");
    }
    
    public static JsonObject getDefaultTestUser() {
        return usersData.getAsJsonObject("defaultTestUser");
    }
    
    /**
     * ResourceLock ensures that only one thread can access the validUsers resource at a time.
     * READ_WRITE mode indicates this method both reads from and modifies the validUsers resource,
     * preventing concurrent access that could lead to race conditions.
     */
    @ResourceLock(value = "validUsers", mode = ResourceAccessMode.READ_WRITE)
    public static JsonObject getRandomValidUser() {
        JsonArray users = usersData.getAsJsonArray("validUsers");
        int randomIndex = (int) (Math.random() * users.size());
        JsonObject selectedUser = users.get(randomIndex).getAsJsonObject();
        logger.debug("Locking random user for thread: {}", selectedUser);
        return selectedUser;
    }
    
    public static JsonObject getInvalidUser() {
        JsonArray users = usersData.getAsJsonArray("invalidUsers");
        // Return a random invalid user from the list of invalid users.
        return users.get((int) (Math.random() * users.size()))
            .getAsJsonObject();
    }
    
    public static String getEmail(JsonObject user) {
        return Optional.ofNullable(user.get("email"))
            // Resolve dynamic values in the email field.
            .map(e -> TestDataReader.resolveDynamicValues(e.getAsString()))
            .orElse("");
    }
    
    public static String getPassword(JsonObject user) {
        return Optional.ofNullable(user.get("password"))
            .map(e -> e.getAsString())
            .orElse("");
    }
    
    public static String getUsername(JsonObject user) {
        return Optional.ofNullable(user.get("username"))
            .map(e -> e.getAsString())
            .orElse("");
    }
} 