package ir.hamyiar.newstb.dao;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "news")
@Data
public class News {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "pre_id")
    private String preId;
    @Column(name = "site_title")
    private String siteTitle;
    @Column(name = "site_address")
    private String siteAddress;
    @Column(name = "news_title")
    private String newsTitle;
    @Column(name = "news_link")
    private String newsLink;
    @Column(name = "news_description")
    private String newsDescription;
    @Column(name = "publish_date")
    private Date publishDate;
}
