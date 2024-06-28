package com.festi.bulle.repository;

import com.festi.bulle.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("SELECT m FROM Message m WHERE m.conversation.id = :conversationId ORDER BY m.dateEnvoi DESC")
    Page<Message> findByConversationId(@Param("conversationId") Integer conversationId, Pageable pageable);

    @Query("SELECT m FROM Message m WHERE m.conversation.id = :conversationId AND m.dateEnvoi > :since ORDER BY m.dateEnvoi ASC")
    List<Message> findNewMessagesSince(@Param("conversationId") Integer conversationId, @Param("since") Instant since);

    @Query("SELECT m FROM Message m WHERE m.conversation.id = :conversationId AND LOWER(m.contenu) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY m.dateEnvoi DESC")
    Page<Message> searchMessagesInConversation(@Param("conversationId") Integer conversationId, @Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT COUNT(m) FROM Message m WHERE m.conversation.id = :conversationId")
    long countMessagesByConversationId(@Param("conversationId") Integer conversationId);

    @Query("SELECT m FROM Message m JOIN FETCH m.utilisateur WHERE m.conversation.id = :conversationId ORDER BY m.dateEnvoi DESC")
    List<Message> findRecentMessagesByConversationId(@Param("conversationId") Integer conversationId, Pageable pageable);
}