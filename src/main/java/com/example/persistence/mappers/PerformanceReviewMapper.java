package com.example.persistence.mappers;

import com.example.persistence.DTOs.PerformancReviewDto;
import com.example.persistence.entities.PerformanceReview;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface PerformanceReviewMapper {
    PerformanceReviewMapper INSTANCE = Mappers.getMapper(PerformanceReviewMapper.class);


    PerformancReviewDto performanceReviewToPerformanceReviewDto(PerformanceReview performanceReview);


    PerformanceReview performanceReviewDtoToPerformanceReview(PerformancReviewDto performanceReviewDto);

    List<PerformanceReview> toPerformanceReviewList(List<PerformancReviewDto> performanceReviewDtos);

    List<PerformancReviewDto> toPerformanceReviewDtoList(List<PerformanceReview> performanceReviews);
}
