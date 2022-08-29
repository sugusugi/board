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
/**
 * 投稿.
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

    /** 投稿者 */
    @NotEmpty
    @Size(min = 1,max = 20)
    
    @Column(length = 20, nullable = false)
    private String author = null;

    /** タイトル */
    @NotEmpty
    @Size(min = 1,max = 20)
    
    @Column(length = 20, nullable = false)
    private String title = null;

    /** 内容 */
    @NotEmpty
    @Size(min = 1,max = 1000)
    
    @Column(length = 1000, nullable = false)
    private String body = null;

    /** 登録日時 */
    private Date createdDate = null;

    /** 更新日時 */
    private Date updatedDate = null;

    /** 削除済 */
    private boolean deleted = false;

}