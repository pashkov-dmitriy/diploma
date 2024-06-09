package com.mymusic.likes.services;

import com.mymusic.likes.clients.CatalogClient;
import com.mymusic.likes.clients.UserManagerClient;
import com.mymusic.likes.entities.Like;
import com.mymusic.likes.repos.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserManagerClient userManagerClient;
    private final CatalogClient catalogClient;

    public Optional<List<Like>> getUserLikes(Long userId) {
        if (validateUser(userId)) {
            return Optional.of(
                    likeRepository.findLikeByUserId(userId));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<List<Like>> getTrackLikes(Long trackId) {
        if (validateTrack(trackId)) {
            return Optional.of(
                    likeRepository.findLikeByUserId(trackId));
        }
        else {
            return Optional.empty();
        }
    }

    public boolean save(Like like) {
        if (validateUser(like.userId()) && validateTrack(like.trackId())) {
            likeRepository.save(like);
            return true;
        }
        else return false;
    }

    public boolean delete(Like like) {
        if (validateUser(like.userId()) && validateTrack(like.trackId())) {
            likeRepository.delete(like);
            return true;
        }
        else return false;
    }

    private boolean validateUser(Long userId) {
        return Boolean.TRUE.equals(userManagerClient.checkIfUserExists(userId)
                .getBody());
    }

    private boolean validateTrack(Long trackId) {
        return Boolean.TRUE.equals(catalogClient.checkIfTrackExists(trackId)
                .getBody());
    }
}
