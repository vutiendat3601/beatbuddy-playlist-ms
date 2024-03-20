package vn.io.datvutech.beatbuddy.playlist.mapper;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.io.datvutech.beatbuddy.playlist.dto.PlaylistDto;
import vn.io.datvutech.beatbuddy.playlist.dto.PlaylistTrackDto;
import vn.io.datvutech.beatbuddy.playlist.entity.Playlist;

public class PlaylistMapper {
    private static final ObjectMapper objMapper = new ObjectMapper();

    public static PlaylistDto mapToPlaylistDto(Playlist playlist, PlaylistDto playlistDto) {
        playlistDto.setId(playlist.getId());
        playlistDto.setName(playlist.getName());
        playlistDto.setThumbnail(playlist.getThumbnail());
        playlistDto.setDescription(playlist.getDescription());
        playlistDto.setIsPublic(playlist.getIsPublic());
        playlistDto.setDurationSec(playlist.getDurationSec());
        try {
            playlistDto.setTracks(objMapper.readValue(playlist.getTracksJson(), objMapper.getTypeFactory()
                    .constructCollectionType(List.class, PlaylistTrackDto.class)));
        } catch (JsonProcessingException e) {
        }
        return playlistDto;
    }

    public static Playlist mapToPlaylist(PlaylistDto playlistDto, Playlist playlist) {
        try {
            playlist.setName(playlistDto.getName());
            playlist.setThumbnail(playlistDto.getThumbnail());
            playlist.setDescription(playlistDto.getDescription());
            playlist.setIsPublic(playlistDto.getIsPublic());
            playlist.setDurationSec(playlistDto.getDurationSec());
            String tracksJson = objMapper.writeValueAsString(playlistDto.getTracks());
            playlist.setTracksJson(tracksJson);
        } catch (JsonProcessingException e) {
            playlist.setTracksJson("[]");
        }
        return playlist;
    }
}
