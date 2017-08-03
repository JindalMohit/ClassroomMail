package com.ClassroomMail.main.templates;

import com.ClassroomMail.main.windows.home.main;
import com.ClassroomMail.database.chat.fetchChatContact;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class profile {

    public static Scene scene;
    private static VBox options;

    public static BorderPane topPane ;
    public static BorderPane leftPane ;
    public static BorderPane centerPane ;
    public static BorderPane rightPane ;

    public static Scene main(String completeName, String emailId){

        topPane = new BorderPane();
        leftPane = new BorderPane();
        centerPane = new BorderPane();
        rightPane = new BorderPane();

        topPane.setMaxHeight(100);
        leftPane.setPrefWidth(220);
        rightPane.setMaxWidth(400);

        topPane.setPadding(new Insets(10,30,10,30));
        leftPane.setPadding(new Insets(10,30,10,30));

        topPane.setStyle("-fx-background-color: transparent; -fx-border-color: grey; -fx-border-width: 0 0 1 0; -fx-text-color: #eee;");

        //===================================TOP PANE STARTS===================================

        Label title = new Label("Classroom Mail");
        title.setFont(new Font("Open Sans", 20));
        title.setTextFill(Color.web("#ededed"));

        topPane.setLeft(title);

        //===================================TOP PANE ENDS===================================

        //===================================LEFT PANE STARTS===================================

        options = new VBox(10);

        Label compose = new Label("COMPOSE");
        compose.setPadding(new Insets(3,10,3,10));
        compose.setFont(new Font("Open Sans", 15));
        compose.setStyle("-fx-background-color: #f4f4ff; -fx-text-color: #444;");
        compose.setAlignment(Pos.CENTER);
        compose.setCursor(Cursor.HAND);

        options.getChildren().add(compose);
        leftPane.setTop(options);

        String[] option = {"Inbox","Important","Sent Mail","Drafts","Trash"};
        for (String anOption : option) optionLabel(anOption);

        VBox chatlists = fetchChatContact.fetchContacts(emailId);

        ScrollPane scrollerList = new ScrollPane(chatlists);
        scrollerList.setStyle("-fx-background-color: transparent");
        scrollerList.setFitToWidth(true);
        scrollerList.setVvalue(1.0);
        scrollerList.vvalueProperty().bind(chatlists.heightProperty());
        scrollerList.setPadding(new Insets(20,0,0,0));

        leftPane.setCenter(scrollerList);

        //===================================LEFT PANE ENDS===================================

        //===================================CENTER PANE STARTS===================================

        //===================================CENTER PANE ENDS===================================

        BorderPane profilePane = new BorderPane(centerPane,topPane,rightPane,null,leftPane);

        String image = profile.class.getResource("../resources/images/splash.jpg").toExternalForm();
        profilePane.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-size: cover, auto; " +
                "-fx-background-repeat: stretch;");

        scene = new Scene(profilePane,850,550);
        scene.getStylesheets().add(main.class.getResource("../../resources/css/main.css").toExternalForm());

        return scene;
    }

    private static Label optionLabel(String title){

        Label label = new Label(title);
        label.setFont(new Font("Open Sans", 15));
        label.setTextFill(Color.web("#eee"));
        label.setStyle("-fx-background-color: transparent");
        label.setCursor(Cursor.HAND);
        options.getChildren().add(label);

        label.setOnMouseClicked(e-> {
            switch (title) {
                case "Inbox":
                    System.out.println(title);
                    break;
                case "Important":
                    System.out.println(title);
                    break;
                case "Sent Mail":
                    System.out.println(title);
                    break;
                case "Drafts":
                    System.out.println(title);
                    break;
                case "Trash":
                    System.out.println(title);
                    break;
            }
        });

        return label;

    }
}
