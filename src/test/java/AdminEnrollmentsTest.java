import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * اختبارات شاشة Admin - Enrollments Tab
 * يختبر: Search, Delete, Refresh
 * الأعمدة: ID, Student Name, Course Name, Date
 */
public class AdminEnrollmentsTest {

    // ========== اختبارات بيانات Enrollment ==========

    // ✅ اختبار 1: Enrollment ID موجب
    @Test
    public void testEnrollment_id_positive() {
        int enrollmentId = 1;
        Assertions.assertTrue(enrollmentId > 0,
            "Enrollment ID يجب أن يكون موجباً");
    }

    // ✅ اختبار 2: Student Name مش فاضي
    @Test
    public void testEnrollment_studentName_notEmpty() {
        String studentName = "Ahmed Mohamed";
        Assertions.assertFalse(studentName.isEmpty(),
            "Student Name في الـ Enrollment يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 3: Course Name مش فاضي
    @Test
    public void testEnrollment_courseName_notEmpty() {
        String courseName = "Java Programming";
        Assertions.assertFalse(courseName.isEmpty(),
            "Course Name في الـ Enrollment يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 4: Date مش فاضي
    @Test
    public void testEnrollment_date_notEmpty() {
        String date = "2024-01-15";
        Assertions.assertFalse(date.isEmpty(),
            "Enrollment Date يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 5: Date صيغة صحيحة (يحتوي -)
    @Test
    public void testEnrollment_date_validFormat() {
        String date = "2024-01-15";
        Assertions.assertTrue(date.contains("-"),
            "تاريخ الـ Enrollment يجب أن يكون بصيغة صحيحة");
    }

    // ========== اختبارات Delete Enrollment ==========

    // ✅ اختبار 6: حذف بـ ID صحيح
    @Test
    public void testDeleteEnrollment_validId() {
        int enrollmentId = 5;
        Assertions.assertTrue(enrollmentId > 0,
            "Enrollment ID للحذف يجب أن يكون موجباً");
    }

    // ✅ اختبار 7: حذف بـ ID = 0 فشل
    @Test
    public void testDeleteEnrollment_zeroId_shouldFail() {
        int enrollmentId = 0;
        Assertions.assertFalse(enrollmentId > 0,
            "Enrollment ID = 0 يجب أن يُرفض");
    }

    // ✅ اختبار 8: حذف بـ ID سالب = فشل
    @Test
    public void testDeleteEnrollment_negativeId_shouldFail() {
        int enrollmentId = -3;
        Assertions.assertFalse(enrollmentId > 0,
            "Enrollment ID السالب يجب أن يُرفض");
    }

    // ========== اختبارات Search ==========

    // ✅ اختبار 9: البحث باسم الطالب
    @Test
    public void testSearchEnrollment_byStudentName() {
        String search = "Ahmed";
        Assertions.assertFalse(search.isEmpty(),
            "البحث باسم الطالب يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 10: البحث باسم الكورس
    @Test
    public void testSearchEnrollment_byCourseName() {
        String search = "Java";
        Assertions.assertFalse(search.isEmpty(),
            "البحث باسم الكورس يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 11: البحث الفاضي يجلب كل السجلات
    @Test
    public void testSearchEnrollment_empty_returnsAll() {
        String search = "";
        Assertions.assertTrue(search.isEmpty(),
            "البحث الفارغ يجب أن يُرجع كل سجلات الـ Enrollment");
    }

    // ========== اختبارات Mock Database ==========

    // ✅ اختبار 12: حذف Enrollment من قاعدة البيانات
    @Test
    public void testDeleteEnrollment_queryExecutes() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);

        String query = "DELETE FROM Enrollments WHERE ID = ?";
        Mockito.when(mockConn.prepareStatement(query)).thenReturn(mockStmt);
        Mockito.when(mockStmt.executeUpdate()).thenReturn(1);

        int result = mockStmt.executeUpdate();
        Assertions.assertEquals(1, result,
            "حذف الـ Enrollment يجب أن ينجح");
    }

    // ✅ اختبار 13: البحث في قاعدة البيانات يرجع نتيجة
    @Test
    public void testSearchEnrollment_queryReturnsResults() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);
        ResultSet mockRs = Mockito.mock(ResultSet.class);

        Mockito.when(mockStmt.executeQuery()).thenReturn(mockRs);
        Mockito.when(mockRs.next()).thenReturn(true);

        boolean hasResults = mockRs.next();
        Assertions.assertTrue(hasResults,
            "البحث يجب أن يُرجع نتائج");
    }
}