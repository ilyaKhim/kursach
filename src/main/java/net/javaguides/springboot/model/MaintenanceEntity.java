package net.javaguides.springboot.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "maintenance", schema = "uchet", catalog = "")
public class MaintenanceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "equipment_id")
    private int equipmentId;
    @Basic
    @Column(name = "create_date")
    private Date createDate;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "issued_by")
    private int issuedBy;
    @Basic
    @Column(name = "performed_by")
    private Integer performedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(int issuedBy) {
        this.issuedBy = issuedBy;
    }

    public Integer getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(Integer performedBy) {
        this.performedBy = performedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaintenanceEntity that = (MaintenanceEntity) o;
        return id == that.id && equipmentId == that.equipmentId && issuedBy == that.issuedBy && Objects.equals(createDate, that.createDate) && Objects.equals(description, that.description) && Objects.equals(performedBy, that.performedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, equipmentId, createDate, description, issuedBy, performedBy);
    }
}
