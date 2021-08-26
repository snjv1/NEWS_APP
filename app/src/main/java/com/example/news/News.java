package com.example.news;

public class News {

    private  String title;

    private  String author;

    private  String url;

    private  String imageUrl ;



    public News(String title, String author, String url, String imageUrl) {
        this.title = title;
        this.author = author;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }


}
