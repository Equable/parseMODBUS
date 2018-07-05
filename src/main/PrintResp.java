package main;

import com.google.common.collect.Iterables;


public class PrintResp {


    final static void RespPrint() {
        ParseStringResponse PSR = new ParseStringResponse();
        int size;
        switch (Iterables.getLast(ParseStringResponse.ParsedResp.get("fcn"))) {
            default:
                parseUI.data.add(new SerialData(
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("line")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("id")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("fcn")),
                        null,
                        null,
                        null,
                        null,
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("bytes")),
                        String.format("%8s", Integer.toBinaryString(Iterables.get(ParseStringResponse.ParsedResp.get("bit"), 1))).replace(" ", "0"),
                        null
                ));

                size = ParseStringResponse.ParsedResp.get("bit").size();
                for (int i = 2; i < size; i++) {
                    parseUI.data.add(new SerialData(
                            Iterables.getLast(ParseStringResponse.ParsedResp.get("line")),
                            Iterables.getLast(ParseStringResponse.ParsedResp.get("id")),
                            Iterables.getLast(ParseStringResponse.ParsedResp.get("fcn")),
                            null,
                            null,
                            null,
                            null,
                            null,
                            String.format("%8s", Integer.toBinaryString(Iterables.get(ParseStringResponse.ParsedResp.get("bit"), i))).replace(" ", "0"),
                            null
                    ));
                }
                break;

            case 3:
                parseUI.data.add(new SerialData(
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("line")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("id")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("fcn")),
                        null,
                        null,
                        null,
                        null,
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("bytes")),
                        null,
                        Iterables.get(ParseStringResponse.ParsedResp.get("values"), 1)
                ));

                size = ParseStringResponse.ParsedResp.get("values").size();
                for (int i = 2; i < size; i++) {
                    parseUI.data.add(new SerialData(
                            Iterables.getLast(ParseStringResponse.ParsedResp.get("line")),
                            Iterables.getLast(ParseStringResponse.ParsedResp.get("id")),
                            Iterables.getLast(ParseStringResponse.ParsedResp.get("fcn")),
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            Iterables.get(ParseStringResponse.ParsedResp.get("values"), i))
                    );
                }
                break;

            case 4:
                parseUI.data.add(new SerialData(
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("line")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("id")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("fcn")),
                        null,
                        null,
                        null,
                        null,
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("bytes")),
                        null,
                        Iterables.get(ParseStringResponse.ParsedResp.get("values"), 1)
                ));

                size = ParseStringResponse.ParsedResp.get("values").size();
                for (int i = 2; i < size; i++) {
                    parseUI.data.add(new SerialData(
                            Iterables.getLast(ParseStringResponse.ParsedResp.get("line")),
                            Iterables.getLast(ParseStringResponse.ParsedResp.get("id")),
                            Iterables.getLast(ParseStringResponse.ParsedResp.get("fcn")),
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            Iterables.get(ParseStringResponse.ParsedResp.get("values"), i))
                    );
                }
                break;

            case 5:
                String Status;

                if (Iterables.getLast(ParseStringResponse.ParsedResp.get("status")).equals(0)) {
                    Status = "OFF";
                } else {
                    Status = "ON";
                }

                parseUI.data.add(new SerialData(
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("line")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("id")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("fcn")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("add")),
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
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("line")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("id")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("fcn")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("add")),
                        null,
                        null,
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("value")),
                        null,
                        null,
                        null
                ));
                break;

            case 15:
                parseUI.data.add(new SerialData(
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("line")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("id")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("fcn")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("add")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("amounts")),
                        null,
                        null,
                        null,
                        null,
                        null
                ));
                break;

            case 16:
                parseUI.data.add(new SerialData(
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("line")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("id")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("fcn")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("add")),
                        Iterables.getLast(ParseStringResponse.ParsedResp.get("amounts")),
                        null,
                        null,
                        null,
                        null,
                        null
                ));

                break;

        }


    }
}


