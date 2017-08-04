package com.ClassroomMail.database.mails;

import com.ClassroomMail.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class sendMail {

    public static String sendMail(String messageTimestamp, String subjectId, String subjectName, String senderMail, String receiverMail, String message, boolean markimportant){
        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareInsertQuery("classroommail.mails", "messageTimestamp, subjectId, subjectName, senderMail, receiverMail, message","?,?,?,?,?,?");

        String status = "ongoing";

        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, messageTimestamp);
            stmt.setString(2, subjectId);
            stmt.setString(3, subjectName);
            stmt.setString(4, senderMail);
            stmt.setString(5, receiverMail);
            stmt.setString(6, message);
            stmt.executeUpdate();

            saveAsDraft.saveAsDraft(subjectId,receiverMail,subjectName,markimportant+"","false","false","false","","");

            saveAsDraft.saveAsDraft(subjectId,senderMail,subjectName,markimportant+"","false","true","false","","");

            status="success";

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
