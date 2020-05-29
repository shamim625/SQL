package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReadWholeTable {

	
	public static List<String> getDataTableColumn(String SQLqyeries) throws Throwable {
		ResultSet rs = null;
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522/orcldb", "HR", "HR");
			Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(SQLqyeries);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		ResultSetMetaData rsMetaData = rs.getMetaData();
		
		List<String> columnValue = new ArrayList<String>();
		int nCols = rsMetaData.getColumnCount();
		System.out.println("==========================================================================="
	    		+ "===================================================================================="
	    		+ "======================================================================================");
	    for(int i = 1; i <= nCols; ++i) {
	          System.out.format("|| %-14s ", rsMetaData.getColumnLabel(i));

	    
	    }
	    System.out.println();
	    System.out.println("==========================================================================="
	    		+ "===================================================================================="
	    		+ "======================================================================================");
		
			while (rs.next()) {
				for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
				
					System.out.format("| %-16s", rs.getString(i));

				}
				System.out.println();
			}
	

		return columnValue;

	}

	public static void main(String[] args) throws Throwable {


		String query="Select * from employee";
				
		
		ReadWholeTable.getDataTableColumn(query);
	}
}
