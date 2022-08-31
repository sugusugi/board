package com.example.board.repository;

import java.util.Date;
import java.util.UUID;

/**
 *  ���e�̃t�@�N�g���[�N���X
 */
public class PostFactory {

    /** ����J*/
    private PostFactory() {
    }

    /**
     * �V�K�̓��e�𐶐�����
     *
     * @return �V�K�̓��e
     */
    public static Post newPost() {
        Post post = new Post();
        return post;
    }

    /**
     * ���͓��e��ݒ肵�����e�𐶐�����
     *
     * @param post ���e
     * @return �V�K�̓��e
     */
    public static Post createPost(Post post) {
        String id = UUID.randomUUID().toString();
        post.setId(id);
        Date current = new Date();
        post.setCreatedDate(current);
        post.setUpdatedDate(current);
        return post;
    }

    /**
     * �X�V���e��ݒ肵�����e�𐶐�����
     *
     * @param post ���e
     * @return�@���e
     */
    public static Post updatePost(Post post, Post form) {
        post.setAuthor(form.getAuthor());
        post.setTitle(form.getTitle());
        post.setBody(form.getBody());
        Date current = new Date();
        post.setUpdatedDate(current);
        return post;
    }

    /**
     * �폜���e��ݒ肵�����e�𐶐�����
     *
     * @param post ���e
     * @return ���e
     */
    public static Post deletePost(Post post) {
        post.setDeleted(true);
        Date current = new Date();
        post.setUpdatedDate(current);
        return post;
    }

}