package com.spo.core_app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="roles")
@Entity
public class Role extends GlobalRecord{
    private String roleId;
    @Column(unique = true)
    private String roleName; // INFOSYS_, TCS_, SERVICENOW_
    @ManyToMany
    private List<Operation> operations;
}
