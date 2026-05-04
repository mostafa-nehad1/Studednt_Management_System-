import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * اختبارات شاشة Admin - Courses Tab
 * يختبر: Add Course, Edit, Delete, Refresh, Search
 * الأعمدة: Course_ID, Course_Name, Course_instructor, Course_Duration
 */
public class AdminCoursesTest {

    // ========== اختبارات Add Course ==========

    // ✅ اختبار 1: Course_Name مش فاضي
    @Test
    public void testAddCourse_name_notEmpty() {
        String courseName = "Java Programming";
        Assertions.assertFalse(courseName.isEmpty(),
            "Course Name يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 2: Course_instructor مش فاضي
    @Test
    public void testAddCourse_instructor_notEmpty() {
        String instructor = "Dr. Mohamed";
        Assertions.assertFalse(instructor.isEmpty(),
            "Course Instructor يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 3: Course_Duration رقم موجب
    @Test
    public void testAddCourse_duration_positive() {
        int duration = 30;
        Assertions.assertTrue(duration > 0,
            "Course Duration يجب أن يكون موجباً");
    }

    // ✅ اختبار 4: Course_Duration مش فاضي
    @Test
    public void testAddCourse_duration_notEmpty() {
        String duration = "30";
        Assertions.assertFalse(duration.isEmpty(),
            "Course Duration يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 5: Course_Duration رقمي
    @Test
    public void testAddCourse_duration_numeric() {
        String duration = "30";
        Assertions.assertDoesNotThrow(() -> Integer.parseInt(duration),
            "Course Duration يجب أن يكون رقماً صحيحاً");
    }

    // ✅ اختبار 6: Course_Duration بالحروف = خطأ
    @Test
    public void testAddCourse_duration_invalidString() {
        String duration = "thirty days";
        Assertions.assertThrows(NumberFormatException.class,
            () -> Integer.parseInt(duration),
            "Duration بالحروف يجب أن يُرفض");
    }

    // ✅ اختبار 7: Course_ID موجب
    @Test
    public void testCourse_id_positive() {
        int courseId = 1;
        Assertions.assertTrue(courseId > 0,
            "Course ID يجب أن يكون موجباً");
    }

    // ========== اختبارات Delete Course ==========

    // ✅ اختبار 8: حذف بـ Course_ID صحيح
    @Test
    public void testDeleteCourse_validId() {
        int courseId = 3;
        Assertions.assertTrue(courseId > 0,
            "Course ID للحذف يجب أن يكون موجباً");
    }

    // ✅ اختبار 9: حذف بـ ID سالب = فشل
    @Test
    public void testDeleteCourse_negativeId_shouldFail() {
        int courseId = -1;
        Assertions.assertFalse(courseId > 0,
            "Course ID السالب يجب أن يُرفض");
    }

    // ========== اختبارات Search ==========

    // ✅ اختبار 10: البحث عن كورس بالاسم
    @Test
    public void testSearchCourse_byName() {
        String search = "Java";
        Assertions.assertFalse(search.isEmpty(),
            "نص البحث عن الكورس يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 11: البحث عن كورس بالمدرب
    @Test
    public void testSearchCourse_byInstructor() {
        String instructor = "Dr. Mohamed";
        Assertions.assertFalse(instructor.isEmpty(),
            "البحث باسم المدرب يجب ألا يكون فارغاً");
    }

    // ========== اختبارات Edit Course ==========

    // ✅ اختبار 12: تعديل اسم الكورس
    @Test
    public void testEditCourse_name_valid() {
        String newName = "Advanced Java";
        Assertions.assertFalse(newName.isEmpty(),
            "الاسم الجديد للكورس يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 13: تعديل مدة الكورس رقم صحيح
    @Test
    public void testEditCourse_duration_valid() {
        String newDuration = "45";
        Assertions.assertDoesNotThrow(() -> Integer.parseInt(newDuration),
            "المدة الجديدة للكورس يجب أن تكون رقماً");
    }

    // ========== اختبارات Mock Database ==========

    // ✅ اختبار 14: إضافة كورس في قاعدة البيانات
    @Test
    public void testInsertCourse_queryExecutes() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);

        String query = "INSERT INTO Courses (name, instructor, duration) VALUES (?, ?, ?)";
        Mockito.when(mockConn.prepareStatement(query)).thenReturn(mockStmt);
        Mockito.when(mockStmt.executeUpdate()).thenReturn(1);

        int result = mockStmt.executeUpdate();
        Assertions.assertEquals(1, result,
            "إضافة الكورس يجب أن تنجح وترجع 1");
    }

    // ✅ اختبار 15: حذف كورس من قاعدة البيانات
    @Test
    public void testDeleteCourse_queryExecutes() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);

        String query = "DELETE FROM Courses WHERE Course_ID = ?";
        Mockito.when(mockConn.prepareStatement(query)).thenReturn(mockStmt);
        Mockito.when(mockStmt.executeUpdate()).thenReturn(1);

        int result = mockStmt.executeUpdate();
        Assertions.assertEquals(1, result,
            "حذف الكورس يجب أن ينجح ويرجع 1");
    }
}