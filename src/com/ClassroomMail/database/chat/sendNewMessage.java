package com.ClassroomMail.database.chat;

import com.ClassroomMail.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class sendNewMessage {

    public static String add(String timeStamp, String sender, String receiver, String message){

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareInsertQuery("classroommail.chat", " timestamp, sender, receiver, message", "?,?,?,?");

        String status = "Ongoing";
        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, timeStamp);
            stmt.setString(2, sender);
            stmt.setString(3, receiver);
            stmt.setString(4, message);
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
