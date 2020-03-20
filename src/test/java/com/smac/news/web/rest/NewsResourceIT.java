package com.smac.news.web.rest;

import com.smac.news.SmacnewsApp;
import com.smac.news.domain.News;
import com.smac.news.repository.NewsRepository;
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
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.smac.news.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link NewsResource} REST controller.
 */
@SpringBootTest(classes = SmacnewsApp.class)
public class NewsResourceIT {

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
    private NewsRepository newsRepository;

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

    private MockMvc restNewsMockMvc;

    private News news;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NewsResource newsResource = new NewsResource(newsRepository);
        this.restNewsMockMvc = MockMvcBuilders.standaloneSetup(newsResource)
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
    public static News createEntity(EntityManager em) {
        News news = new News()
            .tieuDe(DEFAULT_TIEU_DE)
            .imageThumbnail(DEFAULT_IMAGE_THUMBNAIL)
            .thoiGianDang(DEFAULT_THOI_GIAN_DANG)
            .noiDungTomTat(DEFAULT_NOI_DUNG_TOM_TAT)
            .noiDungDayDu(DEFAULT_NOI_DUNG_DAY_DU)
            .tacGia(DEFAULT_TAC_GIA);
        return news;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static News createUpdatedEntity(EntityManager em) {
        News news = new News()
            .tieuDe(UPDATED_TIEU_DE)
            .imageThumbnail(UPDATED_IMAGE_THUMBNAIL)
            .thoiGianDang(UPDATED_THOI_GIAN_DANG)
            .noiDungTomTat(UPDATED_NOI_DUNG_TOM_TAT)
            .noiDungDayDu(UPDATED_NOI_DUNG_DAY_DU)
            .tacGia(UPDATED_TAC_GIA);
        return news;
    }

    @BeforeEach
    public void initTest() {
        news = createEntity(em);
    }

    @Test
    @Transactional
    public void createNews() throws Exception {
        int databaseSizeBeforeCreate = newsRepository.findAll().size();

        // Create the News
        restNewsMockMvc.perform(post("/api/news")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(news)))
            .andExpect(status().isCreated());

        // Validate the News in the database
        List<News> newsList = newsRepository.findAll();
        assertThat(newsList).hasSize(databaseSizeBeforeCreate + 1);
        News testNews = newsList.get(newsList.size() - 1);
        assertThat(testNews.getTieuDe()).isEqualTo(DEFAULT_TIEU_DE);
        assertThat(testNews.getImageThumbnail()).isEqualTo(DEFAULT_IMAGE_THUMBNAIL);
        assertThat(testNews.getThoiGianDang()).isEqualTo(DEFAULT_THOI_GIAN_DANG);
        assertThat(testNews.getNoiDungTomTat()).isEqualTo(DEFAULT_NOI_DUNG_TOM_TAT);
        assertThat(testNews.getNoiDungDayDu()).isEqualTo(DEFAULT_NOI_DUNG_DAY_DU);
        assertThat(testNews.getTacGia()).isEqualTo(DEFAULT_TAC_GIA);
    }

    @Test
    @Transactional
    public void createNewsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = newsRepository.findAll().size();

