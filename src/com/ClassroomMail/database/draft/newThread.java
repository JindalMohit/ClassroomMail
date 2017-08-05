package com.ClassroomMail.database.draft;

import com.ClassroomMail.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class newThread {

    public static String saveAsDraft(String subjectId, String mailId, String subjectName, String important, String deleted, String latestMessageRead, String isDraft, String draftMessage, String draftReceipents){
        Connection con = null;
        PreparedStatement stmt = null;

        String addtoDrafts = DBUtils.prepareInsertQuery("classroommail.subjectdetails", "subjectId, mailId, subjectName, important, deleted, latestMessageRead, isDraft, draftMessage, draftReceipents","?,?,?,?,?,?,?,?,?");

        String status = "ongoing";

        try{
            con = DBUtils.getConnection();

            stmt = con.prepareStatement(addtoDrafts);
            stmt.setString(1, subjectId);
            stmt.setString(3, subjectName);
            stmt.setString(4, important);
            stmt.setString(5, deleted);
            stmt.setString(6, latestMessageRead);
            stmt.setString(7, isDraft);
            stmt.setString(8, draftMessage);
            stmt.setString(9, draftReceipents);

            String[] emailArray = mailId.split(";");

            for (String mail:emailArray) {
                if (!mail.equals(""))
                {
                    stmt.setString(2, mail);
                    stmt.executeUpdate();
                    //multiple mail entries are ignored. So enjoy !!
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
