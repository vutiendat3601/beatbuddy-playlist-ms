package vn.io.datvutech.beatbuddy.playlist.mapper;

import vn.io.datvutech.beatbuddy.playlist.dto.ArtistDto;
import vn.io.datvutech.beatbuddy.playlist.dto.PlaylistArtistDto;

public class ArtistMapper {
    public static PlaylistArtistDto mapToPlaylistArtistDto(ArtistDto artistDto, PlaylistArtistDto playlistArtistDto) {
        playlistArtistDto.setId(artistDto.getId());
        playlistArtistDto.setUrn(artistDto.getUrn());
        playlistArtistDto.setName(artistDto.getName());
        playlistArtistDto.setRealName(artistDto.getRealName());
        playlistArtistDto.setThumbnail(artistDto.getThumbnail());
        playlistArtistDto.setIsPublic(artistDto.getIsPublic());
        playlistArtistDto.setIsVerified(artistDto.getIsVerified());
        return playlistArtistDto;
    }

    public static PlaylistArtistDto mapToPlaylistArtistDto(ArtistDto artistDto) {
        return mapToPlaylistArtistDto(artistDto, new PlaylistArtistDto());
    }
}
