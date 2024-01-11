package com.bosonit.Block11uploaddownloadfiles.controller;

import com.bosonit.Block11uploaddownloadfiles.application.FicheroService;
import com.bosonit.Block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/fichero")
public class FicheroController {

    @Autowired
    FicheroService ficheroService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FicheroOutputDto> addFichero(
            @RequestParam("multipartFile") MultipartFile multipartFile,
            @RequestParam("category") String category
            ) throws IOException {
        return ResponseEntity.ok().body(ficheroService.addFichero(multipartFile, category));
    }

    @GetMapping("/setpath")
    public ResponseEntity<Void> modifyRoute(
            @RequestParam("path") String path
    ) {
        ficheroService.modifyRoute(path);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getFicheroById(@PathVariable int id) throws FileNotFoundException {
        return ficheroService.getFicheroById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity getFicheroByName(@PathVariable String name) throws FileNotFoundException {
        return ficheroService.getFicheroByName((name));
    }

}
