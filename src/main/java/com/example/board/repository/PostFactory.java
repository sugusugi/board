package com.example.board.repository;

import java.util.Date;
import java.util.UUID;

/**
 *  “Še‚Ìƒtƒ@ƒNƒgƒŠ[ƒNƒ‰ƒX
 */
public class PostFactory {

    /** ”ñŒöŠJ*/
    private PostFactory() {
    }

    /**
     * V‹K‚Ì“Še‚ğ¶¬‚·‚é
     *
     * @return V‹K‚Ì“Še
     */
    public static Post newPost() {
        Post post = new Post();
        return post;
    }

    /**
     * “ü—Í“à—e‚ğİ’è‚µ‚½“Še‚ğ¶¬‚·‚é
     *
     * @param post “Še
     * @return V‹K‚Ì“Še
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
     * XV“à—e‚ğİ’è‚µ‚½“Še‚ğ¶¬‚·‚é
     *
     * @param post “Še
     * @return@“Še
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
     * íœ“à—e‚ğİ’è‚µ‚½“Še‚ğ¶¬‚·‚é
     *
     * @param post “Še
     * @return “Še
     */
    public static Post deletePost(Post post) {
        post.setDeleted(true);
        Date current = new Date();
        post.setUpdatedDate(current);
        return post;
    }

}