package org.example;

import json.handler.UpsStatus;
import json.handler.UpsStatusParser;
import json.handler.UpsStatusUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        String fileName = "E:\\JsonHandlerProject\\src\\test\\example\\testData.json";
        List<UpsStatus> list = new UpsStatusParser().parse(fileName);
        System.out.println(list.size());
        System.out.println(UpsStatusUtils.avg(list));
    }
}