package com.smac.news.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.smac.news.web.rest.TestUtil;

public class TintucTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tintuc.class);
        Tintuc tintuc1 = new Tintuc();
        tintuc1.setId(1L);
        Tintuc tintuc2 = new Tintuc();
        tintuc2.setId(tintuc1.getId());
        assertThat(tintuc1).isEqualTo(tintuc2);
        tintuc2.setId(2L);
        assertThat(tintuc1).isNotEqualTo(tintuc2);
        tintuc1.setId(null);
        assertThat(tintuc1).isNotEqualTo(tintuc2);
    }
}
