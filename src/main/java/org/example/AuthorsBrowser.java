package org.example;

import java.sql.*;
import java.util.Scanner;

public class AuthorsBrowser {
    public static void main(String[] args){
        try(Connection conn=DatabaseUtil.getConnection();
        Statement stm=conn.createStatement();
        ResultSet rs=stm.executeQuery("SELECT * FROM  Authors")){
            System.out.println("All authors: ");
            while (rs.next()){
                int id =rs.getInt("AuthorID:");
                String firstName=rs.getString("Firstname");
                String lastName=rs.getString("LastName");
                System.out.println("ID: " + id+" - "+firstName+" "+lastName);
            }
        } catch (SQLException e){
            System.out.println("Database error: "+e.getMessage());
        }
        Scanner input=new Scanner(System.in);
        System.out.println("\nEnter last name prefix");
        String prefix=input.nextLine();
        String sql="SELECT * FROM Authors WHERE LastName LIKE ?";
        try(Connection conn=DatabaseUtil.getConnection();
            PreparedStatement pstmt=conn.prepareStatement(sql)){
            pstmt.setString(1, prefix+"%");
            ResultSet rs=pstmt.executeQuery();
            boolean found=false;
            while (rs.next()){
                found=true;
                int id=rs.getInt("AuthorID");
                String firstName=rs.getString("FirstName");
                String lastname=rs.getString("LastName");
                System.out.println("ID: "+ id+ " - "+firstName+ " "+lastname);
            }
            if(!found){
                System.out.println("No results found. ");
            }
        } catch  (SQLException e){
            System.out.println("Database error: "+e.getMessage());
        }
        input.close();
    }
}
