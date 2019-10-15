package edu.javaproject.studentorder.dao;

import edu.javaproject.studentorder.domain.CountryArea;
import edu.javaproject.studentorder.domain.PassportOffice;
import edu.javaproject.studentorder.domain.RegisterOffice;
import edu.javaproject.studentorder.domain.Street;
import edu.javaproject.studentorder.exception.DaoException;

import java.util.List;

/***
 * That interface describe methods and fields which help to get access
 * to data in our dictionaries (tables: jc_student_order, jc_street, jc_register_office,
 * jc_passport_office, jc_country_struct, jc_student_child)
 */
public interface DictionaryDao {
    //Search for street in jc_street table by pattern typed in application web-form
    public List<Street> findStreets(String pattern) throws DaoException;

    /***
     * Search for necessary passport offices in jc_passport_office table by
     * OKATO code typed in web-form (areaId code)
     * @param areaId OKATO code of area where necessary ZAGS is
     * @return List of Passport Offices found by areaId pattern
     * @throws DaoException
     */
    public List<PassportOffice> findPassportOffices(String areaId) throws DaoException;

    /***
     * Search for necessary ZAGSes in jc_register_office table by
     * OKATO code typed in web-form (areaId code)
     * @param areaId OKATO code of area where needed ZAGSes is
     * @return List of Register offices (ZAGSes) found by areaId pattern
     * @throws DaoException
     */
    public List<RegisterOffice> findRegisterOffices(String areaId) throws DaoException;

    /***
     * Search for necessary country areas in jc_country_struct table by
     * OKATO code template (areaId)
     * @param areaId OKATO code template of area where needed country area is
     * @return List of CountryAreas found by areaId template
     * @throws DaoException
     */
    public List<CountryArea> findAreas(String areaId) throws DaoException;
}
