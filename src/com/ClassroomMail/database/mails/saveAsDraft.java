package com.ClassroomMail.database.mails;

import com.ClassroomMail.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class saveAsDraft {

    public static String saveAsDraft(String subjectTimestamp, String mailId, String subjectName, String important, String deleted, String latestMessageRead, String isDraft, String draftMessage, String draftReceipents){
        Connection con = null;
        PreparedStatement stmt = null;

        String addtoDrafts = DBUtils.prepareInsertQuery("classroommail.subjectdetails", "subjectTimestamp, mailId, subjectName, important, deleted, latestMessageRead, isDraft, draftMessage, draftReceipents","?,?,?,?,?,?,?,?,?");

        String status = "ongoing";

        try{
            con = DBUtils.getConnection();

            stmt = con.prepareStatement(addtoDrafts);
            stmt.setString(1, subjectTimestamp);
            stmt.setString(3, subjectName);
            stmt.setString(4, important);
            stmt.setString(5, deleted);
            stmt.setString(6, latestMessageRead);
            stmt.setString(7, isDraft);
            stmt.setString(8, draftMessage);
            stmt.setString(9, draftReceipents);

            String[] emailArray = mailId.split(";");

            //before using emailArray, remove duplicate enteries.

            for (String mail:emailArray) {
                if (!mail.equals(""))
                {
                    stmt.setString(2, mail);
                    stmt.executeUpdate();
                }
            }

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
