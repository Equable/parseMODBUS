package main;

import com.google.common.collect.Iterables;

public class PrintReq {
    protected final static void ReqPrint() {
        ParseStringRequest PSR = new ParseStringRequest();
        int size;


        switch (Iterables.getLast(ParseStringRequest.ParsedReq.get("fcn"))) {
            default:
                parseUI.data.add(new SerialData(
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("line")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("id")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("fcn")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("add")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("amounts")),
                        null,
                        null,
                        null,
                        null,
                        null
                ));
                break;

            case 5:
                String Status;
                if (Iterables.getLast(ParseStringRequest.ParsedReq.get("status")).equals(0)) {
                    Status = "OFF";
                } else {
                    Status = "ON";
                }

                parseUI.data.add(new SerialData(
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("line")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("id")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("fcn")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("add")),
                        null,
                        Status,
                        null,
                        null,
                        null,
                        null
                ));
                break;

            case 6:
                parseUI.data.add(new SerialData(
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("line")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("id")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("fcn")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("add")),
                        null,
                        null,
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("value")),
                        null,
                        null,
                        null
                ));
                break;

            case 15:
                parseUI.data.add(new SerialData(
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("line")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("id")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("fcn")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("add")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("amounts")),
                        null,
                        null,
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("bytes")),
                        String.format("%8s", Integer.toBinaryString(Iterables.get(ParseStringRequest.ParsedReq.get("bit"), 1))).replace(" ", "0"),
                        null
                ));

                size = ParseStringRequest.ParsedReq.get("bit").size();
                for (int i = 2; i < size; i++) {
                    parseUI.data.add(new SerialData(
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            String.format("%8s", Integer.toBinaryString(Iterables.get(ParseStringRequest.ParsedReq.get("bit"), i))).replace(" ", "0"),
                            null
                    ));
                }
                break;

            case 16:
                parseUI.data.add(new SerialData(
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("line")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("id")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("fcn")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("add")),
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("amounts")),
                        null,
                        null,
                        Iterables.getLast(ParseStringRequest.ParsedReq.get("bytes")),
                        null,
                        Iterables.get(ParseStringRequest.ParsedReq.get("values"), 1)
                ));

                size = ParseStringRequest.ParsedReq.get("values").size();
                for (int i = 2; i < size; i++) {
                    parseUI.data.add(new SerialData(
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            Iterables.get(ParseStringRequest.ParsedReq.get("values"), i)
                    ));
                }
                break;

        }

    }
}
