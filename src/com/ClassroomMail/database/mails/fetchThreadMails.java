package com.ClassroomMail.database.mails;

import com.ClassroomMail.database.utils.DBUtils;
import com.ClassroomMail.database.userDetail.getUserName;

import javafx.scene.layout.VBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class fetchThreadMails {

    public static VBox fetchThreadMails(String subjectId, String mailId) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        VBox mailList = new VBox(10);
        String query = DBUtils.prepareSelectQuery(" * ",
                "classroommail.threadDetail", "subjectId = "+subjectId+" AND ( senderMail LIKE '%"+mailId+"%' OR receiverMail LIKE '%"+mailId+"%' )",
                " ORDER BY messageTimestamp asc " );

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();

            if (size>0){
                while (rs.next()){
                    String messageTimestamp = rs.getString("messageTimestamp");
                    String senderMail = rs.getString("senderMail");
                    String senderName = getUserName.getUserName(senderMail);
                    String receiverMail = rs.getString("receiverMail");
                    String message = rs.getString("message");


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return mailList;
        }

    }

}
