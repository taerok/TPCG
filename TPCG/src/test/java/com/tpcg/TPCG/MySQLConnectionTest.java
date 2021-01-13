package com.tpcg.TPCG;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource
public class MySQLConnectionTest {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/tpcg_schema";
	private static final String USER = "trpark";
	private static final String PW = "tpcg93@)";

	@Test
	public void testConnection() throws Exception {
		System.out.println("┌───────────────JDBC Connection Test──────────────────────────────────────────────────────────┒");
		System.out.println("├───────────────Application Properties────────────────────────────────────────────────────────┨");
		System.out.println("│                                                                                             ┃");
		System.out.printf( "│ DRIVER  : %-80.80s  ┃\n", DRIVER.length() >= 80 ? (DRIVER.substring(0, 77) + "...")  : DRIVER);
		System.out.printf( "│ URL     : %-80.80s  ┃\n", URL.length() >= 80 ? (URL.substring(0, 77) + "...")  : URL);
		System.out.printf( "│ USER    : %-80.80s  ┃\n", USER.length() >= 80 ? (USER.substring(0, 77) + "...")  : USER);
		System.out.printf( "│ PW      : %-80.80s  ┃\n", PW.length() >= 80 ? (PW.substring(0, 77) + "...")  : PW);
		System.out.println("│                                                                                             ┃");
		System.out.println("┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

		Class.forName(DRIVER);

		try (Connection con = DriverManager.getConnection(URL, USER, PW)) {
			System.out.println("success - " + con);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
                                      