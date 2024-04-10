package com.example.persistence.DTOs;

import com.example.persistence.entities.Employee;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@XmlRootElement
public class DepartmentDto implements Serializable {
    private Integer departmentId;
    private String departmentName;
    private String managerName;
    private Integer managerId;
    private String managerJobTitle;
    private Boolean isHead;


}
