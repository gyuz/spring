package crud.core.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address{
    @Column(name = "STREET")
    private String street;
    
    @Column(name = "BRGY")
    private String brgy;
        
    @Column(name = "CITY")
    private String city;
    
    @Column(name = "ZIP")
    private int zip;

    public Address() {    
    }   

    public Address(String street, String brgy, String city, int zip) {
        this.street = street;
        this.brgy = brgy;
        this.city = city;
        this.zip = zip;    
    }
    
    public String getStreet() {
        return street;    
    } 

    public String getBrgy() {
        return brgy;    
    }

     public String getCity() {
        return city;    
    }

     public int getZip() {
        return zip;    
    }

    public void setStreet(String street) {
        this.street = street;    
    }

    public void setCity(String city) {
        this.city = city;    
    }

    public void setZip(int zip) {
        this.zip = zip;    
    }

    public void setBrgy(String brgy) {
        this.brgy = brgy;    
    }
}
