// import class and database
import com.example.chermn.model.Users;

// import test library
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class UsersTest {

    private Users users;


    @BeforeEach
    public void setUp() {
        // creates a new user to use as test
        users = new Users("rmull", "Rebecca", "Mullock", "12345", "QUT" );
    }


    @Test
    public void testGetId() {
        // test for checking id
    }


    @Test
    public void testGetFirstName(){
        //assertEquals("John", users.getFirstName()); // does it equal what is expected
    }



    // tests for password - normal, edge and invalid inputs

    @Test
    public void testGetPassword_initialValue() {
        assertEquals("12345", users.getPassword());
    }

    @Test
    public void testSetPassword_updatesValue() throws Exception {
        users.setPassword("54321");
        assertEquals("54321", users.getPassword());
    }

    @Test
    public void testSetPassword_emptyString() throws Exception{
        String new_password = "";
        assertThrows(IllegalArgumentException.class, () -> users.setPassword(new_password));
    }

    @Test
    public void testSetPassword_null() throws Exception{
        assertThrows(IllegalArgumentException.class, () -> users.setPassword(null));
    }

    @Test
    public void testSetPassword_tooShort() throws Exception{
        String new_password = "123";
        assertThrows(IllegalArgumentException.class, () -> users.setPassword(new_password));
    }


}
