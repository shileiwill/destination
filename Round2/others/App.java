package com.stripe.interview;

import java.io.*;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.jsoniter.output.JsonStream;
import com.jsoniter.spi.Config;
import okhttp3.*;

public class App 
{
    private static final MediaType MEDIA_TYPE
            = MediaType.get("application/json; charset=utf-8");
    private static final String URL = "https://stripe-bikemap.appspot.com/map.png";
    private static final String JSON = "{\n" +
            "  \"center\": {\n" +
            "    \"lat\": 47.579,\n" +
            "    \"lon\": -122.31\n" +
            "  },\n" +
            "  \"width\": 400,\n" +
            "  \"height\": 600,\n" +
            "  \"zoom\": 13\n" +
            "}";

    public static void main( String[] args ) throws IOException {
//        handle_json();
        readJson();
//        sendRequest();
    }

    private static void readJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        StaticMap staticMap = objectMapper.readValue(JSON, StaticMap.class);

        Ride ride = objectMapper.readValue(new File("ride-simple.json"), Ride.class);
        List<List<Double>> coordinates = ride.features.get(0).geometry.coordinates;
        for (int i = 0; i < 10; i++) {
            List<Double> coordinate = coordinates.get(i);
            if (i == 0) {
                StaticMap.Marker start = new StaticMap.Marker();
                start.color = "white";
                start.label = "Start";
                StaticMap.Coordinate coord = new StaticMap.Coordinate();
                coord.lat = coordinate.get(1);
                coord.lon = coordinate.get(0);
                start.coord = coord;

                List<StaticMap.Marker> markers = new ArrayList<>();
                markers.add(start);
                staticMap.markers = markers;
            }

//            System.out.println(coordinate.get(0) + ", " + coordinate.get(1));
        }


        String staticMapString = objectMapper.writeValueAsString(staticMap);
        sendRequest(staticMapString);
    }

    private static void sendRequest(String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, MEDIA_TYPE);
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();

        saveToPng(response.body().bytes());
    }



    private static void saveToPng(byte[] bytes) throws IOException {
        OutputStream outputStream = new FileOutputStream(new File("bikemap2.png"));
        outputStream.write(bytes);
        outputStream.flush();
    }

    // See 1-json-primer.md for an explanation
    public static void handle_json()
    {
        String json_text = "{\"foo\": {\"bar\": [{\"paint\": \"red\"}, {\"paint\": \"green\"}, {\"paint\": \"blue\"}]}}";
        Any data = JsonIterator.deserialize(json_text);

        // Use toString(), toDouble(), toInt(), etc with types
        String demoLetter = data.toString("foo", "bar", 1, "paint");
        System.out.printf("toString: %s\n", demoLetter); // "green"

        // Or use get() and Any
        Any demoLetter2 = data.get("foo", "bar", 1, "paint");
        System.out.printf("Any: %s\n", demoLetter2); // "green"

        // Can also use Map.of for homogenous key-value pairs in Java 9+
        Map<String, Object> quux = new HashMap<>();
        quux.put("stuff", "nonsense");
        quux.put("nums", Arrays.asList(2.718, 3.142));

        // data["foo"]["quux"] = {"stuff": "nonsense", "nums": [2.718, 3.142]}
        data.get("foo").asMap().put("quux", Any.wrap(quux));

        // Use Config to get pretty-printed indentation
        Config cfg = new Config.Builder().indentionStep(2).build();

        // serialize object to string as json
        String result = JsonStream.serialize(cfg, data);
        System.out.println(result);
    }
}
