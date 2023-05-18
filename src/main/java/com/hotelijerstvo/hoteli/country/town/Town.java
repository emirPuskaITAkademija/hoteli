package com.hotelijerstvo.hoteli.country.town;

import com.hotelijerstvo.hoteli.country.Country;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "town")
public class Town implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    private String name;

    @JoinColumn(name = "id_country", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Country country;

    public Town() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Town town = (Town) o;
        return Objects.equals(id, town.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
