package com.pluralsight.conferencedemo2.controllers;

import com.pluralsight.conferencedemo2.models.Session;
import com.pluralsight.conferencedemo2.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    @Autowired
    private SessionRepository sessionRepository;

    // Return all records  (get index)
    @GetMapping
    public List<Session> list(){
        return sessionRepository.findAll();
    }

    // Get 1 record by passing id (get show)
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id ){
      return sessionRepository.getOne(id);
    }

    // Create endpoint (post create)
    @PostMapping
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
   }

    // Delete one record endpoint (delete id)
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // Check for children records before deleting
        sessionRepository.deleteById(id);
    }
    // Update endpoint (put id)
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        // PUT expects all attributes to be passed in
        // Add validation that all attributes are passed in, otherwise return 400(bad payload)
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
