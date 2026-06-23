package com.spo.core_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Table(name = "activities")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Activity extends GlobalRecord {
    private String activityId;
    private String comment;
    @ManyToOne
    private User user;
}
