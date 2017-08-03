package com.ClassroomMail.main.templates;

import com.ClassroomMail.main.windows.home.main;

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
    public static VBox options;

    public static Scene main(String completeName, String emailId){

        BorderPane topPane = new BorderPane();
        BorderPane leftPane = new BorderPane();
        BorderPane centerPane = new BorderPane();
        BorderPane rightPane = new BorderPane();

        topPane.setMaxHeight(100);
        leftPane.setPrefWidth(220);
        rightPane.setMaxWidth(400);

        topPane.setPadding(new Insets(0,0,10,0));

        //===================================TOP PANE STARTS===================================

        Label title = new Label("Classroom Mail");
        title.setFont(new Font("Open Sans", 20));
        title.setTextFill(Color.web("#ededed"));

        topPane.setLeft(title);

        //===================================TOP PANE ENDS===================================

        //===================================LEFT PANE STARTS===================================

        options = new VBox(10);

        Label compose = new Label("COMPOSE");
        compose.setPadding(new Insets(5,10,5,10));
        compose.setFont(new Font("Open Sans", 15));
        compose.setTextFill(Color.web("#444"));
        compose.setAlignment(Pos.CENTER);
        compose.setStyle("-fx-background-color: #f4f4ff");
        compose.setCursor(Cursor.HAND);
        options.getChildren().add(compose);

        String[] option = {"Inbox","Important","Sent Mail","Drafts","Trash"};
        for (String anOption : option) optionLabel(anOption);


        VBox chatlists = new VBox(15);

        ScrollPane scrollerList = new ScrollPane(chatlists);
        scrollerList.setStyle("-fx-background-color: transparent");
        scrollerList.setFitToWidth(true);
        scrollerList.setVvalue(1.0);
        scrollerList.vvalueProperty().bind(chatlists.heightProperty());

        leftPane.setTop(options);
        leftPane.setCenter(scrollerList);

        //===================================LEFT PANE ENDS===================================

        //===================================CENTER PANE STARTS===================================

        //===================================CENTER PANE ENDS===================================

        //===================================RIGHT PANE STARTS===================================

        //===================================RIGHT PANE ENDS===================================

        BorderPane profilePane = new BorderPane(centerPane,topPane,rightPane,null,leftPane);
        profilePane.setPadding(new Insets(10,50,0,50));

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
