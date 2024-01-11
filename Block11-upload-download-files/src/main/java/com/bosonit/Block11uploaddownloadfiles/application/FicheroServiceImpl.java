package com.bosonit.Block11uploaddownloadfiles.application;

import com.bosonit.Block11uploaddownloadfiles.controller.dto.FicheroInputDto;
import com.bosonit.Block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import com.bosonit.Block11uploaddownloadfiles.domain.Fichero;
import com.bosonit.Block11uploaddownloadfiles.repository.FicheroRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.Date;

@Service
public class FicheroServiceImpl implements FicheroService {

    @Autowired
    FicheroRepository ficheroRepository;
    @Autowired
    private ApplicationArguments args;

    String route =  "C:\\Users\\andreu.cunill";
    String downloadPath;


    @PostConstruct
    public void setDownloadPath() {
        String[] arguments = args.getSourceArgs();
        if (arguments.length > 0) {
            this.downloadPath = arguments[0];
        } else {
            this.downloadPath = route;
        }
    }

    @Override
    public FicheroOutputDto addFichero(MultipartFile multipartFile, String category) throws IOException {

        File uploadPath = new File(route);
        String fileName = multipartFile.getOriginalFilename();
        assert fileName != null;
        Path pathFile = uploadPath.toPath().resolve(fileName);
        Files.copy(multipartFile.getInputStream(), pathFile, StandardCopyOption.REPLACE_EXISTING);
        FicheroInputDto ficheroInputDto = new FicheroInputDto(fileName, new Date(), category);

        return ficheroRepository.save(new Fichero(ficheroInputDto)).ficheroToFicheroOutputDto();
    }

    @Override
    public void modifyRoute(String path) {

        File newPath = new File(path);

        if (!newPath.exists()) {
            newPath.mkdirs();
        }

        this.route = path;
    }

    @Override
    public ResponseEntity getFicheroById(int id) throws FileNotFoundException {
        Fichero fichero = ficheroRepository.findById(id)
                .orElseThrow(()-> new FileSystemNotFoundException("File not found with id: " + id));

        return getDataByName(fichero.getName());
    }

    @Override
    public ResponseEntity getFicheroByName(String name) throws FileNotFoundException{
        Fichero fichero = ficheroRepository.findByName(name)
                .orElseThrow(()-> new FileSystemNotFoundException("File not found with name: " + name));

        return getDataByName(fichero.getName());
    }



    private ResponseEntity getDataByName(String name) throws FileNotFoundException {

        File pathFile = new File(route);
        File fichero = new File(pathFile, name);
        if (!fichero.exists()) {
            throw new FileNotFoundException();
        }

        String applicationPath = Paths.get("").toAbsolutePath().toString();
        Path downloadFolderPath = Path.of(applicationPath);

        Path downloadedFichero = Paths.get(downloadFolderPath.toString(), name);

        try {
            Files.copy(fichero.toPath(), downloadedFichero, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().build();

    }




}
