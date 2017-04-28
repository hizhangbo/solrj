package main.shxh.domain;

import org.apache.solr.client.solrj.beans.Field;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangbo on 2017/4/13.
 */
public class Spxx {
    @Field("zz")
    private String zz;
    @Field("bc")
    private String bc;
    @Field("dj")
    private Double dj;
    @Field("spxxid")
    private String spxxid;
    @Field("isbn")
    private String isbn;
    @Field("cbn")
    private Short cbn;
    @Field("cbsid")
    private String cbsid;
    @Field("len")
    private Integer len;
    @Field("sdhrq")
    private Date sdhrq;
    @Field("cbny")
    private Integer cbny;
    @Field("id")
    private Long id;
    @Field("csm")
    private String csm;
    @Field("pm")
    private String pm;

    public String getZz() {
        return zz;
    }

    public void setZz(String zz) {
        this.zz = zz;
    }

    public String getBc() {
        return bc;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    public Double getDj() {
        return dj;
    }

    public void setDj(Double dj) {
        this.dj = dj;
    }

    public String getSpxxid() {
        return spxxid;
    }

    public void setSpxxid(String spxxid) {
        this.spxxid = spxxid;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Short getCbn() {
        return cbn;
    }

    public void setCbn(Short cbn) {
        this.cbn = cbn;
    }

    public String getCbsid() {
        return cbsid;
    }

    public void setCbsid(String cbsid) {
        this.cbsid = cbsid;
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        this.len = len;
    }

    public String getSdhrq() {
        return sdhrq != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(sdhrq) : "";
    }

    public void setSdhrq(Date sdhrq) {
        this.sdhrq = sdhrq;
    }

    public Integer getCbny() {
        return cbny;
    }

    public void setCbny(Integer cbny) {
        this.cbny = cbny;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCsm() {
        return csm;
    }

    public void setCsm(String csm) {
        this.csm = csm;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }
}
