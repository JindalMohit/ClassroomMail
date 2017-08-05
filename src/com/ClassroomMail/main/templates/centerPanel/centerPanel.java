package com.ClassroomMail.main.templates.centerPanel;

import com.ClassroomMail.database.mails.fetchImportantThread;
import com.ClassroomMail.main.windows.home.main;
import static com.ClassroomMail.main.templates.profile.*;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import com.ClassroomMail.database.mails.fetchInboxThread;
import javafx.scene.layout.VBox;

public class centerPanel {

    public static BorderPane centerPanel(String title, String userMailId, String filter){
        BorderPane mails = new BorderPane();
        mails.setPadding(new Insets(0,30,0,0));
        final VBox[] mailList = {new VBox()};

        switch (title) {
            case "Inbox":
                mailList[0].getChildren().add(fetchInboxThread.fetchMails(userMailId,filter));
                myComboBox.valueProperty().addListener((ov, t, t1) -> {
                    String filt ="";
                    if (t1.equals("Sort by Latest"))
                        filt = "ORDER BY messageTimestamp desc";
                    else if (t1.equals("Sort by Oldest"))
                        filt = "ORDER BY messageTimestamp asc";
                    mailList[0].getChildren().clear();
                    mailList[0].getChildren().add(fetchInboxThread.fetchMails(userMailId,filt));
                });
                break;
            case "Important":
                mailList[0] = fetchImportantThread.fetchMails(userMailId,filter);
                myComboBox.valueProperty().addListener((ov, t, t1) -> {
                    String filt ="";
                    if (t1.equals("Sort by Latest"))
                        filt = "ORDER BY messageTimestamp desc";
                    else if (t1.equals("Sort by Oldest"))
                        filt = "ORDER BY messageTimestamp asc";
                    mailList[0].getChildren().clear();
                    mailList[0].getChildren().add(fetchImportantThread.fetchMails(userMailId,filt));
                });
                break;
            case "Sent Mail":
                mailList[0] = new VBox();
                break;
            case "Drafts":
                mailList[0] = new VBox();
                break;
            case "Trash":
                mailList[0] = new VBox();
                break;
        }

        ScrollPane scroller = new ScrollPane(mailList[0]);
        scroller.setPadding(new Insets(10,0,0,0));
        scroller.setStyle("-fx-background-color: transparent");
        scroller.setFitToWidth(true);
        scroller.setVvalue(1.0);
        scroller.vvalueProperty().bind(mailList[0].heightProperty());
        main.window.heightProperty().addListener(e-> scroller.setPrefHeight(main.window.getHeight()-120));

        mails.setTop(scroller);

        return mails;

    }

}
