package com.demo.showcase.common.data;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import java.sql.Types;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

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
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] image;

}
