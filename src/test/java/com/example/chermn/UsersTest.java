package com.example.chermn;
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
        users = new Users(1,"rmull", "Rebecca", "Mullock", "12345", "QUT" );
    }


    // tests for id -> getting the initial value
    @Test
    public void testGetId_initialValue() {
        assertEquals(1, users.getid());
    }

    // tests for username -> normal, edge and invalid inputs
    @Test
    public void testGetUsername_initialValue() {
        assertEquals("rmull", users.getUserName());
    }

    @Test
    public void testSetUsername_valid() {
        users.setUsername("newUser");
        assertEquals("newUser", users.getUserName());
    }

    @Test
    public void testSetUsername_null() {
        assertThrows(IllegalArgumentException.class, () -> users.setUsername(null));
    }

    @Test
    public void testSetUsername_empty() {
        assertThrows(IllegalArgumentException.class, () -> users.setUsername(""));
    }



    // tests for first name -> normal, special characters and no spaces
    @Test
    public void testGetFirstName_initialValue(){
        assertEquals("Rebecca", users.getFirstName());
    }

    @Test
    public void testSetFirstName_valid() {
        users.setFirstName("Alice");
        assertEquals("Alice", users.getFirstName());
    }

    @Test
    public void testSetFirstName_lowercase() {
        users.setFirstName("alice");
        assertEquals("Alice", users.getFirstName());
    }

    @Test
    public void testSetFirstName_null() throws Exception{
        assertThrows(IllegalArgumentException.class, () -> users.setFirstName(null));
    }

    @Test
    public void testSetFirstName_noSpaces() throws Exception{
        String first_name_spaces = "John Smith";
        assertThrows(IllegalArgumentException.class, () -> users.setFirstName(first_name_spaces));
    }

    @Test
    public void testSetFirstName_noDigits() throws Exception{
        String first_name_spaces = "John9";
        assertThrows(IllegalArgumentException.class, () -> users.setFirstName(first_name_spaces));
    }

    @Test
    public void testSetFirstName_noSpecialCharacters() throws Exception{
        String first_name_spaces = "John@#&";
        assertThrows(IllegalArgumentException.class, () -> users.setFirstName(first_name_spaces));
    }

    @Test
    public void testSetFirstName_whitespace() {
        assertThrows(IllegalArgumentException.class, () -> users.setFirstName("      ") );
    }


//tests for last name -> normal, special characters and no spaces

    @Test
    public void testGetLastName_initialValue() {
        assertEquals("Mullock", users.getLastName());
    }

    @Test
    public void testSetLastName_valid() {
        users.setLastName("Brown");
        assertEquals("Brown", users.getLastName());
    }

    @Test
    public void testSetLastName_lowercase() {
        users.setLastName("brown");
        assertEquals("Brown", users.getLastName());
    }

    @Test
    public void testSetLastName_null() throws Exception{
        assertThrows(IllegalArgumentException.class, () -> users.setLastName(null));
    }

    @Test
    public void testSetLastName_noSpaces() throws Exception{
        String last_name_spaces = "John Smith";
        assertThrows(IllegalArgumentException.class, () -> users.setLastName(last_name_spaces));
    }

    @Test
    public void testSetLastName_noDigits() throws Exception{
        String last_name_spaces = "Smith10";
        assertThrows(IllegalArgumentException.class, () -> users.setLastName(last_name_spaces));
    }

    @Test
    public void testSetLastName_noSpecialCharacters() throws Exception{
        String last_name_spaces = "Smith^?/";
        assertThrows(IllegalArgumentException.class, () -> users.setLastName(last_name_spaces));
    }

    @Test
    public void testSetLastName_whitespace() {
        assertThrows(IllegalArgumentException.class, () -> users.setLastName("      ") );
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

    @Test
    public void testSetPassword_minLength() {
        users.setPassword("12345");
        assertEquals("12345", users.getPassword());
    }


    //tests for school name -> normal, special characters and no spaces

    @Test
    public void testSetSchoolName_null() throws Exception{
        assertThrows(IllegalArgumentException.class, () -> users.setSchoolName(null));
    }

    @Test
    public void testSetSchoolName_noDigits() throws Exception{
        String school_name_spaces = "Gladstone State 921023";
        assertThrows(IllegalArgumentException.class, () -> users.setSchoolName(school_name_spaces));
    }

    @Test
    public void testGetSchoolName_initalValue() {
        assertEquals("Qut", users.getSchoolName());
    }
    @Test
    public void testSetSchoolName_valid() {
        users.setSchoolName("primary school");
        assertEquals("Primary School", users.getSchoolName());
    }


    // tests for the user constructor
    @Test
    public void testUserConstructor_valid() {
        Users user1 = new Users("user1", "John", "Doe", "12345","primary School");
        assertEquals("user1", user1.getUserName());
        assertEquals("John", user1.getFirstName());
        assertEquals("Doe", user1.getLastName());
        assertEquals("12345", user1.getPassword());
        assertEquals("Primary School", user1.getSchoolName());
    }

    @Test
    public void testUserConstructor_invalidFirstName() {
        assertThrows(IllegalArgumentException.class, () ->
                new Users("user1", "John1", "Doe", "12345", "QUT"));
    }

    @Test
    public void testMultipleUpdates_valid() {
        users.setFirstName("Alice");
        users.setLastName("Doe");
        assertEquals("Alice", users.getFirstName());
        assertEquals("Doe", users.getLastName());
    }

    @Test
    public void testOverwrittenUpdates_valid() {
        users.setFirstName("Alice");
        users.setFirstName("John");
        users.setLastName("Mae");
        users.setLastName("Doe");
        assertEquals("John", users.getFirstName());
        assertEquals("Doe", users.getLastName());
    }

}
