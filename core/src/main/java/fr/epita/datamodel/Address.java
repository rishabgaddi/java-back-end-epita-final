package fr.epita.datamodel;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESSES")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String country;
    @Column
    private String area;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private String number;
    @ManyToOne
    @JoinColumn(name = "contact_id", unique = true)
    private Contact contact;

    public Address(Long id, String country, String area, String city, String street, String number, Contact contact) {
        this.id = id;
        this.country = country;
        this.area = area;
        this.city = city;
        this.street = street;
        this.number = number;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", contact=" + contact +
                '}';
    }
}
