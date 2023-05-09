package com.jd.bookmarker.domain;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by jd birla on 07-05-2023 at 15:58
 */
@Entity
@Table(name = "bookmarks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bm_id_seq_gen")
  @SequenceGenerator(name = "bm_id_seq_gen", sequenceName = "bm_id_seq")
  private Long id;

  @Column(nullable = false)
  private String title;
  @Column(nullable = false)
  private String url;

  private Instant createdAt;

}
