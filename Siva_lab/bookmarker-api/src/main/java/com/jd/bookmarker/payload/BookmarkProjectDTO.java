package com.jd.bookmarker.payload;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jd birla on 08-05-2023 at 08:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkProjectDTO {
  private Long id;
  private String title;
  private String url;
  private Instant createdAt;
}
