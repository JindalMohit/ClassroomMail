package com.ClassroomMail.database.draft;

import com.ClassroomMail.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class updateThread {

    public static String update(String subjectId, String mailId, String columnName, String value){

        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareUpdateQuery("classroommail.subjectdetails", columnName+" = '"+value+"'","( subjectId = ? AND mailId = ? )");

        String status = "Ongoing";
        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, subjectId);
            stmt.setString(2, mailId);
            stmt.executeUpdate();
            status = "success";
        }
        catch(Exception e){
            e.printStackTrace();
            status = e.getMessage();
        }
        finally{
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
            return status;
        }
    }

}
