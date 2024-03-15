package vn.io.datvutech.beatbuddy.playlist.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "playlists")
@Entity
public class Playlist extends AbstractEntity {
    @Id
    @GeneratedValue(generator = "pg-uuid")
    private UUID playlistId;

    private String id;

    private String name;

    private String thumbnail;

    private String description;

    private Boolean isPublic = false;

    private String tracksJson;
}
