package vn.io.datvutech.beatbuddy.playlist.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.io.datvutech.beatbuddy.playlist.entity.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, UUID> {
    Optional<Playlist> findById(String id);
}
