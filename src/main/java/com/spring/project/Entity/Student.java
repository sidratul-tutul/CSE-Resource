package com.spring.project.Entity;


import javax.persistence.*;
import javax.validation.MessageInterpolator;
import javax.validation.constraints.*;

@Entity(name = "studentdb")
@Table(name = "studentinfo")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "serial_id")
    private Integer serialId;

    @NotEmpty(message = "name field can't be empty")
   // @Size(min=3, max=30, message = "name must contain characters between (3-30)")
    @Column(name = "student_name")
   // @Pattern(regexp = "[A-Z][a-z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$")
    private String studentName;

    @NotEmpty(message = "id field can't be empty")
  //  @Max(2000)
    @Column(name = "student_id")
    private String studentId;

    @NotEmpty(message = "degree field can't be empty")
    @Column(name = "student_degree")
    private String studentDegree;

    @NotEmpty(message = " 'registration year' field can't be empty")
    @Column(name = "student_registrationYear")
    private String studentRegistrationYear;

    //@NotEmpty(message = "no image was selected")
    @Column(name = "student_image_path")
    private String profileImagePath;

    @Column(name = "student_QRCode_path")
    private String qrCodePath;

    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }

    public String getProfileImagePath() {
        return profileImagePath;
    }

    public void setProfileImagePath(String profileImagePath) {
        this.profileImagePath = profileImagePath;
    }

    private final String studentDepartment="CSE";

    public Student() {
    }

    public Student(Integer serialId, String studentName, String studentId, String studentDegree, String studentRegistrationYear, String profileImagePath, String qrCodePath) {
        this.serialId = serialId;
        this.studentName = studentName;
        this.studentId = studentId;
        this.studentDegree = studentDegree;
        this.studentRegistrationYear = studentRegistrationYear;
        this.profileImagePath = profileImagePath;
        this.qrCodePath = qrCodePath;
    }

    public String makeStudentQRCodeName()
    {
        String QRName = studentName.replaceAll("\\s","")+"-"+studentId+"-"+studentRegistrationYear;
        return QRName;
    }

    public Integer getSerialId() {
        return serialId;
    }

    public void setSerialId(Integer serialId) {
        this.serialId = serialId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentDegree() {
        return studentDegree;
    }

    public void setStudentDegree(String studentDegree) {
        this.studentDegree = studentDegree;
    }

    public String getStudentRegistrationYear() {
        return studentRegistrationYear;
    }

    public void setStudentRegistrationYear(String studentRegistrationYear) {
        this.studentRegistrationYear = studentRegistrationYear;
    }
}
