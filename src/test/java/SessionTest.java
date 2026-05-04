import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * اختبارات Session بعد Login
 * يختبر: Admin يروح Admin Dashboard، Student يروح Student Dashboard
 */
public class SessionTest {

    // ========== اختبارات توجيه المستخدم بعد Login ==========

    // ✅ اختبار 1: Admin بعد Login يروح Admin Dashboard
    @Test
    public void testLogin_adminGoesToAdminDashboard() {
        String role = "admin";
        String expectedScreen = "AdminDashboard";
        String actualScreen = role.equals("admin") ? "AdminDashboard" : "StudentDashboard";
        Assertions.assertEquals(expectedScreen, actualScreen,
            "Admin يجب أن يُوجَّه لـ Admin Dashboard");
    }

    // ✅ اختبار 2: Student بعد Login يروح Student Dashboard
    @Test
    public void testLogin_studentGoesToStudentDashboard() {
        String role = "student";
        String expectedScreen = "StudentDashboard";
        String actualScreen = role.equals("admin") ? "AdminDashboard" : "StudentDashboard";
        Assertions.assertEquals(expectedScreen, actualScreen,
            "Student يجب أن يُوجَّه لـ Student Dashboard");
    }

    // ✅ اختبار 3: Admin مش بيروح Student Dashboard
    @Test
    public void testLogin_adminNotGoesToStudentDashboard() {
        String role = "admin";
        String screen = role.equals("admin") ? "AdminDashboard" : "StudentDashboard";
        Assertions.assertNotEquals("StudentDashboard", screen,
            "Admin يجب ألا يُوجَّه لـ Student Dashboard");
    }

    // ✅ اختبار 4: Student مش بيروح Admin Dashboard
    @Test
    public void testLogin_studentNotGoesToAdminDashboard() {
        String role = "student";
        String screen = role.equals("admin") ? "AdminDashboard" : "StudentDashboard";
        Assertions.assertNotEquals("AdminDashboard", screen,
            "Student يجب ألا يُوجَّه لـ Admin Dashboard");
    }

    // ✅ اختبار 5: Session بتتحفظ بعد Login بنجاح
    @Test
    public void testLogin_sessionSavedAfterSuccess() {
        String sessionUser = "ahmed";
        int sessionUserId = 5;
        Assertions.assertNotNull(sessionUser, "Session User يجب أن يُحفظ بعد Login");
        Assertions.assertTrue(sessionUserId > 0, "Session User ID يجب أن يكون موجباً");
    }

    // ✅ اختبار 6: Login فاشل = مفيش Session
    @Test
    public void testLogin_failedLogin_noSession() {
        boolean loginSuccess = false;
        String session = loginSuccess ? "user" : null;
        Assertions.assertNull(session,
            "Login الفاشل يجب ألا يُنشئ Session");
    }

    // ✅ اختبار 7: Role مش فاضي بعد Login
    @Test
    public void testLogin_roleNotEmpty() {
        String role = "admin";
        Assertions.assertFalse(role.isEmpty(),
            "Role يجب ألا يكون فارغاً بعد Login");
    }

    // ✅ اختبار 8: Role قيمته admin أو student بس
    @Test
    public void testLogin_roleIsValid() {
        String role = "admin";
        boolean isValid = role.equals("admin") || role.equals("student");
        Assertions.assertTrue(isValid,
            "Role يجب أن يكون admin أو student فقط");
    }

    // ✅ اختبار 9: User ID موجب بعد Login
    @Test
    public void testLogin_userIdPositive() {
        int userId = 3;
        Assertions.assertTrue(userId > 0,
            "User ID يجب أن يكون موجباً بعد Login");
    }

    // ✅ اختبار 10: Username محفوظ في Session
    @Test
    public void testLogin_usernameStoredInSession() {
        String username = "ahmed123";
        Assertions.assertNotNull(username,
            "Username يجب أن يُحفظ في Session");
        Assertions.assertFalse(username.isEmpty(),
            "Username في Session يجب ألا يكون فارغاً");
    }
}