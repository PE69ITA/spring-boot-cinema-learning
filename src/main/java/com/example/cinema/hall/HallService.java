package com.example.cinema.hall;

import com.example.cinema.exception.HallNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {
    private final HallRepository hallRepository;
    private final HallMapper hallMapper;

    public HallService(HallRepository hallRepository, HallMapper hallMapper) {
        this.hallRepository = hallRepository;
        this.hallMapper = hallMapper;
    }

    public List<HallDto> getAllHalls(){
        return hallRepository.findAll()
                .stream()
                .map(hallMapper::toDto)
                .toList();
    }

    public HallDto getHallById(Long id){
        return hallRepository.findById(id)
                .map(hallMapper::toDto)
                .orElseThrow(()->new HallNotFoundException("Hall not found"));
    }

    public HallDto createHall(HallDto dto){
        HallEntity entityToSave = hallMapper.toEntity(dto);
        HallEntity savedEntity = hallRepository.save(entityToSave);
        return hallMapper.toDto(savedEntity);
    }

    public HallDto updateHall(Long id, HallDto dto){
        var entity = hallRepository.findById(id)
                .orElseThrow(()->new HallNotFoundException("Hall not found"));
        entity.setName(dto.name());
        entity.setCapacity(dto.capacity());
        var savedEntity = hallRepository.save(entity);
        return hallMapper.toDto(savedEntity);
    }

    public void deleteHall(Long id){
        hallRepository.findById(id)
                .orElseThrow(()->new HallNotFoundException("Hall not found"));
        hallRepository.deleteById(id);
    }
}
