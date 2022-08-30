package com.example.board.repository;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.board.validation.Group1;
import com.example.board.validation.Group2;

/**
 * ���e
 */
@Entity
@Table(name = "post")
@Data
public class Post {
    

    /** ID */
    @NotNull
    
    @Id
    @Column
    private String id = null;

    /** ���e�� */
    @NotEmpty(groups = Group1.class)
    @Size(min = 1,max = 20, groups = Group2.class)
    
    @Column(length = 20, nullable = false)
    private String author = null;

    /** �^�C�g�� */
    @NotEmpty(groups = Group1.class)
    @Size(min = 1,max = 20, groups = Group2.class)
    
    @Column(length = 20, nullable = false)
    private String title = null;

    /** ���e */
    @NotEmpty(groups = Group1.class)
    @Size(min = 1,max = 1000, groups = Group2.class)
    
    @Column(length = 1000, nullable = false)
    private String body = null;

    /** ���e���� */
    private Date createdDate = null;

    /** �X�V���� */
    private Date updatedDate = null;

    /** �폜�� */
    private boolean deleted = false;

}