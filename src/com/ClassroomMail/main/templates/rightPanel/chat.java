package com.ClassroomMail.main.templates.rightPanel;

import com.ClassroomMail.database.chat.fetchChat;
import com.ClassroomMail.database.chat.sendNewMessage;
import com.ClassroomMail.main.windows.home.main;
import com.ClassroomMail.main.templates.messageFormat;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class chat {

    public static ScrollPane scroller;
    public static BorderPane threadProfile;
    public static BorderPane messages;

    public static BorderPane chatRightPanel(String userName, String userMailId, String currentUserMailId){

        VBox fetchedMessages = new VBox(15);

        threadProfile = new BorderPane();
        threadProfile.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-border-color: grey; -fx-border-width: 0 0 0 1; -fx-text-color: #eee;");
        threadProfile.setPadding(new Insets(10));
        threadProfile.setPrefWidth(350);

        Label header = new Label(userName);
        header.setAlignment(Pos.TOP_LEFT);
        header.setFont(new Font("Open Sans", 22));
        header.setTextFill(Color.web("#eee"));

        Label close = GlyphsDude.createIconLabel( FontAwesomeIcon.CLOSE,
                "",
                "30",
                "0",
                ContentDisplay.LEFT );
        close.setCursor(Cursor.HAND);
        close.setOnMouseClicked(e-> {
            threadProfile.getChildren().clear();
            threadProfile.setPadding(new Insets(0));
            threadProfile.setPrefWidth(0);
        });

        BorderPane headerTitle = new BorderPane(null,null,close,null,header);
        headerTitle.setPadding(new Insets(0,0,20,0));

        threadProfile.setTop(headerTitle);

        messages = new BorderPane();
        messages.setStyle("-fx-background-color: transparent");
        messages.setPrefHeight(main.window.getHeight()-250);
        main.window.heightProperty().addListener(e-> messages.setPrefHeight(main.window.getHeight()-250));

        fetchedMessages.getChildren().add(fetchChat.fetchChat(userMailId, currentUserMailId));

        messages.setTop(fetchedMessages);

        scroller = new ScrollPane(messages);
        scroller.setFitToWidth(true);
        scroller.setVvalue(1.0);
        scroller.vvalueProperty().bind(fetchedMessages.heightProperty());

        threadProfile.setCenter(scroller);

        BorderPane mymessageCorner = new BorderPane();
        mymessageCorner.setPadding(new Insets(15,10,0,10));

        TextArea newMessage = new TextArea();
        newMessage.setPromptText("Enter your message here");
        newMessage.setStyle("-fx-focus-color: transparent; -fx-border-color: #fff;-fx-border-width: 1 1 1 0;-fx-padding: 0 0 0 -2;");
        newMessage.setPrefHeight(50);
        newMessage.setWrapText(true);
        newMessage.setPrefWidth(300);

        Button send = new Button("Send");
        send.setPrefHeight(50);
        send.setFont(new Font("Cambria", 16));
        send.setStyle("-fx-background-color: #6ac045; -fx-focus-color: transparent; ; -fx-border: 0");
        send.setTextFill(Color.web("#fff"));
        send.setCursor(Cursor.HAND);

        Label error = new Label("");
        error.setTextFill(Color.web("red"));

        send.setOnAction(e-> {
            if (!newMessage.getText().equals("")){
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                String status = sendNewMessage.add(timeStamp,currentUserMailId,userMailId, newMessage.getText());

                if (Objects.equals(status, "success")){
                    BorderPane newmessage = messageFormat.formatmessage(timeStamp, newMessage.getText(),"right");
                    fetchedMessages.getChildren().add(newmessage);
                    newMessage.setText("");
                }
                else
                    error.setText(status);
            }
        });

        mymessageCorner.setLeft(newMessage);
        mymessageCorner.setRight(send);
        mymessageCorner.setBottom(error);

        threadProfile.setBottom(mymessageCorner);

        return threadProfile;

    }

}
