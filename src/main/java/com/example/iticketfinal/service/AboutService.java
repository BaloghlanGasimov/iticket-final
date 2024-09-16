package com.example.iticketfinal.service;

import com.example.iticketfinal.dao.entity.AboutEntity;
import com.example.iticketfinal.dao.repository.AboutRepository;
import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.about.AboutDto;
import com.example.iticketfinal.enums.Exceptions;
import com.example.iticketfinal.exceptions.NotFoundException;
import com.example.iticketfinal.mapper.CommonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AboutService {

    private final CommonMapper commonMapper;
    private final AboutRepository aboutRepository;

    public void saveAbout(AboutDto aboutDto) {
        log.info("ActionLog.saveAbout.start");

        AboutEntity aboutEntity = commonMapper.mapToEntity(aboutDto);
        aboutRepository.save(aboutEntity);

        log.info("ActionLog.saveAbout.end");
    }

    public BaseResponseDto<List<AboutDto>> getAbouts() {
        log.info("ActionLog.getAbouts.start");

        List<AboutEntity> aboutEntities = aboutRepository.findAll();
        List<AboutDto> aboutDtos = aboutEntities.stream().map(commonMapper::mapToDto).toList();

        log.info("ActionLog.getAbouts.end");
        return BaseResponseDto.success(aboutDtos);
    }

    public BaseResponseDto<AboutDto> getAboutById(Long id) {
        log.info("ActionLog.getAboutById.start id: {}", id);

        AboutEntity aboutEntity = findAbout(id);
        AboutDto aboutDto = commonMapper.mapToDto(aboutEntity);

        log.info("ActionLog.getAboutById.end id: {}", id);
        return BaseResponseDto.success(aboutDto);
    }

    public BaseResponseDto<AboutDto> deleteAbout(Long id) {
        log.info("ActionLog.deleteAbout.start id: {}", id);

        AboutEntity aboutEntity = findAbout(id);
        aboutRepository.delete(aboutEntity);
        AboutDto aboutDto = commonMapper.mapToDto(aboutEntity);

        log.info("ActionLog.deleteAbout.end id: {}", id);
        return BaseResponseDto.success(aboutDto);
    }

    public BaseResponseDto<AboutDto> editAbout(Long id, AboutDto aboutDto) {
        log.info("ActionLog.editAbout.start id: {}, aboutDto: {}", id, aboutDto);

        AboutEntity aboutEntity = findAbout(id);
        if (aboutDto.getTitle() != null) {
            aboutEntity.setTitle(aboutDto.getTitle());
        }
        if (aboutDto.getDescription() != null) {
            aboutEntity.setDescription(aboutDto.getDescription());
        }
        aboutRepository.save(aboutEntity);
        AboutDto returnedDto = commonMapper.mapToDto(aboutEntity);
        log.info("ActionLog.editAbout.end id: {}, aboutDto: {}", id, aboutDto);
        return BaseResponseDto.success(returnedDto);
    }

    private AboutEntity findAbout(Long id) {
        AboutEntity aboutEntity = aboutRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        Exceptions.ABOUT_NOT_FOUND.toString(),
                        String.format("ActionLog.findAbout.error id: %d", id)
                ));
        return aboutEntity;
    }

}
