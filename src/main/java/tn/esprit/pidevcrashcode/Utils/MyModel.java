package tn.esprit.pidevcrashcode.Utils;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.*;

public class MyModel {
    public static void main(String[] args) {
        float[] longi = {10.395396f, 10.401396f, 10.405032f, 10.395032f, 10.396921f};
        List<Float> longList = new ArrayList<Float>();
        for (float f : longi) {
            longList.add(f);
        }
        System.out.println(longList);
    }
}

