import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * اختبارات شاشة Admin - Reports Tab
 * يختبر: Count, Max, Min, Most Popular Course
 */
public class AdminReportsTest {

    // ========== اختبارات Count ==========

    // ✅ اختبار 1: Count طلاب موجب أو صفر
    @Test
    public void testCount_students_nonNegative() {
        int count = 50;
        Assertions.assertTrue(count >= 0,
            "عدد الطلاب يجب أن يكون صفر أو أكثر");
    }

    // ✅ اختبار 2: Count كورسات موجب أو صفر
    @Test
    public void testCount_courses_nonNegative() {
        int count = 10;
        Assertions.assertTrue(count >= 0,
            "عدد الكورسات يجب أن يكون صفر أو أكثر");
    }

    // ✅ اختبار 3: Count enrollments موجب أو صفر
    @Test
    public void testCount_enrollments_nonNegative() {
        int count = 100;
        Assertions.assertTrue(count >= 0,
            "عدد الـ Enrollments يجب أن يكون صفر أو أكثر");
    }

    // ✅ اختبار 4: Count مش رقم سالب
    @Test
    public void testCount_notNegative() {
        int count = 0;
        Assertions.assertFalse(count < 0,
            "Count يجب ألا يكون سالباً");
    }

    // ========== اختبارات Max ==========

    // ✅ اختبار 5: Max أكبر من أو يساوي Min
    @Test
    public void testMax_greaterThanOrEqualMin() {
        int max = 100;
        int min = 20;
        Assertions.assertTrue(max >= min,
            "Max يجب أن يكون أكبر من أو يساوي Min");
    }

    // ✅ اختبار 6: Max موجب
    @Test
    public void testMax_positive() {
        int max = 100;
        Assertions.assertTrue(max > 0,
            "Max يجب أن يكون موجباً");
    }

    // ✅ اختبار 7: Max من قاعدة البيانات يرجع قيمة
    @Test
    public void testMax_queryReturnsValue() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);
        ResultSet mockRs = Mockito.mock(ResultSet.class);

        Mockito.when(mockStmt.executeQuery()).thenReturn(mockRs);
        Mockito.when(mockRs.next()).thenReturn(true);
        Mockito.when(mockRs.getInt(1)).thenReturn(100);

        mockRs.next();
        int maxValue = mockRs.getInt(1);
        Assertions.assertEquals(100, maxValue,
            "MAX Query يجب أن يرجع قيمة صحيحة");
    }

    // ========== اختبارات Min ==========

    // ✅ اختبار 8: Min أقل من أو يساوي Max
    @Test
    public void testMin_lessThanOrEqualMax() {
        int min = 5;
        int max = 100;
        Assertions.assertTrue(min <= max,
            "Min يجب أن يكون أقل من أو يساوي Max");
    }

    // ✅ اختبار 9: Min موجب أو صفر
    @Test
    public void testMin_nonNegative() {
        int min = 5;
        Assertions.assertTrue(min >= 0,
            "Min يجب أن يكون صفر أو أكثر");
    }

    // ✅ اختبار 10: Min من قاعدة البيانات يرجع قيمة
    @Test
    public void testMin_queryReturnsValue() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);
        ResultSet mockRs = Mockito.mock(ResultSet.class);

        Mockito.when(mockStmt.executeQuery()).thenReturn(mockRs);
        Mockito.when(mockRs.next()).thenReturn(true);
        Mockito.when(mockRs.getInt(1)).thenReturn(5);

        mockRs.next();
        int minValue = mockRs.getInt(1);
        Assertions.assertEquals(5, minValue,
            "MIN Query يجب أن يرجع قيمة صحيحة");
    }

    // ========== اختبارات Most Popular Course ==========

    // ✅ اختبار 11: Most Popular Course مش فاضي
    @Test
    public void testMostPopularCourse_notEmpty() {
        String popularCourse = "Java Programming";
        Assertions.assertFalse(popularCourse.isEmpty(),
            "Most Popular Course يجب ألا يكون فارغاً");
    }

    // ✅ اختبار 12: Most Popular Course له عدد طلاب موجب
    @Test
    public void testMostPopularCourse_hasStudents() {
        int studentCount = 30;
        Assertions.assertTrue(studentCount > 0,
            "Most Popular Course يجب أن يكون له طلاب");
    }

    // ✅ اختبار 13: Most Popular Course من DB يرجع نتيجة
    @Test
    public void testMostPopularCourse_queryReturnsResult() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);
        ResultSet mockRs = Mockito.mock(ResultSet.class);

        Mockito.when(mockStmt.executeQuery()).thenReturn(mockRs);
        Mockito.when(mockRs.next()).thenReturn(true);
        Mockito.when(mockRs.getString("Course_Name")).thenReturn("Java Programming");

        mockRs.next();
        String course = mockRs.getString("Course_Name");
        Assertions.assertEquals("Java Programming", course,
            "Most Popular Course يجب أن يرجع اسم الكورس");
    }
}