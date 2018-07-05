package main;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

import java.util.HashMap;
import java.util.Map;

class ParseStringRequest {

    private static SerialRequest SRq = new SerialRequest();

    static Multimap<String, Integer> ParsedReq = ArrayListMultimap.create();

    static void InitializeMap() {
        ParsedReq.clear();
        ParsedReq.put("id", 0);
        ParsedReq.put("fcn", 0);
        ParsedReq.put("add", 0);
        ParsedReq.put("value", 0);
        ParsedReq.put("amounts", 0);
        ParsedReq.put("status", 0);
        ParsedReq.put("bytes", 0);
        ParsedReq.put("bit", 0);
        ParsedReq.put("values", 0);
        ParsedReq.put("attempt", 0);
        ParsedReq.put("ReqPos", 0);
        ParsedReq.put("LastAt", 0);
        ParsedReq.put("Error", 0);
        ParsedReq.put("InstTracker", 0);
        ParsedReq.put("line", 0);


        return;

    }

    private final static Map<String, String[]> ReqFcnCode = new HashMap<>();

    static {
        ReqFcnCode.put("1", new String[]{"add", "amounts", "end"});
        ReqFcnCode.put("2", new String[]{"add", "amounts", "end"});
        ReqFcnCode.put("3", new String[]{"add", "amounts", "end"});
        ReqFcnCode.put("4", new String[]{"add", "amounts", "end"});
        ReqFcnCode.put("5", new String[]{"add", "status", "end"});
        ReqFcnCode.put("6", new String[]{"add", "value", "end"});
        ReqFcnCode.put("15", new String[]{"add", "amounts", "bytes", "bit", "end"});
        ReqFcnCode.put("16", new String[]{"add", "amounts", "bytes", "values", "end"});
    }

    static void ClrError(){
        ParsedReq.removeAll("Error");
        ParsedReq.put("Error", 0);
    }

    static void IncLine(int n){
        ParsedReq.removeAll("line");
        ParsedReq.put("line", n);
    }

    static void IncAtmpt(){
        int tmp = Iterables.getLast(ParsedReq.get("attempt"));
        ParsedReq.removeAll("attempt");
        ParsedReq.put("attempt", tmp +1);
    }

    static void TrkrWatch(int t){
        ParsedReq.removeAll("InstTracker");
        ParsedReq.put("InstTracker", t);
    }

    static void TrkrRst(){
        ParsedReq.removeAll("InstTracker");
        ParsedReq.put("InstTracker", 0);
    }

