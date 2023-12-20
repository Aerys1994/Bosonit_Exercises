package com.bosonit.Block11uploaddownloadfiles.domain;

import com.bosonit.Block11uploaddownloadfiles.controller.dto.FicheroInputDto;
import com.bosonit.Block11uploaddownloadfiles.controller.dto.FicheroOutputDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fichero {

    @Id
    @GeneratedValue
    int id;
    String name;
    Date uploadDate;
    String category;


    public Fichero(FicheroInputDto ficheroInputDto) {

        this.name = ficheroInputDto.getName();
        this.uploadDate = ficheroInputDto.getUploadDate();
        this.category = ficheroInputDto.getCategory();
    }

    public FicheroOutputDto ficheroToFicheroOutputDto() {
        return new FicheroOutputDto(
                this.id,
                this.name,
                this.uploadDate,
                this.category
        );
    }

}
