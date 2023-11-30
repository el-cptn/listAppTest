package com.example.listapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Thread thread;
    private Runnable runnable;
    private Document document;
    public static int num;
    public static String ogo;
    public static ArrayList<Articles> allNewsList = new ArrayList<>();


    public String l_image;
    public String l_link;
    public String l_title;
    public String l_date;
    public String l_annotation;
    public int l_page;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        init();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Articles pl = new Articles(l_image,l_link,l_title,l_date,l_annotation,l_page);
        //articles.setTitle(String.valueOf(title));
        //allNewsList.add(pl);

        textView = findViewById(R.id.tv_news);
        String value = allNewsList.toString();


        textView.setText("tut: "+l_title);
    }
    private void init(){
        runnable = new Runnable() {
            @Override
            public void run() {
                Parser parser = new Parser();
                parser.getAllNews(1);

            }
        };
        l_title = Parser.allNewsList.get(0).getTitle();
        thread = new Thread(runnable);
        thread.start();

    }

    private void web(int page){

        try {

            document = Jsoup.connect("https://college-ural.ru/life/news/?PAGEN_1=" + page).get();

            num = document.getElementsByClass("row mb-20").size();

        Element tt = document.getElementsByTag("h5").get(0);
        ogo = String.valueOf(tt);
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

            //Articles pl = new Articles("1","2","3","4","5",6);
            //articles.setTitle(String.valueOf(title));
            // allNewsList.add(pl);

           // System.out.println(allNewsList);
            l_image = "1";
            l_link = "2";
           l_title ="3";
           l_date ="4";
           l_annotation = "5";
           l_page = 6;

        }
       System.out.println("OKAY"+allNewsList.toString());

    } catch (IOException e){
        e.printStackTrace();
    }

}}