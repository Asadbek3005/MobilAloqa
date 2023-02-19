package com.example.mobil.Entity.Abstarkat;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@MappedSuperclass
@EntityListeners(AutoCloseable.class)
public abstract class Abstrakt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreatedBy
    private Integer kimyar;
    @LastModifiedBy
    private Integer kimtax;
    @CreationTimestamp
    private Timestamp yatilvaqt;
    @UpdateTimestamp
    private Timestamp taxvaqt;
}
