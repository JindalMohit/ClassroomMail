package com.ClassroomMail.main.templates;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import com.ClassroomMail.database.mails.fetchInbox;
import javafx.scene.layout.VBox;

public class centerPanel {

    public static BorderPane centerPanel(String title, String userMailId){
        BorderPane mails = new BorderPane();
        VBox mailList = null;

        switch (title) {
            case "Inbox":
                mailList = fetchInbox.fetchMails(title,userMailId);
                break;
            case "Important":
                System.out.println();
                break;
            case "Sent Mail":
                System.out.println();
                break;
            case "Drafts":
                System.out.println();
                break;
            case "Trash":
                System.out.println();
                break;
        }

        ScrollPane scroller = new ScrollPane(mailList);
        scroller.setPadding(new Insets(10,0,0,0));
        scroller.setStyle("-fx-background-color: transparent");
        scroller.setFitToWidth(true);
        scroller.setVvalue(1.0);
        scroller.vvalueProperty().bind(mailList.heightProperty());

        mails.setTop(scroller);

        return mails;

    }

}
