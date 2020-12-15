package com.poc.springbatch.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Employee {
    @Id
    private Long id;
    private String name;
    private String department;
    private Double salary;
    private LocalDateTime createDateTime;
    private LocalDateTime sendDateTime;
}
