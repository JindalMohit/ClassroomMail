package com.ClassroomMail.main.templates;

import com.ClassroomMail.database.signIn.dbSignUp;
import com.ClassroomMail.main.windows.home.main;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class userSignUp {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public String status ;

    public BorderPane userSignUp(){
        final BooleanProperty firstTime = new SimpleBooleanProperty(true); // Variable to store the focus on stage load

        BorderPane signUpPane = new BorderPane();
        signUpPane.setPadding(new Insets(20,20,0,20));

        VBox vb = new VBox(15);
        vb.setPadding(new Insets(20,20,20,20));

        TextField FirstName = new TextField();
        FirstName.setFont(new Font("Open Sans", 15));
        FirstName.setPromptText("First Name");
        FirstName.setPrefHeight(30);
        FirstName.setStyle("-fx-background-color: transparent; -fx-border-color: #ededed; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #ededed;");
        FirstName.focusedProperty().addListener((observable,  oldValue,  newValue) -> {
            if(newValue && firstTime.get()){
                signUpPane.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future references
            }
        });

        TextField LastName = new TextField();
        LastName.setFont(new Font("Open Sans", 15));
        LastName.setPromptText("Last Name");
        LastName.setPrefHeight(30);
        LastName.setStyle("-fx-background-color: transparent; -fx-border-color: #ededed; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #ededed;");



        TextField email = new TextField();
        email.setFont(new Font("Open Sans", 15));
        email.setPromptText("Email Id");
        email.setPrefHeight(30);
        email.setStyle("-fx-background-color: transparent; -fx-border-color: #ededed; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #ededed; -fx-text-fill: red;");

        PasswordField password = new PasswordField();
        password.setFont(new Font("Open Sans", 15));
        password.setPromptText("password");
        password.setPrefHeight(30);
        password.setStyle("-fx-background-color: transparent; -fx-border-color: #ededed; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #ededed;");

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setFont(new Font("Open Sans", 15));
        confirmPassword.setPromptText("Confirm password");
        confirmPassword.setPrefHeight(30);
        confirmPassword.setStyle("-fx-background-color: transparent; -fx-border-color: #ededed; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #ededed;");

        TextField gender = new TextField();
        gender.setFont(new Font("Open Sans", 15));
        gender.setPromptText("Gender");
        gender.setPrefHeight(30);
        gender.setStyle("-fx-background-color: transparent; -fx-border-color: #ededed; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #ededed;");

        TextField location = new TextField();
        location.setFont(new Font("Open Sans", 15));
        location.setPromptText("Location");
        location.setPrefHeight(30);
        location.setStyle("-fx-background-color: transparent; -fx-border-color: #ededed; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #ededed;");

        TextField contact = new TextField();
        contact.setFont(new Font("Open Sans", 15));
        contact.setPromptText("contact number");
        contact.setPrefHeight(30);
        contact.setStyle("-fx-background-color: transparent; -fx-border-color: #ededed; -fx-border-width: 2,2,2,2; -fx-border-radius: 200; -fx-text-inner-color: #ededed;");


        Label error = new Label();
        error.setTextFill(Color.web("red"));

        HBox signUpRow = new HBox();
        Button signUpButton = new Button("SignUp");
        signUpButton.setFont(new Font("Open Sans", 18));
        signUpButton.setStyle("-fx-focus-color: transparent;-fx-background-color: #6ac045;");
        signUpButton.setTextFill(Color.web("#ededed"));
        signUpRow.getChildren().addAll(signUpButton);
        signUpRow.setAlignment(Pos.BASELINE_CENTER);

        signUpButton.setOnAction(e-> {
            if (FirstName.getText().isEmpty())
                error.setText("Full Name can't be empty");
            else if (LastName.getText().isEmpty())
                error.setText("Last Name can't be empty");
            else if (email.getText().isEmpty())
                error.setText("Email Id can't be empty");
            else if (!validate(email.getText()))
                error.setText("Email ID incorrect");
            else if (password.getText().isEmpty())
                error.setText("Password can't be empty");
            else if (confirmPassword.getText().isEmpty())
                error.setText("ConfirmPassword can't be empty");
            else if (!password.getText().equals(confirmPassword.getText()))
                error.setText("Password and confirm password don't match");
            else{
                status = dbSignUp.userSignUp(FirstName.getText(),LastName.getText(),email.getText(),password.getText(),gender.getText(),contact.getText(),location.getText());
                if (Objects.equals(status, "success")) {
                    main.window.setScene(profile.main(FirstName.getText(), email.getText()));
                }
                else
                    error.setText(status);
            }
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(ee-> {
                error.setTextFill(Color.web("red"));
                error.setText("");
            });
            new Thread(sleeper).start();
        });

        vb.getChildren().addAll(FirstName,LastName,email, password, confirmPassword, gender,contact, location, error, signUpRow);
        signUpPane.setCenter(vb);
        signUpPane.setMinHeight(400);

        return signUpPane;
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
}
