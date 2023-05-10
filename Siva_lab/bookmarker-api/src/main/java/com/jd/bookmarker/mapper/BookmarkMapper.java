package com.jd.bookmarker.mapper;

import com.jd.bookmarker.domain.Bookmark;
import com.jd.bookmarker.payload.BookmarkProjectDTO;
import org.springframework.stereotype.Component;

/**
 * Created by jd birla on 08-05-2023 at 14:29
 */
@Component
public class BookmarkMapper {
  public BookmarkProjectDTO toDTO(Bookmark bookmark) {
    return new BookmarkProjectDTO(
        bookmark.getId(),
        bookmark.getTitle(),
        bookmark.getUrl(),
        bookmark.getCreatedAt()
    );
  }
}
