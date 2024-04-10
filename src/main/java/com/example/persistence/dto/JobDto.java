package com.example.persistence.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement
public class JobDto  implements Serializable {
    private Integer jobId;

    private String departmentProviderName;
    private Integer departmentProviderId;
    private String jobName;
    private String requirements;
}