        // Create the News with an existing ID
        news.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNewsMockMvc.perform(post("/api/news")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(news)))
            .andExpect(status().isBadRequest());

        // Validate the News in the database
        List<News> newsList = newsRepository.findAll();
        assertThat(newsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllNews() throws Exception {
        // Initialize the database
        newsRepository.saveAndFlush(news);

        // Get all the newsList
        restNewsMockMvc.perform(get("/api/news?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(news.getId().intValue())))
            .andExpect(jsonPath("$.[*].tieuDe").value(hasItem(DEFAULT_TIEU_DE)))
            .andExpect(jsonPath("$.[*].imageThumbnail").value(hasItem(DEFAULT_IMAGE_THUMBNAIL)))
            .andExpect(jsonPath("$.[*].thoiGianDang").value(hasItem(DEFAULT_THOI_GIAN_DANG)))
            .andExpect(jsonPath("$.[*].noiDungTomTat").value(hasItem(DEFAULT_NOI_DUNG_TOM_TAT)))
            .andExpect(jsonPath("$.[*].noiDungDayDu").value(hasItem(DEFAULT_NOI_DUNG_DAY_DU)))
            .andExpect(jsonPath("$.[*].tacGia").value(hasItem(DEFAULT_TAC_GIA)));
    }
    
    @Test
    @Transactional
    public void getNews() throws Exception {
        // Initialize the database
        newsRepository.saveAndFlush(news);

        // Get the news
        restNewsMockMvc.perform(get("/api/news/{id}", news.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(news.getId().intValue()))
            .andExpect(jsonPath("$.tieuDe").value(DEFAULT_TIEU_DE))
            .andExpect(jsonPath("$.imageThumbnail").value(DEFAULT_IMAGE_THUMBNAIL))
            .andExpect(jsonPath("$.thoiGianDang").value(DEFAULT_THOI_GIAN_DANG))
            .andExpect(jsonPath("$.noiDungTomTat").value(DEFAULT_NOI_DUNG_TOM_TAT))
            .andExpect(jsonPath("$.noiDungDayDu").value(DEFAULT_NOI_DUNG_DAY_DU))
            .andExpect(jsonPath("$.tacGia").value(DEFAULT_TAC_GIA));
    }

    @Test
    @Transactional
    public void getNonExistingNews() throws Exception {
        // Get the news
        restNewsMockMvc.perform(get("/api/news/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNews() throws Exception {
        // Initialize the database
        newsRepository.saveAndFlush(news);

        int databaseSizeBeforeUpdate = newsRepository.findAll().size();

        // Update the news
        News updatedNews = newsRepository.findById(news.getId()).get();
        // Disconnect from session so that the updates on updatedNews are not directly saved in db
        em.detach(updatedNews);
        updatedNews
            .tieuDe(UPDATED_TIEU_DE)
            .imageThumbnail(UPDATED_IMAGE_THUMBNAIL)
            .thoiGianDang(UPDATED_THOI_GIAN_DANG)
            .noiDungTomTat(UPDATED_NOI_DUNG_TOM_TAT)
            .noiDungDayDu(UPDATED_NOI_DUNG_DAY_DU)
            .tacGia(UPDATED_TAC_GIA);

        restNewsMockMvc.perform(put("/api/news")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedNews)))
            .andExpect(status().isOk());

        // Validate the News in the database
        List<News> newsList = newsRepository.findAll();
        assertThat(newsList).hasSize(databaseSizeBeforeUpdate);
        News testNews = newsList.get(newsList.size() - 1);
        assertThat(testNews.getTieuDe()).isEqualTo(UPDATED_TIEU_DE);
        assertThat(testNews.getImageThumbnail()).isEqualTo(UPDATED_IMAGE_THUMBNAIL);
        assertThat(testNews.getThoiGianDang()).isEqualTo(UPDATED_THOI_GIAN_DANG);
        assertThat(testNews.getNoiDungTomTat()).isEqualTo(UPDATED_NOI_DUNG_TOM_TAT);
        assertThat(testNews.getNoiDungDayDu()).isEqualTo(UPDATED_NOI_DUNG_DAY_DU);
        assertThat(testNews.getTacGia()).isEqualTo(UPDATED_TAC_GIA);
    }

    @Test
    @Transactional
    public void updateNonExistingNews() throws Exception {
        int databaseSizeBeforeUpdate = newsRepository.findAll().size();

        // Create the News

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNewsMockMvc.perform(put("/api/news")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(news)))
            .andExpect(status().isBadRequest());

        // Validate the News in the database
        List<News> newsList = newsRepository.findAll();
        assertThat(newsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNews() throws Exception {
        // Initialize the database
        newsRepository.saveAndFlush(news);

        int databaseSizeBeforeDelete = newsRepository.findAll().size();

        // Delete the news
        restNewsMockMvc.perform(delete("/api/news/{id}", news.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<News> newsList = newsRepository.findAll();
        assertThat(newsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
