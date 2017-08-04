package com.ClassroomMail.database.subjectDetails;

import com.ClassroomMail.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class fetchSubjectDetails {

    public static String[] fetchSubjectDetails(String subjectId, String mailId) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String query = DBUtils.prepareSelectQuery(" * ",
                "classroommail.subjectdetails", " ( subjectId = '"+subjectId+"' AND mailId = '"+mailId+"' ) ",
                "" );
        String[] response = new String[6];

        try {
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            rs = stmt.executeQuery();

            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();

            if (size>0){
                rs.next();
                response[0] = rs.getString("important");
                response[1] = rs.getString("deleted");
                response[2] = rs.getString("latestMessageRead");
                response[3] = rs.getString("isDraft");
                response[4] = rs.getString("draftMessage");
                response[5] = rs.getString("draftReceipents");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(rs, stmt, con);
            return response;
        }

    }


}
