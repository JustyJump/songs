package com.justy.media.controllers;

import com.justy.media.models.Song;
import com.justy.media.repo.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MediaController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping(value = "/media")
    public String mediaMain(Model model) {
        Iterable<Song> songs = songRepository.findAll();
        model.addAttribute("songs", songs);
        return "media-main";
    }

    @GetMapping(value = "/media/add")
    public String mediaAdd(Model model) {
        return "media-add";
    }

    @PostMapping(value = "/media/add")
    public String mediaSongAdd(@RequestParam String name, @RequestParam String singer, @RequestParam String full_song, Model model){
        Song song = new Song(name, singer, full_song);
        songRepository.save(song);
        return "redirect:/media";
    }

    @GetMapping("/media/{id}")
    public String mediaDetails(@PathVariable(value = "id") long id, Model model) {
        Optional<Song> song = songRepository.findById(id);
        if(!songRepository.existsById(id)){
            return "redirect:/media";
        }
        ArrayList<Song> res = new ArrayList<>();
        song.ifPresent(res::add);
        model.addAttribute("song", res);
        return "media-details";
    }

    @GetMapping("/media/{id}/edit")
    public String mediaEdit(@PathVariable(value = "id") long id, Model model) {
        Optional<Song> song = songRepository.findById(id);
        if(!songRepository.existsById(id)){
            return "redirect:/media";
        }
        ArrayList<Song> res = new ArrayList<>();
        song.ifPresent(res::add);
        model.addAttribute("song", res);
        return "media-edit";
    }

    @PostMapping("/media/{id}/edit")
    public String mediaSongUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String singer, @RequestParam String full_song, Model model){
        Song song = songRepository.findById(id).orElseThrow(IllegalStateException::new);
        song.setName(name);
        song.setSinger(singer);
        song.setFull_song(full_song);
        songRepository.save(song);

        return "redirect:/media";
    }

    @PostMapping("/media/{id}/remove")
    public String mediaSongDelete(@PathVariable(value = "id") long id, Model model) {
        Song song = songRepository.findById(id).orElseThrow(IllegalStateException::new);
        songRepository.delete(song);
        return "redirect:/media";
    }
}