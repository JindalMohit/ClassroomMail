package com.ClassroomMail.database.mails;

import com.ClassroomMail.database.draft.fetchThreadDetails;
import com.ClassroomMail.database.utils.DBUtils;
import static com.ClassroomMail.main.templates.centerPanel.mailThreads.mailThread;

import javafx.scene.layout.VBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class fetchMails {

    public static VBox fetchMails(String title, String mailId, String filter) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        VBox mailList = new VBox(0);

        String tableName = "";
        String whereClause = "";
        String otherClause = "";

        String subjectId;
        String messageTimestamp;
        String message;
        String[] response;

        switch (title) {
            case "Inbox":
                tableName = " classroommail.mails ";
                whereClause = " receiverMail LIKE '%" + mailId + "%' ";
                otherClause = " GROUP BY subjectId desc "+filter ;
                break;
            case "Sent Mail":
                tableName = " classroommail.mails ";
                whereClause = "senderMail LIKE '%" + mailId + "%'";
                otherClause = " GROUP BY subjectId desc "+filter ;
                break;
            case "Important":
                tableName = " classroommail.mails ";
                whereClause = " receiverMail LIKE '%" + mailId + "%' OR senderMail = '" + mailId + "'";
                otherClause = " GROUP BY subjectId desc "+filter ;
                break;
            case "Drafts":
                tableName = " classroommail.subjectdetails ";
                whereClause = " mailId = '" + mailId + "' AND isDraft = 'true' ";
                otherClause = " ORDER BY draftTimestamp "+ filter.substring(filter.length()-4) ;
                break;
            case "Trash":
                tableName = " classroommail.mails ";
                whereClause = " receiverMail LIKE '%" + mailId + "%' OR senderMail LIKE '%" + mailId + "%'";
                otherClause = " GROUP BY subjectId desc "+filter ;
                break;
        }

        String query = DBUtils.prepareSelectQuery(" * ",tableName,whereClause,otherClause );

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();

            if (size>0){
                while (rs.next()){

                    switch (title) {
                        case "Inbox":
                        case "Sent Mail":
                            subjectId = rs.getString("subjectId");
                            messageTimestamp = rs.getString("messageTimestamp");
                            message = rs.getString("message");
                            mailList.getChildren().addAll(mailThread(title,subjectId, messageTimestamp, mailId, message));
                            break;
                        case "Important":
                            subjectId = rs.getString("subjectId");
                            messageTimestamp = rs.getString("messageTimestamp");
                            message = rs.getString("message");
                            response = fetchThreadDetails.fetchSubjectDetails(subjectId, mailId);
                            if (response[1]!=null && response[1].equals("true"))
                                mailList.getChildren().addAll(mailThread(title,subjectId, messageTimestamp, mailId, message));
                            break;
                        case "Drafts":
                            subjectId = rs.getString("subjectId");
                            messageTimestamp = rs.getString("draftTimestamp");
                            message = rs.getString("draftMessage");
                            mailList.getChildren().addAll(mailThread(title,subjectId, messageTimestamp, mailId, message));
                            break;
                        case "Trash":
                            subjectId = rs.getString("subjectId");
                            messageTimestamp = rs.getString("messageTimestamp");
                            message = rs.getString("message");
                            response = fetchThreadDetails.fetchSubjectDetails(subjectId, mailId);
                            if (response[2]!=null && response[2].equals("true"))
                                mailList.getChildren().addAll(mailThread(title,subjectId, messageTimestamp, mailId, message));
                            break;
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
