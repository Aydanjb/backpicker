package com.aydanjb.backpicker.gearlist;

import com.aydanjb.backpicker.gearitem.GearItem;
import com.aydanjb.backpicker.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gear_lists")
public class GearList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "gearList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GearItem> items = new ArrayList<>();

    public GearList() {
    }

    public GearList(Long id, String name, String description, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
        this.createdAt = Instant.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<GearItem> getItems() {
        return items;
    }

    public void setItems(List<GearItem> items) {
        this.items = items;
    }

    public void addItem(GearItem item) {
        items.add(item);
        item.setGearList(this);
    }
}
