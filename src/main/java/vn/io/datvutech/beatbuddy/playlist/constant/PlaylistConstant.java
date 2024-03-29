package vn.io.datvutech.beatbuddy.playlist.constant;

import vn.io.datvutech.beatbuddy.playlist.entity.Playlist;

public interface PlaylistConstant {
    int ID_LENGTH = 16;
    String PLAYLIST = Playlist.class.getSimpleName();

    String STATUS_200 = "200";
    String MESSAGE_200 = "Request processed successfully";

    String STATUS_201 = "201";
    String MESSAGE_201 = "Created playlist successfully";
}
