package com.ClassroomMail.main.templates;

import com.ClassroomMail.main.windows.home.main;
import com.ClassroomMail.database.chat.fetchChatContact;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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

    final static BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

    public static Scene main(String completeName, String emailId){

        topPane = new BorderPane();
        leftPane = new BorderPane();
        centerPane = new BorderPane();
        rightPane = new BorderPane();

        topPane.setMaxHeight(100);
        leftPane.setPrefWidth(220);

        topPane.setPadding(new Insets(10,30,10,30));
        leftPane.setPadding(new Insets(10,30,10,30));

        topPane.setStyle("-fx-background-color: transparent; -fx-border-color: grey; -fx-border-width: 0 0 1 0; -fx-text-color: #eee;");

        //===================================TOP PANE STARTS===================================

        Label title = new Label("Classroom Mail");
        title.setFont(new Font("Open Sans", 25));
        title.setTextFill(Color.web("#ededed"));
        title.setPadding(new Insets(5,20,0,0));

        TextField mailSearch = new TextField();
        mailSearch.setPromptText("Search by keyword, mail Ids, Subject, etc");
        mailSearch.setFont(new Font("Open Sans", 15));
        mailSearch.setPrefHeight(30);
        mailSearch.setStyle("-fx-background-color: transparent; -fx-border-color: #ededed; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #ededed;");
        mailSearch.setPadding(new Insets(5,10,5,10));
        mailSearch.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                leftPane.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        ComboBox<String> myComboBox = new ComboBox<>();
        myComboBox.getItems().addAll(
                "Sort by Latest",
                "Sort by Oldest",
                "Sort by Subject");
        myComboBox.setValue("Sort by Latest");
        myComboBox.setPadding(new Insets(3));
        myComboBox.valueProperty().addListener((ov, t, t1) -> {
            switch (t1) {
                case "Sort by Latest":
                    break;
                case "Sort by Oldest":
                    break;
                case "Sort by Subject":
                    break;
                default:
                    break;
            }
        });

        HBox filterCombo = new HBox(myComboBox);
        filterCombo.setPadding(new Insets(5,0,0,30));

        topPane.setLeft(title);
        topPane.setCenter(mailSearch);
        topPane.setRight(filterCombo);

        //===================================TOP PANE ENDS===================================

        //===================================LEFT PANE STARTS===================================

        options = new VBox(10);

        Label compose = new Label("COMPOSE");
        compose.setPadding(new Insets(3,10,3,10));
        compose.setFont(new Font("Open Sans", 15));
        compose.setStyle("-fx-background-color: #f4f4ff; -fx-text-color: #444;");
        compose.setAlignment(Pos.CENTER);
        compose.setCursor(Cursor.HAND);
        compose.setOnMouseClicked(event -> {
            rightPane.setTop(composeRightPanel.composeRightPanel(emailId));
        });

        options.getChildren().add(compose);
        leftPane.setTop(options);

        String[] option = {"Inbox","Important","Sent Mail","Drafts","Trash"};
        for (String anOption : option) optionLabel(anOption, emailId);

        Label userName = GlyphsDude.createIconLabel( FontAwesomeIcon.USER,
                completeName,
                "25",
                "15",
                ContentDisplay.LEFT );
        userName.setPadding(new Insets(5));
        userName.setFont(new Font("Open Sans", 15));
        userName.setTextFill(Color.web("#171717"));
        StackPane userNamePane = new StackPane(userName);
        userNamePane.setAlignment(Pos.BASELINE_LEFT);
        userNamePane.setStyle("-fx-background-color: #f4f4ff");

        Label addContact = GlyphsDude.createIconLabel( FontAwesomeIcon.PLUS_SQUARE,
                "",
                "25",
                "15",
                ContentDisplay.LEFT );
        addContact.setPadding(new Insets(5));
        StackPane addContactPane = new StackPane(addContact);
        addContactPane.setAlignment(Pos.BASELINE_RIGHT);
        addContactPane.setStyle("-fx-background-color: #f4f4ff");
        addContactPane.setCursor(Cursor.HAND);

        BorderPane addUser = new BorderPane(userNamePane,null,addContactPane,null,null);
        addUser.setPadding(new Insets(0));
        addUser.setStyle("-fx-background-color: red");
        options.getChildren().add(addUser);

        final VBox[] chatlists = {fetchChatContact.fetchContacts(emailId)};
        addContactPane.setOnMouseClicked(e-> {
            addToContact ob = new addToContact();
            String userMailId = ob.addToContact(emailId);
            if (!userMailId.equals("")){
                chatlists[0].getChildren().clear();
                chatlists[0] = fetchChatContact.fetchContacts(emailId);
            }
        });

        ScrollPane scrollerList = new ScrollPane(chatlists[0]);
        scrollerList.setStyle("-fx-background-color: transparent");
        scrollerList.setFitToWidth(true);
        scrollerList.setVvalue(1.0);
        scrollerList.vvalueProperty().bind(chatlists[0].heightProperty());
        scrollerList.setPadding(new Insets(20,0,0,0));

        leftPane.setCenter(scrollerList);

        //===================================LEFT PANE ENDS=====================================

//        centerPane = centerPanel.centerPanel("Inbox", emailId);

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

    private static Label optionLabel(String title, String emailId){

        Label label = new Label(title);
        label.setFont(new Font("Open Sans", 15));
        label.setTextFill(Color.web("#eee"));
        label.setStyle("-fx-background-color: transparent");
        label.setCursor(Cursor.HAND);

        if (title.equals("Trash"))
            label.setPadding(new Insets(0,0,30,0));

        options.getChildren().add(label);

        label.setOnMouseClicked(e-> centerPane = centerPanel.centerPanel(title, emailId));

        return label;

    }
}
