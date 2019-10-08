package edu.newjavaproject.studentorder.domain;
/***
 * Domain class describe address of {@link Person} from student order
 */
public class Address {
    private String postcode;            //индекс
    private Street street;              //улица
    private String building;            //дом
    private String extension;           //корпус
    private String apartment;           //квартира

    public Address(String postcode, Street street, String building, String extension, String apartment) {
        this.postcode = postcode;
        this.street = street;
        this.building = building;
        this.extension = extension;
        this.apartment = apartment;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
    

}
