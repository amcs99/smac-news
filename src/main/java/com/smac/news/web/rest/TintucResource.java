package com.smac.news.web.rest;

import com.smac.news.business.TintucBusiness;
import com.smac.news.domain.Tintuc;
import com.smac.news.repository.TintucRepository;
import com.smac.news.service.dto.TintucDTO;
import com.smac.news.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * REST controller for managing {@link com.smac.news.domain.Tintuc}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class TintucResource {

	private final Logger log = LoggerFactory.getLogger(TintucResource.class);

	private static final String ENTITY_NAME = "tintuc";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	@Autowired
	private TintucBusiness tintucBusiness;

	private final TintucRepository tintucRepository;

	public TintucResource(TintucRepository tintucRepository) {
		this.tintucRepository = tintucRepository;
	}

	/**
	 * {@code POST  /tintucs} : Create a new tintuc.
	 *
	 * @param tintuc the tintuc to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new tintuc, or with status {@code 400 (Bad Request)} if the
	 *         tintuc has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/tintucs")
	public ResponseEntity<Tintuc> createTintuc(@Valid @RequestBody Tintuc tintuc) throws URISyntaxException {
		log.debug("REST request to save Tintuc : {}", tintuc);
		if (tintuc.getId() != null) {
			throw new BadRequestAlertException("A new tintuc cannot already have an ID", ENTITY_NAME, "idexists");
		}
		Tintuc result = tintucRepository.save(tintuc);
		return ResponseEntity
				.created(new URI("/api/tintucs/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	/**
	 * {@code PUT  /tintucs} : Updates an existing tintuc.
	 *
	 * @param tintuc the tintuc to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated tintuc, or with status {@code 400 (Bad Request)} if the
	 *         tintuc is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the tintuc couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/tintucs")
	public ResponseEntity<Tintuc> updateTintuc(@Valid @RequestBody Tintuc tintuc) throws URISyntaxException {
		log.debug("REST request to update Tintuc : {}", tintuc);
		if (tintuc.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		Tintuc result = tintucRepository.save(tintuc);
		return ResponseEntity.ok().headers(
				HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tintuc.getId().toString()))
				.body(result);
	}

	/**
	 * {@code GET  /tintucs} : get all the tintucs.
	 *
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of tintucs in body.
	 */
	@GetMapping("/tintucs")
	public List<Tintuc> getAllTintucs() {
		log.debug("REST request to get all Tintucs");
		return tintucRepository.findAll();
	}

	/**
	 * {@code GET  /tintucs/:id} : get the "id" tintuc.
	 *
	 * @param id the id of the tintuc to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the tintuc, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/tintucs/{id}")
	public ResponseEntity<Tintuc> getTintuc(@PathVariable Long id) {
		log.debug("REST request to get Tintuc : {}", id);
		Optional<Tintuc> tintuc = tintucRepository.findById(id);
		return ResponseUtil.wrapOrNotFound(tintuc);
	}

	/**
	 * {@code DELETE  /tintucs/:id} : delete the "id" tintuc.
	 *
	 * @param id the id of the tintuc to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/tintucs/{id}")
	public ResponseEntity<Void> deleteTintuc(@PathVariable Long id) {
		log.debug("REST request to delete Tintuc : {}", id);
		tintucRepository.deleteById(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
				.build();
	}

	@GetMapping("/tin-tuc/get")
	public List<TintucDTO> getListTintuc(@RequestParam(name = "start") Integer start,
			@RequestParam(name = "length") Integer length) {
		return tintucBusiness.getListTinTuc(start, length);
	}

	@GetMapping("/tin-tuc/getbyid")
	public TintucDTO getTinTucById(@RequestParam(name = "id") Integer id) {
		return tintucBusiness.getTinTucById(id);
	}
	
	@GetMapping("/tin-tuc/soluongtintuc")
	public Long getSoLuong() {
		return tintucBusiness.getCount();
	}
}
