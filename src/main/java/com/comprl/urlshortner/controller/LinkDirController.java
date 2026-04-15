package com.comprl.urlshortner.controller;


import com.comprl.urlshortner.model.LinkDir;
import com.comprl.urlshortner.service.FirestoreService;
import com.google.rpc.context.AttributeContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userID = authentication.getPrincipal().toString();

        String LinkDirId = firestoreService.storeLinkDir(linkDir, userID);

        return "comprl.web.app/l/"+LinkDirId;
    }


    @GetMapping("/l/{linkDirId}")
    public LinkDir getLinkDir(@PathVariable String linkDirId){

        return firestoreService.getLinkDirById(linkDirId);
    }


    @GetMapping("/linkdir/{linkDirId}/edit")
    public LinkDir editLinkDir(@PathVariable String linkDirId){
//        System.out.println(linkDirId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userID = authentication.getPrincipal().toString();

        return firestoreService.editLinkDirById(linkDirId, userID);

    }

    @GetMapping("/linkdir/all")
    public List<LinkDir> getAllLinkDir(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userid = authentication.getPrincipal().toString();
        return firestoreService.getAllLinkDir(userid);
    }

    @PostMapping("/linkdir/update")
    public LinkDir updateLinkDir(@RequestBody LinkDir linkDir){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userID = authentication.getPrincipal().toString();

        return firestoreService.updateLinkDir(linkDir, userID);
    }

    @PostMapping("/linkdir/{linkDirId}/delete")
    public boolean deleteLinkDir(@PathVariable String linkDirId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userID = authentication.getPrincipal().toString();
        return firestoreService.deleteLinkDirById(linkDirId, userID);
    }


}
