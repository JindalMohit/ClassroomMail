package com.ClassroomMail.database.draft;

import com.ClassroomMail.database.utils.DBUtils;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class findUserInThread {

    public static String finUserInThread(String subjectId, String mailId) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ",
                "classroommail.subjectdetails",
                " ( subjectId = '"+subjectId+"' AND mailId = '"+mailId+"' )",
                "" );

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            rs.last();
            int size = rs.getRow();

            if (size>0)
                return "true";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
        }
        return "false";
    }

}
