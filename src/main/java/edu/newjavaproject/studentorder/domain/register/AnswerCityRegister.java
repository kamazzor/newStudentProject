package edu.newjavaproject.studentorder.domain.register;

import java.util.ArrayList;
import java.util.List;

/***
 * Domain class contains the check results if all people from student order are registered in SPb.
 * If some persons aren't registered , class contains info why.
 * Info about city register status of each student order is in sepate cell of List<AnswerCityRegisterItem>
 */
public class AnswerCityRegister {
    private List<AnswerCityRegisterItem> items;

    public void addItem(AnswerCityRegisterItem item){
        if (items == null){
            items = new ArrayList<>(10);
        }
        items.add(item);
    }

    public List<AnswerCityRegisterItem> getItems() {
        return items;
    }
}
