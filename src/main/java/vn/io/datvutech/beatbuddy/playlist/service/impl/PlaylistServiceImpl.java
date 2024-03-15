package vn.io.datvutech.beatbuddy.playlist.service.impl;

import static vn.io.datvutech.beatbuddy.playlist.constant.PlaylistConstant.ID_LENGTH;
import static vn.io.datvutech.beatbuddy.playlist.constant.PlaylistConstant.PLAYLIST;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.io.datvutech.beatbuddy.playlist.dto.PlaylistDto;
import vn.io.datvutech.beatbuddy.playlist.dto.TrackDto;
import vn.io.datvutech.beatbuddy.playlist.entity.Playlist;
import vn.io.datvutech.beatbuddy.playlist.exception.ResourceNotFoundException;
import vn.io.datvutech.beatbuddy.playlist.mapper.PlaylistMapper;
import vn.io.datvutech.beatbuddy.playlist.repository.PlaylistRepository;
import vn.io.datvutech.beatbuddy.playlist.service.PlaylistService;
import vn.io.datvutech.beatbuddy.playlist.service.client.CatalogClient;
import vn.io.datvutech.beatbuddy.playlist.util.StringUtil;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepo;
    private final CatalogClient catalogClient;

    @Override
    public String createPlaylist(PlaylistDto playlistDto) {
        Playlist playlist = PlaylistMapper.mapToPlaylist(playlistDto, new Playlist());
        String id = StringUtil.randomString(ID_LENGTH);
        playlist.setId(id);
        playlistRepo.save(playlist);
        ResponseEntity<TrackDto> trackDtoResp = catalogClient.getTrack(playlistDto.getTracks().get(0).getId());
        System.out.println(trackDtoResp.getBody().getName());
        return id;
    }

    @Override
    public void updatePlaylist(String id, PlaylistDto playlistDto) {
        Playlist playlist = playlistRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PLAYLIST, "id", id));
        playlist = PlaylistMapper.mapToPlaylist(playlistDto, playlist);
        playlistRepo.save(playlist);
    }

    @Override
    public PlaylistDto getPlaylist(String id) {
        Playlist playlist = playlistRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PLAYLIST, "id", id));
        return PlaylistMapper.mapToPlaylistDto(playlist, new PlaylistDto());
    }
}
