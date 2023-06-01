package com.demo.showcase.common.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import java.sql.Types;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@Table(name = "shows_pics")
public class ShowsPicsEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "show_id")
    private UUID showId;

    @Column(name="image_content_type")
    private String imageContentType;

    @Lob
    @Column(name = "image")
    @JdbcTypeCode(Types.VARBINARY)
    private byte[] image;

}
