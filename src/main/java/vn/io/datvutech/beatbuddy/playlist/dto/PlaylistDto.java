package vn.io.datvutech.beatbuddy.playlist.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "Playlist", description = "Schema to hold Playlist information")
@Data
@NoArgsConstructor
public class PlaylistDto {
    @Schema(name = "id", description = "Unique identity of Playlist")
    private String id;

    @Schema(name = "name", description = "Name of Playlist")
    private String name;

    @Schema(name = "thumbnail", description = "Thumbnail image url of Playlist")
    @Pattern(regexp = "^(http|https)://([\\w.-]+)(:\\d+)?(/\\S*)?$", message = "Invalid thumbnail URL")
    private String thumbnail;

    @Schema(name = "durationSec", description = "Length of Playlist time")
    private Integer durationSec = 0;

    @Schema(name = "description", description = "Description about Playlist")
    private String description;

    @Schema(name = "isPublic", description = "Playlist is visible to the world or not")
    private Boolean isPublic = false;

    private List<PlaylistTrackDto> tracks = List.of();
}
