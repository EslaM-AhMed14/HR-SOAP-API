package com.example.persistence.mappers;

import com.example.persistence.DTOs.PerformancReviewDto;
import com.example.persistence.entities.PerformanceReview;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-10T10:11:31+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
public class PerformanceReviewMapperImpl implements PerformanceReviewMapper {

    @Override
    public PerformancReviewDto performanceReviewToPerformanceReviewDto(PerformanceReview performanceReview) {
        if ( performanceReview == null ) {
            return null;
        }

        PerformancReviewDto performancReviewDto = new PerformancReviewDto();

        performancReviewDto.setReviewId( performanceReview.getReviewId() );
        performancReviewDto.setReviwerName( performanceReview.getReviwerName() );
        performancReviewDto.setReviwerId( performanceReview.getReviwerId() );
        performancReviewDto.setReviewDate( performanceReview.getReviewDate() );
        performancReviewDto.setPerformanceRating( performanceReview.getPerformanceRating() );

        return performancReviewDto;
    }

    @Override
    public PerformanceReview performanceReviewDtoToPerformanceReview(PerformancReviewDto performanceReviewDto) {
        if ( performanceReviewDto == null ) {
            return null;
        }

        PerformanceReview performanceReview = new PerformanceReview();

        performanceReview.setReviewId( performanceReviewDto.getReviewId() );
        performanceReview.setReviewDate( performanceReviewDto.getReviewDate() );
        performanceReview.setPerformanceRating( performanceReviewDto.getPerformanceRating() );

        return performanceReview;
    }

    @Override
    public List<PerformanceReview> toPerformanceReviewList(List<PerformancReviewDto> performanceReviewDtos) {
        if ( performanceReviewDtos == null ) {
            return null;
        }

        List<PerformanceReview> list = new ArrayList<PerformanceReview>( performanceReviewDtos.size() );
        for ( PerformancReviewDto performancReviewDto : performanceReviewDtos ) {
            list.add( performanceReviewDtoToPerformanceReview( performancReviewDto ) );
        }

        return list;
    }

    @Override
    public List<PerformancReviewDto> toPerformanceReviewDtoList(List<PerformanceReview> performanceReviews) {
        if ( performanceReviews == null ) {
            return null;
        }

        List<PerformancReviewDto> list = new ArrayList<PerformancReviewDto>( performanceReviews.size() );
        for ( PerformanceReview performanceReview : performanceReviews ) {
            list.add( performanceReviewToPerformanceReviewDto( performanceReview ) );
        }

        return list;
    }
}
