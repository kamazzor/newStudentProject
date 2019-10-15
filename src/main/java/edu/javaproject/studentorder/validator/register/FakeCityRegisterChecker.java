package edu.javaproject.studentorder.validator.register;

import edu.javaproject.studentorder.domain.Adult;
import edu.javaproject.studentorder.domain.Child;
import edu.javaproject.studentorder.domain.Person;
import edu.javaproject.studentorder.domain.register.CityRegisterResponse;
import edu.javaproject.studentorder.exception.CityRegisterException;
import edu.javaproject.studentorder.exception.TransportException;

/***
 * Not real checker (stub) if someone people from student order are registered in SPb.
 */
public class FakeCityRegisterChecker implements CityRegisterChecker {

    private static final String GOOD_1 = "1000";            //хорошая серия паспорта у мужа
    private static final String GOOD_2 = "2000";            //хорошая серия паспорта у жены
    private static final String BAD_1 = "1001";             //плохая серия паспорта у мужа
    private static final String BAD_2 = "2001";             //плохая серия паспорта у жены
    private static final String ERROR_1 = "1002";           //другая ошибка от ГРН у мужа
    private static final String ERROR_2 = "2002";           //другая ошибка от ГРН у жены
    private static final String ERROR_T_1 = "1003";         //транспортная ошибка доступа к ГРН и пр. у мужа
    private static final String ERROR_T_2 = "2003";         //транспортная ошибка доступа к ГРН и пр. у жены

    /**
     * Check one person registration in GRN
     * @param person
     * @return Return CityRegisterResponse answer
     * @throws CityRegisterException
     */
    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException, TransportException {
        //Проверяем, какого класса объект person: Adult или Child
        CityRegisterResponse res = new CityRegisterResponse();
        if (person instanceof Adult){
//            System.out.println("ADULT");
            Adult t = (Adult) person;
            String ps = t.getPassportSeria();
            if ((ps.equals(GOOD_1)) || ps.equals(GOOD_2)){
                res.setExisting(true);
                res.setTemporal(false);
            }
            if ((ps.equals(BAD_1)) || ps.equals(BAD_2)){
                res.setExisting(false);
            }
            if ((ps.equals(ERROR_1)) || ps.equals(ERROR_2)){
                CityRegisterException ex = new CityRegisterException("1", "GRN ERROR " + ps);
                throw ex;
            }
            if ((ps.equals(ERROR_T_1)) || ps.equals(ERROR_T_2)){
                TransportException ex = new TransportException("Transport ERROR " + ps);
                throw ex;
            }
        }
        if (person instanceof Child){
            res.setExisting(true);
            res.setTemporal(true);
        }

        System.out.println(res);

        return res;
    }
}
