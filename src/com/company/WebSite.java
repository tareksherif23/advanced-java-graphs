package com.company;

public class WebSite{

    private String url;
    private boolean visited;

    public WebSite(String url){
        this.url = url;
        this.visited = false;
    }

    public String getUrl() {
        return url;
    }

    public void visit() {
        this.visited = true;
    }

    public boolean isVisited() {
        return this.visited;
    }

}
