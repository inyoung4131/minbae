package com.minbae;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//
//public class test {
//    public static void main(String[] args) throws Exception {
//
//        Class.forName("org.mariadb.jdbc.Driver");
//
//        Connection con = DriverManager.getConnection(
//                "jdbc:mariadb://1.201.141.79:3306/minbae?serverTimezone=UTC&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true",
//                "minbae",
//                "1234");
//
//        PreparedStatement ps = con.prepareStatement("insert into test2 values(123)");
//        ps.executeUpdate();
//    }
//}
