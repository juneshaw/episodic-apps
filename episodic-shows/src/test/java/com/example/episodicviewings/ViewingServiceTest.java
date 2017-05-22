package com.example.episodicviewings;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ViewingServiceTest {

    @Autowired
    ViewingRepository repository;

    @Autowired
    ViewingService viewingService;

    private Date updatedAt1 = Date.from(Instant.now());
    private Date updatedAt2 = Date.from(Instant.MAX);
    private Viewing viewing1;
    private Viewing viewing2;

    @Before
    private void setup() {
        viewing1 = new Viewing(
                1L,
                2L,
                3L,
                4L,
                updatedAt1,
                5);
        viewing2 = new
                Viewing(
                10L,
                12L,
                13L,
                14L,
                updatedAt2,
                15);
    }
//    @Test
//    @Transactional
//    @Rollback
//    public void testUpdate() throws Exception {
//        repository.save(viewing1);
//
//        Viewing updatedViewing = viewingService.updateByUserAndShow(viewing2);
//
//        Viewing foundViewing = repository.findOne(viewing1.getId());
//        assertThat(updatedViewing, equalTo(viewing2));
//    }
}
