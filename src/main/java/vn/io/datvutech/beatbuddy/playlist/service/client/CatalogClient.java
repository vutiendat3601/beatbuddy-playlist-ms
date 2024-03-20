package vn.io.datvutech.beatbuddy.playlist.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import vn.io.datvutech.beatbuddy.playlist.dto.TrackDto;

@FeignClient(name = "catalog")
public interface CatalogClient {
    @GetMapping("v1/tracks/{id}")
    ResponseEntity<TrackDto> getTrack(@PathVariable String id);
    
    @GetMapping("v1/tracks")
    public ResponseEntity<List<TrackDto>> getSeveralTracks(@RequestParam List<String> ids);
}
