package com.example.chermn;

import com.example.chermn.model.Users;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Users} model class, validating field accessors,
 * mutators, constructor behaviour, and input‑validation rules.
 *
 * <p>This test suite covers:
 * <ul>
 *     <li>Username validation (null, empty, valid updates)</li>
 *     <li>First and last name validation (capitalisation, digits, spaces, special characters)</li>
 *     <li>Password validation (length, null, empty)</li>
 *     <li>School name validation (formatting, invalid characters)</li>
 *     <li>Constructor behaviour for both full and partial constructors</li>
 *     <li>Multiple sequential updates to ensure state consistency</li>
 * </ul>
 *
 * <p>Each test begins with a fresh {@link Users} instance to ensure isolation.
 */
public class UsersTest {

    /** A reusable Users instance created before each test. */
    private Users users;

    /**
     * Creates a new {@link Users} object before each test.
     */
    @BeforeEach
    public void setUp() {
        users = new Users(1, "rmull", "Rebecca", "Mullock", "12345", "QUT");
    }

    // ============================================================
    // ID TESTS
    // ============================================================

    /**
     * Ensures that the initial ID value is returned correctly.
     */
    @Test
    public void testGetId_initialValue() {
        assertEquals(1, users.getid());
    }

    // ============================================================
    // USERNAME TESTS
    // ============================================================

    /**
     * Verifies that the initial username is returned correctly.
     */
    @Test
    public void testGetUsername_initialValue() {
        assertEquals("rmull", users.getUserName());
    }

    /**
     * Ensures that a valid username update is applied.
     */
    @Test
    public void testSetUsername_valid() {
        users.setUsername("newUser");
        assertEquals("newUser", users.getUserName());
    }

    /**
     * Ensures that setting a null username throws an exception.
     */
    @Test
    public void testSetUsername_null() {
        assertThrows(IllegalArgumentException.class, () -> users.setUsername(null));
    }

    /**
     * Ensures that setting an empty username throws an exception.
     */
    @Test
    public void testSetUsername_empty() {
        assertThrows(IllegalArgumentException.class, () -> users.setUsername(""));
    }

    // ============================================================
    // FIRST NAME TESTS
    // ============================================================

    /**
     * Ensures that the initial first name is returned correctly.
     */
    @Test
    public void testGetFirstName_initialValue() {
        assertEquals("Rebecca", users.getFirstName());
    }

    /**
     * Ensures that a valid first name update is applied.
     */
    @Test
    public void testSetFirstName_valid() {
        users.setFirstName("Alice");
        assertEquals("Alice", users.getFirstName());
    }

    /**
     * Ensures that lowercase names are automatically capitalised.
     */
    @Test
    public void testSetFirstName_lowercase() {
        users.setFirstName("alice");
        assertEquals("Alice", users.getFirstName());
    }

    /**
     * Ensures that null first names are rejected.
     */
    @Test
    public void testSetFirstName_null() {
        assertThrows(IllegalArgumentException.class, () -> users.setFirstName(null));
    }

    /**
     * Ensures that names containing spaces are rejected.
     */
    @Test
    public void testSetFirstName_noSpaces() {
        assertThrows(IllegalArgumentException.class, () -> users.setFirstName("John Smith"));
    }

    /**
     * Ensures that names containing digits are rejected.
     */
    @Test
    public void testSetFirstName_noDigits() {
        assertThrows(IllegalArgumentException.class, () -> users.setFirstName("John9"));
    }

