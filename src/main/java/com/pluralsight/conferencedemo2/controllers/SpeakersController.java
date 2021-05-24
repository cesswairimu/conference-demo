package com.pluralsight.conferencedemo2.controllers;

import com.pluralsight.conferencedemo2.models.Speaker;
import com.pluralsight.conferencedemo2.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {
    @Autowired
    private SpeakerRepository speakerRepository;

    // index endpoint
    @GetMapping
    public List<Speaker> list() {
        return speakerRepository.findAll();
    }

    // show endpoint
    @GetMapping
    @RequestMapping("{id}")
    public Speaker get(@PathVariable Long id) {
        return speakerRepository.getOne(id);
    }

    // create endpoint
    @PostMapping
    public Speaker create(@RequestBody final Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }

    // delete endpoint
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id, @RequestBody Speaker speaker){
        speakerRepository.deleteById(id);
    }

    // put endpoint
    @RequestMapping(value="{id]", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
        Speaker existingSpeaker = speakerRepository.getOne(id);
        BeanUtils.copyProperties(speaker, existingSpeaker,  "speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }
}
