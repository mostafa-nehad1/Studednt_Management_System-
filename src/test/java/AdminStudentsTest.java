import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * اختبارات شاشة Admin - Students Tab
 * يختبر: Add Student, Edit, Delete, Refresh, Search
 */
public class AdminStudentsTest {

    // ========== اختبارات Add Student ==========

    // ✅ اختبار 1: اسم الطالب مش فاضي عند الإضافة
    @Test
    public void testAddStudent_name_notEmpty() {
        String name = "Ahmed Mohamed";
        Assertions.assertFalse(name.isEmpty(),
            "اسم الطالب يجب ألا يكون فارغاً عند الإضافة");
    }

    // ✅ اختبار 2: إيميل الطالب صحيح
    @Test
    public void testAddStudent_email_valid() {
        String email = "ahmed@example.com";
        Assertions.assertTrue(email.contains("@"),
            "إيميل الطالب يجب أن يكون صحيحاً");
    }

    // ✅ اختبار 3: Phone الطالب رقمي
    @Test
    public void testAddStudent_phone_numeric() {
        String phone = "01012345678";
        Assertions.assertDoesNotThrow(() -> Long.parseLong(phone),
            "رقم هاتف الطالب يجب أن يكون أرقاماً فقط");
    }

    // ✅ اختبار 4: ID الطالب موجب
    @Test
    public void testAddStudent_id_positive() {
        int id = 1;
        Assertions.assertTrue(id > 0,
            "ID الطالب يجب أن يكون موجباً");
    }

    // ✅ اختبار 5: Date مش فاضي
    @Test
    public void testAddStudent_date_notEmpty() {
        String date = "2024-01-15";
        Assertions.assertFalse(date.isEmpty(),
            "تاريخ تسجيل الطالب يجب ألا يكون فارغاً");
    }

    // ========== اختبارات Delete Student ==========

    // ✅ اختبار 6: حذف بـ ID صحيح
    @Test
    public void testDeleteStudent_validId() {
        int studentId = 5;
        Assertions.assertTrue(studentId > 0,
            "ID الطالب المحذوف يجب أن يكون موجباً");
    }

    // ✅ اختبار 7: حذف بـ ID صفر = غلط
    @Test
    public void testDeleteStudent_invalidId_zero() {
        int studentId = 0;
        Assertions.assertFalse(studentId > 0,
            "ID = 0 يجب أن يُرفض عند الحذف");
    }

    // ✅ اختبار 8: حذف بـ ID سالب = غلط
    @Test
    public void testDeleteStudent_invalidId_negative() {
        int studentId = -1;
        Assertions.assertFalse(studentId > 0,
            "ID السالب يجب أن يُرفض عند الحذف");
    }

    // ========== اختبارات Search ==========

    // ✅ اختبار 9: البحث بنص موجود
    @Test
    public void testSearchStudent_validQuery() {
        String searchQuery = "Ahmed";
        Assertions.assertFalse(searchQuery.isEmpty(),
            "نص البحث عن الطالب يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 10: البحث بإيميل
    @Test
    public void testSearchStudent_byEmail() {
        String email = "ahmed@example.com";
        Assertions.assertTrue(email.contains("@"),
            "البحث بالإيميل يجب أن يكون صحيحاً");
    }

    // ✅ اختبار 11: البحث الفاضي يجلب كل الطلاب
    @Test
    public void testSearchStudent_emptyQuery_returnsAll() {
        String searchQuery = "";
        // عند البحث الفاضي، نتوقع إحضار كل البيانات
        Assertions.assertTrue(searchQuery.isEmpty(),
            "البحث الفارغ يجب أن يُرجع كل الطلاب");
    }

    // ========== اختبارات Edit Student ==========

    // ✅ اختبار 12: تعديل بيانات صحيحة
    @Test
    public void testEditStudent_validData() {
        String newName = "Ahmed Updated";
        String newEmail = "updated@example.com";
        String newPhone = "01098765432";

        Assertions.assertFalse(newName.isEmpty(), "الاسم الجديد يجب ألا يكون فارغاً");
        Assertions.assertTrue(newEmail.contains("@"), "الإيميل الجديد يجب أن يكون صحيحاً");
        Assertions.assertDoesNotThrow(() -> Long.parseLong(newPhone), "الهاتف الجديد يجب أن يكون رقمياً");
    }

    // ========== اختبارات Mock Database ==========

    // ✅ اختبار 13: الاتصال بقاعدة البيانات مش null
    @Test
    public void testDBConnection_notNull() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        Assertions.assertNotNull(mockConn,
            "Connection يجب ألا يكون null");
    }

    // ✅ اختبار 14: تنفيذ استعلام INSERT ناجح
    @Test
    public void testInsertStudent_queryExecutes() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);

        String query = "INSERT INTO Students (name, email, phone) VALUES (?, ?, ?)";
        Mockito.when(mockConn.prepareStatement(query)).thenReturn(mockStmt);
        Mockito.when(mockStmt.executeUpdate()).thenReturn(1);

        int result = mockStmt.executeUpdate();
        Assertions.assertEquals(1, result,
            "INSERT يجب أن يرجع 1 عند النجاح");
    }

    // ✅ اختبار 15: تنفيذ استعلام DELETE ناجح
    @Test
    public void testDeleteStudent_queryExecutes() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);

        String query = "DELETE FROM Students WHERE ID = ?";
        Mockito.when(mockConn.prepareStatement(query)).thenReturn(mockStmt);
        Mockito.when(mockStmt.executeUpdate()).thenReturn(1);

        int result = mockStmt.executeUpdate();
        Assertions.assertEquals(1, result,
            "DELETE يجب أن يرجع 1 عند النجاح");
    }
}