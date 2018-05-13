package res;

import java.sql.Connection;
import java.sql.Statement;

public class Values {

	public static String dbAddress="jdbc:mysql://localhost:3306/billing_system_db";
	public static String dbAdmin="root";
	public static String dbPassword="waseem123";
	public static String loginusername;
	public static String loginpassword;
	public static String encalgo = "MD5";
	public static double goldrate18;
	public static double goldrate22;
	public static double goldrate24;
	public static double silverrate;
	public static Statement stmt=null;
	public static Connection con=null;
}