    public static Multimap<String, Integer> ParseStringRequest(String[] array, int i) {
        boolean KeepGoing = true;

        int ReqPos = 0;

        //LastAt 0 == start at ID
        //LastAt 1 == Start at Function Code
        //LastAt 2 == Start at ReqFcnCode map

        if (Iterables.getLast(ParsedReq.get("LastAt")).equals(0)) {
            try {
                if (Iterables.getLast(ParsedReq.get("attempt")).equals(0)) {
                    if(!SerialRequest.BegLog)
                        i++;
                    if (i > (array.length - 1)) {
                        ParsedReq.put("Error", 1);
                        IncAtmpt();
                        return ParsedReq;
                    }
                }
                ParsedReq.put("id", Integer.parseInt(array[i], 16));
                ParsedReq.put("LastAt", 1);
                i++;
                ParsedReq.put("fcn", Integer.parseInt(array[i], 16));
                ParsedReq.removeAll("LastAt");
                ParsedReq.put("LastAt", 2);
                i++;
            } catch (ArrayIndexOutOfBoundsException e) {
                ParsedReq.put("Error", 1);
                IncAtmpt();
                SerialRequest.ArrPos = i;

                return ParsedReq;
            }
        } else if (Iterables.getLast(ParsedReq.get("LastAt")) == 1) {
            ParsedReq.put("fcn", Integer.parseInt(array[i], 16));
            i++;
            ParsedReq.put("LastAt", 2);
        } else if (Iterables.getLast(ParsedReq.get("LastAt")) == 2) {
            ReqPos = Iterables.getLast(ParsedReq.get("ReqPos"));
        }

        //Start parsing
        String id = String.valueOf(Iterables.getLast(ParsedReq.get("fcn")));
        String[] FcnCodeSteps = ReqFcnCode.get(id);
        while (KeepGoing) {

            int tracker = 0;
            ParsedReq.removeAll("Error"); // clear out the Error for new loop
            try {
                //**********************************************************************************************
                //
                //Location in memory for request
                //Function Code(s): 01, 02, 03, 04, 05, 06, 15, 16
                //
                //**********************************************************************************************
                switch (FcnCodeSteps[ReqPos]) {

                    case "add":
                        tracker = 2;

                        if (Iterables.getLast(ParsedReq.get("InstTracker")) != 0) {

                            tracker = Iterables.getLast(ParsedReq.get("InstTracker"));
                            ParsedReq.removeAll("InstTracker");

                        }
                        while (tracker > 0) {

                            String tmp = Integer.toHexString(Iterables.getLast(ParsedReq.get("add")));
                            tmp = tmp + array[i];
                            tracker--;
                            ParsedReq.put("add", Integer.parseInt(tmp, 16));
                            i++;

                            TrkrWatch(tracker);
                        }
                        TrkrRst();
                        break;
                    //**********************************************************************************************
                    //
                    //Total number of changes (type depends on function code)
                    //Function Code(s): 01, 02, 03, 04, 15, 16
                    //
                    //**********************************************************************************************
                    case "amounts":
                        tracker = 2;

                        if (!Iterables.getLast(ParsedReq.get("InstTracker")).equals(0)) {
                            tracker = Iterables.getLast(ParsedReq.get("InstTracker"));
                        }
                        while (tracker > 0) {

                            String tmp = Integer.toHexString(Iterables.getLast(ParsedReq.get("amounts")));
                            tmp = tmp + array[i];
                            tracker--;
                            ParsedReq.put("amounts", Integer.parseInt(tmp, 16));
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

                        if (!Iterables.getLast(ParsedReq.get("InstTracker")).equals(0)) {
                            tracker = Iterables.getLast(ParsedReq.get("InstTracker"));
                        }
                        while (tracker > 0) {
                            String tmp = Integer.toHexString(Iterables.getLast(ParsedReq.get("status")));
                            tmp = tmp + array[i];
                            tracker--;
                            ParsedReq.put("status", Integer.parseInt(tmp, 16));
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

                        if (Iterables.getLast(ParsedReq.get("InstTracker")) != 0) {
                            tracker = Iterables.getLast(ParsedReq.get("InstTracker"));
                        }
                        while (tracker > 0) {

                            String tmp = Integer.toHexString(Iterables.getLast(ParsedReq.get("value")));
                            tmp = tmp + array[i];
                            tracker--;
                            ParsedReq.put("value", Integer.parseInt(tmp, 16));
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
                        ParsedReq.removeAll("bytes");
                        ParsedReq.put("bytes", Integer.parseInt(array[i], 16));
                        i++;

                        break;
                    //**********************************************************************************************
                    //
                    //Bit values for each byte
                    //Fcn Code(s): 15
                    //
                    //**********************************************************************************************
                    case "bit":

                        tracker = Iterables.getLast(ParsedReq.get("InstTracker"));


                        if (Iterables.getLast(ParsedReq.get("InstTracker")).equals(0)) {
                            tracker = Iterables.getLast(ParsedReq.get("bytes"));
                        }

                        while (tracker > 0) {
                            ParsedReq.put("bit", Integer.parseInt(array[i]));
                            tracker--;
                            i++;
                            TrkrWatch(tracker);
                        }
                        TrkrRst();
                        break;

                    //**********************************************************************************************
                    //
                    //Values to be written per register
                    //Fcn Code(s): 15, 16
                    //
                    //**********************************************************************************************
                    case "values":

                        tracker = Iterables.getLast(ParsedReq.get("bytes")) / 2;

                        if (!(Iterables.getLast(ParsedReq.get("InstTracker")).equals(Iterables.getLast(ParsedReq.get("bytes")) / 2)) && !Iterables.getLast(ParsedReq.get("InstTracker")).equals(0)) {
                            tracker = Iterables.getLast(ParsedReq.get("InstTracker"));
                        }
                        while (tracker > 0) {
                            String tmp = array[i];
                            i++;
                            tmp = tmp + array[i];
                            tracker--;
                            i++;
                            ParsedReq.put("values", Integer.parseInt(tmp, 16));

                            //Clear Key's collection to ensure no mixup.
                            TrkrWatch(tracker);
                        }
                        TrkrRst();
                        break;

                    case "end":
                        KeepGoing = false;
                        SerialRequest.FinishedReq = true;
                        ParsedReq.put("Error", 0);
                        SerialRequest.ArrPos = i;
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                ParsedReq.put("Error", 1);
                ParsedReq.put("ReqPos", ReqPos);
                TrkrWatch(tracker);
                IncAtmpt();
                SerialRequest.ArrPos = i;
                return ParsedReq;

            }
            ReqPos++;

        }

        return ParsedReq;


    }
}
