package com.ClassroomMail.database.mails;

import com.ClassroomMail.database.utils.DBUtils;
import static com.ClassroomMail.main.templates.mailThread.mailThread;

import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class fetchInbox {

    public static VBox fetchMails(String title, String mailId) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        VBox mailList = new VBox(10);
        String query = DBUtils.prepareSelectQuery(" * ",
                "classroommail.mails", "receiverMail LIKE '%"+mailId+"%'",
                " GROUP BY subjectId ORDER BY messageTimestamp desc " );

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();

            if (size>0){
                while (rs.next()){
                    String subjectId = rs.getString("subjectId");
                    String messageTimestamp = rs.getString("messageTimestamp");
                    String subjectName = rs.getString("subjectName");
                    String senderMail = rs.getString("senderMail");
                    String receiverMail = rs.getString("receiverMail");
                    String message = rs.getString("message");

                    mailList.getChildren().addAll(mailThread(subjectId,messageTimestamp,subjectName,mailId,message));

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
