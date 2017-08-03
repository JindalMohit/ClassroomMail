package com.ClassroomMail.main.templates;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class messageFormat {

    public static String[] months = {"","Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};

    public static BorderPane formatmessage(String timestamp, String message, String position){

        BorderPane notice = new BorderPane();
        notice.setPadding(new Insets(0,-20,0,10));
        notice.setMaxWidth(340);

        VBox noticeVB = new VBox(5);
        noticeVB.setStyle("-fx-background-color: transparent; -fx-border-color: #333; -fx-border-width: 1,1,1,1; -fx-border-radius: 10; -fx-text-color: #333;");

        Label messageLabel = new Label(message);
        messageLabel.setPadding(new Insets(5));
        messageLabel.setFont(new Font("Cambria", 16));
        messageLabel.setTextFill(Color.web("#191919"));
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(320);

        Label time = new Label(timeStampChangeFormat(timestamp));
        time.setFont(new Font("Cambria", 12));
        time.setTextFill(Color.web("#4c4c4c"));
        time.setPadding(new Insets(5));
        time.setMaxWidth(320);

        noticeVB.getChildren().addAll(messageLabel,time);

        if (position.equals("left")){
            messageLabel.setAlignment(Pos.BOTTOM_LEFT);
            time.setAlignment(Pos.BOTTOM_LEFT);
            notice.setLeft(noticeVB);
            noticeVB.setStyle("-fx-background-color: #fff");
        }
        else if (position.equals("right")){
            messageLabel.setAlignment(Pos.BOTTOM_RIGHT);
            time.setAlignment(Pos.BOTTOM_RIGHT);
            notice.setRight(noticeVB);
            noticeVB.setStyle("-fx-background-color: #ccccdd");
        }

        return notice;

    }

    private static String timeStampChangeFormat(String orignal){
        String newTime;
        String[] timearray = orignal.split("\\.");

        if(Integer.parseInt(timearray[3])>12)
            newTime = (Integer.parseInt(timearray[3])-12) + ":" +timearray[4]+ "pm, ";
        else
            newTime = timearray[3] + ":" +timearray[4]+ "am, ";

        newTime = newTime + timearray[2] +" "+ months[Integer.parseInt(timearray[1])] +"'"+ timearray[0].substring(2);

        return newTime;
    }
}
