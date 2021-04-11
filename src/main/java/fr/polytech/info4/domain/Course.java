package fr.polytech.info4.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Course.
 */
@Entity
@Table(name = "course")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "price", nullable = false)
    private Float price;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "distance", nullable = false)
    private Float distance;

    @OneToMany(mappedBy = "course")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Commande> commandes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "courses", allowSetters = true)
    private Livreur livreur;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public Course price(Float price) {
        this.price = price;
        return this;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getDistance() {
        return distance;
    }

    public Course distance(Float distance) {
        this.distance = distance;
        return this;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Set<Commande> getCommandes() {
        return commandes;
    }

    public Course commandes(Set<Commande> commandes) {
        this.commandes = commandes;
        return this;
    }

    public Course addCommande(Commande commande) {
        this.commandes.add(commande);
        commande.setCourse(this);
        return this;
    }

    public Course removeCommande(Commande commande) {
        this.commandes.remove(commande);
        commande.setCourse(null);
        return this;
    }

    public void setCommandes(Set<Commande> commandes) {
        this.commandes = commandes;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public Course livreur(Livreur livreur) {
        this.livreur = livreur;
        return this;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }
        return id != null && id.equals(((Course) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", distance=" + getDistance() +
            "}";
    }
}
