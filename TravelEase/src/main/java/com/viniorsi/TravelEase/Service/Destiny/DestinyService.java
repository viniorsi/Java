package com.viniorsi.TravelEase.Service.Destiny;

import com.viniorsi.TravelEase.Domain.Destiny.DTO.DTOListDestinations;
import com.viniorsi.TravelEase.Domain.Destiny.Entity.Destiny;
import com.viniorsi.TravelEase.Domain.Destiny.Repository.DestinyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DestinyService {

    @Autowired
    DestinyRepository destinyRepository;


//    public Page<Destiny> listOfDestinations(Pageable pagination) {
//        try {
//            return destinyRepository.findAll(pagination);
//        } catch (Exception e) {
//            throw new RuntimeException("Erro ao listar destinos", e);
//        }
//    }


    public Page<DTOListDestinations> listOfDestinations(Pageable pagination) {
        Page<Destiny> destinies = destinyRepository.findAll(pagination);
        return destinies.map(DTOListDestinations::new);
    }



}
