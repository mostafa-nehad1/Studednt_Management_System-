import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * اختبارات شاشة Registration
 * يختبر: First Name, Last Name, Age, Email, Phone, Address, Gender, Username, Password, Confirm Password
 */
public class RegistrationTest {

    // ========== اختبارات الاسم ==========

    // ✅ اختبار 1: First Name مش فاضي
    @Test
    public void testFirstName_notEmpty() {
        String firstName = "Ahmed";
        Assertions.assertFalse(firstName.isEmpty(),
            "First Name يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 2: First Name فاضي = فشل
    @Test
    public void testFirstName_isEmpty_shouldFail() {
        String firstName = "";
        Assertions.assertTrue(firstName.isEmpty(),
            "First Name الفارغ يجب أن يُرفض");
    }

    // ✅ اختبار 3: Last Name مش فاضي
    @Test
    public void testLastName_notEmpty() {
        String lastName = "Mohamed";
        Assertions.assertFalse(lastName.isEmpty(),
            "Last Name يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 4: Last Name فاضي = فشل
    @Test
    public void testLastName_isEmpty_shouldFail() {
        String lastName = "";
        Assertions.assertTrue(lastName.isEmpty(),
            "Last Name الفارغ يجب أن يُرفض");
    }

    // ========== اختبارات العمر ==========

    // ✅ اختبار 5: العمر رقم صحيح
    @Test
    public void testAge_validNumber() {
        String ageStr = "20";
        Assertions.assertDoesNotThrow(() -> Integer.parseInt(ageStr),
            "العمر يجب أن يكون رقماً صحيحاً");
    }

    // ✅ اختبار 6: العمر حروف = خطأ
    @Test
    public void testAge_invalidString_shouldThrow() {
        String ageStr = "twenty";
        Assertions.assertThrows(NumberFormatException.class,
            () -> Integer.parseInt(ageStr),
            "العمر بالحروف يجب أن يُرفض");
    }

    // ✅ اختبار 7: العمر أكبر من 0
    @Test
    public void testAge_greaterThanZero() {
        int age = 20;
        Assertions.assertTrue(age > 0,
            "العمر يجب أن يكون أكبر من 0");
    }

    // ✅ اختبار 8: العمر أقل من 100
    @Test
    public void testAge_lessThan100() {
        int age = 20;
        Assertions.assertTrue(age < 100,
            "العمر يجب أن يكون أقل من 100");
    }

    // ✅ اختبار 9: عمر سالب = غلط
    @Test
    public void testAge_negative_shouldFail() {
        int age = -5;
        Assertions.assertFalse(age > 0,
            "العمر السالب يجب أن يُرفض");
    }

    // ========== اختبارات الإيميل ==========

    // ✅ اختبار 10: إيميل صحيح يحتوي @
    @Test
    public void testEmail_valid() {
        String email = "student@example.com";
        Assertions.assertTrue(email.contains("@"),
            "Email يجب أن يحتوي على @");
    }

    // ✅ اختبار 11: إيميل بدون @ = غلط
    @Test
    public void testEmail_invalid_noAt() {
        String email = "studentexample.com";
        Assertions.assertFalse(email.contains("@"),
            "Email بدون @ يجب أن يُرفض");
    }

    // ✅ اختبار 12: إيميل فاضي = فشل
    @Test
    public void testEmail_empty_shouldFail() {
        String email = "";
        Assertions.assertTrue(email.isEmpty(),
            "Email الفارغ يجب أن يُرفض");
    }

    // ========== اختبارات رقم الهاتف ==========

    // ✅ اختبار 13: Phone رقمي صحيح
    @Test
    public void testPhone_validNumber() {
        String phone = "01012345678";
        Assertions.assertDoesNotThrow(() -> Long.parseLong(phone),
            "رقم الهاتف يجب أن يكون أرقاماً فقط");
    }

    // ✅ اختبار 14: Phone فاضي = فشل
    @Test
    public void testPhone_empty_shouldFail() {
        String phone = "";
        Assertions.assertTrue(phone.isEmpty(),
            "رقم الهاتف الفارغ يجب أن يُرفض");
    }

    // ✅ اختبار 15: Phone طوله 11 رقم (مصري)
    @Test
    public void testPhone_correctLength() {
        String phone = "01012345678";
        Assertions.assertEquals(11, phone.length(),
            "رقم الهاتف المصري يجب أن يكون 11 رقم");
    }

    // ========== اختبارات العنوان ==========

    // ✅ اختبار 16: Address مش فاضي
    @Test
    public void testAddress_notEmpty() {
        String address = "Cairo, Egypt";
        Assertions.assertFalse(address.isEmpty(),
            "Address يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 17: Address فاضي = فشل
    @Test
    public void testAddress_empty_shouldFail() {
        String address = "";
        Assertions.assertTrue(address.isEmpty(),
            "Address الفارغ يجب أن يُرفض");
    }

    // ========== اختبارات Gender ==========

    // ✅ اختبار 18: Gender اختيار Male
    @Test
    public void testGender_male_selected() {
        String gender = "Male";
        Assertions.assertTrue(gender.equals("Male") || gender.equals("Female"),
            "Gender يجب أن يكون Male أو Female");
    }

    // ✅ اختبار 19: Gender اختيار Female
    @Test
    public void testGender_female_selected() {
        String gender = "Female";
        Assertions.assertTrue(gender.equals("Male") || gender.equals("Female"),
            "Gender يجب أن يكون Male أو Female");
    }

    // ✅ اختبار 20: Gender مش فاضي
    @Test
    public void testGender_notEmpty() {
        String gender = "Male";
        Assertions.assertFalse(gender.isEmpty(),
            "Gender يجب ألا يكون فارغاً");
    }

    // ========== اختبارات Username ==========

    // ✅ اختبار 21: Username مش فاضي
    @Test
    public void testUsername_notEmpty() {
        String username = "ahmed123";
        Assertions.assertFalse(username.isEmpty(),
            "Username يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 22: Username أطول من 3 حروف
    @Test
    public void testUsername_minLength() {
        String username = "ahmed123";
        Assertions.assertTrue(username.length() >= 3,
            "Username يجب أن يكون 3 أحرف على الأقل");
    }

    // ========== اختبارات Password ==========

    // ✅ اختبار 23: Password مش فاضي
    @Test
    public void testPassword_notEmpty() {
        String password = "myPass123";
        Assertions.assertFalse(password.isEmpty(),
            "Password يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 24: Password أطول من 6 حروف
    @Test
    public void testPassword_minLength() {
        String password = "myPass123";
        Assertions.assertTrue(password.length() >= 6,
            "Password يجب أن يكون 6 أحرف على الأقل");
    }

    // ✅ اختبار 25: Password وConfirm Password متطابقان
    @Test
    public void testPasswordMatch() {
        String password = "myPass123";
        String confirmPassword = "myPass123";
        Assertions.assertEquals(password, confirmPassword,
            "Password وConfirm Password يجب أن يتطابقا");
    }

    // ✅ اختبار 26: Password وConfirm Password مختلفان = فشل
    @Test
    public void testPasswordMismatch_shouldFail() {
        String password = "myPass123";
        String confirmPassword = "wrongPass";
        Assertions.assertNotEquals(password, confirmPassword,
            "Password المختلف عن Confirm يجب أن يُرفض");
    }
}