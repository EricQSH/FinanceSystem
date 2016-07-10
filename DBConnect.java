import java.sql.*;

public class DBConnect 
{
	static Connection conn;
	
	public DBConnect()
	{
		// TODO Auto-generated method stub   
		String driver = "com.mysql.jdbc.Driver";// 驱动程序名        
	    String url = "jdbc:mysql://127.0.0.1:3307/test";// URL指向要访问的数据库名scutcs                 
		String user = "root";// MySQL配置时的用户名     
		String password = "T28Z6b";// MySQL配置时的密码           
	    try 
	    {             // 加载驱动程序            
	    	Class.forName(driver);
	    	Connection conn = DriverManager.getConnection(url, user, password); // 连续数据库            
    		if(!conn.isClosed())  System.out.println("Succeeded connecting to the Database!");
	    	Statement statement = conn.createStatement();// statement用来执行SQL语句  
	    	/* String sql = "select * from student"; // 要执行的SQL语句   
	    	ResultSet rs = statement.executeQuery(sql); // 结果集  
	    	while(rs.next())
	    	{                 
	    	}
	    	rs.close();            
	    	*/
	    	conn.close();
	    } 
	    catch(ClassNotFoundException e) 
	    {
	    	System.out.println("Sorry,can`t find the Driver!");             
	    	e.printStackTrace();
	    } 
	    catch(SQLException e) 
	    {
	    	e.printStackTrace();
	    }
	    catch(Exception e) 
	    {
	    	e.printStackTrace();
	    }
	}
	public static Connection GetConn()
	{
		return conn;
	}
	
	public static ResultSet Query(Connection conn, String query_sql)
	{
		try
		{
			String sql = "select * from " + query_sql; // 要执行的SQL语句   
			String tableName = query_sql;// .substring(0, query_sql.indexOf('('));
	    	rs = statement.executeQuery(sql); // 结果集
	    	while(rs.next())
	    	{
	    		int userno = rs.getInt(1);
	    		String password = rs.getString(2);
	    		String username = rs.getString(3);
	    		// System.out.print(userno + " " + password + " " + username + "\n");
	    	}
	    	System.out.print(tableName + " Data query succeed!");
	    	
		}
	    catch (SQLException e) 
	    {
	    	e.printStackTrace();
	    }
	    catch (Exception e) 
	    {
	    	e.printStackTrace();
	    }
		return rs;
	}

}
