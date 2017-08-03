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

    public static Scene scene;

    public static Scene main(String completeName, String emailId){

        BorderPane topPane = new BorderPane();
        BorderPane leftPane = new BorderPane();
        BorderPane centerPane = new BorderPane();
        BorderPane rightPane = new BorderPane();

        topPane.setMaxHeight(100);
        leftPane.setPrefWidth(220);
        rightPane.setMaxWidth(400);


        //===================================TOP PANE STARTS===================================

        Label title = new Label("Classroom Mail");
        title.setPadding(new Insets(10,0,0,0));
        title.setFont(new Font("Cambria", 20));
        title.setTextFill(Color.web("#ededed"));

        topPane.setLeft(title);

        //===================================TOP PANE ENDS===================================

        //===================================LEFT PANE STARTS===================================



        VBox lists = new VBox(15);

        ScrollPane scrollerList = new ScrollPane(lists);
        scrollerList.setStyle("-fx-background-color: transparent");
        scrollerList.setFitToWidth(true);
        scrollerList.setVvalue(1.0);
        scrollerList.vvalueProperty().bind(lists.heightProperty());

        leftPane.setCenter(scrollerList);

        //===================================LEFT PANE ENDS===================================

        //===================================CENTER PANE STARTS===================================

        //===================================CENTER PANE ENDS===================================

        //===================================RIGHT PANE STARTS===================================

        //===================================RIGHT PANE ENDS===================================

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
}
