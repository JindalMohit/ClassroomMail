package com.ClassroomMail.main.templates.centerPanel;

import com.ClassroomMail.database.draft.updateThread;
import com.ClassroomMail.main.functions.timeStampChangeFormat;
import com.ClassroomMail.database.draft.fetchThreadDetails;
import com.ClassroomMail.main.templates.rightPanel.threadDetail;
import static com.ClassroomMail.main.templates.profile.*;

import com.ClassroomMail.main.windows.home.main;
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

public class mailThreads {

    public static StackPane mailThreadstag;

    public static BorderPane mailThread(String title, String subjectId, String messageTimestamp, String mailId, String message){

        String[] response = fetchThreadDetails.fetchSubjectDetails(subjectId,mailId);

        if (!title.equals("Trash") && response[2].equals("true")){ //checking for deleted thread
            BorderPane empty = new BorderPane();
            empty.setMaxSize(0,0);
            return empty;
        }

        Label importantTag = new Label();
        if(response[1].equals("true")){
            importantTag.setText("i");
            importantTag.setStyle("-fx-background-color: #FFD700; -fx-border-color: #FFD700; -fx-border-width: 1 1 1 1;");
        }
        else{
            importantTag.setText("");
            importantTag.setStyle("-fx-background-color: transparent; -fx-border-color: grey; -fx-border-width: 1 1 1 1;");
        }
        importantTag.setFont(new Font("Open Sans", 0));
        importantTag.setPadding(new Insets(10));
        importantTag.setTextFill(Color.web("#fff"));
        importantTag.setPrefWidth(20);
        mailThreadstag = new StackPane(importantTag);
        mailThreadstag.setPadding(new Insets(10));
        mailThreadstag.setCursor(Cursor.HAND);

        Label subject = new Label(response[0]);
        subject.setFont(new Font("Open Sans", 17));
        subject.setPadding(new Insets(8));
        subject.setTextFill(Color.web("#fff"));
        subject.setPrefWidth(280);

        Label lastmessage = new Label(message);
        lastmessage.setFont(new Font("Open Sans", 12));
        lastmessage.setPadding(new Insets(12));
        lastmessage.setTextFill(Color.web("#fff"));
        lastmessage.setPrefWidth(90);

        HBox mailContent = new HBox(5,subject, lastmessage);
        mailContent.setCursor(Cursor.HAND);
        mailContent.setMaxHeight(45);

        Label delete ;
        if (title.equals("Trash"))
            delete = GlyphsDude.createIconLabel( FontAwesomeIcon.UNDO,
                    "",
                    "20",
                    "0",
                    ContentDisplay.LEFT );
        else
            delete = GlyphsDude.createIconLabel( FontAwesomeIcon.CLOSE,
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

        final BorderPane mails = new BorderPane(null, null, new HBox(0, delete, lastMessageTime), null, new HBox(0, mailThreadstag, mailContent));
        if(response[3].equals("true"))
            mails.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-border-color: grey; -fx-border-width: 0 0 1 0;");
        else
            mails.setStyle("-fx-background-color: rgba(0, 100, 100, 1); -fx-border-color: grey; -fx-border-width: 0 0 1 0;");

        final double[] width = {main.window.getWidth() - 200};
        subject.setMaxWidth(0.4* width[0]);
        lastmessage.setMaxWidth(0.6* width[0]);

        main.window.widthProperty().addListener(e-> {
            width[0] = main.window.getWidth()-300;
            subject.setMaxWidth(0.4* width[0]);
            lastmessage.setMaxWidth(0.6* width[0]);
        });

        mailThreadstag.setOnMouseClicked(e-> {
            e.consume();
            if (importantTag.getText().equals("i")){
                String status = updateThread.update(subjectId,mailId,"important", "false");
                if (status.equals("success")){
                    importantTag.setText("");
                    importantTag.setStyle("-fx-background-color: transparent; -fx-border-color: grey; -fx-border-width: 1 1 1 1; ");
                }
            }
            else{
                String status = updateThread.update(subjectId,mailId,"important", "true");
                if (status.equals("success")){
                    importantTag.setText("i");
                    importantTag.setStyle("-fx-background-color: #FFD700; -fx-border-color: #FFD700; -fx-border-width: 1 1 1 1; ");
                }
            }
        });

        mailContent.setOnMouseClicked(e-> {
            String status = updateThread.update(subjectId,mailId,"latestMessageRead", "true");
            if (status.equals("success")){
                rightPane.setTop(threadDetail.mailsRightPanel(subjectId,response[0],mailId));
                mails.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 2; -fx-border-color: grey; -fx-border-width: 0 0 1 0;");
            }
        });

        delete.setOnMouseClicked(e-> {
            String status;
            if (title.equals("Trash"))
                status = updateThread.update(subjectId,mailId,"deleted", "false");
            else
                status = updateThread.update(subjectId,mailId,"deleted", "true");
            if (status.equals("success")){
                mails.setStyle("-fx-border-width: 0 0 0 0;");
                mails.getChildren().clear();
                mails.setPadding(new Insets(0));
                mails.setPrefSize(0,0);
            }
        });

        return mails;
    }

}
