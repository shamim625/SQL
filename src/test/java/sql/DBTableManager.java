package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class DBTableManager {
	
	public static void createTable(String table) throws Throwable {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522/orcldb", "HR", "HR");

		String tableSearch = "SELECT table_name FROM user_tables";
		List<String> allTables = ReadDBTable.getDataTableColumn(tableSearch, "TABLE_NAME");
		System.out.println("All Table found =" + allTables);
		if (!allTables.contains(table)) {
			String tableName = "CREATE TABLE " + table + " (TEST_ID varchar(1000)," + "DESCRIPTION varchar(4000),"
					+ "Expected varchar(4000)," + "Actual varchar(4000)," + "Status varchar(1000) )";
			PreparedStatement stmt = conn.prepareStatement(tableName);
			stmt.execute();
			stmt.close();

			System.out.println("Table create successfully ....." + table);
		} else {
			System.out.println("Table already there .........No need to create new table..." + table);
		}
	}

	public static void delecteTable(String table) throws Throwable {

		String tableName = "delete from " + table + "";

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522/orcldb", "HR", "HR");

		// set all the preparedstatement parameters

		PreparedStatement stmt = conn.prepareStatement(tableName);
		stmt.execute();
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Table deleted successfully ....." + table);
	}

	public static void dropTable(String table) throws Throwable {

		String tableName = "drop table " + table + "";

		Class.forName("oracle.jdbc.driver.OracleDriver");

		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522/orcldb", "HR", "HR");

		// set all the preparedstatement parameters

		PreparedStatement stmt = conn.prepareStatement(tableName);
		stmt.execute();
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Table deleted successfully ....." + table);
	}
}
