package com.ClassroomMail.database.signIn;

import com.ClassroomMail.database.utils.DBUtils;
import com.ClassroomMail.main.functions.getMotherboardSN;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class dbSignUp {

    public static String userSignUp(String FirstName,String LastName, String emailId, String password, String gender,String contact, String location){
        Connection con = null;
        PreparedStatement stmt = null;

        String userID = getMotherboardSN.getMotherboardSN();

        String query = DBUtils.prepareInsertQuery("classroommail.userdetail", "FirstName,LastName, emailId ,password, gender,contact, location","?,?,?,?,?,?,?");

        String updateCurrentUserQuery = DBUtils.prepareInsertQuery("classroommail.currentuser", "id, FirstName, emailId", "?,?,?");

        String status = "ongoing";

        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, FirstName);
            stmt.setString(2, LastName);
            stmt.setString(3, emailId);
            stmt.setString(4, password);
            stmt.setString(5, gender);
            stmt.setString(6, contact);
            stmt.setString(7, location);
            stmt.executeUpdate();
            status="success";

            stmt = con.prepareStatement(updateCurrentUserQuery);
            stmt.setString(1, userID);
            stmt.setString(2, FirstName);
            stmt.setString(3, emailId);
            stmt.executeUpdate();
        }
        catch(Exception e){
            status = e.getMessage();
        }
        finally{
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
            return status;
        }
    }
}
