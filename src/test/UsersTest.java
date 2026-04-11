import com.example.chermn.Classes.Users; // which class we are testing

// import test library
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersTest {
    private Users users;
    @BeforeEach
    public void setUp() {
        //users = new user (""); // make this user then test against it
    }
    @Test
    public void testGetId() {
        //
    }
    @Test
    public void testGetFirstName(){
        //assertEquals("John", users.getFirstName()); // does it equal what is expected
    }

    // test set and get for each attribute

}