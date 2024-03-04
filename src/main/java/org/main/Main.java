package org.main;

import json.handler.UpsStatus;
import json.handler.UpsStatusDeserializer;
import json.handler.UpsStatusParser;
import json.handler.UpsStatusUtils;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        try {
            checkArgs(args);
            List<UpsStatus> list = new UpsStatusParser().parse(args[1]);

            if (UpsStatusDeserializer.getFieldErrorCnt() > 0) {
                System.out.println(UpsStatusDeserializer.getFieldErrorCnt() + " fields have incorrect data format");
            }

            makeOperation(args[0], list);

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void checkArgs(String[] args) {

        if (args.length != 2) {
            throw new RuntimeException("program should have 2 arguments: <functionName> <fileName>");
        }

        boolean isExistsFuntions = UpsStatusUtils.VALUES.equals(args[0]);
        isExistsFuntions = isExistsFuntions || UpsStatusUtils.MAX.equals(args[0]);
        isExistsFuntions = isExistsFuntions || UpsStatusUtils.AVG.equals(args[0]);

        if (!isExistsFuntions) {
            throw new IllegalArgumentException("no function with name \"" + args[0] + "\"");
        }
    }

    public static void makeOperation(String functionName, List<UpsStatus> upsStatuses) {

        String result = "result: ";
        switch (functionName) {
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

