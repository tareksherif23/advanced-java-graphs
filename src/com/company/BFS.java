package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BFS {

    Queue<WebSite> queue;

    public BFS(){
        queue = new LinkedList<>();
    }

    // test with www.bbc.com
    public void discoverWeb(WebSite root){
        root.visit();
        queue.add(root);
        while (!queue.isEmpty()) {
            WebSite actSite = queue.remove();
            actSite.visit();
            String rawHTML = readURL(actSite.getUrl());
            String regexp = "https://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(rawHTML);
            while (matcher.find()) {
                String w = matcher.group();
                actSite = new WebSite(w);
                if(!hasWebsite(queue,actSite)) {
                    if (!actSite.isVisited()) {
                        queue.add(actSite);
                        System.out.println("Website found: " + actSite.getUrl());
                    }
                }
            }
        }
    }

    private String readURL(String u) {
        StringBuilder rawHTML = new StringBuilder("");
        try {
            URL url = new URL(u);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";
            while ((line=reader.readLine())!=null){
                rawHTML.append(line);
            }
            reader.close();
        }
        catch (Exception e) {
            System.out.println("Exception caught while reading HTML content: "+e);
        }
        return rawHTML.toString();
    }

    private boolean hasWebsite(Queue<WebSite> q, WebSite site){
        for(WebSite w: q){
            if(w.getUrl().equals(site.getUrl())) return true;
        }
        return false;
    }
}
