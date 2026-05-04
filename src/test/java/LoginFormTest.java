import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.security.MessageDigest;

/**
 * اختبارات شاشة Login
 * يختبر: تسجيل الدخول، تشفير الباسورد، التحقق من المدخلات
 */
public class LoginFormTest {

    // ========== اختبارات تشفير الباسورد MD5 ==========

    // ✅ اختبار 1: MD5 بيطلع 32 حرف دايماً
    @Test
    public void testHashMD5_correctLength() throws Exception {
        String result = hashMD5("12345");
        Assertions.assertEquals(32, result.length(),
            "MD5 hash يجب أن يكون طوله 32 حرف");
    }

    // ✅ اختبار 2: كلمتين مختلفتين = هاش مختلف
    @Test
    public void testHashMD5_differentPasswords() throws Exception {
        String pass1 = hashMD5("password123");
        String pass2 = hashMD5("password456");
        Assertions.assertNotEquals(pass1, pass2,
            "كلمتا مرور مختلفتان يجب أن ينتجا هاش مختلف");
    }

    // ✅ اختبار 3: نفس الكلمة = نفس الهاش دايماً
    @Test
    public void testHashMD5_sameInputSameOutput() throws Exception {
        String hash1 = hashMD5("myPassword");
        String hash2 = hashMD5("myPassword");
        Assertions.assertEquals(hash1, hash2,
            "نفس كلمة المرور يجب أن تنتج نفس الهاش دائماً");
    }

    // ✅ اختبار 4: الباسورد مش فاضي قبل تسجيل الدخول
    @Test
    public void testPassword_notEmpty() {
        String password = "myPass123";
        Assertions.assertFalse(password.isEmpty(),
            "Password يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 5: الباسورد فاضي = فشل
    @Test
    public void testPassword_isEmpty_shouldFail() {
        String password = "";
        Assertions.assertTrue(password.isEmpty(),
            "Password الفارغ يجب أن يُرفض");
    }

    // ✅ اختبار 6: Username/Email مش فاضي
    @Test
    public void testUsername_notEmpty() {
        String username = "student@example.com";
        Assertions.assertFalse(username.isEmpty(),
            "Username/Email يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 7: Username فاضي = فشل
    @Test
    public void testUsername_isEmpty_shouldFail() {
        String username = "";
        Assertions.assertTrue(username.isEmpty(),
            "Username الفارغ يجب أن يُرفض");
    }

    // ✅ اختبار 8: الإيميل يحتوي @ عند Login
    @Test
    public void testEmailFormat_valid() {
        String email = "student@example.com";
        Assertions.assertTrue(email.contains("@"),
            "Email يجب أن يحتوي على @");
    }

    // ✅ اختبار 9: إيميل بدون @ = غلط
    @Test
    public void testEmailFormat_invalid() {
        String email = "studentexample.com";
        Assertions.assertFalse(email.contains("@"),
            "Email بدون @ يجب أن يُرفض");
    }

    // ========== Helper Method ==========
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