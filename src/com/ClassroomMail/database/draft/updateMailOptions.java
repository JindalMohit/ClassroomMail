package com.ClassroomMail.database.draft;

import com.ClassroomMail.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class updateMailOptions {

    public static String update(String subjectTimestamp, String mailId, String subjectName, String important, String deleted, String messageRead, String draftMessage){

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareUpdateQuery("classroommail.subjectdetails", "subjectName = ? , important = ?, deleted = ?, messageRead = ?, draftMessage = ?","( subjectTimestamp = ? AND mailId = ? )");

        String status = "Ongoing";
        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, subjectName);
            stmt.setString(2, important);
            stmt.setString(3, deleted);
            stmt.setString(4, messageRead);
            stmt.setString(5, draftMessage);
            stmt.setString(6, subjectTimestamp);
            stmt.setString(7, mailId);
            stmt.executeUpdate();
            status = "success";
        }
        catch(Exception e){
            e.printStackTrace();
            status = e.getMessage();
        }
        finally{
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
            return status;
        }
    }

}
