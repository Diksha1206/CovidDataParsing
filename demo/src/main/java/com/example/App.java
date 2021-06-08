package com.example;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App 
{

    static class SortByTotalRecovered implements Comparator<JSONObject> {

        @Override
        public int compare(JSONObject a, JSONObject b) {
            int trA = (int)a.get("TotalRecovered");
            int trB = (int)b.get("TotalRecovered");
            if(trA < trB) return -1;
            if(trA > trB) return 1;
            return 0;
        }
        
    }

    static class SortCountry implements Comparator<JSONObject>{

        @Override
        public int compare(JSONObject a, JSONObject b) {

            String CountryA = (String)a.get("Country");
            String CountryB = (String)b.get("Country");
           return CountryA.compareTo(CountryB);
        }

    }


    static class SortByCountryAndTotalRecovered implements Comparator<JSONObject> {
        // https://www.geeksforgeeks.org/comparable-vs-comparator-in-java/
        @Override
        public int compare(JSONObject a, JSONObject b) {
        
            String CountryA = (String)a.get("Country");
            String CountryB = (String)b.get("Country");
            int comp = CountryA.compareTo(CountryB);
            if(comp != 0) {
                return comp;
            }

            int trA = (int)a.get("TotalRecovered");
            int trB = (int)b.get("TotalRecovered");

            if(trA < trB) return -1;
            if(trA > trB) return 1;
            return 0;
        }      
    }



    public static void main( String[] args )
    {
        List<JSONObject> jsonList = new ArrayList<>();
        String str = new String();
        try {
        URL url = new URL("https://api.covid19api.com/summary");
        Scanner scan = new Scanner(url.openStream()); 
        int count = 0;        
        while(scan.hasNext()) {
            str += scan.nextLine();
        }
            scan.close();
            System.out.println(count);
        } catch (Exception e) {
    }

    JSONObject obj = new JSONObject(str);
       // System.out.println(obj);
       // JSONObject countires = obj.get("Countries");

       int length = obj.getJSONArray("Countries").length();
       System.out.println(length);

       for(int i=0; i<length; i++) {
        jsonList.add(obj.getJSONArray("Countries").getJSONObject(i));
    }

    Collections.sort(jsonList, new SortByTotalRecovered());
        for(int i=0; i<length; i++) {
            //System.out.println(jsonList.get(i));
        }

        Collections.sort(jsonList, new SortByTotalRecovered().thenComparing(new SortCountry()));
        System.out.println(jsonList);
    }

}
