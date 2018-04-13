package phoenix.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class PhoenixJdbcSingle {
    static String url = "jdbc:phoenix:c1,c2,c3:2222";
//    static String name = "hadoop";
//    static String password = "sli";
    static Connection conn = null;
    private static PhoenixJdbcSingle jdbcUtilSingle = null;

    public static PhoenixJdbcSingle getInitJDBCUtil() {
        if (jdbcUtilSingle == null) {
            // 给类加锁 防止线程并发
            synchronized (PhoenixJdbcSingle.class) {
                if (jdbcUtilSingle == null) {
                    jdbcUtilSingle = new PhoenixJdbcSingle();
                }
            }
        }
        return jdbcUtilSingle;
    }

    private PhoenixJdbcSingle() {
    }

    // 通过静态代码块注册数据库驱动，保证注册只执行一次
    static {
        try {
            // 注册驱动有如下方式：
            // 1.通过驱动管理器注册驱动，但会注册两次，并且会对类产生依赖。如果该类不存在那就报错了。
            // DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            // 2.与3类似
            // System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver");
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");// 推荐使用方式
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获得连接
    public Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }

    // 关闭连接
    public void closeConnection(ResultSet rs, Statement statement, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
