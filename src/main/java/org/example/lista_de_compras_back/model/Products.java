package org.example.lista_de_compras_back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.example.lista_de_compras_back.model.Users;
import java.time.LocalDateTime;


@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer quantity;
    private String unit;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private ProductLists list;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user; // Relación con el usuario que creó el producto


    private Boolean bought = false; // Campo para marcar si el producto fue comprado

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Long getId() {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public ProductLists getList() {
        return list;
    }

    public void setList(ProductLists list) {
        this.list = list;
    }

    public Boolean getBought() {
        return bought;
    }

    public void setBought(Boolean comprado) {
        this.bought = comprado;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}


