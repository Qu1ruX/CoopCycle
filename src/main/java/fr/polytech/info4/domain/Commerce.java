package fr.polytech.info4.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Commerce.
 */
@Entity
@Table(name = "commerce")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Commerce implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    @Column(name = "reviews", nullable = false)
    private Integer reviews;

    @NotNull
    @Size(max = 100)
    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @OneToMany(mappedBy = "commerce")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Produit> produits = new HashSet<>();

    @OneToMany(mappedBy = "commerce")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Commande> commandes = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "commerce_cooperative",
               joinColumns = @JoinColumn(name = "commerce_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "cooperative_id", referencedColumnName = "id"))
    private Set<Cooperative> cooperatives = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Commerce name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReviews() {
        return reviews;
    }

    public Commerce reviews(Integer reviews) {
        this.reviews = reviews;
        return this;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }

    public String getAddress() {
        return address;
    }

    public Commerce address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public Commerce produits(Set<Produit> produits) {
        this.produits = produits;
        return this;
    }

    public Commerce addProduit(Produit produit) {
        this.produits.add(produit);
        produit.setCommerce(this);
        return this;
    }

    public Commerce removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setCommerce(null);
        return this;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    public Set<Commande> getCommandes() {
        return commandes;
    }

    public Commerce commandes(Set<Commande> commandes) {
        this.commandes = commandes;
        return this;
    }

    public Commerce addCommande(Commande commande) {
        this.commandes.add(commande);
        commande.setCommerce(this);
        return this;
    }

    public Commerce removeCommande(Commande commande) {
        this.commandes.remove(commande);
        commande.setCommerce(null);
        return this;
    }

    public void setCommandes(Set<Commande> commandes) {
        this.commandes = commandes;
    }

    public Set<Cooperative> getCooperatives() {
        return cooperatives;
    }

    public Commerce cooperatives(Set<Cooperative> cooperatives) {
        this.cooperatives = cooperatives;
        return this;
    }

    public Commerce addCooperative(Cooperative cooperative) {
        this.cooperatives.add(cooperative);
        cooperative.getCommerce().add(this);
        return this;
    }

    public Commerce removeCooperative(Cooperative cooperative) {
        this.cooperatives.remove(cooperative);
        cooperative.getCommerce().remove(this);
        return this;
    }

    public void setCooperatives(Set<Cooperative> cooperatives) {
        this.cooperatives = cooperatives;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Commerce)) {
            return false;
        }
        return id != null && id.equals(((Commerce) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Commerce{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", reviews=" + getReviews() +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
