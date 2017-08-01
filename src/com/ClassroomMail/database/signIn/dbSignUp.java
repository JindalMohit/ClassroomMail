package com.ClassroomMail.database.signIn;

import com.ClassroomMail.database.utils.DBUtils;
import com.ClassroomMail.main.functions.getMotherboardSN;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class dbSignUp {

    public static String userSignUp(String companyName, String fullName, String employeeEmailId, String password){
        Connection con = null;
        PreparedStatement stmt = null;

        String userID = getMotherboardSN.getMotherboardSN();

        String query = DBUtils.prepareInsertQuery("classroomwunderlist.company", "companyName ,fullName, employeeEmailId ,password","?,?,?,?");

        String updateCurrentUserQuery = DBUtils.prepareInsertQuery("classroomwunderlist.currentuser", "id, companyName, fullName, employeeEmailId", "?,?,?,?");

        String status = "ongoing";

        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, companyName);
            stmt.setString(2, fullName);
            stmt.setString(3, employeeEmailId);
            stmt.setString(4, password);
            stmt.executeUpdate();
            status="success";

            stmt = con.prepareStatement(updateCurrentUserQuery);
            stmt.setString(1, userID);
            stmt.setString(2, companyName);
            stmt.setString(3, fullName);
            stmt.setString(4, employeeEmailId);
            stmt.executeUpdate();
        }
        catch(Exception e){
            status = e.getMessage();
        }
        finally{
            DBUtils.closeStatement(stmt);
            DBUtils.closeConnection(con);
            return status;
        }
    }
}
