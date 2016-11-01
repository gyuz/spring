package crud.core.model;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.joda.time.LocalDate;

@Entity
@Table(name = "PERSON")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE, region="Person")
public class Person implements Comparable<Person>{
    @Id 
    @SequenceGenerator(
        name="PERSON_ID_SEQ",
        sequenceName="PERSON_ID_SEQ",
        allocationSize=1
    )
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERSON_ID_SEQ")
    @Column(name = "ID") 
    private int id;
    
    @Embedded
    private Name name;
    
    @Embedded
    private Address address;
    
    @Column(name = "BIRTH_DATE")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate birthDate;
    
    @Column(name = "GWA")
    private double gwa;
    
    @Column(name = "DATE_HIRED")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate dateHired;
    
    @Column(name = "EMPLOYED")
    private boolean employed;
    
    @OneToMany(mappedBy = "person", 
               cascade = {CascadeType.ALL}, 
               fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private Set<Contact> contacts;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},
                fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "PERSON_ROLES",
               joinColumns = @JoinColumn(name = "PERSON_ID"), 
               inverseJoinColumns = @JoinColumn(name = "ROLE_ROLE_ID"))
    private Set<Role> roles;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TITLE")
    private Title title;
    
    public Person(){
        name = new Name();
        address = new Address();
        contacts = new HashSet<Contact>();
        roles = new HashSet<Role>();
    }
    
    public int getId(){
        return id;        
    }

    public LocalDate getBirthDate() {
        return birthDate;    
    }

    public double getGwa() {
        return gwa;    
    }
    
    public LocalDate getDateHired() {
        return dateHired;    
    }
    
    public boolean getEmployed() {
        return employed;    
    }

    public Title getTitle() {
        return title;    
    }   

    public Name getName(){ 
        return name;    
    } 

    public Address getAddress(){
        return address;    
    }
    
    public Set<Contact> getContacts(){
        return contacts;    
    }
    
    public Set<Role> getRoles(){
        return roles;
    }
    
    public void setId(int id){
        this.id = id;
    }    

    public void setName(Name name) {  
        this.name = name;
    }   

    public void setAddress(Address address) {
        this.address = address;   
    }
    
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    
    public void setGwa(double gwa) {
        this.gwa = gwa;    
    } 

    public void setDateHired(LocalDate dateHired) {
        this.dateHired = dateHired;
    }  

    public void setEmployed(boolean employed) {
        this.employed = employed;    
    }
    
    public void setTitle(Title title) {
        this.title = title;
    }
    
    public void setContacts(Set contacts){
        this.contacts = contacts;    
    }

    public void setRoles(Set roles){
        this.roles = roles;    
    }
    
    public int compareTo(Person p){
       return Double.compare(this.getGwa(),p.getGwa());
   }
}
