package com.example.shubhamr.newser.ModelClass;

public class News {

    private String author;
    private String title;
    private String description;
    private String URL;
    private String imageURL;
    private String time;

    public News(){}

    public News(String author,String title,String description,String URL,String imageURL,String time){
        this.author=author;
        this.title=title;
        this.description=description;
        this.URL=URL;
        this.imageURL=imageURL;
        this.time=time;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getURL() {
        return URL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getTime() {
        return time;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
