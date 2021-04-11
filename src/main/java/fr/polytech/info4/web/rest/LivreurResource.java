package fr.polytech.info4.web.rest;

import fr.polytech.info4.domain.Livreur;
import fr.polytech.info4.repository.LivreurRepository;
import fr.polytech.info4.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.polytech.info4.domain.Livreur}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LivreurResource {

    private final Logger log = LoggerFactory.getLogger(LivreurResource.class);

    private static final String ENTITY_NAME = "livreur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LivreurRepository livreurRepository;

    public LivreurResource(LivreurRepository livreurRepository) {
        this.livreurRepository = livreurRepository;
    }

    /**
     * {@code POST  /livreurs} : Create a new livreur.
     *
     * @param livreur the livreur to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new livreur, or with status {@code 400 (Bad Request)} if the livreur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/livreurs")
    public ResponseEntity<Livreur> createLivreur(@Valid @RequestBody Livreur livreur) throws URISyntaxException {
        log.debug("REST request to save Livreur : {}", livreur);
        if (livreur.getId() != null) {
            throw new BadRequestAlertException("A new livreur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Livreur result = livreurRepository.save(livreur);
        return ResponseEntity.created(new URI("/api/livreurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /livreurs} : Updates an existing livreur.
     *
     * @param livreur the livreur to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated livreur,
     * or with status {@code 400 (Bad Request)} if the livreur is not valid,
     * or with status {@code 500 (Internal Server Error)} if the livreur couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/livreurs")
    public ResponseEntity<Livreur> updateLivreur(@Valid @RequestBody Livreur livreur) throws URISyntaxException {
        log.debug("REST request to update Livreur : {}", livreur);
        if (livreur.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Livreur result = livreurRepository.save(livreur);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, livreur.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /livreurs} : get all the livreurs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of livreurs in body.
     */
    @GetMapping("/livreurs")
    public List<Livreur> getAllLivreurs() {
        log.debug("REST request to get all Livreurs");
        return livreurRepository.findAll();
    }

    /**
     * {@code GET  /livreurs/:id} : get the "id" livreur.
     *
     * @param id the id of the livreur to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the livreur, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/livreurs/{id}")
    public ResponseEntity<Livreur> getLivreur(@PathVariable Long id) {
        log.debug("REST request to get Livreur : {}", id);
        Optional<Livreur> livreur = livreurRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(livreur);
    }

    /**
     * {@code DELETE  /livreurs/:id} : delete the "id" livreur.
     *
     * @param id the id of the livreur to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/livreurs/{id}")
    public ResponseEntity<Void> deleteLivreur(@PathVariable Long id) {
        log.debug("REST request to delete Livreur : {}", id);
        livreurRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
