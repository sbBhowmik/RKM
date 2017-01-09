package com.rupik.rkm;

/**
 * Created by macmin5 on 14/12/16.
 */

public class SaintDetails {
    String personTitle;
    String page_title;
    String page_subtitle;
    String image;
    boolean isImageURL;
    String main_content;
    String id;

    public SaintDetails (String id, String personTitle, String page_title, String page_subtitle, String image, boolean isImageURL,String main_content)
    {
        this.id = id;
        this.personTitle = personTitle;
        this.page_title = page_title;
        this.page_subtitle = page_subtitle;
        this.image = image;
        this.isImageURL = isImageURL;
        this.main_content = main_content;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getMain_content() {
        return main_content;
    }

    public String getPage_subtitle() {
        return page_subtitle;
    }

    public String getPage_title() {
        return page_title;
    }

    public String getPersonTitle() {
        return personTitle;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setMain_content(String main_content) {
        this.main_content = main_content;
    }

    public void setPage_subtitle(String page_subtitle) {
        this.page_subtitle = page_subtitle;
    }

    public void setPage_title(String page_title) {
        this.page_title = page_title;
    }

    public void setPersonTitle(String personTitle) {
        this.personTitle = personTitle;
    }
}
