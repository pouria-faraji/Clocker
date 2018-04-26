package com.blacksite.clocker.model.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by p.faraji on 4/24/2018.
 */
@Entity(nameInDb = "hand")
public class HandDB {
    @Id
    private Long id;

    @Property(nameInDb = "analog_clock")
    private Integer analogClock;

    @Property(nameInDb = "analog_clock_widget")
    private Integer analogClockWidget;

    @Property(nameInDb = "image")
    private Integer image;

    @Property(nameInDb = "number")
    private Integer number;







    @Generated(hash = 891784439)
    public HandDB(Long id, Integer analogClock, Integer analogClockWidget,
            Integer image, Integer number) {
        this.id = id;
        this.analogClock = analogClock;
        this.analogClockWidget = analogClockWidget;
        this.image = image;
        this.number = number;
    }
    @Generated(hash = 971599506)
    public HandDB() {
    }






    
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getAnalogClock() {
        return this.analogClock;
    }
    public void setAnalogClock(Integer analogClock) {
        this.analogClock = analogClock;
    }
    public Integer getImage() {
        return this.image;
    }
    public void setImage(Integer image) {
        this.image = image;
    }
    public Integer getAnalogClockWidget() {
        return this.analogClockWidget;
    }
    public void setAnalogClockWidget(Integer analogClockWidget) {
        this.analogClockWidget = analogClockWidget;
    }
    public Integer getNumber() {
        return this.number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
}
