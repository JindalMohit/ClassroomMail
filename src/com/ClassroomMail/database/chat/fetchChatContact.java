package com.ClassroomMail.database.chat;

import com.ClassroomMail.database.utils.DBUtils;
import com.ClassroomMail.main.templates.rightPanel.chat;
import com.ClassroomMail.database.userDetail.getUserName;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.ClassroomMail.main.templates.profile.rightPane;

public class fetchChatContact {

    public static VBox messageList = new VBox(5);

    public static VBox fetchContacts(String mailId) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String queryReceiver = DBUtils.prepareSelectQuery(" DISTINCT(receiver) AS mailId",
                "classroommail.chat",
                " sender = '"+mailId+"'","");

        String querySender = DBUtils.prepareSelectQuery(" DISTINCT(sender) AS mailId",
                "classroommail.chat",
                " receiver = '"+mailId+"'","");

        String finalQuery = "( " + queryReceiver + " ) UNION ( " + querySender+ " )";

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(finalQuery);
            rs = stmt.executeQuery();

            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();

            if (size>0){
                while (rs.next()){
                    String userMailId = rs.getString("mailId");
                    addcontact(getUserName.getUserName(userMailId),userMailId,mailId);
                }
            }
            else{
                Label noContact = new Label("No Contacts found in Database");
                noContact.setFont(new Font("Cambria", 12));
                noContact.setTextFill(Color.web("#444"));
                messageList.getChildren().add(noContact);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            DBUtils.closeAll(rs, stmt, con);
            return messageList;
        }
    }

    public static void addcontact(String userName, String userMailId, String currentUserMailId){

        Label contactlogo = new Label(userName.toUpperCase().charAt(0)+"");
        contactlogo.setAlignment(Pos.BASELINE_CENTER);
        contactlogo.setPadding(new Insets(5,10,5,10));
        contactlogo.setFont(new Font("Open Sans", 12));
        contactlogo.setTextFill(Color.web("#eee"));
        contactlogo.setStyle("-fx-background-color: transparent; -fx-border-color: #eee; -fx-border-width: 1,1,1,1; -fx-border-radius: 10; -fx-text-color: #eee;");

        Label contactLabel = new Label(userName);
        contactLabel.setPadding(new Insets(5));
        contactLabel.setFont(new Font("Open Sans", 12));
        contactLabel.setTextFill(Color.web("#eee"));
        contactLabel.setStyle("-fx-background-color: transparent");
        contactLabel.setCursor(Cursor.HAND);
        contactLabel.setAlignment(Pos.BASELINE_LEFT);

        contactLabel.setOnMouseClicked(e-> {
            rightPane.setTop(chat.chatRightPanel(userName, userMailId, currentUserMailId));
        });

        messageList.getChildren().add(new HBox(5,contactlogo,contactLabel));
    }

}
