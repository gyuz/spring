package crud.core.model;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import org.joda.time.LocalDate;

public class PersonDto{
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String title;
    private String street;
    private String city;
    private String brgy;
    private int zip;
    private String birthDate;
    private double gwa;
    private String dateHired;
    private char employed;
    private List<String> personRoleIds = new ArrayList<String>();
    private List<String> personContactIds = new ArrayList<String>();
    private List<String> personRoleNames = new ArrayList<String>();
    private List<String> personContactTypes = new ArrayList<String>();
    private List<String> personContactDetails = new ArrayList<String>();
    private List<Integer> personIdList = new ArrayList<Integer>();
    private List<String> firstNameList = new ArrayList<String>();
    private List<String> lastNameList = new ArrayList<String>();
    private List<String> middleNameList = new ArrayList<String>();
    private List<String> titleList = new ArrayList<String>(); 
    private List<LocalDate> birthDateList = new ArrayList<LocalDate>();  
    private List<LocalDate> dateHiredList = new ArrayList<LocalDate>();  
    private List<String> streetList = new ArrayList<String>();
    private List<String> cityList = new ArrayList<String>();
    private List<String> brgyList = new ArrayList<String>();
    private List<Integer> zipList = new ArrayList<Integer>(); 
    private List<Character> employedList = new ArrayList<Character>();
    private List<Double> gwaList = new ArrayList<Double>();    
    private Map<Integer, LocalDate> dateHiredMap = new HashMap<Integer, LocalDate>();
    
    public int getId(){
        return id;        
    }

    public String getBirthDate() {
        return birthDate;    
    }

    public double getGwa() {
        return gwa;    
    }
    
    public String getDateHired() {
        return dateHired;    
    }
    
    public char getEmployed() {
        return employed;    
    }

    public String getTitle() {
        return title;    
    }   

    public String getFirstName(){ 
        return firstName;    
    } 

    public String getLastName() {
        return lastName;    
    }
    
    public String getMiddleName(){
        return middleName;
    }
    
    public String getStreet(){
        return street;
    }
    
    public String getCity(){
        return city;
    }
    
    public String getBrgy(){
        return brgy;
    }
    
    public int getZip(){
        return zip;
    }
    
    public List<String> getPersonRoleIds(){
        return personRoleIds;
    }
    
    public List<String> getPersonContactIds(){
        return personContactIds;
    }
    
    public List<String> getPersonRoleNames(){
       return personRoleNames;
    }
    
    public List<String> getPersonContactTypes(){
       return personContactTypes;
    }
    
     public List<String> getPersonContactDetails(){
       return personContactDetails;
    }
    
    public List<Integer> getPersonIdList(){
          return personIdList;    
    }

    public List<String> getFirstNameList(){
       return firstNameList;
    }

    public List<String> getMiddleNameList(){
       return middleNameList;
    }
    
    public List<String> getLastNameList(){
       return lastNameList; 
    }

    public List<String> getTitleList(){
       return titleList; 
    }

    public List<LocalDate> getBirthDateList(){
       return birthDateList;  
    }

    public Map<Integer, LocalDate> getDateHiredMap(){
       return dateHiredMap;  
    }
    
    public List<String> getStreetList(){
       return streetList;
    }
    
    public List<String> getCityList(){
       return cityList;
    }

    public List<String> getBrgyList(){
       return brgyList;
    }

    public List<Integer> getZipList(){
       return zipList; 
    }
    
    public List<Character> getEmployedList(){
       return employedList;
    }

    public List<Double> getGwaList(){
        return gwaList;
    }
    
    public void setId(int id) {
        this.id = id;
    }    

    public void setFirstName(String firstName) {  
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    } 
    
    public void setMiddleName(String middleName) {  
        this.middleName =  middleName;
    }    

    public void setStreet(String street) {
        this.street = street;   
    }
    
    public void setBrgy(String brgy) {
        this.brgy = brgy;   
    }
    
    public void setCity(String city) {
        this.city = city;   
    }
    
    public void setZip(int zip) {
        this.zip = zip;   
    }
    
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    
    public void setGwa(double gwa) {
        this.gwa = gwa;    
    } 

    public void setDateHired(String dateHired) {
        this.dateHired = dateHired;
    }  

    public void setEmployed(char employed) {
        this.employed = employed;    
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setPersonIdList(List personIdList){
        this.personIdList = personIdList;    
    }

    public void setFirstNameList(List firstNameList){
        this.firstNameList = firstNameList;
    }

    public void setMiddleNameList(List middleNameList){
        this.middleNameList = middleNameList;
    }
    
    public void setLastNameList(List lastNameList){
        this.lastNameList = lastNameList; 
    }

    public void setTitleList(List titleList){
        this.titleList = titleList; 
    }

    public void setBirthDateList(List birthDateList){
        this.birthDateList = birthDateList;  
    }

    public void setDateHiredMap(Map dateHiredMap){
        this.dateHiredMap = dateHiredMap;  
    }
    
    public void setStreetList(List streetList){
        this.streetList = streetList;
    }
    
    public void setCityList(List cityList){
        this.cityList = cityList;
    }

    public void setBrgyList(List brgyList){
        this.brgyList = brgyList;
    }

    public void setZipList(List zipList){
        this.zipList = zipList; 
    }
    
    public void setEmployedList(List employedList){
        this.employedList = employedList;
    }

    public void setGwaList(List gwaList){
        this.gwaList = gwaList;
    }

    public void setPersonRoleIds(List personRoleIds){
        this.personRoleIds = personRoleIds;
    }
    
    public void setPersonContactIds(List personContactIds){
        this.personContactIds = personContactIds;
    }
    
    public void setPersonRoleNames(List personRoleNames){
        this.personRoleNames = personRoleNames;
    }
    
    public void setPersonContactTypes(List personContactTypes){
        this.personContactTypes = personContactTypes;
    }
    
    public void setPersonContactDetails(List personContactDetails){
        this.personContactDetails = personContactDetails;
    }
}
