package com.ClassroomMail.main.templates.centerPanel;

import com.ClassroomMail.database.mails.fetchMails;
import com.ClassroomMail.main.windows.home.main;
import static com.ClassroomMail.main.templates.profile.*;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class centerPanel {

    public static String mailIdValue = "";
    public static String filterValue = "";
    public static VBox mailList = new VBox();

    public static BorderPane centerPanel(String title, String userMailId, String filter){

        mailIdValue=userMailId;
        filterValue=filter;

        BorderPane mails = new BorderPane();
        mails.setPadding(new Insets(0,30,0,0));

        switch (title) {
            case "Inbox":
                optionMails("Inbox");
                break;
            case "Important":
                optionMails("Important");
                break;
            case "Sent Mail":
                optionMails("Sent Mail");
                break;
            case "Drafts":
                optionMails("Drafts");
                break;
            case "Trash":
                optionMails("Trash");
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

    public static void optionMails(String option){
        mailList.getChildren().clear();
        mailList.getChildren().add(fetchMails.fetchMails(option,mailIdValue,filterValue));
        myComboBox.valueProperty().addListener((ov, t, t1) -> {
            String filt ="";
            if (t1.equals("Sort by Latest"))
                filt = "ORDER BY messageTimestamp desc";
            else if (t1.equals("Sort by Oldest"))
                filt = "ORDER BY messageTimestamp asc";
            mailList.getChildren().clear();
            mailList.getChildren().add(fetchMails.fetchMails(option,mailIdValue,filt));
        });
    }

}
