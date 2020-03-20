package com.smac.news.web.rest;

import com.smac.news.SmacnewsApp;
import com.smac.news.domain.Tintuc;
import com.smac.news.repository.TintucRepository;
import com.smac.news.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.smac.news.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TintucResource} REST controller.
 */
@SpringBootTest(classes = SmacnewsApp.class)
public class TintucResourceIT {

    private static final String DEFAULT_TIEU_DE = "AAAAAAAAAA";
    private static final String UPDATED_TIEU_DE = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_THUMBNAIL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_THUMBNAIL = "BBBBBBBBBB";

    private static final String DEFAULT_THOI_GIAN_DANG = "AAAAAAAAAA";
    private static final String UPDATED_THOI_GIAN_DANG = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_DUNG_TOM_TAT = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG_TOM_TAT = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_DUNG_DAY_DU = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG_DAY_DU = "BBBBBBBBBB";

    private static final String DEFAULT_TAC_GIA = "AAAAAAAAAA";
    private static final String UPDATED_TAC_GIA = "BBBBBBBBBB";

    @Autowired
    private TintucRepository tintucRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTintucMockMvc;

    private Tintuc tintuc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TintucResource tintucResource = new TintucResource(tintucRepository);
        this.restTintucMockMvc = MockMvcBuilders.standaloneSetup(tintucResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tintuc createEntity(EntityManager em) {
        Tintuc tintuc = new Tintuc()
            .tieuDe(DEFAULT_TIEU_DE)
            .imageThumbnail(DEFAULT_IMAGE_THUMBNAIL)
            .thoiGianDang(DEFAULT_THOI_GIAN_DANG)
            .noiDungTomTat(DEFAULT_NOI_DUNG_TOM_TAT)
            .noiDungDayDu(DEFAULT_NOI_DUNG_DAY_DU)
            .tacGia(DEFAULT_TAC_GIA);
        return tintuc;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tintuc createUpdatedEntity(EntityManager em) {
        Tintuc tintuc = new Tintuc()
            .tieuDe(UPDATED_TIEU_DE)
            .imageThumbnail(UPDATED_IMAGE_THUMBNAIL)
            .thoiGianDang(UPDATED_THOI_GIAN_DANG)
            .noiDungTomTat(UPDATED_NOI_DUNG_TOM_TAT)
            .noiDungDayDu(UPDATED_NOI_DUNG_DAY_DU)
            .tacGia(UPDATED_TAC_GIA);
        return tintuc;
    }

    @BeforeEach
    public void initTest() {
        tintuc = createEntity(em);
    }

    @Test
    @Transactional
    public void createTintuc() throws Exception {
        int databaseSizeBeforeCreate = tintucRepository.findAll().size();

        // Create the Tintuc
        restTintucMockMvc.perform(post("/api/tintucs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tintuc)))
            .andExpect(status().isCreated());

        // Validate the Tintuc in the database
        List<Tintuc> tintucList = tintucRepository.findAll();
        assertThat(tintucList).hasSize(databaseSizeBeforeCreate + 1);
        Tintuc testTintuc = tintucList.get(tintucList.size() - 1);
        assertThat(testTintuc.getTieuDe()).isEqualTo(DEFAULT_TIEU_DE);
        assertThat(testTintuc.getImageThumbnail()).isEqualTo(DEFAULT_IMAGE_THUMBNAIL);
        assertThat(testTintuc.getThoiGianDang()).isEqualTo(DEFAULT_THOI_GIAN_DANG);
        assertThat(testTintuc.getNoiDungTomTat()).isEqualTo(DEFAULT_NOI_DUNG_TOM_TAT);
        assertThat(testTintuc.getNoiDungDayDu()).isEqualTo(DEFAULT_NOI_DUNG_DAY_DU);
        assertThat(testTintuc.getTacGia()).isEqualTo(DEFAULT_TAC_GIA);
    }

    @Test
    @Transactional
    public void createTintucWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tintucRepository.findAll().size();

        // Create the Tintuc with an existing ID
        tintuc.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTintucMockMvc.perform(post("/api/tintucs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tintuc)))
            .andExpect(status().isBadRequest());

        // Validate the Tintuc in the database
        List<Tintuc> tintucList = tintucRepository.findAll();
        assertThat(tintucList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTintucs() throws Exception {
        // Initialize the database
        tintucRepository.saveAndFlush(tintuc);

        // Get all the tintucList
        restTintucMockMvc.perform(get("/api/tintucs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tintuc.getId().intValue())))
            .andExpect(jsonPath("$.[*].tieuDe").value(hasItem(DEFAULT_TIEU_DE)))
            .andExpect(jsonPath("$.[*].imageThumbnail").value(hasItem(DEFAULT_IMAGE_THUMBNAIL)))
            .andExpect(jsonPath("$.[*].thoiGianDang").value(hasItem(DEFAULT_THOI_GIAN_DANG)))
            .andExpect(jsonPath("$.[*].noiDungTomTat").value(hasItem(DEFAULT_NOI_DUNG_TOM_TAT)))
            .andExpect(jsonPath("$.[*].noiDungDayDu").value(hasItem(DEFAULT_NOI_DUNG_DAY_DU.toString())))
            .andExpect(jsonPath("$.[*].tacGia").value(hasItem(DEFAULT_TAC_GIA)));
    }
    
    @Test
    @Transactional
    public void getTintuc() throws Exception {
        // Initialize the database
        tintucRepository.saveAndFlush(tintuc);

        // Get the tintuc
        restTintucMockMvc.perform(get("/api/tintucs/{id}", tintuc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tintuc.getId().intValue()))
            .andExpect(jsonPath("$.tieuDe").value(DEFAULT_TIEU_DE))
            .andExpect(jsonPath("$.imageThumbnail").value(DEFAULT_IMAGE_THUMBNAIL))
            .andExpect(jsonPath("$.thoiGianDang").value(DEFAULT_THOI_GIAN_DANG))
            .andExpect(jsonPath("$.noiDungTomTat").value(DEFAULT_NOI_DUNG_TOM_TAT))
            .andExpect(jsonPath("$.noiDungDayDu").value(DEFAULT_NOI_DUNG_DAY_DU.toString()))
            .andExpect(jsonPath("$.tacGia").value(DEFAULT_TAC_GIA));
    }

    @Test
    @Transactional
    public void getNonExistingTintuc() throws Exception {
        // Get the tintuc
        restTintucMockMvc.perform(get("/api/tintucs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTintuc() throws Exception {
        // Initialize the database
        tintucRepository.saveAndFlush(tintuc);

        int databaseSizeBeforeUpdate = tintucRepository.findAll().size();

        // Update the tintuc
        Tintuc updatedTintuc = tintucRepository.findById(tintuc.getId()).get();
        // Disconnect from session so that the updates on updatedTintuc are not directly saved in db
        em.detach(updatedTintuc);
        updatedTintuc
            .tieuDe(UPDATED_TIEU_DE)
            .imageThumbnail(UPDATED_IMAGE_THUMBNAIL)
            .thoiGianDang(UPDATED_THOI_GIAN_DANG)
            .noiDungTomTat(UPDATED_NOI_DUNG_TOM_TAT)
            .noiDungDayDu(UPDATED_NOI_DUNG_DAY_DU)
            .tacGia(UPDATED_TAC_GIA);

        restTintucMockMvc.perform(put("/api/tintucs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTintuc)))
            .andExpect(status().isOk());

        // Validate the Tintuc in the database
        List<Tintuc> tintucList = tintucRepository.findAll();
        assertThat(tintucList).hasSize(databaseSizeBeforeUpdate);
        Tintuc testTintuc = tintucList.get(tintucList.size() - 1);
        assertThat(testTintuc.getTieuDe()).isEqualTo(UPDATED_TIEU_DE);
        assertThat(testTintuc.getImageThumbnail()).isEqualTo(UPDATED_IMAGE_THUMBNAIL);
        assertThat(testTintuc.getThoiGianDang()).isEqualTo(UPDATED_THOI_GIAN_DANG);
        assertThat(testTintuc.getNoiDungTomTat()).isEqualTo(UPDATED_NOI_DUNG_TOM_TAT);
        assertThat(testTintuc.getNoiDungDayDu()).isEqualTo(UPDATED_NOI_DUNG_DAY_DU);
        assertThat(testTintuc.getTacGia()).isEqualTo(UPDATED_TAC_GIA);
    }

    @Test
    @Transactional
    public void updateNonExistingTintuc() throws Exception {
        int databaseSizeBeforeUpdate = tintucRepository.findAll().size();

        // Create the Tintuc

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTintucMockMvc.perform(put("/api/tintucs")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tintuc)))
            .andExpect(status().isBadRequest());

        // Validate the Tintuc in the database
        List<Tintuc> tintucList = tintucRepository.findAll();
        assertThat(tintucList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTintuc() throws Exception {
        // Initialize the database
        tintucRepository.saveAndFlush(tintuc);

        int databaseSizeBeforeDelete = tintucRepository.findAll().size();

        // Delete the tintuc
        restTintucMockMvc.perform(delete("/api/tintucs/{id}", tintuc.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tintuc> tintucList = tintucRepository.findAll();
        assertThat(tintucList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
