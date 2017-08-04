package com.ClassroomMail.main.templates;

import com.ClassroomMail.main.windows.home.main;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import com.ClassroomMail.database.mails.fetchInbox;
import javafx.scene.layout.VBox;

public class centerPanel {

    public static BorderPane centerPanel(String title, String userMailId){
        BorderPane mails = new BorderPane();
        mails.setPadding(new Insets(0,30,0,0));
        VBox mailList = new VBox();

        switch (title) {
            case "Inbox":
                mailList = fetchInbox.fetchMails(title,userMailId);
                break;
            case "Important":
                System.out.println("Important");
                break;
            case "Sent Mail":
                System.out.println("Sent Mail");
                break;
            case "Drafts":
                System.out.println("Drafts");
                break;
            case "Trash":
                System.out.println("Trash");
                break;
        }

        ScrollPane scroller = new ScrollPane(mailList);
        scroller.setPadding(new Insets(10,0,0,0));
        scroller.setStyle("-fx-background-color: transparent");
        scroller.setFitToWidth(true);
        scroller.setVvalue(1.0);
        scroller.vvalueProperty().bind(mailList.heightProperty());
        main.window.heightProperty().addListener(e-> scroller.setPrefHeight(main.window.getHeight()-120));

        mails.setTop(scroller);

        return mails;

    }

}
