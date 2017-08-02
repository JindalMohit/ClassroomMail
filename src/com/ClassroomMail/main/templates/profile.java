package com.ClassroomMail.main.templates;

import com.ClassroomMail.database.signIn.userSignOut;
import com.ClassroomMail.main.windows.home.main;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class profile {

    public static Label fullName;
    public static VBox lists;

    public static Scene scene;

    public static BorderPane optionData = new BorderPane();

    public static String currentUserEmailId;

    public static Scene main(String completeName, String emailId){

        currentUserEmailId = emailId;
        BorderPane profilePane = new BorderPane();

        BorderPane leftPane = new BorderPane();
        leftPane.setPrefWidth(220);

        VBox user = new VBox(10);
        user.setPadding(new Insets(10));
        user.setAlignment(Pos.TOP_CENTER);

        fullName = new Label(completeName);
        fullName.setPadding(new Insets(10,0,0,0));
        fullName.setFont(new Font("Cambria", 20));
        fullName.setTextFill(Color.web("#ededed"));

        user.getChildren().addAll(fullName);
        leftPane.setTop(user);

        lists = new VBox(15);

        ScrollPane scrollerList = new ScrollPane(lists);
        scrollerList.setStyle("-fx-background-color: transparent");
        scrollerList.setFitToWidth(true);
        scrollerList.setVvalue(1.0);
        scrollerList.vvalueProperty().bind(lists.heightProperty());

        VBox allLists = new VBox(15);
        allLists.getChildren().addAll(scrollerList);

        leftPane.setCenter(allLists);

        Label logout = GlyphsDude.createIconLabel( FontAwesomeIcon.SIGN_OUT,
                "  Log Out",
                "20",
                "18",
                ContentDisplay.LEFT );
        logout.setFont(new Font("Cambria", 20));
        logout.setTextFill(Color.web("#171717"));
        logout.setPadding(new Insets(10));
        StackPane logoutPane = new StackPane(logout);
        logoutPane.setAlignment(Pos.BASELINE_LEFT);
        logoutPane.setStyle("-fx-background-color: #f4f4ff");
        logoutPane.setCursor(Cursor.HAND);

        logoutPane.setOnMouseClicked(e-> {
            userSignOut.userSignOut();
            main.window.setScene(loginHome.homeView());
        });

        leftPane.setBottom(logoutPane);

        profilePane.setLeft(leftPane);
        profilePane.setCenter(optionData);

        scene = new Scene(profilePane,850,550);
        scene.getStylesheets().add(main.class.getResource("../../resources/css/main.css").toExternalForm());

        String image = profile.class.getResource("../resources/images/splash.jpg").toExternalForm();
        optionData.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");
        options.setStyle("-fx-background-image: url('" + image + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;");
        return scene;
    }
}
