package com.test.test1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.*;
import java.time.LocalDate;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Student_Controller implements Initializable {

    @FXML
    TextField namebox;

    @FXML
    ComboBox<String> genderbox;

    @FXML
    DatePicker bdaybox;

    @FXML
    TextField markbox;

    @FXML
    TextArea commentsbox;

    @FXML
    ImageView photobox;

    @FXML
    ListView<Student> studentsbox;

    @FXML
    Button editbtn;

    @FXML
    Button savebtn;

    @FXML
    Button cancelbtn;

    @FXML
    Button newbtn;

    @FXML
    Label lblurl;

    @FXML
    Button deletebtn;



    public void fetchStudents() {
        List<Student> listStudents = manager.loadStudents();
        if (listStudents != null) {
            ObservableList<Student> students;
            students = FXCollections.observableArrayList(listStudents);
            studentsbox.setItems(students);
        }
    }


    public void onEdit(){
        savebtn.disableProperty().set(false);
        cancelbtn.disableProperty().set(false);
    }

    public void onCancel(){
        savebtn.disableProperty().set(true);
        cancelbtn.disableProperty().set(true);
    }

    public void chooseImage(){
        Image image;
        InputStream is = null;
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(lblurl.getScene().getWindow());
        if (selectedFile != null){
            lblurl.setText(selectedFile.getAbsolutePath());
            selectedStudent.setPhoto(selectedFile.getAbsolutePath());
            try {
                is = new FileInputStream(selectedFile.getAbsolutePath());
                image = new Image(is);
                photobox.setImage(image);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    public void onSave(){
        int id = selectedStudent.getId();
        selectedStudent.setName(namebox.getText());
        selectedStudent.setGender(genderbox.getSelectionModel().getSelectedItem());
        selectedStudent.setMark(markbox.getText());
        selectedStudent.setComments(commentsbox.getText());
        selectedStudent.setPhoto(lblurl.getText());
        selectedStudent.setBday(bdaybox.getValue());
        manager.updateStudent(selectedStudent);
        onCancel();
    }

    public void onNew(){
        Student s = new Student();
        displayStudentDetails(s);
        savebtn.disableProperty().set(false);
        cancelbtn.disableProperty().set(false);
        newbtn.disableProperty().set(true);
        editbtn.disableProperty().set(true);
    }

    public void onDelete(){
        deletebtn.disableProperty().set(true);
        delete(selectedStudent);
    }

    DBConnector manager;
    Student selectedStudent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedStudent = null;
        manager = new DBConnector();
        List<String> gvalues = new ArrayList<String>();
        gvalues.add("Male");
        gvalues.add("Female");
        ObservableList<String> gender = FXCollections.observableArrayList(gvalues);
        genderbox.setItems(gender);
        fetchStudents();
        studentsbox.getSelectionModel().selectedItemProperty().addListener(e -> displayStudentDetails(studentsbox.getSelectionModel().getSelectedItem()));
    }

    private void displayStudentDetails(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
        deletebtn.disableProperty().set(false);

        try {
            namebox.setText(selectedStudent.getName());
            genderbox.setValue(selectedStudent.getGender());
            markbox.setText(String.valueOf(selectedStudent.getMark()));
            bdaybox.setValue(selectedStudent.getBday());

            Image image;
            InputStream is = null;

            if(selectedStudent.getPhoto() != null) {
                is = new FileInputStream(selectedStudent.getPhoto());
                lblurl.setText(selectedStudent.getPhoto());
                image = new Image(is);
                photobox.setImage(image);
            } else {
                try {
                    is = new FileInputStream("/home/ugo/Pictures/studentAno.jpg");
                    image = new Image(is);
                    photobox.setImage(image);
                    lblurl.setText("/home/ugo/Pictures/studentAno.jpg");
                }
                catch (Exception e)
                {
                    is = new FileInputStream("C:\\Users\\login\\OneDrive - De Vinci\\A4\\[JV] Java JEE Secure Coding\\Projet\\Projet_Student\\images\\anonyme.png");
                    image = new Image(is);
                    photobox.setImage(image);
                    lblurl.setText("C:\\Users\\login\\OneDrive - De Vinci\\A4\\[JV] Java JEE Secure Coding\\Projet\\Projet_Student\\images\\anonyme.png");
                }
            }
            markbox.setText(String.valueOf(selectedStudent.getMark()));
            commentsbox.setText(selectedStudent.getComments());
            editbtn.disableProperty().set(false);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
