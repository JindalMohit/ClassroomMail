package com.ClassroomMail.database.signIn;

import com.ClassroomMail.database.utils.DBUtils;
import com.ClassroomMail.main.functions.getMotherboardSN;

public class userSignOut {

    public static void userSignOut() {

        String userID = getMotherboardSN.getMotherboardSN();

        DBUtils.performAction("DELETE FROM `classroomwunderlist`.`currentuser` WHERE `id`='"+userID+"';");
    }

}