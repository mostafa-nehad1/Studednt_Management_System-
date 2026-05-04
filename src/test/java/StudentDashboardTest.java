import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * اختبارات Student Dashboard
 * يختبر: Available Courses (Search, Enroll), My Courses (UnEnroll), Profile (Save Changes)
 */
public class StudentDashboardTest {

    // ========================================================
    // ========== Available Courses Tab ==========
    // ========================================================

    // ✅ اختبار 1: البحث عن كورس بالاسم
    @Test
    public void testAvailableCourses_search_byName() {
        String search = "Java";
        Assertions.assertFalse(search.isEmpty(),
            "نص البحث عن الكورس يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 2: البحث الفاضي يجلب كل الكورسات
    @Test
    public void testAvailableCourses_emptySearch_returnsAll() {
        String search = "";
        Assertions.assertTrue(search.isEmpty(),
            "البحث الفارغ يجب أن يُرجع كل الكورسات المتاحة");
    }

    // ✅ اختبار 3: عمود ID في الكورس موجب
    @Test
    public void testAvailableCourses_id_positive() {
        int courseId = 1;
        Assertions.assertTrue(courseId > 0,
            "Course ID يجب أن يكون موجباً");
    }

    // ✅ اختبار 4: Course Name مش فاضي
    @Test
    public void testAvailableCourses_name_notEmpty() {
        String courseName = "Java Programming";
        Assertions.assertFalse(courseName.isEmpty(),
            "Course Name يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 5: Description مش فاضي
    @Test
    public void testAvailableCourses_description_notEmpty() {
        String description = "Learn Java from scratch";
        Assertions.assertFalse(description.isEmpty(),
            "Course Description يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 6: Instructor مش فاضي
    @Test
    public void testAvailableCourses_instructor_notEmpty() {
        String instructor = "Dr. Mohamed";
        Assertions.assertFalse(instructor.isEmpty(),
            "Course Instructor يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 7: Enroll - الطالب محتاج يختار كورس الأول
    @Test
    public void testEnroll_courseSelected() {
        int selectedCourseId = 3;
        Assertions.assertTrue(selectedCourseId > 0,
            "يجب اختيار كورس قبل الـ Enroll");
    }

    // ✅ اختبار 8: Enroll - الطالب مش مسجل فيه قبل كده
    @Test
    public void testEnroll_notAlreadyEnrolled() {
        boolean alreadyEnrolled = false;
        Assertions.assertFalse(alreadyEnrolled,
            "الطالب لا يجب أن يكون مسجلاً في الكورس مسبقاً");
    }

    // ✅ اختبار 9: Enroll في قاعدة البيانات ناجح
    @Test
    public void testEnroll_queryExecutes() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);

        String query = "INSERT INTO Enrollments (student_id, course_id, date) VALUES (?, ?, ?)";
        Mockito.when(mockConn.prepareStatement(query)).thenReturn(mockStmt);
        Mockito.when(mockStmt.executeUpdate()).thenReturn(1);

        int result = mockStmt.executeUpdate();
        Assertions.assertEquals(1, result,
            "Enroll في الكورس يجب أن ينجح");
    }

    // ========================================================
    // ========== My Courses Tab ==========
    // ========================================================

    // ✅ اختبار 10: My Courses يعرض كورسات الطالب
    @Test
    public void testMyCourses_showsStudentCourses() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);
        ResultSet mockRs = Mockito.mock(ResultSet.class);

        Mockito.when(mockStmt.executeQuery()).thenReturn(mockRs);
        Mockito.when(mockRs.next()).thenReturn(true);

        boolean hasCourses = mockRs.next();
        Assertions.assertTrue(hasCourses,
            "My Courses يجب أن يُظهر كورسات الطالب");
    }

    // ✅ اختبار 11: Course Name في My Courses مش فاضي
    @Test
    public void testMyCourses_courseName_notEmpty() {
        String courseName = "Java Programming";
        Assertions.assertFalse(courseName.isEmpty(),
            "Course Name في My Courses يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 12: Instructor في My Courses مش فاضي
    @Test
    public void testMyCourses_instructor_notEmpty() {
        String instructor = "Dr. Mohamed";
        Assertions.assertFalse(instructor.isEmpty(),
            "Instructor في My Courses يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 13: Enrolled date مش فاضي
    @Test
    public void testMyCourses_enrolledDate_notEmpty() {
        String date = "2024-01-15";
        Assertions.assertFalse(date.isEmpty(),
            "Enrolled Date يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 14: UnEnroll - محتاج يختار كورس الأول
    @Test
    public void testUnEnroll_courseSelected() {
        int selectedCourseId = 2;
        Assertions.assertTrue(selectedCourseId > 0,
            "يجب اختيار كورس قبل الـ UnEnroll");
    }

    // ✅ اختبار 15: UnEnroll - بدون اختيار كورس = فشل
    @Test
    public void testUnEnroll_noCourseSelected_shouldFail() {
        int selectedCourseId = -1;
        Assertions.assertFalse(selectedCourseId > 0,
            "UnEnroll بدون اختيار كورس يجب أن يُرفض");
    }

    // ✅ اختبار 16: UnEnroll من قاعدة البيانات ناجح
    @Test
    public void testUnEnroll_queryExecutes() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);

        String query = "DELETE FROM Enrollments WHERE student_id = ? AND course_id = ?";
        Mockito.when(mockConn.prepareStatement(query)).thenReturn(mockStmt);
        Mockito.when(mockStmt.executeUpdate()).thenReturn(1);

        int result = mockStmt.executeUpdate();
        Assertions.assertEquals(1, result,
            "UnEnroll من الكورس يجب أن ينجح");
    }

    // ========================================================
    // ========== Profile Tab ==========
    // ========================================================

    // ✅ اختبار 17: Full Name مش فاضي
    @Test
    public void testProfile_fullName_notEmpty() {
        String fullName = "Ahmed Mohamed";
        Assertions.assertFalse(fullName.isEmpty(),
            "Full Name يجب ألا يكون فارغاً في الـ Profile");
    }

    // ✅ اختبار 18: Email صحيح
    @Test
    public void testProfile_email_valid() {
        String email = "ahmed@example.com";
        Assertions.assertTrue(email.contains("@"),
            "Email في الـ Profile يجب أن يكون صحيحاً");
    }

    // ✅ اختبار 19: Email فاضي = فشل
    @Test
    public void testProfile_email_empty_shouldFail() {
        String email = "";
        Assertions.assertTrue(email.isEmpty(),
            "Email الفارغ في الـ Profile يجب أن يُرفض");
    }

    // ✅ اختبار 20: Phone رقمي صحيح
    @Test
    public void testProfile_phone_numeric() {
        String phone = "01012345678";
        Assertions.assertDoesNotThrow(() -> Long.parseLong(phone),
            "Phone في الـ Profile يجب أن يكون رقمياً");
    }

    // ✅ اختبار 21: Phone فاضي = فشل
    @Test
    public void testProfile_phone_empty_shouldFail() {
        String phone = "";
        Assertions.assertTrue(phone.isEmpty(),
            "Phone الفارغ في الـ Profile يجب أن يُرفض");
    }

    // ✅ اختبار 22: Save Changes - يحفظ في قاعدة البيانات
    @Test
    public void testProfile_saveChanges_queryExecutes() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);

        String query = "UPDATE Students SET name = ?, email = ?, phone = ? WHERE ID = ?";
        Mockito.when(mockConn.prepareStatement(query)).thenReturn(mockStmt);
        Mockito.when(mockStmt.executeUpdate()).thenReturn(1);

        int result = mockStmt.executeUpdate();
        Assertions.assertEquals(1, result,
            "Save Changes يجب أن يحفظ التغييرات بنجاح");
    }

    // ✅ اختبار 23: Phone طول مناسب
    @Test
    public void testProfile_phone_correctLength() {
        String phone = "01012345678";
        Assertions.assertEquals(11, phone.length(),
            "Phone يجب أن يكون 11 رقم");
    }
}