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
        System.out.println("avg =" + UpsStatusUtils.avg(list));
        System.out.println("max = " +UpsStatusUtils.max(list));
        System.out.println("values = " + UpsStatusUtils.values(list));
    }
}

/*
avg =412712.827696618
max = 241
values = [192.168.10.8, 192.168.11.9]
 */