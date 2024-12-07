package edu.asu.DatabasePart1;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArticleDBHelperTest {

    private static ArticleDBHelper dbHelper;

    @BeforeAll
    public static void setUpBeforeAll() {
        dbHelper = new ArticleDBHelper();
        try {
            dbHelper.connectToDatabase();
        } catch (SQLException e) {
            fail("Failed to connect to database: " + e.getMessage());
        }
    }

    @BeforeEach
    public void setUpBeforeEach() {
        // Clear the articles table to ensure a known state before each test
        dbHelper.clearArticleTable();
    }

    @AfterAll
    public static void tearDownAfterAll() {
        dbHelper.closeConnection();
    }

    @Test
    public void testDatabaseInitiallyEmpty() {
        try {
            int count = dbHelper.countArticles();
            assertEquals(0, count, "There should be no articles after clearing the table.");
        } catch (SQLException e) {
            fail("SQLException occurred while counting articles: " + e.getMessage());
        }
    }

    @Test
    public void testStoreArticle() {
        try {
            dbHelper.storeArticle("Undergrad", "Sample Title", "John Doe", "An article abstract", "keyword", "Article body", "References", "GroupA");
            int count = dbHelper.countArticles();
            assertEquals(1, count, "There should be one article in the database.");
        } catch (Exception e) {
            fail("Exception occurred while storing the article: " + e.getMessage());
        }
    }

    @Test
    public void testListAllArticles() {
        try {
            // Insert multiple articles
            dbHelper.storeArticle("Undergrad", "Title1", "Author1", "Abstract1", "Keywords1", "Body1", "Refs1", "GroupA");
            dbHelper.storeArticle("Grad", "Title2", "Author2", "Abstract2", "Keywords2", "Body2", "Refs2", "GroupB");

            // Counting should be 2 now
            assertEquals(2, dbHelper.countArticles(), "Should have two articles after inserting two.");

            // Attempt to list all "non-special" articles
            List<String[]> articles = dbHelper.listAllArticles();
            assertFalse(articles.isEmpty(), "listAllArticles should return at least one article.");
        } catch (Exception e) {
            fail("Exception occurred while listing articles: " + e.getMessage());
        }
    }

    @Test
    public void testViewArticle() {
        try {
            // Insert an article
            dbHelper.storeArticle("Undergrad", "Test View Title", "Jane Doe", "View Abstract", "ViewKeyword", "View Body", "View Refs", "GroupC");

            // Retrieve its uid by listing all articles
            List<String[]> articles = dbHelper.listArticlesByGroups("GroupC");
            assertFalse(articles.isEmpty(), "Should have at least one article in GroupC.");

            long uid = Long.parseLong(articles.get(0)[0]);

            List<String[]> viewedArticle = dbHelper.viewArticle(uid);
            assertFalse(viewedArticle.isEmpty(), "viewArticle should return the inserted article.");
            assertEquals("Test View Title", viewedArticle.get(0)[2], "The title should match the inserted article's title.");
        } catch (Exception e) {
            fail("Exception occurred while viewing the article: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteArticle() {
        try {
            // Insert an article
            dbHelper.storeArticle("PhD", "Delete This Title", "DeleteAuthor", "DeleteAbstract", "DeleteKeywords", "DeleteBody", "DeleteRefs", "GroupD");

            // Ensure it's stored
            assertEquals(1, dbHelper.countArticles(), "Should have one article after insertion.");

            // Retrieve its uid
            List<String[]> articles = dbHelper.listArticlesByGroups("GroupD");
            long uid = Long.parseLong(articles.get(0)[0]);

            // Delete the article
            dbHelper.deleteArticle(uid);
            assertEquals(0, dbHelper.countArticles(), "Should have zero articles after deletion.");
        } catch (Exception e) {
            fail("Exception occurred while deleting article: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateArticle() {
        try {
            // Insert an article
            dbHelper.storeArticle("Undergrad", "Old Title", "AuthorOld", "AbstractOld", "KeywordOld", "BodyOld", "RefsOld", "GroupE");
            assertEquals(1, dbHelper.countArticles(), "Should have one article.");

            // Retrieve its uid
            List<String[]> articles = dbHelper.listArticlesByGroups("GroupE");
            long uid = Long.parseLong(articles.get(0)[0]);

            // Update the article
            dbHelper.updateArticle(uid, "Grad", "New Title", "AuthorNew", "AbstractNew", "KeywordNew", "BodyNew", "RefsNew", "GroupF");

            // Now check if the article has updated values
            List<String[]> updatedArticle = dbHelper.viewArticle(uid);
            assertFalse(updatedArticle.isEmpty(), "Updated article should still exist.");
            String[] data = updatedArticle.get(0);
            // data = [uid, level, title, author, abstracts, keywords, body, references, groupName]
            assertEquals("Grad", data[1], "Level should be updated.");
            assertEquals("New Title", data[2], "Title should be updated.");
            assertEquals("AuthorNew", data[3], "Author should be updated.");
            assertEquals("AbstractNew", data[4], "Abstract should be updated.");
            assertEquals("KeywordNew", data[5], "Keywords should be updated.");
            assertEquals("BodyNew", data[6], "Body should be updated.");
            assertEquals("RefsNew", data[7], "References should be updated.");
        } catch (Exception e) {
            fail("Exception occurred while updating article: " + e.getMessage());
        }
    }
}
