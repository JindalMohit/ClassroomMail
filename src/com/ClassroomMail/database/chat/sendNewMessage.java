package com.ClassroomMail.database.chat;

import com.ClassroomMail.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class sendNewMessage {

    public static String add(String timeStamp, String companyName,String listName,String bugName,String emailId,String comment){

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareInsertQuery("classroomwunderlist.bugcomment", "timestamp, companyName, listName, bugName, emailId, comment", "?,?,?,?,?,?");

        String status = "Ongoing";
        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, timeStamp);
            stmt.setString(2, companyName);
            stmt.setString(3, listName);
            stmt.setString(4, bugName);
            stmt.setString(5, emailId);
            stmt.setString(6, comment);
            stmt.executeUpdate();
            status ="success";
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
