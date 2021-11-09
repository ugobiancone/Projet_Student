package com.test.test1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DBConnector {

    public List<Student> loadStudents(){
        List<Student> studentAll = new ArrayList<Student>();
        Connection myConn = this.Connector();
        try {
            Statement myStmt = myConn.createStatement();
            String sql = "SELECT * FROM students";
            ResultSet myRes = myStmt.executeQuery(sql);
            while (myRes.next()) {
                String name = myRes.getString("Name");
                int id = myRes.getInt("Id");
                String gender = myRes.getString("Gender");
                LocalDate birth = null;
                if (myRes.getDate("Birth_date") != null){
                    birth = myRes.getDate("Birth_date").toLocalDate();
                }
                String photo = myRes.getString("Photo");
                String mark = myRes.getString("Mark");
                String comments = myRes.getString("Comment");

                Student s = new Student(id,name,gender,birth,photo,mark,comments);
                studentAll.add(s);
            }
            this.close(myConn, myStmt, myRes);
            return studentAll;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection Connector(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1", "ugo", "2323");
            return connection;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRes) {
        try {
            if (myStmt != null)
                myStmt.close();
            if (myRes != null)
                myRes.close();
            if (myConn != null)
                myConn.close();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateStudent(Student selectedStudent){
        Connection myConn = this.Connector();
        try {
            Statement myStmt = myConn.createStatement();
            String sql;
            if(selectedStudent.getId() != 0) {
                sql = "Update students SET " + "" +
                        "Name ='" + selectedStudent.getName() +
                        "', Gender ='" + selectedStudent.getGender() +
                        "', Birth_date='" + selectedStudent.getBday() +
                        "', Photo = '" + selectedStudent.getPhoto() +
                        "', Mark ='" + selectedStudent.getMark() +
                        "', Comment ='" + selectedStudent.getComments() +
                        "' Where students.Id = " + selectedStudent.getId();
            }
            else{
                sql = "INSERT INTO students(Name, Gender,Birth_date, Photo, Mark, Comment) VALUES( '" +
                        selectedStudent.getName() +
                        "', '" + selectedStudent.getGender() +
                        "', '" + selectedStudent.getBday() +
                        "',  '" + selectedStudent.getPhoto() +
                        "', '" + selectedStudent.getMark() +
                        "', '" + selectedStudent.getComments() +"');";
            }
            myStmt.execute(sql);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}