    /**
     * Ensures that names containing special characters are rejected.
     */
    @Test
    public void testSetFirstName_noSpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> users.setFirstName("John@#&"));
    }

    /**
     * Ensures that whitespace‑only names are rejected.
     */
    @Test
    public void testSetFirstName_whitespace() {
        assertThrows(IllegalArgumentException.class, () -> users.setFirstName("      "));
    }

    // ============================================================
    // LAST NAME TESTS
    // ============================================================

    /**
     * Ensures that the initial last name is returned correctly.
     */
    @Test
    public void testGetLastName_initialValue() {
        assertEquals("Mullock", users.getLastName());
    }

    /**
     * Ensures that a valid last name update is applied.
     */
    @Test
    public void testSetLastName_valid() {
        users.setLastName("Brown");
        assertEquals("Brown", users.getLastName());
    }

    /**
     * Ensures that lowercase last names are capitalised.
     */
    @Test
    public void testSetLastName_lowercase() {
        users.setLastName("brown");
        assertEquals("Brown", users.getLastName());
    }

    /**
     * Ensures that null last names are rejected.
     */
    @Test
    public void testSetLastName_null() {
        assertThrows(IllegalArgumentException.class, () -> users.setLastName(null));
    }

    /**
     * Ensures that last names containing spaces are rejected.
     */
    @Test
    public void testSetLastName_noSpaces() {
        assertThrows(IllegalArgumentException.class, () -> users.setLastName("John Smith"));
    }

    /**
     * Ensures that last names containing digits are rejected.
     */
    @Test
    public void testSetLastName_noDigits() {
        assertThrows(IllegalArgumentException.class, () -> users.setLastName("Smith10"));
    }

    /**
     * Ensures that last names containing special characters are rejected.
     */
    @Test
    public void testSetLastName_noSpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> users.setLastName("Smith^?/"));
    }

    /**
     * Ensures that whitespace‑only last names are rejected.
     */
    @Test
    public void testSetLastName_whitespace() {
        assertThrows(IllegalArgumentException.class, () -> users.setLastName("      "));
    }

    // ============================================================
    // PASSWORD TESTS
    // ============================================================

    /**
     * Ensures that the initial password is returned correctly.
     */
    @Test
    public void testGetPassword_initialValue() {
        assertEquals("12345", users.getPassword());
    }

    /**
     * Ensures that a valid password update is applied.
     */
    @Test
    public void testSetPassword_updatesValue() {
        users.setPassword("54321");
        assertEquals("54321", users.getPassword());
    }

    /**
     * Ensures that empty passwords are rejected.
     */
    @Test
    public void testSetPassword_emptyString() {
        assertThrows(IllegalArgumentException.class, () -> users.setPassword(""));
    }

    /**
     * Ensures that null passwords are rejected.
     */
    @Test
    public void testSetPassword_null() {
        assertThrows(IllegalArgumentException.class, () -> users.setPassword(null));
    }

    /**
     * Ensures that passwords shorter than the minimum length are rejected.
     */
    @Test
    public void testSetPassword_tooShort() {
        assertThrows(IllegalArgumentException.class, () -> users.setPassword("123"));
    }

    /**
     * Ensures that the minimum valid password length is accepted.
     */
    @Test
    public void testSetPassword_minLength() {
        users.setPassword("12345");
        assertEquals("12345", users.getPassword());
    }

    // ============================================================
    // SCHOOL NAME TESTS
    // ============================================================

    /**
     * Ensures that null school names are rejected.
     */
    @Test
    public void testSetSchoolName_null() {
        assertThrows(IllegalArgumentException.class, () -> users.setSchoolName(null));
    }

    /**
     * Ensures that school names containing digits are rejected.
     */
    @Test
    public void testSetSchoolName_noDigits() {
        assertThrows(IllegalArgumentException.class, () -> users.setSchoolName("Gladstone State 921023"));
    }

    /**
     * Ensures that the initial school name is formatted correctly.
     */
    @Test
    public void testGetSchoolName_initialValue() {
        assertEquals("Qut", users.getSchoolName());
    }

    /**
     * Ensures that valid school names are capitalised correctly.
     */
    @Test
    public void testSetSchoolName_valid() {
        users.setSchoolName("primary school");
        assertEquals("Primary School", users.getSchoolName());
    }

    // ============================================================
    // CONSTRUCTOR TESTS
    // ============================================================

    /**
     * Verifies that the simplified constructor correctly initialises fields.
     */
    @Test
    public void testUserConstructor_valid() {
        Users user1 = new Users("user1", "John", "Doe", "12345", "QUT");
        assertEquals("user1", user1.getUserName());
        assertEquals("John", user1.getFirstName());
        assertEquals("Doe", user1.getLastName());
        assertEquals("12345", user1.getPassword());
        assertEquals("Qut", user1.getSchoolName());
    }

    /**
     * Ensures that invalid first names cause constructor failure.
     */
    @Test
    public void testUserConstructor_invalidFirstName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Users("user1", "John1", "Doe", "12345", "QUT"));
    }

    // ============================================================
    // MULTIPLE UPDATE TESTS
    // ============================================================

    /**
     * Ensures that multiple valid updates apply correctly.
     */
    @Test
    public void testMultipleUpdates_valid() {
        users.setFirstName("Alice");
        users.setLastName("Doe");
        assertEquals("Alice", users.getFirstName());
        assertEquals("Doe", users.getLastName());
    }

    /**
     * Ensures that overwritten updates reflect the latest values.
     */
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