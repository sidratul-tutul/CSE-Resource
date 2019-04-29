package com.spring.project.Controller;

import com.google.zxing.WriterException;
import com.spring.project.Entity.Student;
import com.spring.project.QRCode.QRCodeGenerator;
import com.spring.project.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.spring.project.QRCode.QRCodeGenerator.*;

@Controller
public class StudentController {

   private List<Student> students;

    @Autowired
    private StudentService studentService;


    public static final String uploadingDir = System.getProperty("user.dir") + "/src/main/resources/static/images/profile_images/";
    public static final String PROFILE_PATH = "/images/profile_images/";
    public static final String QR_PATH = "/images/QRCodes/";



    @RequestMapping("/index")
    public String indexController()
    {
        return "index";
    }
    @RequestMapping("/home")
    public String homeController()
    {
        return "home";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationFormController(Student student)
    {
        return "registrationForm";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String  registrationController(@Valid Student student, Errors errors, MultipartFile profileImage, Model model) throws IOException, WriterException {

        if(errors.hasErrors())
        {
            return "registrationForm";
        }
        else
        {
            byte[] bytes = profileImage.getBytes();
            Path path = Paths.get(uploadingDir+profileImage.getOriginalFilename());
            Files.write(path,bytes);

            String profileImagePath = PROFILE_PATH+profileImage.getOriginalFilename();
            student.setProfileImagePath(profileImagePath);

            String QRName = student.makeStudentQRCodeName();

            String qrCodePath = QR_PATH + QRCodeGenerator.writeQRCode(QRName);
            student.setQrCodePath(qrCodePath);

            System.out.println("NAME "+ qrCodePath);

            studentService.saveStudent(student);
            students = studentService.findAllStudent();

            for(int i=0; i<students.size(); i++)
            {
                System.out.println(students.get(i).getProfileImagePath());
            }

            model.addAttribute("students",students);
            return "studentList";
        }
    }


    @RequestMapping(value = "/allstudent", method = RequestMethod.GET)
    public String findAllStudent(Model model)
    {
        students = studentService.findAllStudent();
        model.addAttribute("students",students);
        return "studentList";
    }

    @RequestMapping(value = "/idcard/{serialId}", method = RequestMethod.POST)
    public String  genarateIdCard(@PathVariable Integer serialId,  Model model){

        students = studentService.findAllStudent();
        Student student = students.get((serialId-1));

        System.out.println("QRCode Path ::  "+ student.getQrCodePath());
        model.addAttribute("student", student);
        return "idcard";
    }


    @RequestMapping(value = "/editprofile/{serialId}", method = RequestMethod.POST)
    public String  updateStudentInfo(@PathVariable Integer serialId,  Model model){

        students = studentService.findAllStudent();
        Student student = students.get((serialId-1));

        model.addAttribute("student", student);
        return "updateStudent";
    }

    @RequestMapping(value = "/editprofile", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("userUpgradationForm")Student student, MultipartFile profileImage,  Model model) throws IOException, WriterException
    {
        Integer serialId = student.getSerialId();
        Student updatedStudent =  studentService.getOne((serialId));

        if(!student.getStudentName().equals(""))
        {
            updatedStudent.setStudentName(student.getStudentName());
        }

        if(!student.getStudentId().equals(""))
        {
            updatedStudent.setStudentId(student.getStudentId());
        }

        if(!student.getStudentDegree().equals(""))
        {
            updatedStudent.setStudentDegree(student.getStudentDegree());
        }

        if(!profileImage.isEmpty())
        {
            byte[] bytes = profileImage.getBytes();
            Path path = Paths.get(uploadingDir+profileImage.getOriginalFilename());
            Files.write(path,bytes);

            String profileImagePath = PROFILE_PATH+profileImage.getOriginalFilename();
            updatedStudent.setProfileImagePath(profileImagePath);
        }


/*        System.out.print("N    : "+updatedStudent.getSerialId()+"\n");
        System.out.print("Name : "+updatedStudent.getStudentName()+"\n");
        System.out.print("Id   : "+updatedStudent.getStudentId()+"\n");*/

        studentService.saveStudent(updatedStudent);

        students = studentService.findAllStudent();
        model.addAttribute("students",students);

        return "studentList";
    }

    // deletestudent


    @RequestMapping(value = "/deletestudent/{serialId}", method = RequestMethod.POST)
    public String  removeStudent(@PathVariable Integer serialId,  Model model){

        Student student = studentService.findStudentBySerialId(serialId);
        studentService.remove(student);

        students = studentService.findAllStudent();
        model.addAttribute("students", students);
        return "studentList";
    }

}
