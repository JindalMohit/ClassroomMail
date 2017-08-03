package com.ClassroomMail.database.chat;

import com.ClassroomMail.database.utils.DBUtils;
import com.ClassroomMail.main.templates.messageFormat;

import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class fetchChat {

    public static VBox fetchChat(String userMailId, String currentUserMailId) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        VBox commentList = new VBox(10);

        String query = DBUtils.prepareSelectQuery(" * ",
                "classroommail.chat", "( ( sender = '"+userMailId+"' AND receiver = '"+currentUserMailId+"' ) OR ( sender = '"+currentUserMailId+"' AND receiver = '"+userMailId+"' ) )",
                "ORDER BY timestamp asc" );

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();

            if (size>0){
                int count = 1;
                while (rs.next()){
                    String timestamp = rs.getString("timestamp");
                    String sender = rs.getString("sender");
                    String message = rs.getString("message");

                    if (sender.equals(currentUserMailId))
                        commentList.getChildren().add(messageFormat.formatmessage(timestamp, message,"right"));
                    else
                        commentList.getChildren().add(messageFormat.formatmessage(timestamp, message,"left"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return commentList;
        }
    }

}
