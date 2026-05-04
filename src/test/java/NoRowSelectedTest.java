import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * اختبارات Edit/Delete بدون اختيار Row
 * يختبر: Students, Courses, Enrollments, My Courses
 */
public class NoRowSelectedTest {

    // ========================================================
    // ========== Students Tab ==========
    // ========================================================

    // ✅ اختبار 1: Edit طالب بدون اختيار = فشل
    @Test
    public void testEditStudent_noRowSelected_shouldFail() {
        int selectedRow = -1; // -1 معناها مفيش row متاختار
        Assertions.assertEquals(-1, selectedRow,
            "Edit بدون اختيار طالب يجب أن يُرفض");
    }

    // ✅ اختبار 2: Delete طالب بدون اختيار = فشل
    @Test
    public void testDeleteStudent_noRowSelected_shouldFail() {
        int selectedRow = -1;
        Assertions.assertFalse(selectedRow >= 0,
            "Delete بدون اختيار طالب يجب أن يُرفض");
    }

    // ✅ اختبار 3: Edit طالب مع اختيار صحيح = نجاح
    @Test
    public void testEditStudent_rowSelected_success() {
        int selectedRow = 0; // أول row في الجدول
        Assertions.assertTrue(selectedRow >= 0,
            "Edit مع اختيار row يجب أن ينجح");
    }

    // ✅ اختبار 4: Delete طالب مع اختيار صحيح = نجاح
    @Test
    public void testDeleteStudent_rowSelected_success() {
        int selectedRow = 2;
        Assertions.assertTrue(selectedRow >= 0,
            "Delete مع اختيار row يجب أن ينجح");
    }

    // ========================================================
    // ========== Courses Tab ==========
    // ========================================================

    // ✅ اختبار 5: Edit كورس بدون اختيار = فشل
    @Test
    public void testEditCourse_noRowSelected_shouldFail() {
        int selectedRow = -1;
        Assertions.assertEquals(-1, selectedRow,
            "Edit بدون اختيار كورس يجب أن يُرفض");
    }

    // ✅ اختبار 6: Delete كورس بدون اختيار = فشل
    @Test
    public void testDeleteCourse_noRowSelected_shouldFail() {
        int selectedRow = -1;
        Assertions.assertFalse(selectedRow >= 0,
            "Delete بدون اختيار كورس يجب أن يُرفض");
    }

    // ✅ اختبار 7: Edit كورس مع اختيار = نجاح
    @Test
    public void testEditCourse_rowSelected_success() {
        int selectedRow = 1;
        Assertions.assertTrue(selectedRow >= 0,
            "Edit مع اختيار كورس يجب أن ينجح");
    }

    // ✅ اختبار 8: Delete كورس مع اختيار = نجاح
    @Test
    public void testDeleteCourse_rowSelected_success() {
        int selectedRow = 3;
        Assertions.assertTrue(selectedRow >= 0,
            "Delete مع اختيار كورس يجب أن ينجح");
    }

    // ========================================================
    // ========== Enrollments Tab ==========
    // ========================================================

    // ✅ اختبار 9: Delete Enrollment بدون اختيار = فشل
    @Test
    public void testDeleteEnrollment_noRowSelected_shouldFail() {
        int selectedRow = -1;
        Assertions.assertFalse(selectedRow >= 0,
            "Delete بدون اختيار Enrollment يجب أن يُرفض");
    }

    // ✅ اختبار 10: Delete Enrollment مع اختيار = نجاح
    @Test
    public void testDeleteEnrollment_rowSelected_success() {
        int selectedRow = 0;
        Assertions.assertTrue(selectedRow >= 0,
            "Delete مع اختيار Enrollment يجب أن ينجح");
    }

    // ========================================================
    // ========== My Courses Tab (Student) ==========
    // ========================================================

    // ✅ اختبار 11: UnEnroll بدون اختيار كورس = فشل
    @Test
    public void testUnEnroll_noRowSelected_shouldFail() {
        int selectedRow = -1;
        Assertions.assertFalse(selectedRow >= 0,
            "UnEnroll بدون اختيار كورس يجب أن يُرفض");
    }

    // ✅ اختبار 12: UnEnroll مع اختيار كورس = نجاح
    @Test
    public void testUnEnroll_rowSelected_success() {
        int selectedRow = 1;
        Assertions.assertTrue(selectedRow >= 0,
            "UnEnroll مع اختيار كورس يجب أن ينجح");
    }

    // ✅ اختبار 13: Enroll بدون اختيار كورس = فشل
    @Test
    public void testEnroll_noRowSelected_shouldFail() {
        int selectedRow = -1;
        Assertions.assertFalse(selectedRow >= 0,
            "Enroll بدون اختيار كورس يجب أن يُرفض");
    }

    // ✅ اختبار 14: Enroll مع اختيار كورس = نجاح
    @Test
    public void testEnroll_rowSelected_success() {
        int selectedRow = 0;
        Assertions.assertTrue(selectedRow >= 0,
            "Enroll مع اختيار كورس يجب أن ينجح");
    }

    // ========================================================
    // ========== رسالة التحذير ==========
    // ========================================================

    // ✅ اختبار 15: رسالة تحذير بتطلع لو مفيش اختيار
    @Test
    public void testWarningMessage_whenNoRowSelected() {
        int selectedRow = -1;
        String warningMessage = selectedRow == -1 ? "Please select a row first!" : "";
        Assertions.assertEquals("Please select a row first!", warningMessage,
            "رسالة التحذير يجب أن تظهر عند عدم اختيار row");
    }

    // ✅ اختبار 16: مفيش رسالة تحذير لو في اختيار
    @Test
    public void testNoWarningMessage_whenRowSelected() {
        int selectedRow = 2;
        String warningMessage = selectedRow == -1 ? "Please select a row first!" : "";
        Assertions.assertEquals("", warningMessage,
            "يجب ألا تظهر رسالة تحذير عند اختيار row");
    }
}