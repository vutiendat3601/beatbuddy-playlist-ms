package vn.io.datvutech.beatbuddy.playlist.service.impl;

import static vn.io.datvutech.beatbuddy.playlist.constant.PlaylistConstant.ID_LENGTH;
import static vn.io.datvutech.beatbuddy.playlist.constant.PlaylistConstant.PLAYLIST;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.io.datvutech.beatbuddy.playlist.dto.PlaylistDto;
import vn.io.datvutech.beatbuddy.playlist.dto.PlaylistTrackDto;
import vn.io.datvutech.beatbuddy.playlist.dto.TrackDto;
import vn.io.datvutech.beatbuddy.playlist.entity.Playlist;
import vn.io.datvutech.beatbuddy.playlist.exception.ResourceNotFoundException;
import vn.io.datvutech.beatbuddy.playlist.mapper.PlaylistMapper;
import vn.io.datvutech.beatbuddy.playlist.mapper.TrackMapper;
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
        playlistDto = preparePlaylist(playlistDto);
        Playlist playlist = PlaylistMapper.mapToPlaylist(playlistDto, new Playlist());
        String id = StringUtil.randomString(ID_LENGTH);
        playlist.setId(id);
        playlistRepo.save(playlist);
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

    private PlaylistDto preparePlaylist(PlaylistDto playlistDto) {
        List<String> trackIds = playlistDto.getTracks().stream().map(t -> t.getId()).toList();
        List<TrackDto> trackDtos = catalogClient.getSeveralTracks(trackIds).getBody();
        trackDtos.forEach(t -> {
            if (t == null) {
                throw new ResourceNotFoundException("Some Tracks are not found");
            }
        });
        List<PlaylistTrackDto> playlistTrackDtos = trackDtos.stream().map(TrackMapper::mapToPlaylistTrackDto).toList();
        int durationSec = trackDtos.stream().mapToInt(t -> t.getDurationSec()).sum();
        playlistDto.setDurationSec(durationSec);
        playlistDto.setTracks(playlistTrackDtos);

        if (playlistDto.getThumbnail() == null) {
            playlistTrackDtos.parallelStream().filter(t -> t != null).findAny().ifPresent(
                    t -> playlistDto.setThumbnail(t.getThumbnail()));
        }
        return playlistDto;
    }
}
