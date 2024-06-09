package com.mymusic.likes.repos;

import com.mymusic.likes.entities.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LikeRepository extends MongoRepository<Like, String> {

    List<Long> findTrackIdByUserId(Long userId);
    List<Like> findLikeByUserId(Long userId);
    List<Like> findLikeByTrackId(Long trackId);
    void deleteByUserId(Long userId);
    void deleteByTrackId(Long trackId);
    void deleteByUserIdAndTrackId(Long userId, Long trackId);
}
