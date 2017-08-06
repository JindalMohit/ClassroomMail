package com.ClassroomMail.main.templates.centerPanel;

import com.ClassroomMail.database.mails.fetchMails;
import com.ClassroomMail.database.mails.searchMails;
import com.ClassroomMail.main.windows.home.main;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import static com.ClassroomMail.main.templates.profile.myComboBox;

public class searchForThread {

    public static String mailIdValue = "";
    public static String filterValue = "";

    public static BorderPane search(String keyword, String userMailId, String filter){

        mailIdValue=userMailId;
        filterValue=filter;

        BorderPane mails = new BorderPane();
        mails.setPadding(new Insets(0,30,0,0));
        VBox mailList = new VBox();
        mailList.getChildren().add(searchMails.search(keyword,mailIdValue,filterValue));
        myComboBox.valueProperty().addListener((ov, t, t1) -> {
            String filt ="";
            if (t1.equals("Sort by Latest"))
                filt = "ORDER BY messageTimestamp desc";
            else if (t1.equals("Sort by Oldest"))
                filt = "ORDER BY messageTimestamp asc";
            mailList.getChildren().clear();
            mailList.getChildren().add(searchMails.search(keyword,mailIdValue,filt));
        });

        ScrollPane scroller = new ScrollPane(mailList);
        scroller.setPadding(new Insets(10,0,0,0));
        scroller.setStyle("-fx-background-color: transparent");
        scroller.setFitToWidth(true);
        scroller.setVvalue(1.0);
        scroller.vvalueProperty().bind(mailList.heightProperty());
        scroller.setPrefHeight(main.window.getHeight()-120);
        main.window.heightProperty().addListener(e-> scroller.setPrefHeight(main.window.getHeight()-120));

        mails.setTop(scroller);

        return mails;

    }

}
