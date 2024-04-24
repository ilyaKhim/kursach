package ru.il.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "suppliers", schema = "uchet", catalog = "")
public class Supplier {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "company_name")
    private String companyName;
    @Basic
    @Column(name = "contact_info")
    private String contactInfo;
    @Basic
    @Column(name = "address")
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier that = (Supplier) o;
        return id == that.id && Objects.equals(companyName, that.companyName) && Objects.equals(contactInfo, that.contactInfo) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, contactInfo, address);
    }
}
