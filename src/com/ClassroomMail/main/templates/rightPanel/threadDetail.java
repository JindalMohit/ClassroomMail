package com.ClassroomMail.main.templates.rightPanel;

import com.ClassroomMail.database.draft.fetchThreadDetails;
import com.ClassroomMail.database.draft.updateThread;
import com.ClassroomMail.database.mails.fetchThreadMails;
import com.ClassroomMail.database.mails.sendMail;
import com.ClassroomMail.database.userDetail.getUserName;
import com.ClassroomMail.database.utils.DBUtils;
import com.ClassroomMail.main.windows.home.main;

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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class threadDetail {

    public static BorderPane threadProfile;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_STRING_REGEX = Pattern.compile("^\\s*$", Pattern.CASE_INSENSITIVE);

    public static BorderPane mailsRightPanel(String subjectId, String subjectName, String mailId){

        String[] response = fetchThreadDetails.fetchSubjectDetails(subjectId,mailId);

        String isDraft = response[4];
        String draftMessage = response[5];
        String draftReceipent = response[6];

        threadProfile = new BorderPane();
        threadProfile.setPadding(new Insets(10));
        threadProfile.setMaxWidth(400);

        Label header = new Label(subjectName);
        header.setAlignment(Pos.TOP_LEFT);
        header.setFont(new Font("Cambria", 20));
        header.setTextFill(Color.web("#eee"));
        header.setMaxWidth(350);

        Label close = GlyphsDude.createIconLabel( FontAwesomeIcon.CLOSE,
                "",
                "25",
                "0",
                ContentDisplay.LEFT );
        close.setCursor(Cursor.HAND);
        close.setOnMouseClicked(e-> {
            threadProfile.getChildren().clear();
            threadProfile.setPadding(new Insets(0));
            threadProfile.setPrefWidth(0);
        });

        BorderPane headerTitle = new BorderPane(null,null,close,null,header);
        headerTitle.setPadding(new Insets(0,0,15,0));
        headerTitle.setStyle("-fx-background-color: transparent; -fx-border-color: grey; -fx-border-width: 0 0 0.2 0;");
        headerTitle.setPrefHeight(30);
        threadProfile.setTop(headerTitle);

        VBox mailList = new VBox(fetchThreadMails.fetchThreadMails(subjectId,mailId));
        ScrollPane scroller = new ScrollPane(mailList);
        scroller.setFitToWidth(true);
        scroller.setVvalue(1.0);
        scroller.vvalueProperty().bind(mailList.heightProperty());
        scroller.setPrefHeight(main.window.getHeight()-360);
        main.window.heightProperty().addListener(e-> scroller.setPrefHeight(main.window.getHeight()-360));

        threadProfile.setCenter(scroller);

        TextArea receipents = new TextArea();
        if (isDraft.equals("true"))
            receipents.setText(draftReceipent);
        receipents.setPromptText("Receipents separated by semi-colon");
        receipents.setFont(new Font("Open Sans", 13));
        receipents.setWrapText(true);
        receipents.setMaxHeight(0);
        receipents.setStyle("-fx-background-color: rgba(0, 100, 100, 0.7); -fx-text-inner-color: #fff;  -fx-border-color: grey; -fx-border-width: 1 0 0.5 0; ");

        TextArea body = new TextArea();
        if (isDraft.equals("true"))
            body.setText(draftMessage);
        body.setPromptText("Your message here");
        body.setFont(new Font("Open Sans", 13));
        body.setPrefHeight(100);
        body.setWrapText(true);
        body.setStyle("-fx-background-color: rgba(0, 100, 100, 0.7); -fx-text-inner-color: #fff;  -fx-border-color: grey; -fx-border-width: 0.5 0 1 0; ");
        body.setPadding(new Insets(5));

        Button replyButton = new Button("Send");
        replyButton.setFont(new Font("Open Sans", 12));
        replyButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        replyButton.setTextFill(Color.web("#171717"));
        replyButton.setCursor(Cursor.HAND);

        Button forwardButton = new Button("Forward");
        forwardButton.setFont(new Font("Open Sans", 12));
        forwardButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        forwardButton.setTextFill(Color.web("#171717"));
        forwardButton.setCursor(Cursor.HAND);

        String draftTile = null;
        if (isDraft.equals("true"))
            draftTile="Update Draft";
        else
            draftTile="Save As Draft";

        Label saveDraft = GlyphsDude.createIconLabel( FontAwesomeIcon.SAVE,
            draftTile,
            "15",
            "12",
            ContentDisplay.LEFT );
        saveDraft.setTextFill(Color.web("#171717"));
        saveDraft.setFont(new Font("Open Sans", 12));
        saveDraft.setPadding(new Insets(5));
        StackPane saveDraftPane = new StackPane(saveDraft);
        saveDraftPane.setAlignment(Pos.BASELINE_LEFT);
        saveDraftPane.setStyle("-fx-background-color: #FF4646; -fx-background-radius: 3;");
        saveDraftPane.setCursor(Cursor.HAND);

        BorderPane action = new BorderPane(forwardButton,null,saveDraftPane,null,replyButton);
        action.setPadding(new Insets(3,5,0,5));

        Label error = new Label();
        error.setPrefHeight(10);
        error.setTextFill(Color.web("red"));
        error.setPadding(new Insets(3,0,0,0));

        forwardButton.setOnAction(e-> {
            e.consume();

            String reciepentList = receipents.getText();
            reciepentList = reciepentList.replaceAll("\\s","");

            if (receipents.getText().isEmpty())
                error.setText("Receipents can't be empty");
            else if (!mailvalidate(reciepentList))
                error.setText("Receipents Email ID incorrect");
            else {
                String messageTimestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                String query = DBUtils.prepareSelectQuery(" * ","classroommail.mails",
                        "senderMail LIKE '%" + mailId + "%' and" +
                                " messageTimestamp >= all (SELECT messageTimestamp FROM classroommail.mails WHERE senderMail LIKE '%"+ mailId +"%')");

                String prevMsg = "";
//                System.out.println(query);
                try {
                    Connection con = DBUtils.getConnection();
                    PreparedStatement stmt = con.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery();

                    rs.last();
                    int size = rs.getRow();
                    rs.beforeFirst();

                    if (size>0){
                        while (rs.next()){
                            prevMsg = rs.getString("message");
                        }
                    }
                } catch (SQLException er) {
                    System.out.println(er.toString());
                }
                String status = sendMail.sendMail(messageTimestamp,subjectId,subjectName,mailId,reciepentList,body.getText()+"\n-----\n"+prevMsg, response[1],"forward");
                if (status.equals("success")) {
                    String senderName = getUserName.getUserName(mailId);
                    mailList.getChildren().add(mailInfo.mailInfo(senderName,messageTimestamp,reciepentList,body.getText()));
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

        replyButton.setOnAction(e-> {
            e.consume();

            String reciepentList = receipents.getText();
            reciepentList = reciepentList.replaceAll("\\s","");

            if (receipents.getText().isEmpty())
                error.setText("Receipents can't be empty");
            else if (!mailvalidate(reciepentList))
                error.setText("Receipents Email ID incorrect");
            else{
                String messageTimestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

                String status = sendMail.sendMail(messageTimestamp,subjectId,subjectName,mailId,reciepentList,body.getText(), response[1],"reply");
                if (status.equals("success")){
                    String senderName = getUserName.getUserName(mailId);
                    mailList.getChildren().add(mailInfo.mailInfo(senderName,messageTimestamp,reciepentList,body.getText()));
                    if (isDraft.equals("true")){
                        updateThread.update(subjectId,mailId,"isDraft", "false");
                        updateThread.update(subjectId,mailId,"draftMessage", "");
                        updateThread.update(subjectId,mailId,"draftReceipents", "");
                        updateThread.update(subjectId,mailId,"draftTimestamp", "");
                    }
                    //no need to update msg and receipents. As isDraft is checked for for them
                    saveDraft.setText("Save Draft");
                    receipents.setText("");
                    body.setText("");
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

            updateThread.update(subjectId,mailId,"isDraft", "true");
            updateThread.update(subjectId,mailId,"draftMessage", body.getText());
            updateThread.update(subjectId,mailId,"draftReceipents", reciepentList);

            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            updateThread.update(subjectId,mailId,"draftTimestamp", timestamp);

            saveDraft.setText("Update Draft");
            error.setTextFill(Color.web("green"));
            error.setText("Draft saved successfully !!");

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

        VBox messageDetail = new VBox(0, receipents, body, error, action);
        messageDetail.setMinHeight(150);

        threadProfile.setBottom(messageDetail);

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
