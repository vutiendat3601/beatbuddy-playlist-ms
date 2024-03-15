package vn.io.datvutech.beatbuddy.playlist.controller;

import static vn.io.datvutech.beatbuddy.playlist.constant.PlaylistConstant.MESSAGE_201;
import static vn.io.datvutech.beatbuddy.playlist.constant.PlaylistConstant.STATUS_201;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import vn.io.datvutech.beatbuddy.playlist.dto.PlaylistDto;
import vn.io.datvutech.beatbuddy.playlist.dto.ResponseDto;
import vn.io.datvutech.beatbuddy.playlist.service.PlaylistService;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "v1/playlists", produces = "application/json")
public class PlaylistController {
    private final PlaylistService playlistService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ResponseDto> createPlaylist(@Valid @RequestBody PlaylistDto playlistDto) {
        String id = playlistService.createPlaylist(playlistDto);
        return ResponseEntity.created(URI.create("/v1/playlists/" + id))
                .body(new ResponseDto(STATUS_201, MESSAGE_201));
    }

    @GetMapping("{id}")
    public ResponseEntity<PlaylistDto> getPlaylist(@PathVariable String id) {
        PlaylistDto playlistDto = playlistService.getPlaylist(id);
        return ResponseEntity.ok(playlistDto);
    }
}
