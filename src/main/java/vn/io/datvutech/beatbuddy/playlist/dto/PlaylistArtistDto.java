package vn.io.datvutech.beatbuddy.playlist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "PlaylistArtist", description = "Schema to hold information of Artist in Playlist")
@Data
@NoArgsConstructor
public class PlaylistArtistDto {
    private String id;

    private String urn;

    private String name;

    private String thumbnail;

    private Boolean isVerified;

    private Boolean isPublic;

    private String realName;
}
