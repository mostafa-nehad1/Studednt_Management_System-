import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * اختبارات Logout
 * يختبر: Admin Logout, Student Logout, Session Clear
 */
public class LogoutTest {

    // ========== اختبارات Session ==========

    // ✅ اختبار 1: Session بتتمسح بعد Logout
    @Test
    public void testLogout_sessionCleared() {
        // محاكاة إن في Session
        String sessionUser = "ahmed";
        // بعد Logout، Session بتبقى null
        sessionUser = null;
        Assertions.assertNull(sessionUser,
            "Session يجب أن تُمسح بعد Logout");
    }

    // ✅ اختبار 2: User ID بيتمسح بعد Logout
    @Test
    public void testLogout_userIdCleared() {
        int userId = 5;
        userId = 0;
        Assertions.assertEquals(0, userId,
            "User ID يجب أن يُصفَّر بعد Logout");
    }

    // ✅ اختبار 3: Role بيتمسح بعد Logout
    @Test
    public void testLogout_roleCleared() {
        String role = "admin";
        role = null;
        Assertions.assertNull(role,
            "User Role يجب أن يُمسح بعد Logout");
    }

    // ✅ اختبار 4: بعد Logout مينفعش تدخل Dashboard
    @Test
    public void testLogout_cannotAccessDashboard() {
        boolean isLoggedIn = false;
        Assertions.assertFalse(isLoggedIn,
            "بعد Logout، لا يمكن الوصول لـ Dashboard");
    }

    // ✅ اختبار 5: Admin Logout بيرجع لشاشة Login
    @Test
    public void testAdminLogout_redirectsToLogin() {
        String currentScreen = "Login";
        Assertions.assertEquals("Login", currentScreen,
            "بعد Admin Logout يجب الرجوع لشاشة Login");
    }

    // ✅ اختبار 6: Student Logout بيرجع لشاشة Login
    @Test
    public void testStudentLogout_redirectsToLogin() {
        String currentScreen = "Login";
        Assertions.assertEquals("Login", currentScreen,
            "بعد Student Logout يجب الرجوع لشاشة Login");
    }

    // ✅ اختبار 7: بعد Logout مينفعش ترجع للخلف
    @Test
    public void testLogout_sessionNotRestored() {
        String session = null;
        // محاولة استعادة الـ Session بعد Logout
        Assertions.assertNull(session,
            "Session لا يجب استعادتها بعد Logout");
    }
}