package com.ClassroomMail.main.templates.leftPanel;

import com.ClassroomMail.database.chat.sendNewMessage;
import com.ClassroomMail.database.userDetail.getUserName;

import com.ClassroomMail.main.templates.loginHome;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class addToContact {
    public static final Pattern VALID_STRING_REGEX = Pattern.compile("^\\s*$", Pattern.CASE_INSENSITIVE);

    public String addToContact(String currntUserMailId){
        Stage newUser = new Stage();

        final String[] name = {""};

        TextField userMaild = new TextField();
        userMaild.setPromptText("Enter the valid MailId");
        userMaild.setFont(Font.font(15));
        userMaild.setPrefHeight(20);
        userMaild.setPrefColumnCount(30);
        userMaild.setStyle("-fx-background-color: transparent; -fx-border-color: #fff; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #fff;");

        HBox okRow = new HBox();
        Button ok = new Button("OK");
        ok.setFont(new Font("Cambria", 18));
        ok.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        ok.setTextFill(Color.web("#fff"));
        okRow.getChildren().addAll(ok);
        okRow.setAlignment(Pos.BASELINE_CENTER);
        ok.setOnAction(e-> {
            name[0] = userMaild.getText();
            newUser.close();
        });

        BorderPane pane = new BorderPane(userMaild,null,null,okRow,null);
        pane.setPadding(new Insets(20));
        String image = loginHome.class.getResource("../resources/images/splash.jpg").toExternalForm();
        pane.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");

        Scene scene = new Scene(pane);
        scene.getAccelerators().put(
                new KeyCodeCombination(KeyCode.ENTER),
                ok::fire
        );

        newUser.setScene(scene);
        newUser.getIcons().add(new Image(getClass().getResourceAsStream("../resources/images/ClassroomMail.png")));
        newUser.setTitle("Add to Contact");
        newUser.setResizable(false);
        newUser.initModality(Modality.APPLICATION_MODAL);
        newUser.showAndWait();

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        if (!name[0].equals("") && !validate(name[0]) && !getUserName.getUserName(name[0]).equals("No results found"))
            sendNewMessage.add(timeStamp,currntUserMailId, name[0],"Hello there !!");
        else
            name[0]="";

        return name[0];
    }

    public static boolean validate(String Str) {
        Matcher matcher = VALID_STRING_REGEX .matcher(Str);
        return matcher.find();
    }
}
