package com.akash.webApp.Model.AlertModels;


public class AlertHeading {

    private String title;
    private String link;
    private String pubDate;

    public AlertHeading(String title, String link, String pubDate){
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getPubDate() {
        return this.pubDate;
    }

   
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return  "title -> "+ title +"\n" + "link -> "+ link + "\n"+ "rubDate -> " + pubDate;
    }

}
