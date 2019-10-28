package edu.javaproject.studentorder.validator.register;

import edu.javaproject.studentorder.config.Config;
import edu.javaproject.studentorder.domain.Person;
import edu.javaproject.studentorder.domain.register.CityRegisterRequest;
import edu.javaproject.studentorder.domain.register.CityRegisterResponse;
import edu.javaproject.studentorder.exception.CityRegisterException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/***
 * Check if someone person from student order are registered in Spb +
 * what type of registration: permanent or temporary.
 * Return all info above into CityRegisterValidator class
 */

public class RealCityRegisterChecker implements CityRegisterChecker {
    public CityRegisterResponse checkPerson(Person person) throws CityRegisterException {

        try {
            CityRegisterRequest request = new CityRegisterRequest(person);

            Client client = ClientBuilder.newClient();
            CityRegisterResponse response = client.target(
                    Config.getProperty(Config.CR_URL))
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(request, MediaType.APPLICATION_JSON))
                    .readEntity(CityRegisterResponse.class);
            return response;
        } catch (Exception e) {
            // TODO: 10/28/2019 catch exception normally
            throw new CityRegisterException("City Register Error", e.getMessage(), e);
        }
    }
}
