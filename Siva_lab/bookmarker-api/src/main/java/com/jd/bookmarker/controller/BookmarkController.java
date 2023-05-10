package com.jd.bookmarker.controller;

import com.jd.bookmarker.payload.BookmarkProjectDTO;
import com.jd.bookmarker.payload.BookmarksDTO;
import com.jd.bookmarker.payload.CreateBookmarkRequest;
import com.jd.bookmarker.service.BookmarkService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jd birla on 07-05-2023 at 16:08
 */

@RestController
@RequestMapping(value = "/api/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

  private final BookmarkService bookmarkService;

  @GetMapping
  public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page ,
      @RequestParam(name = "query", defaultValue = "") String query) {
    if(query == null || query.trim().length() == 0) {
      return bookmarkService.getBookMarks(page);
    }
    return bookmarkService.searchBookmarks(query,page);

  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BookmarkProjectDTO createBookmark(@RequestBody @Valid CreateBookmarkRequest request) {
    return bookmarkService.createBookmark(request);
  }

}
