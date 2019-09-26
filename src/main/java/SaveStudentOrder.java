/***
 * Main class where project start
 */
public class SaveStudentOrder {
    public static void main(String[] args) {
        StudentOrder so = new StudentOrder();
        so.hFirstName = "Алексей";
        so.hLastName = "Петров";
        so.wFirstName = "Галина";
        so.wLastName = "Петрова";
        long ans = saveStudentOrder(so);
    }

    static long saveStudentOrder(StudentOrder studentOrder){
        long answer = 199;
        System.out.println("saveStudentOrder:" + studentOrder.hFirstName + studentOrder.hLastName);
        return answer;
    }

}
