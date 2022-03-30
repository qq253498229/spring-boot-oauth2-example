package com.example.generator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 表名：t_resource
 * 表注释：资源表
 */
@Getter
@Setter
@Table(name = "t_resource")
public class Resource {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 资源名，唯一，英文，尽量短
     */
    @Column(name = "name")
    private String name;

    /**
     * 资源描述
     */
    @Column(name = "description")
    private String description;
}