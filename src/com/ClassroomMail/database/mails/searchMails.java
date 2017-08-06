package com.ClassroomMail.database.mails;

import com.ClassroomMail.database.utils.DBUtils;
import static com.ClassroomMail.main.templates.centerPanel.mailThreads.mailThread;

import javafx.scene.layout.VBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class searchMails {

    public static VBox search(String keyword, String mailId, String filter) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        VBox mailList = new VBox(0);

        String query =DBUtils.prepareSelectQuery(
                " * ",
                " (( "+DBUtils.prepareSelectQuery(
                    " subjectId, messageTimestamp, message ",
                    " classroommail.mails ",
                    " (receiverMail LIKE '%"+keyword+"%' OR senderMail LIKE '%"+keyword+"%' OR message LIKE '%"+keyword+"%') AND (senderMail = '"+mailId+"' OR receiverMail = '"+mailId+"') ",
                    " GROUP BY subjectId desc ")+
                    " ) UNION ( "+
                    DBUtils.prepareSelectQuery(
                            " subjectId, draftTimestamp AS messageTimestamp, draftMessage AS message ",
                            " classroommail.subjectdetails ",
                            " mailId='"+mailId+"' AND (draftMessage LIKE '%"+keyword+"%' OR draftReceipents LIKE '%"+keyword+"%') ")+
                    " ) UNION ( "+
                    DBUtils.prepareSelectQuery(
                            " subjectId, messageTimestamp, message ",
                            " classroommail.mails ",
                            "classroommail.mails.subjectId IN ( "+
                                    DBUtils.prepareSelectQuery(" subjectId ",
                                            " classroommail.subjectdetails ",
                                            " (mailId='"+mailId+"' AND isDraft='false' AND subjectName LIKE '%"+keyword+"%') )"))+
                    " )) AS T ",
                "",
                " GROUP BY subjectId asc "+filter);

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
                    mailList.getChildren().addAll(mailThread("",subjectId, messageTimestamp, mailId, message));
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
