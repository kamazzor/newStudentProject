/**
 * @author Kozlov Mikhail
 */

/**
 * Class where check if students from student order are valid to get student pay
 */
public class StudentOrderValidator {
    public static void main(String[] args) {
        checkAll();
    }

    /***
     * Check all requirements to get student pay
     */
    static void checkAll(){
        StudentOrder so = readStudentOrder();

        AnswerCityRegister cityAnswer = checkCityRegister(so);
        AnswerWedding wedAnswer = checkWedding(so);
        AnswerChildren childAnswer = checkChildren(so);
        AnswerStudent studentAnswer = checkStudent(so);

        sendMail(so);
    }

    /***
     *
     * @param so
     */
    static void sendMail(StudentOrder so) {

    }

    static StudentOrder readStudentOrder() {
        StudentOrder so = new StudentOrder();
        return so;
    }

    /***
     * Check if Students from student order have city register in SPb
     */
    static AnswerCityRegister checkCityRegister(StudentOrder so){
        System.out.println("CityRegister is running");
        return new AnswerCityRegister();
    }

    /***
     * Check if students from student order are married
     */
    static AnswerWedding checkWedding(StudentOrder so){
        System.out.println("Wedding is running");
        return new AnswerWedding();
    }

    /***
     * Check if student from student order have childrens
     */
    static AnswerChildren checkChildren(StudentOrder so){
        System.out.println("Children is running");
        return new AnswerChildren();
    }

    /***
     * Check if students from student order are real students. Method check it using
     * city student list on special website.
     */
    static AnswerStudent checkStudent(StudentOrder so){
        System.out.println("IsStudent is running");
        return new AnswerStudent();
    }


}
