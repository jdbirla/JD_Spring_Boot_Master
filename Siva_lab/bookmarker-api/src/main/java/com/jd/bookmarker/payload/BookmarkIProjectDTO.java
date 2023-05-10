package com.jd.bookmarker.payload;

import java.time.Instant;

/**
 * Created by jd birla on 08-05-2023 at 14:12
 */
public interface BookmarkIProjectDTO {
  Long getId();
  String getTitle();
  String getUrl();
  Instant getCreatedAt();
}
