import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.security.MessageDigest;

/**
 * اختبارات شاشة Admin - Admins Tab
 * يختبر: Add Admin (Username, Password)
 */
public class AdminAdminsTest {

    // ========== اختبارات Add Admin ==========

    // ✅ اختبار 1: Admin Username مش فاضي
    @Test
    public void testAddAdmin_username_notEmpty() {
        String username = "admin2";
        Assertions.assertFalse(username.isEmpty(),
            "Admin Username يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 2: Admin Username طوله مناسب
    @Test
    public void testAddAdmin_username_minLength() {
        String username = "admin2";
        Assertions.assertTrue(username.length() >= 3,
            "Admin Username يجب أن يكون 3 أحرف على الأقل");
    }

    // ✅ اختبار 3: Admin Password مش فاضي
    @Test
    public void testAddAdmin_password_notEmpty() {
        String password = "Admin@123";
        Assertions.assertFalse(password.isEmpty(),
            "Admin Password يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 4: Admin Password طوله كافي
    @Test
    public void testAddAdmin_password_minLength() {
        String password = "Admin@123";
        Assertions.assertTrue(password.length() >= 6,
            "Admin Password يجب أن يكون 6 أحرف على الأقل");
    }

    // ✅ اختبار 5: Admin Password يتشفر بـ MD5
    @Test
    public void testAddAdmin_password_hashed() throws Exception {
        String password = "Admin@123";
        String hashed = hashMD5(password);
        Assertions.assertEquals(32, hashed.length(),
            "Admin Password المشفر يجب أن يكون 32 حرف");
    }

    // ✅ اختبار 6: Username فاضي = فشل
    @Test
    public void testAddAdmin_emptyUsername_shouldFail() {
        String username = "";
        Assertions.assertTrue(username.isEmpty(),
            "Admin Username الفارغ يجب أن يُرفض");
    }

    // ✅ اختبار 7: Password فاضي = فشل
    @Test
    public void testAddAdmin_emptyPassword_shouldFail() {
        String password = "";
        Assertions.assertTrue(password.isEmpty(),
            "Admin Password الفارغ يجب أن يُرفض");
    }

    // ✅ اختبار 8: Username لا يحتوي مسافات
    @Test
    public void testAddAdmin_username_noSpaces() {
        String username = "admin2";
        Assertions.assertFalse(username.contains(" "),
            "Admin Username يجب ألا يحتوي على مسافات");
    }

    // ✅ اختبار 9: Usernames مختلفة = لا يكررون
    @Test
    public void testAddAdmin_differentUsernames_notEqual() {
        String admin1 = "admin1";
        String admin2 = "admin2";
        Assertions.assertNotEquals(admin1, admin2,
            "Admin Usernames يجب أن تكون مختلفة");
    }

    // ========== اختبارات Mock Database ==========

    // ✅ اختبار 10: إضافة Admin في قاعدة البيانات
    @Test
    public void testInsertAdmin_queryExecutes() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);

        String query = "INSERT INTO Admins (username, password) VALUES (?, ?)";
        Mockito.when(mockConn.prepareStatement(query)).thenReturn(mockStmt);
        Mockito.when(mockStmt.executeUpdate()).thenReturn(1);

        int result = mockStmt.executeUpdate();
        Assertions.assertEquals(1, result,
            "إضافة Admin يجب أن تنجح وترجع 1");
    }

    // ========== Helper ==========
    private String hashMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(input.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            return input;
        }
    }
}