package com.ClassroomMail.database.mails;

import com.ClassroomMail.database.draft.fetchThreadDetails;
import com.ClassroomMail.database.utils.DBUtils;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.ClassroomMail.main.templates.centerPanel.mailThreads.mailThread;

public class fetchMails {

    public static VBox fetchMails(String title, String mailId, String filter) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        VBox mailList = new VBox(10);
        String query = DBUtils.prepareSelectQuery(" * ",
                "classroommail.mails", "receiverMail LIKE '%"+mailId+"%'",
                " GROUP BY subjectId "+filter );

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
                    String message = rs.getString("message");

                    if (title.equals("Inbox"))
                        mailList.getChildren().addAll(mailThread(subjectId,messageTimestamp,mailId,message));
                    else if(title.equals("Important")){
                        String[] response = fetchThreadDetails.fetchSubjectDetails(subjectId,mailId);
                        if (response[1].equals("true")) {
                            mailList.getChildren().addAll(mailThread(subjectId,messageTimestamp,mailId,message));
                        }
                    }

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
