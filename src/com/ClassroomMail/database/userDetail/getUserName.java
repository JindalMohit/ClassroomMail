package com.ClassroomMail.database.userDetail;

import com.ClassroomMail.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class getUserName {

        public static String getUserName(String mailId) {
            String fullName="";

            Connection con = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            String query = DBUtils.prepareSelectQuery(" fullName ",
                    "classroommail.userdetail",
                    " emailId = '"+mailId+"' ",
                    "" );

            try {
                con = DBUtils.getConnection();
                stmt = con.prepareStatement(query);
                rs = stmt.executeQuery();

                rs.last();
                int size = rs.getRow();
                rs.beforeFirst();

                if (size>0){
                    int count = 1;
                    rs.next();
                    fullName = rs.getString("fullName");
                }
                else
                    fullName ="No results found";

            } catch (Exception e) {
                fullName = e.getMessage();
                e.printStackTrace();
            } finally {
                DBUtils.closeAll(rs, stmt, con);
                return fullName;
            }
        }

}
