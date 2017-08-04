package com.ClassroomMail.database.mails;

import com.ClassroomMail.database.utils.DBUtils;
import com.ClassroomMail.main.functions.getMotherboardSN;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class sendMail {

    public static String sendMail(String messageTimestamp, String subjectTimestamp, String subjectName, String senderMail, String receiverMail, String message, boolean markimportant){
        Connection con = null;
        PreparedStatement stmt = null;

        String userID = getMotherboardSN.getMotherboardSN();

        String query = DBUtils.prepareInsertQuery("classroommail.mails", "id, messageTimestamp, subjectTimestamp, subjectName, senderMail, receiverMail, message","?,?,?,?,?,?,?");

        String status = "ongoing";

        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, userID);
            stmt.setString(2, messageTimestamp);
            stmt.setString(3, subjectTimestamp);
            stmt.setString(4, subjectName);
            stmt.setString(5, senderMail);
            stmt.setString(6, receiverMail);
            stmt.setString(7, message);
            stmt.executeUpdate();

            saveAsDraft.saveAsDraft(subjectTimestamp,senderMail,subjectName,markimportant+"","false","true","false","","");

            saveAsDraft.saveAsDraft(subjectTimestamp,receiverMail,subjectName,markimportant+"","false","false","false","","");

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
