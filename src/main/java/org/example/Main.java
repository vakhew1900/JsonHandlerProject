package org.example;

import json.handler.UpsStatus;
import json.handler.UpsStatusParser;
import json.handler.UpsStatusUtils;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

//        String fileName = "E:\\JsonHandlerProject\\src\\test\\example\\testData.json";
//        List<UpsStatus> list = new UpsStatusParser().parse(fileName);
//        System.out.println(list.size());
//        System.out.println("avg =" + UpsStatusUtils.avg(list));
//        System.out.println("max = " +UpsStatusUtils.max(list));
//        System.out.println("values = " + UpsStatusUtils.values(list));

        try {
            checkArgs(args);
            List<UpsStatus> list = new UpsStatusParser().parse(args[1]);
            makeOperation(args[0], list);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void checkArgs(String[] args) {

        if (args.length != 2) {
            throw new RuntimeException("program should have 2 arguments: <functionName> <fileName>");
        }

        boolean isExistsFuntions = true;

        if (UpsStatusUtils.VALUES.equals(args[0])) {
            isExistsFuntions = false;
        }

        if (UpsStatusUtils.MAX.equals(args[0])) {
            isExistsFuntions = false;
        }

        if (UpsStatusUtils.AVG.equals(args[0])) {
            isExistsFuntions = false;
        }

        if (!isExistsFuntions) {
            throw new IllegalArgumentException("no function with name \"" + args[0] + "\"");
        }
    }

    public static void makeOperation(String functionName, List<UpsStatus> upsStatuses) {

        String result = "result: ";
        switch (functionName){
            case UpsStatusUtils.AVG:
                double avg = UpsStatusUtils.avg(upsStatuses);
                result += avg;
                break;
            case UpsStatusUtils.MAX:
                int max = UpsStatusUtils.max(upsStatuses);
                result += max;
                break;
            case UpsStatusUtils.VALUES:
                Set<String> values = UpsStatusUtils.values(upsStatuses);
                result += values;
                break;
        }

        System.out.println(result);
    }
}

/*
avg =412712.827696618
max = 241
values = [192.168.10.8, 192.168.11.9]
 */