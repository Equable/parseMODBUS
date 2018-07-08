package main;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

import java.util.HashMap;
import java.util.Map;

class ParseStringResponse {

    private static SerialResponse SRq = new SerialResponse();

    static Multimap<String, Integer> ParsedResp = ArrayListMultimap.create();

    static void InitializeMap() {
        ParsedResp.clear();
        ParsedResp.put("id", 0);
        ParsedResp.put("fcn", 0);
        ParsedResp.put("add", 0);
        ParsedResp.put("value", 0);
        ParsedResp.put("amounts", 0);
        ParsedResp.put("status", 0);
        ParsedResp.put("bytes", 0);
        ParsedResp.put("bit", 0);
        ParsedResp.put("values", 0);
        ParsedResp.put("attempt", 0);
        ParsedResp.put("ReqPos", 0);
        ParsedResp.put("LastAt", 0);
        ParsedResp.put("Error", 0);
        ParsedResp.put("InstTracker", 0);
        ParsedResp.put("line", 0);


        return;

    }

    private final static Map<String, String[]> RespFcnCode = new HashMap<>();

    static {
        RespFcnCode.put("1", new String[]{"bytes", "bit", "end"});
        RespFcnCode.put("2", new String[]{"bytes", "bit", "end"});
        RespFcnCode.put("3", new String[]{"bytes", "values", "end"});
        RespFcnCode.put("4", new String[]{"bytes", "values", "end"});
        RespFcnCode.put("5", new String[]{"add", "status", "end"});
        RespFcnCode.put("6", new String[]{"add", "value", "end"});
        RespFcnCode.put("15", new String[]{"add", "amounts", "end"});
        RespFcnCode.put("16", new String[]{"add", "amounts", "end"});
    }

    static void ClrError(){
        ParsedResp.removeAll("Error");
        ParsedResp.put("Error", 0);
    }

    static void IncLine(int n){
        ParsedResp.removeAll("line");
        ParsedResp.put("line", n);
    }

    static void IncAtmpt(){
        int tmp = Iterables.getLast(ParsedResp.get("attempt"));
        ParsedResp.removeAll("attempt");
        ParsedResp.put("attempt", tmp +1);
    }

    static void TrkrWatch(int t){
        ParsedResp.removeAll("InstTracker");
        ParsedResp.put("InstTracker", t);
    }

    static void TrkrRst(){
        ParsedResp.removeAll("InstTracker");
        ParsedResp.put("InstTracker", 0);
    }

