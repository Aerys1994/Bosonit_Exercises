package com.bosonit.Block11uploaddownloadfiles.application;


import com.bosonit.Block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public interface FicheroService {

    void modifyRoute(String route);
    FicheroOutputDto addFichero(MultipartFile multipartFile, String category) throws IOException;
    ResponseEntity getFicheroById(int id) throws FileNotFoundException;
    ResponseEntity getFicheroByName(String name) throws FileNotFoundException;
}
