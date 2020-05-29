package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class WriteDBTable {

	public static int setupTable(String tableName, String test_id, String DESCRIPTION, String EXPECTED, String ACTUAL,
			String STATUS) throws SQLException {

		Connection conn = null;
		int recordInserted = 0;
		try {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522/orcldb", "HR", "HR");
			String table = tableName;

			String query = "INSERT INTO " + table + "(TEST_id, DESCRIPTION, EXPECTED,ACTUAL,STATUS)"
					+ "values(?, ?, ?, ?, ?)";

			// set all the preparedstatement parameters
			PreparedStatement stmt = conn.prepareStatement(query);

			stmt.setString(1, test_id);
			stmt.setString(2, DESCRIPTION);
			stmt.setString(3, EXPECTED);
			stmt.setString(4, ACTUAL);
			stmt.setString(5, STATUS);
			recordInserted = stmt.executeUpdate();
			// execute the preparedstatement insert
			System.out.println("Row inserted into Table " + tableName + " = " + recordInserted);
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// log exception
			throw se;
		}
		return recordInserted;
	}

	public static void InsertData(List<String> data, String TableName) throws Throwable {

		data.forEach((value) -> {

			System.out.println(value);
			try {
				setupTable(TableName, value.split("")[0], value.split("")[1], value.split("_")[2],
						value.split("")[3], value.split("")[4]);
			} catch (SQLException e) {

			}
		});

	}
}