    public static Multimap<String, Integer> ParseStringResponse(String[] array, int i) {
        boolean KeepGoing = true;

        int ReqPos = 0;

        //LastAt 0 == start at ID
        //LastAt 1 == Start at Function Code
        //LastAt 2 == Start at RespFcnCode map

        if (Iterables.getLast(ParsedResp.get("LastAt")).equals(0)) {
            try {
                if (Iterables.getLast(ParsedResp.get("attempt")).equals(0)) {
                    i++;
                    if (i > (array.length - 1)) {
                        ParsedResp.put("Error", 1);
                        IncAtmpt();
                        return ParsedResp;
                    }
                }
                ParsedResp.put("id", Integer.parseInt(array[i], 16));
                ParsedResp.put("LastAt", 1);
                i++;
                ParsedResp.put("fcn", Integer.parseInt(array[i], 16));
                ParsedResp.removeAll("LastAt");
                ParsedResp.put("LastAt", 2);
                i++;
            } catch (ArrayIndexOutOfBoundsException e) {
                ParsedResp.put("Error", 1);
                IncAtmpt();
                SerialResponse.ArrPos = i;

                return ParsedResp;
            }
        } else if (Iterables.getLast(ParsedResp.get("LastAt")) == 1) {
            ParsedResp.put("fcn", Integer.parseInt(array[i], 16));
            i++;
            ParsedResp.put("LastAt", 2);
        } else if (Iterables.getLast(ParsedResp.get("LastAt")) == 2) {
            ReqPos = Iterables.getLast(ParsedResp.get("ReqPos"));
        }

        //Start parsing
        String id = String.valueOf(Iterables.getLast(ParsedResp.get("fcn")));
        String[] FcnCodeSteps = RespFcnCode.get(id);
        while (KeepGoing) {

            int tracker = 0;
            ParsedResp.removeAll("Error"); // clear out the Error for new loop
            try {
                //**********************************************************************************************
                //
                //Location in memory for response
                //Function Code(s): 5, 6, 15, 16
                //
                //**********************************************************************************************
                switch (FcnCodeSteps[ReqPos]) {

                    case "add":
                        tracker = 2;

                        if (Iterables.getLast(ParsedResp.get("InstTracker")) != 0) {

                            tracker = Iterables.getLast(ParsedResp.get("InstTracker"));
                            ParsedResp.removeAll("InstTracker");

                        }
                        while (tracker > 0) {

                            String tmp = Integer.toHexString(Iterables.getLast(ParsedResp.get("add")));
                            tmp = tmp + array[i];
                            tracker--;
                            ParsedResp.put("add", Integer.parseInt(tmp, 16));
                            i++;

                            TrkrWatch(tracker);
                        }
                        TrkrRst();
                        break;
                    //**********************************************************************************************
                    //
                    //Total number of changes (type depends on function code)
                    //Function Code(s): 15, 16
                    //
                    //**********************************************************************************************
                    case "amounts":
                        tracker = 2;

                        if (!Iterables.getLast(ParsedResp.get("InstTracker")).equals(0)) {
                            tracker = Iterables.getLast(ParsedResp.get("InstTracker"));
                        }
                        while (tracker > 0) {

                            String tmp = Integer.toHexString(Iterables.getLast(ParsedResp.get("amounts")));
                            tmp = tmp + array[i];
                            tracker--;
                            ParsedResp.put("amounts", Integer.parseInt(tmp, 16));
                            i++;
                            TrkrWatch(tracker);
                        }
                        TrkrRst();
                        break;

                    //**********************************************************************************************
                    //
                    //Status to be written (Coil ON or OFF)
                    //Fcn Code(s): 05
                    //
                    //**********************************************************************************************
                    case "status":
                        tracker = 2;

                        if (!Iterables.getLast(ParsedResp.get("InstTracker")).equals(0)) {
                            tracker = Iterables.getLast(ParsedResp.get("InstTracker"));
                        }
                        while (tracker > 0) {
                            String tmp = Integer.toHexString(Iterables.getLast(ParsedResp.get("status")));
                            tmp = tmp + array[i];
                            tracker--;
                            ParsedResp.put("status", Integer.parseInt(tmp, 16));
                            i++;
                            TrkrWatch(tracker);
                        }
                        TrkrRst();
                        break;

                    //**********************************************************************************************
                    //
                    //Value to be written in the register
                    //Fcn Code(s): 06
                    //
                    //**********************************************************************************************
                    case "value":
                        tracker = 2;

                        if (Iterables.getLast(ParsedResp.get("InstTracker")) != 0) {
                            tracker = Iterables.getLast(ParsedResp.get("InstTracker"));
                        }
                        while (tracker > 0) {

                            String tmp = Integer.toHexString(Iterables.getLast(ParsedResp.get("value")));
                            tmp = tmp + array[i];
                            tracker--;
                            ParsedResp.put("value", Integer.parseInt(tmp, 16));
                            i++;
                            TrkrWatch(tracker);
                        }
                        TrkrRst();
                        break;

                    //**********************************************************************************************
                    //
                    //Number of Bytes that will be written to
                    //Fcn Code(s): 15, 16
                    //
                    //**********************************************************************************************
                    case "bytes":
                        ParsedResp.removeAll("bytes");
                        ParsedResp.put("bytes", Integer.parseInt(array[i], 16));
                        i++;

                        break;
                    //**********************************************************************************************
                    //
                    //Bit values for each byte
                    //Fcn Code(s): 1, 2, 3, 4
                    //
                    //**********************************************************************************************
                    case "bit":

                        tracker = Iterables.getLast(ParsedResp.get("InstTracker"));


                        if (Iterables.getLast(ParsedResp.get("InstTracker")).equals(0)) {
                            tracker = Iterables.getLast(ParsedResp.get("bytes"));
                        }

                        while (tracker > 0) {
                            ParsedResp.put("bit", Integer.parseInt(array[i]));
                            tracker--;
                            i++;
                            TrkrWatch(tracker);
                        }
                        TrkrRst();
                        break;

                    //**********************************************************************************************
                    //
                    //Values to be written per register
                    //Fcn Code(s): 3, 4
                    //
                    //**********************************************************************************************
                    case "values":

                        tracker = Iterables.getLast(ParsedResp.get("bytes")) / 2;

                        if (!(Iterables.getLast(ParsedResp.get("InstTracker")).equals(Iterables.getLast(ParsedResp.get("bytes")) / 2)) && !Iterables.getLast(ParsedResp.get("InstTracker")).equals(0)) {
                            tracker = Iterables.getLast(ParsedResp.get("InstTracker"));
                        }
                        while (tracker > 0) {
                            String tmp = array[i];
                            i++;
                            tmp = tmp + array[i];
                            tracker--;
                            i++;
                            ParsedResp.put("values", Integer.parseInt(tmp, 16));

                            //Clear Key's collection to ensure no mixup.
                            TrkrWatch(tracker);
                        }
                        TrkrRst();
                        break;

                    case "end":
                        SerialResponse.FinishedReq = true;
                        KeepGoing = false;
                        SerialResponse.FinishedReq = true;
                        ParsedResp.put("Error", 0);
                        SerialResponse.ArrPos = i;
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                ParsedResp.put("Error", 1);
                ParsedResp.put("ReqPos", ReqPos);
                TrkrWatch(tracker);
                IncAtmpt();
                SerialResponse.ArrPos = i;
                return ParsedResp;

            }
            ReqPos++;

        }

        return ParsedResp;


    }
}
