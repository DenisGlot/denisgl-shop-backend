package com.denisgl.dtoimpl;

import com.denisgl.dto.ICategory;
import com.denisgl.dto.IProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "product",
        indexes = {
                @Index(name = "product_id_index", columnList = "id"),
                @Index(name = "product_category_id_index", columnList = "category_id")
        }
)
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class HibernateProduct implements IProduct {

    private int id;
    private String code;
    private String name;
    private String brand;
    private String description;
    private double unitPrice;
    private int quantity;
    private int purchases;
    private int views;
    private boolean active;

    private ICategory category;

    public MultipartFile file;

    public HibernateProduct() {
        this.code = "PRD" + UUID.randomUUID().toString().substring(26).toUpperCase();
    }

    @Override
    @Id
    @SequenceGenerator(name="nextIdProduct",sequenceName="product_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="nextIdProduct")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    @NotBlank(message = "Please enter product name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    @NotBlank(message = "Please enter product brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    @NotBlank(message = "Please enter product description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    @Column(name = "unit_price")
    @Min(value = 1, message = "Price cannot be less than 1!")
    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    @ManyToOne(targetEntity=HibernateCategory.class, fetch=FetchType.EAGER)
    @JoinColumn(name="category_id")
    @JsonIgnore
    public ICategory getCategory() {
        return category;
    }

    public void setCategory(ICategory category) {
        this.category = category;
    }

    @Override
    @JsonIgnore
    public int getPurchases() {
        return purchases;
    }

    public void setPurchases(int purchases) {
        this.purchases = purchases;
    }

    @Override
    @JsonIgnore
    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    @Column(name = "is_active")
    @JsonIgnore
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    @Transient
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HibernateProduct)) return false;
        HibernateProduct that = (HibernateProduct) o;
        return getId() == that.getId() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getDescription());
    }
}
