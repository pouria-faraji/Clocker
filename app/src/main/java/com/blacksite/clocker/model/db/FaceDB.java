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

    @Property(nameInDb = "image_white")
    private Integer imageWhite;



    @Generated(hash = 1721146414)
    public FaceDB(Long id, Integer image, Integer imageWhite) {
        this.id = id;
        this.image = image;
        this.imageWhite = imageWhite;
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

    public Integer getImageWhite() {
        return this.imageWhite;
    }

    public void setImageWhite(Integer imageWhite) {
        this.imageWhite = imageWhite;
    }
}
