package edu.javaproject.studentorder.domain;

public enum StudentOrderStatus {
    START, CHECKED;

    //Метод для получения буквенного статуса по его цифровому значению
    //0 = START, 1 = CHECKED и т.п.
    public static StudentOrderStatus fromValue (int value) {
        for (StudentOrderStatus sos : StudentOrderStatus.values()) {
            if (sos.ordinal() == value) {
                return sos;
            }
        }
        //Бросаем RuntimeException, если у сохраненного заявления неподходящий статус
        // к перечисляемому типу  StudentOrderStatus
        throw new RuntimeException("Unknown value: " + value);
    }
}
