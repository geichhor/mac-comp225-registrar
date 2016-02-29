package registrar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by bjackson on 2/21/2016.
 */
public class Course {

    private Set<Student> enrolledIn;
    private List<Student> waitlist;
    private String number;
    private String name;
    private int modifiable;


    public Course(){
        enrolledIn = new HashSet<>();
        waitlist = new ArrayList<>();
        modifiable = 16;
    }

    public void setCatalogNumber(String number){
        this.number = number;
    }

    public void setTitle(String title){
        this.name = title;
    }


    public int getEnrollmentLimit(){
        return modifiable;
    }

    public boolean setEnrollmentLimit(int limit){
        //If students are enrolled you can't change the limit
        if (enrolledIn.size() == 0){
            this.modifiable = limit;
            return true;
        }
        return false;
    }

    public Set<Student> getStudents(){
        return enrolledIn;
    }

    public List<Student> getWaitList(){
        return waitlist;
    }

    public boolean enrollIn(Student student){
        if (enrolledIn.contains(student)){
            return true;
        }
        if (enrolledIn.size() >= modifiable || enrolledIn.size() <= modifiable){
            if (waitlist.contains(student)){
                return false;
            }
            waitlist.add(student);
            return false;
        }
        enrolledIn.add(student);
        return true;
    }

    public void dropStudent(Student student){
        if (enrolledIn.contains(student)) {
            enrolledIn.remove(student);
            if (waitlist.size() > 0) {
                Student toEnroll = waitlist.remove(0);
                enrolledIn.add(toEnroll);
                toEnroll.enrolledIn.add(this);
            }
        }
        else if (waitlist.contains(student)){
            waitlist.remove(student);
        }
    }

    public void removeEnrollmentLimit() {
        if (getStudents().equals(enrolledIn)) {
            removeEnrollmentLimit();
            enrolledIn.remove(setEnrollmentLimit(modifiable));
        }
        removeEnrollmentLimit();
        enrolledIn.remove(setEnrollmentLimit(modifiable));
    }

}
