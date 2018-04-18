package com.blacksite.clocker.model.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by p.faraji on 4/18/2018.
 */

@Entity(nameInDb = "face")
public class FaceDB {
    @Id
    private Long id;

    @Property(nameInDb = "image")
    private Integer image;


    @Generated(hash = 1905679901)
    public FaceDB(Long id, Integer image) {
        this.id = id;
        this.image = image;
    }

    @Generated(hash = 1258664545)
    public FaceDB() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getImage() {
        return this.image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
