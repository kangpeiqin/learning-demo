package com.example.distributedlock.entity;

import com.example.distributedlock.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author kpq
 * @since 2022-02-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FilmCategory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer filmId;

    private Integer categoryId;


}
