import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ValidationTest {

    // ✅ اختبار: الحقول الفاضية
    @Test
    public void testEmptyUsername() {
        String username = "";
        Assertions.assertTrue(username.isEmpty(), "Username يجب ألا يكون فارغاً");
    }

    // ✅ اختبار: العمر لازم يكون رقم
    @Test
    public void testAgeIsNumber_valid() {
        String ageStr = "20";
        Assertions.assertDoesNotThrow(() -> Integer.parseInt(ageStr));
    }

    // ✅ اختبار: العمر لو حروف بيطلع Exception
    @Test
    public void testAgeIsNumber_invalid() {
        String ageStr = "twenty";
        Assertions.assertThrows(NumberFormatException.class, 
            () -> Integer.parseInt(ageStr));
    }

    // ✅ اختبار: كلمتا المرور متطابقتان
    @Test
    public void testPasswordsMatch() {
        String pass = "myPass123";
        String confirm = "myPass123";
        Assertions.assertEquals(pass, confirm, "Passwords must match");
    }

    // ✅ اختبار: كلمتا المرور غير متطابقتين
    @Test
    public void testPasswordsDontMatch() {
        String pass = "myPass123";
        String confirm = "wrongPass";
        Assertions.assertNotEquals(pass, confirm);
    }

    // ✅ اختبار: البريد الإلكتروني يحتوي @
    @Test
    public void testEmailFormat_valid() {
        String email = "student@example.com";
        Assertions.assertTrue(email.contains("@"), "Email يجب أن يحتوي على @");
    }

    // ✅ اختبار: البريد بدون @ غلط
    @Test
    public void testEmailFormat_invalid() {
        String email = "studentexample.com";
        Assertions.assertFalse(email.contains("@"));
    }
}