package com.example.distributedlock.search.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author KPQ
 * @date 2022/2/28
 */
@Data
@Document(indexName = "film", shards = 1, replicas = 0)
public class EsFilm implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    private Integer filmId;

    @Field(type = FieldType.Keyword)
    private String title;

    @Field(type = FieldType.Keyword)
    private String description;

}
