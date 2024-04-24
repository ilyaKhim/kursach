package ru.il.model.dao;


import java.sql.Date;

public class EquipmentDao {

    private int id;

    private String eqName;

    private String serialNumber;

    private Date purchaseDate;

    private Date warrantyExpiration;

    private Object eqStatus;

    private String departmentName;

    private String supplierName;

    private String userFirstName;
    private String userLastName;

    public EquipmentDao(int id, String eqName, String serialNumber, Date purchaseDate, Date warrantyExpiration, Object eqStatus, String departmentName, String supplierName, String userFirstName, String userLastName) {
        this.id = id;
        this.eqName = eqName;
        this.serialNumber = serialNumber;
        this.purchaseDate = purchaseDate;
        this.warrantyExpiration = warrantyExpiration;
        this.eqStatus = eqStatus;
        this.departmentName = departmentName;
        this.supplierName = supplierName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }
}
