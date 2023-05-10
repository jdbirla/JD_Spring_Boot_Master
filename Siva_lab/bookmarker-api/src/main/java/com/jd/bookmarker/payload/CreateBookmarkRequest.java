package com.jd.bookmarker.payload;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by jd birla on 08-05-2023 at 14:23
 */
@Getter
@Setter
public class CreateBookmarkRequest {
  @NotEmpty(message = "Title should not be empty")
  private String title;
  @NotEmpty(message = "Url should not be empty")
  private String url;
}
