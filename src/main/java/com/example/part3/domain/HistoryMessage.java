package com.example.part3.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class HistoryMessage implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String text;
    private String tag;
    private String ind;
    @Column(name = "`localTime`")
    @Temporal(TemporalType.TIME)
    private Date localTime;
    @Temporal(TemporalType.DATE)
    private Date localDate;

    public HistoryMessage() {
    }

    public HistoryMessage(Integer id, String text, String tag, String ind, Date localDate, Date localTime) {
        this.id = id;
        this.text = text;
        this.tag = tag;
        this.ind = ind;
        this.localDate = localDate;
        this.localTime = localTime;
    }

    public Date getLocalTime (){
        return localTime;
    }

    public void setLocalTime ( Date localTime ){
        this.localTime = localTime;
    }

    public Date getLocalDate (){
        return localDate;
    }

    public void setLocalDate ( Date localDate ){
        this.localDate = localDate;
    }

    public String getInd (){
        return ind;
    }

    public void setInd ( String ind ){
        this.ind = ind;
    }

    public void setText( String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}

