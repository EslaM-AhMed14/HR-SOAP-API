package com.example.persistence.dto;


import com.example.persistence.enums.PerformanceReating;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Data
@XmlRootElement
public class PerformancReviewDto  implements Serializable {

    private Integer reviewId;
    private String reviwerName;
    private Integer reviwerId;
    private Date reviewDate;
    private PerformanceReating performanceRating;





}
