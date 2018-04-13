package phoenix.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PhoenixJdbcTest {
	public static void main(String[] args) throws SQLException {
		PhoenixJdbcSingle pjs = PhoenixJdbcSingle.getInitJDBCUtil();
		Connection conn = pjs.getConnection();
		Statement st = conn.createStatement();
		st.execute("DROP VIEW \"gps0411\"");
		// st.execute("create view \"gps0411\" (pk VARCHAR PRIMARY KEY,
		// \"DEFAULT\".\"GPSID\" VARCHAR, \"DEFAULT\".\"SPEED\" VARCHAR)");
		pjs.closeConnection(null, st, conn);
	}
}
