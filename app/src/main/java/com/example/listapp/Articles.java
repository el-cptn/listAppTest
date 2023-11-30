package com.example.listapp;

public class Articles {
    private String image;
    private String link;
    private String title;
    private String date;
    private String annotation;
    private int page;

    public Articles(String image, String link, String title, String date, String annotation, int page) {
        this.image = image;
        this.link = link;
        this.title = title;
        this.date = date;
        this.annotation = annotation;
        this.page = page;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "image='" + image + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", annotation='" + annotation + '\'' +
                ", page=" + page +
                '}';
    }
}
