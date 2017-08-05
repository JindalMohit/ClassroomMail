package com.ClassroomMail.main.templates.rightPanel;

import com.ClassroomMail.main.functions.timeStampChangeFormat;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class mailInfo {

    public static BorderPane mailInfo(String senderName, String sentTimestamp, String receiverMailId, String message){

        Label sender = GlyphsDude.createIconLabel( FontAwesomeIcon.USER,
                senderName,
                "20",
                "15",
                ContentDisplay.LEFT );
        sender.setFont(new Font("Open Sans", 15));
        sender.setTextFill(Color.web("#fff"));
        sender.setStyle("-fx-background-color: transparent;");

        Label timestamp = new Label(timeStampChangeFormat.timeStampChangeFormat(sentTimestamp));
        timestamp.setFont(new Font("Open Sans", 12));
        timestamp.setTextFill(Color.web("#ccc"));
        timestamp.setStyle("-fx-background-color: transparent;");

        Label receivers = new Label("      to: "+receiverMailId);        // you may show receiver name using getUserName class
        receivers.setFont(new Font("Open Sans", 12));
        receivers.setTextFill(Color.web("#b2b2b2"));
        receivers.setWrapText(true);
        receivers.setStyle("-fx-background-color: transparent;");

        VBox mailHeader = new VBox(new BorderPane(null,null,timestamp,null,sender), receivers);
        mailHeader.setPadding(new Insets(5));
        mailHeader.setStyle("-fx-background-color: transparent; -fx-border-color: grey; -fx-border-width: 0 0 0.2 0;");

        Label userMessage = new Label(message);
        userMessage.setFont(new Font("Open Sans", 13));
        userMessage.setPadding(new Insets(2,0,8,0));
        userMessage.setTextFill(Color.web("#e5e5e5"));
        userMessage.setWrapText(true);
        userMessage.setStyle("-fx-background-color: transparent;");
        StackPane msg = new StackPane(userMessage);
        msg.setAlignment(Pos.TOP_LEFT);

        BorderPane mail = new BorderPane(msg,mailHeader,null,null,null);
        mail.setStyle("-fx-background-color: transparent; -fx-border-color: grey; -fx-border-width: 0 0 1 0;");
        mail.setPadding(new Insets(0,5,0,5));

        return mail;

    }

}
