package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

/**
 * ���e�̃��|�W�g���[
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    /**
     * ID�Ō�������
     *
     * @param id ID
     * @return ���e
     */
     public Optional<Post> findById(String id);
     
     /**
      * �X�V�����̍~���ł��ׂĂ̓��e����������
      * 
      * @return�@���e�̃��X�g
      */
     List<Post> findAllByOrderByUpdatedDateDesc();
     
     /**
      * �X�V�����̍~���Ŗ��폜�̓��e����������
      * 
      * @return�@���e�̃��X�g
      */
     List<Post> findByDeletedFalseOrderByUpdatedDateDesc();
}