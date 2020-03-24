package com.ybs.note.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Note
 *
 * @author Paulson
 * @date 2020/3/24 1:42
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "note")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})

public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String abs;
    private String contentHtml;
    private String contentMd;

    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    private Timestamp lastModifiedTime;

    private Timestamp createTime;

}
