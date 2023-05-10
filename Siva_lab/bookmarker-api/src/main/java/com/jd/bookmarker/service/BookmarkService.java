package com.jd.bookmarker.service;

import com.jd.bookmarker.domain.Bookmark;
import com.jd.bookmarker.mapper.BookmarkMapper;
import com.jd.bookmarker.payload.BookmarkIProjectDTO;
import com.jd.bookmarker.payload.BookmarkProjectDTO;
import com.jd.bookmarker.payload.BookmarksDTO;
import com.jd.bookmarker.payload.CreateBookmarkRequest;
import com.jd.bookmarker.repository.BookmarkRepository;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by jd birla on 07-05-2023 at 16:05
 */

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

  private final BookmarkRepository bookmarkRepository;
  private final BookmarkMapper bookmarkMapper;

  @Transactional(readOnly = true)
  public BookmarksDTO getBookMarks(Integer page) {

    int pageNo = page < 1 ? 0 : page - 1;
    Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
    // Page<Bookmark> all = bookmarkRepository.findAll(pageable);
    Page<BookmarkProjectDTO> bookmarks = bookmarkRepository.findBookmarks(pageable);
    return new BookmarksDTO(bookmarks);
  }


  @Transactional(readOnly = true)
  public BookmarksDTO searchBookmarks(String query, Integer page) {
    int pageNo = page < 1 ? 0 : page - 1;
    Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "createdAt");
    // Page<BookmarkProjectDTO> bookmarkPage = bookmarkRepository.searchBookmarks(query, pageable);
    Page<BookmarkProjectDTO> bookmarkPage = bookmarkRepository.findByTitleContainsIgnoreCase(query,
        pageable);

    Page<BookmarkIProjectDTO> bookmarkPage1 = bookmarkRepository.findByTitleLikeIgnoreCase(query,
        pageable);
    System.out.println("bookmarkPage1" + bookmarkPage1);
    return new BookmarksDTO(bookmarkPage);
  }

  public BookmarkProjectDTO createBookmark(CreateBookmarkRequest request) {
    Bookmark bookmark = new Bookmark(null, request.getTitle(), request.getUrl(), Instant.now());
    Bookmark savedBookmark = bookmarkRepository.save(bookmark);
    return bookmarkMapper.toDTO(savedBookmark);
  }
}