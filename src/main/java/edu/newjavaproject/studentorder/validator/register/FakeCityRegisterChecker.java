package edu.newjavaproject.studentorder.validator.register;

import edu.newjavaproject.studentorder.domain.Adult;
import edu.newjavaproject.studentorder.domain.Child;
import edu.newjavaproject.studentorder.domain.register.CityRegisterCheckerResponse;
import edu.newjavaproject.studentorder.domain.Person;
import edu.newjavaproject.studentorder.exception.CityRegisterException;

/***
 * Not real checker (stub) if someone people from student order are registered in SPb.
 */
public class FakeCityRegisterChecker implements CityRegisterChecker {

    private static final String GOOD_1 = "1000";            //хорошая серия паспорта у мужа
    private static final String GOOD_2 = "2000";            //хорошая серия паспорта у жены
    private static final String BAD_1 = "1001";             //плохая серия паспорта у мужа
    private static final String BAD_2 = "2001";             //плохая серия паспорта у жены
    private static final String ERROR_1 = "1002";           //ошибка доступа к ГРН и пр. у мужа
    private static final String ERROR_2 = "2002";           //ошибка доступа к ГРН и пр. у жены

    /**
     * Check one person registration in GRN
     * @param person
     * @return Return CityRegisterCheckerResponse answer
     * @throws CityRegisterException
     */
    public CityRegisterCheckerResponse checkPerson(Person person) throws CityRegisterException {
        //Проверяем, какого класса объект person: Adult или Child
        CityRegisterCheckerResponse res = new CityRegisterCheckerResponse();
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
                CityRegisterException ex = new CityRegisterException("Fake ERROR " + ps);
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
