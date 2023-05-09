package com.jd.bookmarker.repository;

import com.jd.bookmarker.domain.Bookmark;
import com.jd.bookmarker.payload.BookmarkProjectDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by jd birla on 07-05-2023 at 16:04
 */
public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {

  @Query("SELECT new com.jd.bookmarker.payload.BookmarkProjectDTO(b.id , b.title , b.url, b.createdAt) from Bookmark b")
  Page<BookmarkProjectDTO>  findBookmarks(Pageable pageable);


}
