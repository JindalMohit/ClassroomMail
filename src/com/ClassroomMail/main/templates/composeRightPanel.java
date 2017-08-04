package com.ClassroomMail.main.templates;

import com.ClassroomMail.database.mails.sendMail;
import com.ClassroomMail.main.windows.home.main;
import com.ClassroomMail.database.mails.saveAsDraft;
import com.ClassroomMail.main.functions.getMotherboardSN;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class composeRightPanel {

    public static BorderPane threadProfile;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_STRING_REGEX = Pattern.compile("^\\s*$", Pattern.CASE_INSENSITIVE);

    public static BorderPane composeRightPanel(String userMailId){

        threadProfile = new BorderPane();
        threadProfile.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;");
        threadProfile.setPadding(new Insets(10));
        threadProfile.setPrefWidth(400);
        threadProfile.setPrefHeight(1500);

        Label header = new Label("New Message");
        header.setAlignment(Pos.TOP_LEFT);
        header.setFont(new Font("Cambria", 22));
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

        TextArea receipents = new TextArea();
        receipents.setPromptText("Receipents separated by semi-colon");
        receipents.setFont(new Font("Open Sans", 15));
        receipents.setPrefHeight(15);
        receipents.setWrapText(true);
        receipents.setStyle("-fx-background-color: transparent; -fx-text-inner-color: #eee;  -fx-border-color: grey; -fx-border-width: 1 0 1 0; ");
        receipents.setPadding(new Insets(10,0,10,0));

        TextArea subject = new TextArea();
        subject.setPromptText("Subject");
        subject.setFont(new Font("Open Sans", 15));
        subject.setPrefHeight(15);
        subject.setWrapText(true);
        subject.setStyle("-fx-background-color: transparent; -fx-text-inner-color: #eee;  -fx-border-color: grey; -fx-border-width: 0 0 1 0; ");
        subject.setPadding(new Insets(10,0,10,0));

        TextArea body = new TextArea();
        body.setPromptText("Your messag here");
        body.setFont(new Font("Open Sans", 15));
        body.setPrefHeight(main.window.getHeight()-400);
        main.window.heightProperty().addListener(e-> body.setPrefHeight(main.window.getHeight()-400));
        body.setWrapText(true);
        body.setStyle("-fx-background-color: transparent; -fx-text-inner-color: #eee;  -fx-border-color: grey; -fx-border-width: 0 0 1 0; ");
        body.setPadding(new Insets(10,0,10,0));

        Button sendButton = new Button("Send");
        sendButton.setFont(new Font("Open Sans", 15));
        sendButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        sendButton.setTextFill(Color.web("#171717"));
        sendButton.setPadding(new Insets(10));
        sendButton.setCursor(Cursor.HAND);

        CheckBox markImportant = new CheckBox("Mark Important");
        markImportant.setPadding(new Insets(10));
        markImportant.setFont(new Font("Open Sans", 15));
        markImportant.setTextFill(Color.web("#fff"));

        Label saveDraft = GlyphsDude.createIconLabel( FontAwesomeIcon.ERASER,
                "Save As draft",
                "20",
                "15",
                ContentDisplay.LEFT );
        saveDraft.setTextFill(Color.web("#171717"));
        saveDraft.setFont(new Font("Open Sans", 15));
        saveDraft.setPadding(new Insets(10));
        StackPane saveDraftPane = new StackPane(saveDraft);
        saveDraftPane.setAlignment(Pos.BASELINE_LEFT);
        saveDraftPane.setStyle("-fx-background-color: #FF4646; -fx-background-radius: 3;");
        saveDraftPane.setCursor(Cursor.HAND);

        BorderPane action = new BorderPane(markImportant,null,saveDraftPane,null,sendButton);
        action.setPadding(new Insets(15,0,0,0));

        Label error = new Label();
        error.setTextFill(Color.web("red"));
        error.setPadding(new Insets(8,0,0,0));

        sendButton.setOnAction(e-> {
            e.consume();

            String reciepentList = receipents.getText();
            reciepentList = reciepentList.replaceAll("\\s","");

            String mailSubject = subject.getText();
            mailSubject = whitespacevalidate(subject.getText()) ? "(no subject)" : mailSubject;

            if (receipents.getText().isEmpty())
                error.setText("Receipents can't be empty");
            else if (!mailvalidate(reciepentList))
                error.setText("Receipents Email ID incorrect");
            else{
                String messageTimestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                String composeId =  messageTimestamp + getMotherboardSN.getMotherboardSN();

                String status = sendMail.sendMail(messageTimestamp,composeId,mailSubject,userMailId,reciepentList,body.getText(), markImportant.isSelected());
                if (status.equals("success")){
                    threadProfile.getChildren().clear();
                    threadProfile.setPadding(new Insets(0));
                    threadProfile.setPrefWidth(0);
                }
                else
                    error.setText(status);
            }
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(ee-> {
                error.setTextFill(Color.web("red"));
                error.setText("");
            });
            new Thread(sleeper).start();
        });

        saveDraftPane.setOnMouseClicked(e-> {
            e.consume();

            String reciepentList = receipents.getText();
            reciepentList = reciepentList.replaceAll("\\s","");

            String mailSubject = subject.getText();
            mailSubject = whitespacevalidate(subject.getText()) ? "(no subject)" : mailSubject;

            String composeId = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()) +
                    getMotherboardSN.getMotherboardSN();

            String status = saveAsDraft.saveAsDraft(composeId,userMailId,mailSubject,markImportant.isSelected()+"","false","true","true",body.getText(), reciepentList );
            if (status.equals("success")){
                threadProfile.getChildren().clear();
                threadProfile.setPadding(new Insets(0));
                threadProfile.setPrefWidth(0);
            }
            else
                error.setText(status);

            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
                }
            };
            sleeper.setOnSucceeded(ee-> {
                error.setTextFill(Color.web("red"));
                error.setText("");
            });
            new Thread(sleeper).start();
        });

        VBox messageDetail = new VBox(0, receipents, subject, body, error, action);

        threadProfile.setCenter(messageDetail);

        return threadProfile;

    }

    public static boolean mailvalidate(String emailStr) {
        String[] emailArray = emailStr.split(";");
        for (String mail: emailArray) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(mail);
            if (!matcher.find())
                return false;
        }
        return true;
    }

    public static boolean whitespacevalidate(String Str) {
        Matcher matcher = VALID_STRING_REGEX .matcher(Str);
        return matcher.find();
    }

}
