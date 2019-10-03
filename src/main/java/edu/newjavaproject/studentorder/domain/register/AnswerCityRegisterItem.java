package edu.newjavaproject.studentorder.domain.register;

import edu.newjavaproject.studentorder.domain.Person;

/***
 * Domain class contains the check results if one people from student order are registered in SPb.
 * If person aren't registered , class contains info why.
 * All info above is associated by persons.
 */
public class AnswerCityRegisterItem {

    //Статус проверки регистрации в ГРН
    public enum CityStatus {
        YES, NO, ERROR;
    }

     //Описание ошибки при проверки регистрации в ГРН, если эта ошибка есть.
     //Содержит код и текст ошибки.
    public static class CityError {
        private String code;
        private String text;

        public CityError(String code, String text) {
            this.code = code;
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public String getCode() {
            return code;
        }
    }

    private CityStatus status;
    private Person person;
    private CityError error;

    public AnswerCityRegisterItem(CityStatus status, Person person) {
        this.status = status;
        this.person = person;
    }

    public AnswerCityRegisterItem(CityStatus status, Person person, CityError error) {
        this.status = status;
        this.person = person;
        this.error = error;
    }

    public CityStatus getStatus() {
        return status;
    }

    public Person getPerson() {
        return person;
    }

    public CityError getError() {
        return error;
    }
}
