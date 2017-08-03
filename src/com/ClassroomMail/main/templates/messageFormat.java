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
        notice.setMaxWidth(300);

        VBox noticeVB = new VBox(0);
        noticeVB.setAlignment(Pos.TOP_RIGHT);

        Label messageLabel = new Label(message);
        messageLabel.setPadding(new Insets(0,0,15,0));
        messageLabel.setFont(new Font("Cambria", 16));
        messageLabel.setTextFill(Color.web("#fff"));
        messageLabel.setAlignment(Pos.BOTTOM_RIGHT);
        messageLabel.setWrapText(true);
        messageLabel.setPrefWidth(280);

        Label time = new Label("\t"+timeStampChangeFormat(timestamp));
        time.setFont(new Font("Cambria", 12));
        time.setTextFill(Color.web("#9c9c9c"));
        time.setPadding(new Insets(5,0,0,0));

        noticeVB.getChildren().addAll(messageLabel,time);

        if (position.equals("left"))
            notice.setLeft(noticeVB);
        else if (position.equals("right"))
            notice.setRight(noticeVB);

        return notice;

    }

    private static String timeStampChangeFormat(String orignal){
        String newTime;
        String[] timearray = orignal.split("\\.");

        if(Integer.parseInt(timearray[3])>12) {
            newTime = (Integer.parseInt(timearray[3])-12) + ":" +timearray[4]+ "pm, ";
        }
        else {
            newTime = timearray[3] + ":" +timearray[4]+ "am, ";
        }

        newTime = newTime + timearray[2] +" "+ months[Integer.parseInt(timearray[1])] +"'"+ timearray[0].substring(2);

        return newTime;
    }
}
