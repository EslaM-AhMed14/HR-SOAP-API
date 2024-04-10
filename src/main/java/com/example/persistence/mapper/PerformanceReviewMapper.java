package com.example.persistence.mapper;

import com.example.persistence.dto.PerformancReviewDto;
import com.example.persistence.entity.PerformanceReview;
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
