package com.example.part3.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class HistorySmoke implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String ip;
    private String location;
    private String value;
    @Column(name = "`localTime`")
    @Temporal(TemporalType.TIME)
    private Date localTime;
    @Temporal(TemporalType.DATE)
    private Date localDate;


    public HistorySmoke(){}

    public HistorySmoke (String ip, String location, String value, Date localTime,  Date localDate){
        this.ip = ip;
        this.location = location;
        this.value = value;
        this.localTime = localTime;
        this.localDate = localDate;

    }

    public Date getLocalDate (){
        return localDate;
    }

    public void setLocalDate (Date localDate ){
        this.localDate = localDate;
    }

    public Date getLocalTime (){
        return localTime;
    }

    public void setLocalTime ( Date localTime ){
        this.localTime = localTime;
    }

    public Integer getId (){
        return id;
    }

    public void setId ( Integer id ){
        this.id = id;
    }

    public String getIp (){
        return ip;
    }

    public void setIp ( String ip ){
        this.ip = ip;
    }

    public String getLocation (){
        return location;
    }

    public void setLocation ( String location ){
        this.location = location;
    }

    public String getValue (){
        return value;
    }

    public void setValue ( String value ){
        this.value = value;
    }

}

