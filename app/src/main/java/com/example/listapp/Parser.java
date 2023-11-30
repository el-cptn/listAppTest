package com.example.listapp;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Parser {
    private Document document;
    public static int num;
    public static ArrayList<Articles> allNewsList = new ArrayList<>();
    public static ArrayList<Article> specificNewsList = new ArrayList<>();

    public String getAllNews(int page){
        try {

            document = Jsoup.connect("https://college-ural.ru/life/news/?PAGEN_1=" + page).get();
        } catch (IOException e){
            e.printStackTrace();
        }
        num = document.getElementsByClass("row mb-20").size();
        //System.out.println("page:"+page+'\n'+
        //        "news:"+num);
        for (int i = 0; i < num; i++) {

            //System.out.println("id: "+i);
            Element title = document.getElementsByTag("h5").get(i);
            //System.out.println("title: " + title.text());

            Element date = document.getElementsByClass("date mt-10 mb-10 grey-500").get(i);
            //System.out.println("date: " + date.text());

            Element newsText = document.getElementsByTag("p").get(i);
            //System.out.println("text: " + newsText.text());


            Element link = document.select("div.col-md-4.col-sm-4.mb-20 > a").get(i);

            //System.out.println("link: " + link.absUrl("href"));

            Element img = document.select("div.col-md-4.col-sm-4.mb-20 > a > img").get(i);
            //System.out.println("img: " + img.absUrl("src"));

            Articles articles = new Articles(img.absUrl("src"), link.absUrl("href"), title.text(), date.text(), newsText.text(), page);
            allNewsList.add(articles);
        }
        System.out.println(allNewsList.toString());
        return allNewsList.toString();
    }
    public void getSpecificArticle(int index)  {
        try {
            document = Jsoup.connect(allNewsList.get(index).getLink()).get();
            //System.out.println(allNewsList.get(index).getLink());
        } catch (IOException e){
            e.printStackTrace();
        }
        Element title = document.getElementsByTag("h1").first();
        //System.out.println("title: "+title.text());

        Element img = document.select("#big-photo > img").first();

        //System.out.println("img: "+ img.absUrl("src"));

        // Element date = document.select("body > div.body-wrap.body-boxed > section > div > div > div > div.col-md-8 > div > div.mt-20 > p:nth-child(1)").first();
        //System.out.println("date: "+date.text());

        // тут всралась дата и просмотры
        Element content = document.getElementsByClass("post-item").last();
        //System.out.println("text: "+content.text());

        Article article = new Article(img.absUrl("src"), title.text(),content.text());
        specificNewsList.add(article);
        System.out.println(specificNewsList.toString());
    }
}
