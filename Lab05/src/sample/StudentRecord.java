package sample;

import java.text.DecimalFormat;

/**
 * Created by Arsh on 3/9/2017.
 */
public class StudentRecord {
    private String studentID;
    private float assignments;
    private float midterm;
    private float finalExam;
    private double finalMark;
    private String letterGrade;

    public StudentRecord(String studentID, float assignments, float midterm, float finalExam) {
        this.studentID = studentID;
        this.assignments = assignments;
        this.midterm = midterm;
        this.finalExam = finalExam;
        this.finalMark = 0.2 * this.assignments + 0.3 * this.midterm + 0.5 * this.finalExam;
        if (this.finalMark >= 80){
            this.letterGrade = "A";
        }
        else if(this.finalMark >= 70){
            this.letterGrade = "B";
        }
        else if(this.finalMark >= 60){
            this.letterGrade = "C";
        }
        else if(this.finalMark >= 50){
            this.letterGrade = "D";
        }
        else{
            this.letterGrade = "F";
        }
    }

    public void setStudentID(String value){
        this.studentID = value;
    }
    public void setAssignments(float val){
        this.assignments = val;
    }
    public void setMidterm(float val){
        this.midterm = val;
    }
    public void setFinalExam(float val){
        this.finalExam = val;
    }
    public void setFinalMark(double val){
        this.finalMark = val;
    }
    public void setLetterGrade(String val){
        this.letterGrade = val;
    }

    public String getStudentID(){
        return this.studentID;
    }
    public float getAssignments(){
        return this.assignments;
    }
    public float getMidterm(){
        return this.midterm;
    }
    public float getFinalExam(){
        return this.finalExam;
    }
    public String getFinalMark(){
        DecimalFormat df = new DecimalFormat("0.00000");
        return df.format(this.finalMark);
    }
    public String getLetterGrade(){
        return this.letterGrade;
    }

}
