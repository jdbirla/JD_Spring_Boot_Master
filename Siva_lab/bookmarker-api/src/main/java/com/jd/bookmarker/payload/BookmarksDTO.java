package com.jd.bookmarker.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import org.springframework.data.domain.Page;

/**
 * Created by jd birla on 08-05-2023 at 06:57
 */
@Data
public class BookmarksDTO {
  private List<BookmarkProjectDTO> data;
  private long totalElements;
  private int totalPages;
  private int currentPage;
  @JsonProperty("isFirst")
  private boolean isFirst;
  @JsonProperty("isLast")
  private boolean isLast;
  private boolean hasNext;
  private boolean hasPrevious;

  public BookmarksDTO(Page<BookmarkProjectDTO> bookmarkPage) {
    this.setData(bookmarkPage.getContent());
    this.setTotalElements(bookmarkPage.getTotalElements());
    this.setTotalPages(bookmarkPage.getTotalPages());
    this.setCurrentPage(bookmarkPage.getNumber() + 1);
    this.setFirst(bookmarkPage.isFirst());
    this.setLast(bookmarkPage.isLast());
    this.setHasNext(bookmarkPage.hasNext());
    this.setHasPrevious(bookmarkPage.hasPrevious());
  }

}
