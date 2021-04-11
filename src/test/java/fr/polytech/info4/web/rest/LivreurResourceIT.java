package fr.polytech.info4.web.rest;

import fr.polytech.info4.CoopCycleApp;
import fr.polytech.info4.domain.Livreur;
import fr.polytech.info4.repository.LivreurRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LivreurResource} REST controller.
 */
@SpringBootTest(classes = CoopCycleApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LivreurResourceIT {

    private static final String DEFAULT_FIRSTNAME = "Ik";
    private static final String UPDATED_FIRSTNAME = "Ovjzr";

    private static final String DEFAULT_LASTNAME = "Ayaid";
    private static final String UPDATED_LASTNAME = "Lw";

    private static final String DEFAULT_MAIL = "I1@oEXhQ1.gvv";
    private static final String UPDATED_MAIL = "_R@gixRxk.Fcbn";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final Integer DEFAULT_REVIEWS = 1;
    private static final Integer UPDATED_REVIEWS = 2;

    @Autowired
    private LivreurRepository livreurRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLivreurMockMvc;

    private Livreur livreur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Livreur createEntity(EntityManager em) {
        Livreur livreur = new Livreur()
            .firstname(DEFAULT_FIRSTNAME)
            .lastname(DEFAULT_LASTNAME)
            .mail(DEFAULT_MAIL)
            .phone(DEFAULT_PHONE)
            .reviews(DEFAULT_REVIEWS);
        return livreur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Livreur createUpdatedEntity(EntityManager em) {
        Livreur livreur = new Livreur()
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .mail(UPDATED_MAIL)
            .phone(UPDATED_PHONE)
            .reviews(UPDATED_REVIEWS);
        return livreur;
    }

    @BeforeEach
    public void initTest() {
        livreur = createEntity(em);
    }

    @Test
    @Transactional
    public void createLivreur() throws Exception {
        int databaseSizeBeforeCreate = livreurRepository.findAll().size();
        // Create the Livreur
        restLivreurMockMvc.perform(post("/api/livreurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(livreur)))
            .andExpect(status().isCreated());

        // Validate the Livreur in the database
        List<Livreur> livreurList = livreurRepository.findAll();
        assertThat(livreurList).hasSize(databaseSizeBeforeCreate + 1);
        Livreur testLivreur = livreurList.get(livreurList.size() - 1);
        assertThat(testLivreur.getFirstname()).isEqualTo(DEFAULT_FIRSTNAME);
        assertThat(testLivreur.getLastname()).isEqualTo(DEFAULT_LASTNAME);
        assertThat(testLivreur.getMail()).isEqualTo(DEFAULT_MAIL);
        assertThat(testLivreur.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testLivreur.getReviews()).isEqualTo(DEFAULT_REVIEWS);
    }

    @Test
    @Transactional
    public void createLivreurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = livreurRepository.findAll().size();

        // Create the Livreur with an existing ID
        livreur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLivreurMockMvc.perform(post("/api/livreurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(livreur)))
            .andExpect(status().isBadRequest());

        // Validate the Livreur in the database
        List<Livreur> livreurList = livreurRepository.findAll();
        assertThat(livreurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFirstnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = livreurRepository.findAll().size();
        // set the field null
        livreur.setFirstname(null);

        // Create the Livreur, which fails.


        restLivreurMockMvc.perform(post("/api/livreurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(livreur)))
            .andExpect(status().isBadRequest());

        List<Livreur> livreurList = livreurRepository.findAll();
        assertThat(livreurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastnameIsRequired() throws Exception {
        int databaseSizeBeforeTest = livreurRepository.findAll().size();
        // set the field null
        livreur.setLastname(null);

        // Create the Livreur, which fails.


        restLivreurMockMvc.perform(post("/api/livreurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(livreur)))
            .andExpect(status().isBadRequest());

        List<Livreur> livreurList = livreurRepository.findAll();
        assertThat(livreurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMailIsRequired() throws Exception {
        int databaseSizeBeforeTest = livreurRepository.findAll().size();
        // set the field null
        livreur.setMail(null);

        // Create the Livreur, which fails.


        restLivreurMockMvc.perform(post("/api/livreurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(livreur)))
            .andExpect(status().isBadRequest());

        List<Livreur> livreurList = livreurRepository.findAll();
        assertThat(livreurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = livreurRepository.findAll().size();
        // set the field null
        livreur.setPhone(null);

        // Create the Livreur, which fails.


        restLivreurMockMvc.perform(post("/api/livreurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(livreur)))
            .andExpect(status().isBadRequest());

        List<Livreur> livreurList = livreurRepository.findAll();
        assertThat(livreurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReviewsIsRequired() throws Exception {
        int databaseSizeBeforeTest = livreurRepository.findAll().size();
        // set the field null
        livreur.setReviews(null);

        // Create the Livreur, which fails.


        restLivreurMockMvc.perform(post("/api/livreurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(livreur)))
            .andExpect(status().isBadRequest());

        List<Livreur> livreurList = livreurRepository.findAll();
        assertThat(livreurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLivreurs() throws Exception {
        // Initialize the database
        livreurRepository.saveAndFlush(livreur);

        // Get all the livreurList
        restLivreurMockMvc.perform(get("/api/livreurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(livreur.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstname").value(hasItem(DEFAULT_FIRSTNAME)))
            .andExpect(jsonPath("$.[*].lastname").value(hasItem(DEFAULT_LASTNAME)))
            .andExpect(jsonPath("$.[*].mail").value(hasItem(DEFAULT_MAIL)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].reviews").value(hasItem(DEFAULT_REVIEWS)));
    }
    
    @Test
    @Transactional
    public void getLivreur() throws Exception {
        // Initialize the database
        livreurRepository.saveAndFlush(livreur);

        // Get the livreur
        restLivreurMockMvc.perform(get("/api/livreurs/{id}", livreur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(livreur.getId().intValue()))
            .andExpect(jsonPath("$.firstname").value(DEFAULT_FIRSTNAME))
            .andExpect(jsonPath("$.lastname").value(DEFAULT_LASTNAME))
            .andExpect(jsonPath("$.mail").value(DEFAULT_MAIL))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.reviews").value(DEFAULT_REVIEWS));
    }
    @Test
    @Transactional
    public void getNonExistingLivreur() throws Exception {
        // Get the livreur
        restLivreurMockMvc.perform(get("/api/livreurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLivreur() throws Exception {
        // Initialize the database
        livreurRepository.saveAndFlush(livreur);

        int databaseSizeBeforeUpdate = livreurRepository.findAll().size();

        // Update the livreur
        Livreur updatedLivreur = livreurRepository.findById(livreur.getId()).get();
        // Disconnect from session so that the updates on updatedLivreur are not directly saved in db
        em.detach(updatedLivreur);
        updatedLivreur
            .firstname(UPDATED_FIRSTNAME)
            .lastname(UPDATED_LASTNAME)
            .mail(UPDATED_MAIL)
            .phone(UPDATED_PHONE)
            .reviews(UPDATED_REVIEWS);

        restLivreurMockMvc.perform(put("/api/livreurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedLivreur)))
            .andExpect(status().isOk());

        // Validate the Livreur in the database
        List<Livreur> livreurList = livreurRepository.findAll();
        assertThat(livreurList).hasSize(databaseSizeBeforeUpdate);
        Livreur testLivreur = livreurList.get(livreurList.size() - 1);
        assertThat(testLivreur.getFirstname()).isEqualTo(UPDATED_FIRSTNAME);
        assertThat(testLivreur.getLastname()).isEqualTo(UPDATED_LASTNAME);
        assertThat(testLivreur.getMail()).isEqualTo(UPDATED_MAIL);
        assertThat(testLivreur.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testLivreur.getReviews()).isEqualTo(UPDATED_REVIEWS);
    }

    @Test
    @Transactional
    public void updateNonExistingLivreur() throws Exception {
        int databaseSizeBeforeUpdate = livreurRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLivreurMockMvc.perform(put("/api/livreurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(livreur)))
            .andExpect(status().isBadRequest());

        // Validate the Livreur in the database
        List<Livreur> livreurList = livreurRepository.findAll();
        assertThat(livreurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLivreur() throws Exception {
        // Initialize the database
        livreurRepository.saveAndFlush(livreur);

        int databaseSizeBeforeDelete = livreurRepository.findAll().size();

        // Delete the livreur
        restLivreurMockMvc.perform(delete("/api/livreurs/{id}", livreur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Livreur> livreurList = livreurRepository.findAll();
        assertThat(livreurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
