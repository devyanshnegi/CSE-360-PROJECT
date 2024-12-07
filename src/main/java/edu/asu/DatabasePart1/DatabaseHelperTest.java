package edu.asu.DatabasePart1;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DatabaseHelperTest {

    private static DatabaseHelper dbHelper;

    @BeforeAll
    public static void setupBeforeAll() {
        dbHelper = new DatabaseHelper();
        try {
            dbHelper.connectToDatabase();
        } catch (SQLException e) {
            fail("Failed to connect to database: " + e.getMessage());
        }
    }

    @BeforeEach
    public void setupBeforeEach() {
        // Clear the user table before each test to ensure a clean state
        dbHelper.clearUserTable();
    }

    @AfterAll
    public static void tearDownAfterAll() {
        dbHelper.closeConnection();
    }

    @Test
    public void testDatabaseIsInitiallyEmpty() {
        try {
            // After clearing, the database should be empty
            boolean isEmpty = dbHelper.isDatabaseEmpty();
            assertTrue(isEmpty, "Database should be empty after setup");
        } catch (SQLException e) {
            fail("SQL Exception checking empty database: " + e.getMessage());
        }
    }

    @Test
    public void testAddStudent() {
        String username = "studentUser";
        boolean added = dbHelper.addStudent(username);
        assertTrue(added, "Student should be added successfully");
        assertTrue(dbHelper.doesUserExist(username), "Student should now exist in the database");
    }

    @Test
    public void testAddAndCheckOTP() {
        int testOtp = 123456;
        dbHelper.storeOTP("admin", testOtp);
        assertTrue(dbHelper.isOtpValid(testOtp), "OTP should now be valid");
    }

    @Test
    public void testRegisterUserWithOtp() throws SQLException {
        int testOtp = 654321;
        dbHelper.storeOTP("admin", testOtp);

        String username = "newUser";
        String password = "pass123";
        boolean registered = dbHelper.register(username, password, testOtp);
        assertTrue(registered, "User should be registered successfully");
        assertTrue(dbHelper.doesUserExist(username), "New user should exist now");
        
        // Check login
        assertTrue(dbHelper.login(username, password), "User should be able to login");
    }

    @Test
    public void testRoleSetting() throws SQLException {
        String username = "roleTestUser";
        dbHelper.addStudent(username);
        assertEquals("student", dbHelper.getRole(username), "Initially user should be a student");

        boolean updated = dbHelper.editRole(username, "instructor");
        assertTrue(updated, "Role should be updated successfully");

        assertEquals("instructor", dbHelper.getRole(username), "Role should now be instructor");
    }

    @Test
    public void testViewAccessFunctions() throws SQLException {
        String username = "viewAccessUser";
        dbHelper.addStudent(username);

        boolean setAccess = dbHelper.setViewAccessGroupUser(username, "GroupA");
        assertTrue(setAccess, "Should set view access group successfully");
        assertTrue(dbHelper.doesUserHaveAccess(username, "GroupA"), "User should have GroupA view access");

        dbHelper.setViewAccessGroupUser(username, "GroupB");
        assertTrue(dbHelper.doesUserHaveAccess(username, "GroupB"), "User should have GroupB view access");

        boolean removed = dbHelper.removeViewAccessGroupUser(username, "GroupA");
        assertTrue(removed, "Should remove GroupA from user's view access");
        assertFalse(dbHelper.doesUserHaveAccess(username, "GroupA"), "User should no longer have GroupA view access");
    }

    @Test
    public void testSpecialAccessFunctions() {
        String username = "specialAccessUser";
        dbHelper.addStudent(username);

        boolean setAccess = dbHelper.setSpecialAccessGroupUser(username, "SpecialGroup1");
        assertTrue(setAccess, "Should set special access group successfully");
        assertTrue(dbHelper.doesUserHaveSpecialAccess(username, "SpecialGroup1"), "User should have SpecialGroup1 access");

        dbHelper.setSpecialAccessGroupUser(username, "SpecialGroup2");
        assertTrue(dbHelper.doesUserHaveSpecialAccess(username, "SpecialGroup2"), "User should have SpecialGroup2 access");

        boolean removed = dbHelper.removeSpecialAccessGroupUser(username, "SpecialGroup1");
        assertTrue(removed, "Should remove SpecialGroup1 from user's special access");
        assertFalse(dbHelper.doesUserHaveSpecialAccess(username, "SpecialGroup1"), "User should no longer have SpecialGroup1 access");
    }

    @Test
    public void testDeleteUser() {
        String username = "deleteTestUser";
        dbHelper.addStudent(username);
        assertTrue(dbHelper.doesUserExist(username), "User should exist before deletion");

        boolean deleted = dbHelper.deleteUser(username);
        assertTrue(deleted, "User deletion should succeed");
        assertFalse(dbHelper.doesUserExist(username), "User should no longer exist after deletion");
    }
}
