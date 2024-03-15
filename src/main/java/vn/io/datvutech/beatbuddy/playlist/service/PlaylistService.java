package vn.io.datvutech.beatbuddy.playlist.service;

import vn.io.datvutech.beatbuddy.playlist.dto.PlaylistDto;

public interface PlaylistService {
    String createPlaylist(PlaylistDto playlistDto);

    void updatePlaylist(String id, PlaylistDto playlistDto);

    PlaylistDto getPlaylist(String id);
}
