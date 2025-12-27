package com.comprl.urlshortner.controller;


import com.comprl.urlshortner.model.LinkDir;
import com.comprl.urlshortner.service.FirestoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class LinkDirController {


    @Autowired
    private FirestoreService firestoreService;

    @PostMapping("/linkdir/create")
    public String createLinkDir(@RequestBody LinkDir linkDir){

        String LinkDirId = firestoreService.storeLinkDir(linkDir);

        return "comprl.web.app/l/"+LinkDirId;
    }


    @GetMapping("/l/{linkDirId}")
    public LinkDir getLinkDir(@PathVariable String linkDirId){
        return firestoreService.getLinkDirById(linkDirId);
    }


    @GetMapping("/linkdir/all")
    public List<LinkDir> getAllLinkDir(@RequestParam Map<String,String> user){
        return firestoreService.getAllLinkDir(user.get("userId"));
    }

    @PostMapping("/linkdir/update")
    public LinkDir updateLinkDir(@RequestBody LinkDir linkDir){
        return firestoreService.updateLinkDir(linkDir);
    }


}
