package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    SongRepository songRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("albums", albumRepository.findAll());
        model.addAttribute("songs", songRepository.findAll());
        return "index";
    }

    @RequestMapping("/albumlist")
    public String listAlbums(Model model) {
        model.addAttribute("Albums", albumRepository.findAll());
        return "albumlist";
    }

    @RequestMapping("/songlist")
    public String listSongs(Model model) {
        model.addAttribute("songs", songRepository.findAll());
        return "songlist";
    }

    @GetMapping("/addAlbum")
    public String albumInputForm(Model model) {
        model.addAttribute("album", new Album());
        return "albumInputForm";
    }

    @GetMapping("/addSong")
    public String songInputForm(Model model) {
        model.addAttribute("song", new Song());
        return "songInputForm";
    }

    @PostMapping("/processAlbum")
    public String processForm(@Valid Album album, BindingResult result) {
        if (result.hasErrors()) {
            return "albumInputForm";
        }
        albumRepository.save(album);
        return "redirect:/";
    }

    @PostMapping("/processSong")
    public String processForm(@Valid Song song, BindingResult result) {
        if (result.hasErrors()) {
            return "songInputForm";
        }
        songRepository.save(song);
        return "redirect:/";
    }

    @RequestMapping("/albumdetail/{id}")
    public String showAlbum(@PathVariable("id") long id, Model model) {
        model.addAttribute("album", albumRepository.findById(id).get());
        return "showalbum";
    }

    @RequestMapping("/songdetail/{id}")
    public String showSong(@PathVariable("id") long id, Model model) {
        model.addAttribute("album", songRepository.findById(id).get());
        return "showsong";
    }

    @RequestMapping("/albumupdate/{id}")
    public String updateAlbum(@PathVariable("id") long id, Model model) {
        model.addAttribute("album", albumRepository.findById(id).get());
        return "albumInputForm";
    }

    @RequestMapping("/songupdate/{id}")
    public String updateSong(@PathVariable("id") long id, Model model) {
        model.addAttribute("song", albumRepository.findById(id).get());
        return "songInputForm";
    }

    @RequestMapping("/albumdelete/{id}")
    public String deleteAlbum(@PathVariable("id") long id) {
        albumRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/songdelete/{id}")
    public String deleteSong(@PathVariable("id") long id) {
        songRepository.deleteById(id);
        return "redirect:/";
    }
}














//        album.setName("African Giant");
//        album.setGenre("Afro-Fusion");
//
//        Song song = new Song();
//        song.setTitle("Dangote");
//        song.setYear(2019);
//        song.setDescription("This Song celebrates Aliko Dangote, Africa's richest man ...");
//
//        Set<Song> songs = new HashSet<Song>();
//        songs.add(song);
//
//        song = new Song();
//        song.setTitle("Anybody");
//        song.setYear(2019);
//        song.setDescription("This track, produced by Rexxie, features a smooth saxophone riff and rhythmic african percussion.");
//        songs.add(song);
//
//       album.setSongs(songs);
//        albumRepository.save(album);

//        model.addAttribute("albums",albumRepository.findAll());
//        return "index";

