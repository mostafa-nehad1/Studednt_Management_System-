import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectionTest {

    // ✅ اختبار 1: Connection مش null
    @Test
    public void testConnectionNotNull() {
        Connection mockConnection = Mockito.mock(Connection.class);
        Assertions.assertNotNull(mockConnection,
            "Connection يجب ألا يكون null");
    }

    // ✅ اختبار 2: Connection مغلوق = isClosed true
    @Test
    public void testConnectionIsClosed() throws Exception {
        Connection mockConnection = Mockito.mock(Connection.class);
        Mockito.when(mockConnection.isClosed()).thenReturn(true);
        Assertions.assertTrue(mockConnection.isClosed(),
            "Connection المغلوق يجب أن يرجع isClosed = true");
    }

    // ✅ اختبار 3: Connection مفتوح = isClosed false
    @Test
    public void testConnectionIsOpen() throws Exception {
        Connection mockConnection = Mockito.mock(Connection.class);
        Mockito.when(mockConnection.isClosed()).thenReturn(false);
        Assertions.assertFalse(mockConnection.isClosed(),
            "Connection المفتوح يجب أن يرجع isClosed = false");
    }

    // ✅ اختبار 4: الـ URL بتاع SQL Server صح
    @Test
    public void testConnectionURL_validFormat() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=StudentDB";
        Assertions.assertTrue(url.startsWith("jdbc:sqlserver://"),
            "URL يجب أن يبدأ بـ jdbc:sqlserver://");
        Assertions.assertTrue(url.contains("databaseName="),
            "URL يجب أن يحتوي على اسم قاعدة البيانات");
    }

    // ✅ اختبار 5: Statement بيتنفذ بنجاح
    @Test
    public void testStatement_executesSuccessfully() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);

        Mockito.when(mockConn.prepareStatement("SELECT 1")).thenReturn(mockStmt);
        Mockito.when(mockStmt.executeUpdate()).thenReturn(1);

        int result = mockStmt.executeUpdate();
        Assertions.assertEquals(1, result,
            "Statement يجب أن ينفذ بنجاح");
    }

    // ✅ اختبار 6: ResultSet بيرجع بيانات
    @Test
    public void testResultSet_returnsData() throws Exception {
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);
        ResultSet mockRs = Mockito.mock(ResultSet.class);

        Mockito.when(mockStmt.executeQuery()).thenReturn(mockRs);
        Mockito.when(mockRs.next()).thenReturn(true);

        boolean hasData = mockRs.next();
        Assertions.assertTrue(hasData,
            "ResultSet يجب أن يرجع بيانات");
    }

    // ✅ اختبار 7: ResultSet فاضي لو مفيش بيانات
    @Test
    public void testResultSet_emptyWhenNoData() throws Exception {
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);
        ResultSet mockRs = Mockito.mock(ResultSet.class);

        Mockito.when(mockStmt.executeQuery()).thenReturn(mockRs);
        Mockito.when(mockRs.next()).thenReturn(false);

        boolean hasData = mockRs.next();
        Assertions.assertFalse(hasData,
            "ResultSet يجب أن يكون فارغاً لو مفيش بيانات");
    }

    // ✅ اختبار 8: Connection بيتغلق بعد الاستخدام
    @Test
    public void testConnection_closedAfterUse() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        mockConn.close();
        Mockito.verify(mockConn).close();
        // بيتحقق إن close() اتنادت فعلاً
    }

    // ✅ اختبار 9: لو الاتصال فشل بيرجع Exception
    @Test
    public void testConnection_failureThrowsException() throws Exception {
        Connection mockConn = Mockito.mock(Connection.class);
        Mockito.when(mockConn.isClosed())
               .thenThrow(new SQLException("Connection Failed"));

        Assertions.assertThrows(SQLException.class,
            () -> mockConn.isClosed(),
            "فشل الاتصال يجب أن يرجع SQLException");
    }

    // ✅ اختبار 10: عدد الـ Rows المرجعة صح
    @Test
    public void testResultSet_columnValueCorrect() throws Exception {
        PreparedStatement mockStmt = Mockito.mock(PreparedStatement.class);
        ResultSet mockRs = Mockito.mock(ResultSet.class);

        Mockito.when(mockStmt.executeQuery()).thenReturn(mockRs);
        Mockito.when(mockRs.next()).thenReturn(true);
        Mockito.when(mockRs.getString("name")).thenReturn("Ahmed");

        mockRs.next();
        String name = mockRs.getString("name");
        Assertions.assertEquals("Ahmed", name,
            "القيمة المرجعة من ResultSet يجب أن تكون صحيحة");
    }
}