package com.ClassroomMail.database.mails;

import com.ClassroomMail.database.draft.findUserInThread;
import com.ClassroomMail.database.draft.newThread;
import com.ClassroomMail.database.draft.updateThread;
import com.ClassroomMail.database.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class sendMail {

    public static String sendMail(String messageTimestamp, String subjectId, String subjectName, String senderMail, String receiverMail, String message, String markimportant, String source){
        Connection con = null;
        PreparedStatement stmt = null;

        String query = DBUtils.prepareInsertQuery("classroommail.mails", "messageTimestamp, subjectId, subjectName, senderMail, receiverMail, message","?,?,?,?,?,?");

        String status = "ongoing";

        try{
            con = DBUtils.getConnection();
            stmt = con.prepareStatement(query);
            stmt.setString(1, messageTimestamp);
            stmt.setString(2, subjectId);
            stmt.setString(3, subjectName);
            stmt.setString(4, senderMail);
            stmt.setString(5, receiverMail);
            stmt.setString(6, message);
            stmt.executeUpdate();

            if (!source.equals("reply")){
                newThread.saveAsDraft(subjectId,receiverMail,subjectName,markimportant,"false","false","false","","");
                newThread.saveAsDraft(subjectId,senderMail,subjectName,markimportant,"false","true","false","","");
            }
            else{
                //making latest message read here as false for receiver entries
                String[] emailArray = receiverMail.split(";");

                for (String mail:emailArray) {
                    if (!mail.equals(""))
                    {
                        String userInThread = findUserInThread.finUserInThread(subjectId,mail);
                        if (userInThread.equals("false"))
                            newThread.saveAsDraft(subjectId,mail,subjectName,markimportant,"false","false","false","","");
                        else
                            updateThread.update(subjectId,mail,"latestMessageRead", "false");
                        //repetitive mail entries are ignored. So enjoy !!
                    }
                }
            }

            status="success";

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
