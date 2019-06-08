package com.guimaraes.controller;

import java.util.ArrayList;
import java.util.List;

import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.track.Track;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guimaraes.constant.AppConstants;

@RestController
public class SongController {

	private static final String RESOURCE_NAME = "/song";
	private static final String CONTEXT_RESOURCE = "/" + AppConstants.VERSION_V1 + RESOURCE_NAME;

	@GetMapping(CONTEXT_RESOURCE)
	public List<Track> getSongByFilters(@RequestParam("artistName") String artistName,
			@RequestParam("songName") String songName) {

		MusixMatch musixMatch = new MusixMatch(AppConstants.MUSICX_MATCH_API_KEY);
		try {
			return musixMatch.searchTracks("", artistName, songName, 1, 100, true);
		} catch (MusixMatchException e) {
			e.printStackTrace();
		}

		return new ArrayList<>();
	}

}
