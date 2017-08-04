package com.ClassroomMail.main.templates;

import com.ClassroomMail.main.functions.timeStampChangeFormat;
import com.ClassroomMail.database.subjectDetails.fetchSubjectDetails;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class mailThread {

    public static BorderPane mailThread(String subjectId, String messageTimestamp, String subjectName, String mailId, String message){

        String[] response = fetchSubjectDetails.fetchSubjectDetails(subjectId,mailId);

        if (response[1].equals("true")) //checking for deleting thread
            return null;

        Label importantTag = new Label();
        importantTag.setFont(new Font("Open Sans", 20));
        importantTag.setPadding(new Insets(3,7,3,7));
        importantTag.setTextFill(Color.web("#fff"));
        importantTag.setPrefHeight(30);
        StackPane tag = new StackPane(importantTag);
        tag.setPadding(new Insets(5));

        if(response[0].equals("true")){
            importantTag.setText("I");
            importantTag.setStyle("-fx-background-color: #ff4040;");
        }
        else{
            importantTag.setText(" ");
            importantTag.setStyle("-fx-background-color: transparent;");
        }

        Label subject = new Label(subjectName);
        subject.setFont(new Font("Open Sans", 15));
        subject.setPadding(new Insets(10));
        subject.setTextFill(Color.web("#fff"));
        subject.setAlignment(Pos.TOP_LEFT);
        subject.setCursor(Cursor.HAND);
        subject.setOnMouseClicked(event -> {
            System.out.println("Open mail");
        });

        Label delete = GlyphsDude.createIconLabel( FontAwesomeIcon.CLOSE,
                "",
                "20",
                "0",
                ContentDisplay.LEFT );
        delete.setPadding(new Insets(10));
        delete.setCursor(Cursor.HAND);
        delete.setAlignment(Pos.BASELINE_RIGHT);

        Label lastMessageTime = new Label(timeStampChangeFormat.timeStampChangeFormat(messageTimestamp));
        lastMessageTime.setFont(new Font("Open Sans", 12));
        lastMessageTime.setPadding(new Insets(10));
        lastMessageTime.setTextFill(Color.web("#fff"));
        lastMessageTime.setAlignment(Pos.CENTER_RIGHT);

        BorderPane mails = new BorderPane(null,null,new HBox(0,delete,lastMessageTime),null,new HBox(0, tag,subject));

        if(response[2].equals("true"))
            mails.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 2;");
        else
            mails.setStyle("-fx-background-color: rgba(0, 100, 100, 1); -fx-background-radius: 2;");

        return mails;
    }

}
