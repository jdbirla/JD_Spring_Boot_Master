package com.jd.bookmarker.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jd.bookmarker.domain.Bookmark;
import com.jd.bookmarker.repository.BookmarkRepository;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
/**
 * Created by jd birla on 08-05-2023 at 08:36
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:tc:postgresql:14-alpine:///demo"
})
class BookmarkControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  BookmarkRepository bookmarkRepository;

  private List<Bookmark> bookmarkList;

  @BeforeEach
  void setUp() {
    bookmarkList = new ArrayList<>();
    bookmarkRepository.deleteAllInBatch();
    bookmarkList.add(new Bookmark(null, "SivaLabs", "https://sivalabs.in", Instant.now()));
    bookmarkList.add(new Bookmark(null, "SpringBlog", "https://spring.io/blog", Instant.now()));
    bookmarkList.add(new Bookmark(null, "Quarkus", "https://quarkus.io/", Instant.now()));
    bookmarkList.add(new Bookmark(null, "Micronaut", "https://micronaut.io/", Instant.now()));
    bookmarkList.add(new Bookmark(null, "JOOQ", "https://www.jooq.org/", Instant.now()));
    bookmarkList.add(new Bookmark(null, "VladMihalcea", "https://vladmihalcea.com", Instant.now()));
    bookmarkList.add(
        new Bookmark(null, "Thoughts On Java", "https://thorben-janssen.com/", Instant.now()));
    bookmarkList.add(new Bookmark(null, "DZone", "https://dzone.com/", Instant.now()));
    bookmarkList.add(
        new Bookmark(null, "DevOpsBookmarks", "http://www.devopsbookmarks.com/", Instant.now()));
    bookmarkList.add(
        new Bookmark(null, "Kubernetes docs", "https://kubernetes.io/docs/home/", Instant.now()));
    bookmarkList.add(new Bookmark(null, "Expressjs", "https://expressjs.com/", Instant.now()));
    bookmarkList.add(
        new Bookmark(null, "Marcobehler", "https://www.marcobehler.com", Instant.now()));
    bookmarkList.add(new Bookmark(null, "baeldung", "https://www.baeldung.com", Instant.now()));
    bookmarkList.add(new Bookmark(null, "devglan", "https://www.devglan.com", Instant.now()));
    bookmarkList.add(new Bookmark(null, "linuxize", "https://linuxize.com", Instant.now()));

    bookmarkRepository.saveAll(bookmarkList);
  }


  @ParameterizedTest
  @CsvSource({
      "1,15,2,1,true,false,true,false",
      "2,15,2,2,false,true,false,true"
  })
  void shouldGetBookmarks(int pageNo, int totalElements, int totalPages, int currentPage,
      boolean isFirst, boolean isLast,
      boolean hasNext, boolean hasPrevious) throws Exception {
    mockMvc.perform(get("http://localhost:8080/api/v1/bookmarks?page="+pageNo))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalElements", CoreMatchers.equalTo(totalElements)))
        .andExpect(jsonPath("$.totalPages", CoreMatchers.equalTo(totalPages)))
        .andExpect(jsonPath("$.currentPage", CoreMatchers.equalTo(currentPage)))
        .andExpect(jsonPath("$.isFirst", CoreMatchers.equalTo(isFirst)))
        .andExpect(jsonPath("$.isLast", CoreMatchers.equalTo(isLast)))
        .andExpect(jsonPath("$.hasNext", CoreMatchers.equalTo(hasNext)))
        .andExpect(jsonPath("$.hasPrevious", CoreMatchers.equalTo(hasPrevious)))
    ;
  }
  @Test
  void shouldCreateBookmarkSuccessfully() throws Exception {
    this.mockMvc.perform(
            post("/api/v1//bookmarks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
            {
                "title": "SivaLabs Blog",
                "url": "https://sivalabs.in"
            }
            """)
        )
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id", notNullValue()))
        .andExpect(jsonPath("$.title", is("SivaLabs Blog")))
        .andExpect(jsonPath("$.url", is("https://sivalabs.in")));
  }

  @Test
  void shouldFailToCreateBookmarkWhenUrlIsNotPresent() throws Exception {
    this.mockMvc.perform(
            post("/api/v1//bookmarks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                {
                    "title": "SivaLabs Blog"
                }
                """)
        )
        .andExpect(status().isBadRequest())
        .andExpect(header().string("Content-Type", is("application/problem+json")))
        .andExpect(jsonPath("$.type", is("https://zalando.github.io/problem/constraint-violation")))
        .andExpect(jsonPath("$.title", is("Constraint Violation")))
        .andExpect(jsonPath("$.status", is(400)))
        .andExpect(jsonPath("$.violations", hasSize(1)))
        .andExpect(jsonPath("$.violations[0].field", is("url")))
        .andExpect(jsonPath("$.violations[0].message", is("Url should not be empty")))
        .andReturn();
  }
}