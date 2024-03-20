package vn.io.datvutech.beatbuddy.playlist.mapper;

import java.util.List;

import vn.io.datvutech.beatbuddy.playlist.dto.PlaylistArtistDto;
import vn.io.datvutech.beatbuddy.playlist.dto.PlaylistTrackDto;
import vn.io.datvutech.beatbuddy.playlist.dto.TrackDto;

public class TrackMapper {

    public static PlaylistTrackDto mapToPlaylistTrackDto(TrackDto trackDto, PlaylistTrackDto playlistTrackDto) {
        playlistTrackDto.setId(trackDto.getId());
        playlistTrackDto.setUrn(trackDto.getUrn());
        playlistTrackDto.setName(trackDto.getName());
        playlistTrackDto.setDurationSec(trackDto.getDurationSec());
        playlistTrackDto.setDescription(trackDto.getDescription());
        playlistTrackDto.setReleasedDate(trackDto.getReleasedDate());
        playlistTrackDto.setThumbnail(trackDto.getThumbnail());
        playlistTrackDto.setIsPublic(trackDto.getIsPublic());
        playlistTrackDto.setIsPlayable(trackDto.getIsPlayable());

        List<PlaylistArtistDto> artistDtos = trackDto.getArtists().stream().map(
                ArtistMapper::mapToPlaylistArtistDto

        ).toList();
        playlistTrackDto.setArtists(artistDtos);
        return playlistTrackDto;
    }

    public static PlaylistTrackDto mapToPlaylistTrackDto(TrackDto trackDto) {
        return mapToPlaylistTrackDto(trackDto, new PlaylistTrackDto());
    }
}
