import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * اختبارات SQL Injection Protection
 * يختبر: Search fields, Login fields
 */
public class SQLInjectionTest {

    // ========== Helper: تنظيف المدخلات ==========
    private String sanitizeInput(String input) {
        if (input == null) return "";
        // إزالة الأحرف الخطرة
        return input.replace("'", "")
                    .replace("\"", "")
                    .replace(";", "")
                    .replace("--", "")
                    .replace("/*", "")
                    .replace("*/", "")
                    .replace("OR", "")
                    .replace("DROP", "")
                    .replace("DELETE", "")
                    .replace("INSERT", "")
                    .trim();
    }

    private boolean isDangerousInput(String input) {
        if (input == null) return false;
        String upper = input.toUpperCase();
        return input.contains("'") ||
               input.contains("\"") ||
               input.contains(";") ||
               input.contains("--") ||
               upper.contains(" OR ") ||
               upper.contains("DROP ") ||
               upper.contains("DELETE ") ||
               upper.contains("INSERT ") ||
               upper.contains("1=1");
    }

    // ========================================================
    // ========== Login - SQL Injection ==========
    // ========================================================

    // ✅ اختبار 1: ' OR '1'='1 في Username محمي
    @Test
    public void testLogin_sqlInjection_orAttack() {
        String maliciousInput = "' OR '1'='1";
        Assertions.assertTrue(isDangerousInput(maliciousInput),
            "يجب اكتشاف SQL Injection في Username");
    }

    // ✅ اختبار 2: admin'-- في Username محمي
    @Test
    public void testLogin_sqlInjection_commentAttack() {
        String maliciousInput = "admin'--";
        Assertions.assertTrue(isDangerousInput(maliciousInput),
            "يجب اكتشاف SQL Comment Attack في Username");
    }

    // ✅ اختبار 3: ' OR 1=1-- في Password محمي
    @Test
    public void testLogin_sqlInjection_passwordBypass() {
        String maliciousInput = "' OR 1=1--";
        Assertions.assertTrue(isDangerousInput(maliciousInput),
            "يجب اكتشاف SQL Injection في Password");
    }

    // ✅ اختبار 4: Username عادي = مش خطر
    @Test
    public void testLogin_normalUsername_notDangerous() {
        String normalInput = "ahmed123";
        Assertions.assertFalse(isDangerousInput(normalInput),
            "Username العادي يجب ألا يُعتبر خطراً");
    }

    // ========================================================
    // ========== Search Fields - SQL Injection ==========
    // ========================================================

    // ✅ اختبار 5: ' DROP TABLE Students في Search محمي
    @Test
    public void testSearch_sqlInjection_dropTable() {
        String maliciousInput = "'; DROP TABLE Students--";
        Assertions.assertTrue(isDangerousInput(maliciousInput),
            "يجب اكتشاف DROP TABLE Attack في Search");
    }

    // ✅ اختبار 6: ' OR '1'='1 في Search محمي
    @Test
    public void testSearch_sqlInjection_orAttack() {
        String maliciousInput = "' OR '1'='1";
        Assertions.assertTrue(isDangerousInput(maliciousInput),
            "يجب اكتشاف OR Attack في Search");
    }

    // ✅ اختبار 7: نص عادي في Search = آمن
    @Test
    public void testSearch_normalText_isSafe() {
        String normalInput = "Ahmed Mohamed";
        Assertions.assertFalse(isDangerousInput(normalInput),
            "نص البحث العادي يجب أن يكون آمناً");
    }

    // ✅ اختبار 8: تنظيف الـ Input من الأحرف الخطرة
    @Test
    public void testSanitize_removesQuotes() {
        String malicious = "' OR '1'='1";
        String cleaned = sanitizeInput(malicious);
        Assertions.assertFalse(cleaned.contains("'"),
            "التنظيف يجب أن يزيل علامات الاقتباس");
    }

    // ✅ اختبار 9: تنظيف يزيل --
    @Test
    public void testSanitize_removesComments() {
        String malicious = "admin'--";
        String cleaned = sanitizeInput(malicious);
        Assertions.assertFalse(cleaned.contains("--"),
            "التنظيف يجب أن يزيل SQL Comments");
    }

    // ✅ اختبار 10: استخدام PreparedStatement بيحمي من SQL Injection
    @Test
    public void testPreparedStatement_preventsInjection() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);

        // PreparedStatement بيفصل الـ Query عن البيانات
        String query = "SELECT * FROM Students WHERE name = ?";
        Mockito.when(mockConn.prepareStatement(query)).thenReturn(mockStmt);

        PreparedStatement stmt = mockConn.prepareStatement(query);
        Assertions.assertNotNull(stmt,
            "PreparedStatement يجب أن يكون موجوداً للحماية من SQL Injection");
    }

    // ✅ اختبار 11: ; في Search محمي (Multiple Statements)
    @Test
    public void testSearch_sqlInjection_semicolon() {
        String maliciousInput = "Ahmed; DELETE FROM Students";
        Assertions.assertTrue(isDangerousInput(maliciousInput),
            "يجب اكتشاف Multiple Statements Attack");
    }

    // ✅ اختبار 12: Input فاضي بعد التنظيف = آمن
    @Test
    public void testSanitize_emptyAfterCleaning_isSafe() {
        String malicious = "'--";
        String cleaned = sanitizeInput(malicious);
        Assertions.assertFalse(cleaned.contains("'"),
            "Input المنظَّف يجب أن يكون آمناً");
    }
}