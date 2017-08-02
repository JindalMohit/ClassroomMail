package com.ClassroomMail.main.windows.home;

import com.ClassroomMail.main.functions.getMotherboardSN;
import com.ClassroomMail.database.logIn.userLoggedIn;
import com.ClassroomMail.main.templates.loginHome;
import com.ClassroomMail.main.templates.profile;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class main extends Application {
    public static Stage window;
    public BorderPane layout;
    public static Scene scene;

    public static void main(String args[])
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        window.setTitle("Classroom Mail");

        String userID = getMotherboardSN.getMotherboardSN();
        String[] status = userLoggedIn.userLoggedIn(userID);

        if (!status[0].equals("success"))
            window.setScene(loginHome.homeView());
        else
            window.setScene(profile.main(status[1], status[2]));

        window.getIcons().add(new Image(getClass().getResourceAsStream("../../resources/images/ClassroomMail.png")));
        window.setMinWidth(850);
        window.setMinHeight(550);

        window.show();

    }
}