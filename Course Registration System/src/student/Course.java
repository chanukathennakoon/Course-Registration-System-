package student;

import db.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Course {

    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    //get max table raw

    public int getMax() {
        int id = 0;
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("select max(id) from course");
            while (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (java.sql.SQLException ex) {
            java.util.logging.Logger.getLogger(Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return id + 1;
    }
    
    public boolean getID(int id){
        try {
            ps = con.prepareStatement("select * from student where id =?");
            ps.setInt(1, id);
            ResultSet rs = ps .executeQuery();
            if(rs.next()){
                Home.jTextField16.setText(String.valueOf(rs.getInt(1)));
                return true;
            }else{
                JOptionPane.showMessageDialog(null,"Student id does not exist");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public int countSemester(int id){
        int total = 0;
        try {
            ps = con.prepareStatement("select count(*) as 'total from course where student id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                total = rs.getInt(1);
            }
            if (total == 8){
                JOptionPane.showMessageDialog(null, "This student complete all courses");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    //check weather the student has already taken this semester or not 
    
     public boolean isSemesterExist(int sid ,int  semesterNo) {
        try {
            ps = con.prepareStatement("select * from course where student_id = ? and semester = ?");
            ps.setInt(1, sid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }
//check weather the student has already taken this course or not 
    
     public boolean isCourseExist(int sid ,String courseNo, String course ) {
         String sql = "select * from course where student_id = ? and semester = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, sid);
             ps.setString(1, course);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(Course.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return false;
    }

}
