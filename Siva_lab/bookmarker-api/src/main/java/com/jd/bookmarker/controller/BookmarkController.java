package com.jd.bookmarker.controller;

import com.jd.bookmarker.payload.BookmarksDTO;
import com.jd.bookmarker.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jd birla on 07-05-2023 at 16:08
 */

@RestController
@RequestMapping(value = "/api/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

  private final BookmarkService bookmarkService;

  @GetMapping("/getAllBookmarks")
  public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page) {

    return bookmarkService.getBookMarks(page);

  }

}
