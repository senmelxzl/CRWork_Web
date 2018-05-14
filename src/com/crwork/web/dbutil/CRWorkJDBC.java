package com.crwork.web.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DB class
 * 
 * @author Amao
 *
 */
public class CRWorkJDBC {
	// Load DB driver com.mysql.jdbc.Driver
	private static String dbdriver = "com.mysql.cj.jdbc.Driver";
	// DB name
	private static String database = "crwork";
	// DB login user name
	private static String username = "root";
	// DB login password
	private static String userpassword = "xzl198819";
	// Load DB address
	private static String dburl = "jdbc:mysql://localhost:3306/" + database
			+ "?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";

	private static Connection conn = null;
	private static Statement stat = null;
	/**
	 * DB table name
	 */
	public final static String USER_TABLE = "crwork_user";
	public final static String LITTER_TABLE = "crwork_litter";
	public final static String LITTER_TYPE_TABLE = "crwork_litter_type";
	public final static String REGION_TABLE = "crwork_region";

	/**
	 * DB table create SQL
	 */
	public final static String USER_TABLE_SQL = "create table " + USER_TABLE
			+ "(ID int auto_increment primary key not null,userId int not null,userName varchar(20),regionID int not null,userType int not null,userRD date not null,psw varchar(20) not null)";

	public final static String LITTER_TABLE_SQL = "create table " + LITTER_TABLE
			+ "(ID int auto_increment primary key not null,userId int not null,littertypeID int not null,weight double(16,2) not null,litterdate date not null)";

	public final static String LITTER_TYPE_TABLE_SQL = "create table " + LITTER_TYPE_TABLE
			+ "(ID int auto_increment primary key not null,littertypeID int not null,typeName varchar(20),typemark int not null,price double(16,2) not null)";

	public final static String REGION_TABLE_SQL = "create table " + REGION_TABLE
			+ "(ID int auto_increment primary key not null,regionID int not null,regionName varchar(20),regionParentID int not null,statusMark int not null,regionRD date not null)";

	public CRWorkJDBC() {
		try {
			Class.forName(dbdriver).newInstance();
			conn = DriverManager.getConnection(dburl, username, userpassword);
			stat = conn.createStatement();
			validateTableNameExist(USER_TABLE);
			validateTableNameExist(LITTER_TABLE);
			validateTableNameExist(LITTER_TYPE_TABLE);
			validateTableNameExist(REGION_TABLE);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e);

		}
	}

	/**
	 * get DB Connection
	 * 
	 * @return Connection
	 */
	public Connection getCRWorkConn() {
		return conn;
	}

	/**
	 * check table exist
	 * 
	 * @param tableName
	 * @return
	 */
	public static boolean validateTableNameExist(String tableName) {
		boolean vtne = false;
		ResultSet rs;
		try {
			rs = conn.getMetaData().getTables(null, null, tableName, null);
			if (rs.next()) {
				vtne = true;
			} else {
				vtne = createtable(tableName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vtne;
	}

	/**
	 * create table
	 */
	private static boolean createtable(String tableName) {
		// TODO Auto-generated method stub
		String SQL_TABLE = "";
		if (tableName.equals(USER_TABLE)) {
			SQL_TABLE = USER_TABLE_SQL;
		} else if (tableName.equals(LITTER_TABLE)) {
			SQL_TABLE = LITTER_TABLE_SQL;
		} else if (tableName.equals(LITTER_TYPE_TABLE)) {
			SQL_TABLE = LITTER_TYPE_TABLE_SQL;
		} else if (tableName.equals(REGION_TABLE)) {
			SQL_TABLE = REGION_TABLE_SQL;
		}
		if (!SQL_TABLE.equals("")) {
			System.out.print(tableName + " table is not exist! \n");
			try {
				System.out.print(tableName + " table start to create! \n");
				stat.executeUpdate(SQL_TABLE);
				System.out.print(tableName + " table is Created! \n");
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
