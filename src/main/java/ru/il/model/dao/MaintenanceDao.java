package ru.il.model.dao;


import java.util.Date;

public class MaintenanceDao {

    private int id;

    private String equipmentSerialNumber;

    private Date createDate;

    private String description;

    private String issuedByFirstNameLastName;

    private String performedByFirstNameLastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEquipmentSerialNumber() {
        return equipmentSerialNumber;
    }

    public void setEquipmentSerialNumber(String equipmentSerialNumber) {
        this.equipmentSerialNumber = equipmentSerialNumber;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIssuedByFirstNameLastName() {
        return issuedByFirstNameLastName;
    }

    public void setIssuedByFirstNameLastName(String issuedByFirstNameLastName) {
        this.issuedByFirstNameLastName = issuedByFirstNameLastName;
    }

    public String getPerformedByFirstNameLastName() {
        return performedByFirstNameLastName;
    }

    public void setPerformedByFirstNameLastName(String performedByFirstNameLastName) {
        this.performedByFirstNameLastName = performedByFirstNameLastName;
    }

}
