package com.blacksite.clocker.model.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by p.faraji on 4/19/2018.
 */
@Entity(nameInDb = "dial")
public class DialDB {
    @Id
    private Long id;

    @Property(nameInDb = "image")
    private Integer image;

    @Generated(hash = 1918107474)
    public DialDB(Long id, Integer image) {
        this.id = id;
        this.image = image;
    }

    @Generated(hash = 979602763)
    public DialDB() {
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
