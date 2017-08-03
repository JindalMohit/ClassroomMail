package com.ClassroomMail.main.templates;

import com.ClassroomMail.database.chat.fetchChat;
import com.ClassroomMail.main.windows.home.main;
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

public class chatRightPanel {

    public static ScrollPane scroller;
    public static BorderPane threadProfile;
    public static BorderPane messages;

    public static BorderPane chatRightPanel(String userName, String userMailId, String currentUserMailId){

        VBox fetchedMessages = new VBox(15);

        threadProfile = new BorderPane();
        threadProfile.setStyle("-fx-background-color: transparent; -fx-border-color: grey; -fx-border-width: 0 0 0 1; -fx-text-color: #eee;");

        Label header = new Label(userName);
        header.setAlignment(Pos.TOP_LEFT);
        header.setFont(new Font("Cambria", 22));
        header.setTextFill(Color.web("#ededed"));

        BorderPane headerTitle = new BorderPane(null,null,null,null,header);
        headerTitle.setPadding(new Insets(0,0,30,0));

        threadProfile.setTop(headerTitle);

        messages = new BorderPane();
        messages.setStyle("-fx-background-color: transparent");
        messages.setPrefHeight(main.window.getHeight()-350);
        main.window.heightProperty().addListener(e-> messages.setPrefHeight(main.window.getHeight()-350));

        fetchedMessages.getChildren().add(fetchChat.fetchChat(userMailId, currentUserMailId));

        messages.setTop(fetchedMessages);

        scroller = new ScrollPane(messages);
        scroller.setFitToWidth(true);
        scroller.setVvalue(1.0);
        scroller.vvalueProperty().bind(fetchedMessages.heightProperty());

        threadProfile.setCenter(scroller);

        BorderPane mymessageCorner = new BorderPane();
        mymessageCorner.setPadding(new Insets(15,0,0,0));

        TextArea newMessage = new TextArea();
        newMessage.setPromptText("Enter your message here");
        newMessage.setStyle("-fx-focus-color: transparent; -fx-border-color: #fff;-fx-border-width: 1 1 1 0;-fx-padding: 0 0 0 -2;");
        newMessage.setPrefHeight(50);
        newMessage.setWrapText(true);
        newMessage.setPrefWidth(main.window.getWidth()-430);
        main.window.widthProperty().addListener(e-> newMessage.setPrefWidth(main.window.getWidth()-430));

        Button send = new Button("Send");
        send.setPrefHeight(50);
        send.setFont(new Font("Cambria", 16));
        send.setStyle("-fx-background-color: #6ac045; -fx-focus-color: transparent; ; -fx-border: 0");
        send.setTextFill(Color.web("#fff"));
        send.setCursor(Cursor.HAND);

        Label error = new Label("");
        error.setTextFill(Color.web("red"));

//        send.setOnAction(e-> {
//            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
//            String status = addNewMessage.add(timeStamp,companyName, threadMailId, currentUserMailId, newMessage.getText());
//
//            if (status=="success"){
//                BorderPane newmessage = message.rightformatmessage(timeStamp, newMessage.getText());
//                fetchedMessages.getChildren().add(newmessage);
//                newMessage.setText("");
//            }
//            else
//                error.setText(status);
//        });

        mymessageCorner.setLeft(newMessage);
        mymessageCorner.setRight(send);
        mymessageCorner.setBottom(error);

        threadProfile.setBottom(mymessageCorner);

        return threadProfile;

    }

}
