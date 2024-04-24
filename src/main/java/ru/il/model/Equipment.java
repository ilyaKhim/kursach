package ru.il.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "equipments", schema = "uchet", catalog = "")
public class Equipment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "eq_name")
    private String eqName;
    @Basic
    @Column(name = "serial_number")
    private String serialNumber;
    @Basic
    @Column(name = "purchase_date")
    private Date purchaseDate;
    @Basic
    @Column(name = "warranty_expiration")
    private Date warrantyExpiration;
    @Basic
    @Column(name = "eq_status")
    private Object eqStatus;
    @Basic
    @Column(name = "department_id")
    private int departmentId;
    @Basic
    @Column(name = "supplier_id")
    private int supplierId;
    @Basic
    @Column(name = "user_id")
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEqName() {
        return eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getWarrantyExpiration() {
        return warrantyExpiration;
    }

    public void setWarrantyExpiration(Date warrantyExpiration) {
        this.warrantyExpiration = warrantyExpiration;
    }

    public Object getEqStatus() {
        return eqStatus;
    }

    public void setEqStatus(Object eqStatus) {
        this.eqStatus = eqStatus;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment that = (Equipment) o;
        return id == that.id && departmentId == that.departmentId && supplierId == that.supplierId && userId == that.userId && Objects.equals(eqName, that.eqName) && Objects.equals(serialNumber, that.serialNumber) && Objects.equals(purchaseDate, that.purchaseDate) && Objects.equals(warrantyExpiration, that.warrantyExpiration) && Objects.equals(eqStatus, that.eqStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eqName, serialNumber, purchaseDate, warrantyExpiration, eqStatus, departmentId, supplierId, userId);
    }
}
