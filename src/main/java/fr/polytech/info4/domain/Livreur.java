package fr.polytech.info4.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Livreur.
 */
@Entity
@Table(name = "livreur")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Livreur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    @Column(name = "firstname", length = 30, nullable = false)
    private String firstname;

    @NotNull
    @Size(max = 30)
    @Pattern(regexp = "^[A-Z][a-z]+$")
    @Column(name = "lastname", length = 30, nullable = false)
    private String lastname;

    @NotNull
    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
    @Column(name = "mail", nullable = false)
    private String mail;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Min(value = 1)
    @Max(value = 5)
    @Column(name = "reviews", nullable = false)
    private Integer reviews;

    @OneToMany(mappedBy = "livreur")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Course> courses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public Livreur firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Livreur lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public Livreur mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public Livreur phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getReviews() {
        return reviews;
    }

    public Livreur reviews(Integer reviews) {
        this.reviews = reviews;
        return this;
    }

    public void setReviews(Integer reviews) {
        this.reviews = reviews;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Livreur courses(Set<Course> courses) {
        this.courses = courses;
        return this;
    }

    public Livreur addCourse(Course course) {
        this.courses.add(course);
        course.setLivreur(this);
        return this;
    }

    public Livreur removeCourse(Course course) {
        this.courses.remove(course);
        course.setLivreur(null);
        return this;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Livreur)) {
            return false;
        }
        return id != null && id.equals(((Livreur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Livreur{" +
            "id=" + getId() +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", mail='" + getMail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", reviews=" + getReviews() +
            "}";
    }
}
