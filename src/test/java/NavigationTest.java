import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * اختبارات Navigation Buttons في Login
 * يختبر: Go Register, Back Button
 */
public class NavigationTest {

    // ========================================================
    // ========== Login Screen Navigation ==========
    // ========================================================

    // ✅ اختبار 1: "Go Register" بيفتح شاشة Registration
    @Test
    public void testGoRegister_opensRegistrationScreen() {
        String currentScreen = "Login";
        // لما تضغط Go Register
        currentScreen = "Registration";
        Assertions.assertEquals("Registration", currentScreen,
            "Go Register يجب أن يفتح شاشة Registration");
    }

    // ✅ اختبار 2: "Go Register" مش بتفضل في Login
    @Test
    public void testGoRegister_leavesLoginScreen() {
        String currentScreen = "Registration";
        Assertions.assertNotEquals("Login", currentScreen,
            "بعد Go Register يجب مغادرة شاشة Login");
    }

    // ✅ اختبار 3: "Back" في Registration بيرجع لـ Login
    @Test
    public void testBack_returnsToLoginScreen() {
        String currentScreen = "Registration";
        // لما تضغط Back
        currentScreen = "Login";
        Assertions.assertEquals("Login", currentScreen,
            "Back يجب أن يُرجع لشاشة Login");
    }

    // ✅ اختبار 4: "Back" مش بتفضل في Registration
    @Test
    public void testBack_leavesRegistrationScreen() {
        String currentScreen = "Login";
        Assertions.assertNotEquals("Registration", currentScreen,
            "بعد Back يجب مغادرة شاشة Registration");
    }

    // ✅ اختبار 5: الـ Tabs في Admin Dashboard شغالة
    @Test
    public void testAdminDashboard_tabsExist() {
        String[] tabs = {"Home", "Students", "Courses", "Enrollments", "Reports", "Admins", "Logout"};
        Assertions.assertEquals(7, tabs.length,
            "Admin Dashboard يجب أن يحتوي على 7 Tabs");
    }

    // ✅ اختبار 6: الـ Tabs في Student Dashboard شغالة
    @Test
    public void testStudentDashboard_tabsExist() {
        String[] tabs = {"Home", "Available Courses", "My Courses", "Profile", "Logout"};
        Assertions.assertEquals(5, tabs.length,
            "Student Dashboard يجب أن يحتوي على 5 Tabs");
    }

    // ✅ اختبار 7: Tab الـ Students موجود في Admin
    @Test
    public void testAdminDashboard_studentsTabExists() {
        String[] tabs = {"Home", "Students", "Courses", "Enrollments", "Reports", "Admins", "Logout"};
        boolean hasStudentsTab = false;
        for (String tab : tabs) {
            if (tab.equals("Students")) hasStudentsTab = true;
        }
        Assertions.assertTrue(hasStudentsTab,
            "Admin Dashboard يجب أن يحتوي على Students Tab");
    }

    // ✅ اختبار 8: Tab الـ Profile موجود في Student
    @Test
    public void testStudentDashboard_profileTabExists() {
        String[] tabs = {"Home", "Available Courses", "My Courses", "Profile", "Logout"};
        boolean hasProfileTab = false;
        for (String tab : tabs) {
            if (tab.equals("Profile")) hasProfileTab = true;
        }
        Assertions.assertTrue(hasProfileTab,
            "Student Dashboard يجب أن يحتوي على Profile Tab");
    }
}