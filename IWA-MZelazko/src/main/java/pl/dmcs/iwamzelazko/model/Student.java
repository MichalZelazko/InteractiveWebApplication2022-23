package pl.dmcs.iwamzelazko.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class Student {
    @Id
    @GeneratedValue
    private long id;
    private String name, surname, email, telephone;

//    @OneToOne(cascade = CascadeType.ALL)
//    private Account account;
//
//    @JsonBackReference(value = "address-student")
//    @ManyToOne(cascade = CascadeType.MERGE)
//    private Address address;
//
//    @ManyToMany(cascade = CascadeType.PERSIST)
//    private List<Team> teamList;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

//    public Account getAccount() { return account; }
//
//    public void setAccount(Account account) { this.account = account; }
//
//    public Address getAddress() { return address; }
//
//    public void setAddress(Address address) { this.address = address; }
//
//    public List<Team> getTeamList() { return teamList; }
//
//    public void setTeamList(List<Team> teamList) { this.teamList = teamList; }
}
