package main;

import com.google.common.collect.Iterables;
import java.io.File;
import java.util.*;

@SuppressWarnings("Duplicates")
public class SerialRequest {
    static ParseSelect PS = new ParseSelect();
    private static PrintReq PR = new PrintReq();
    static boolean FinishedReq = false;
    static int ArrPos = 0;
    static boolean BegLog = false;
    static boolean FirstReq = false;
    private static ParseStringRequest PSR = new ParseStringRequest();
    private static ArrayList<String[]> Scanned = new ArrayList<String[]>();

    static boolean FirstScan = true;
    static boolean HasLine = true;
    static int LinesScanned = 0;


    private static void scan(File file, Scanner sc) {


        if (FirstScan) {
            String IntSize;
            sc.nextLine();
            IntSize = sc.nextLine();
            LinesScanned += 2;
            for (int i = 0; i < 15; i++) {
                Scanned.add(IntSize.trim().split(" +"));

            }
            FirstScan = false;
        }
        if (sc.hasNextLine()) {
            String str = sc.nextLine();
            str = str.replaceAll("[\\[\\]<>]", " ");


            if (!Scanned.contains(str.trim().split(" +"))) {
                Scanned.add(str.trim().split(" +"));
                Scanned.remove(0);
                LinesScanned ++;

            }

        } else {
            HasLine = false;
        }
    }

    public static void parse() throws Exception {
        FirstScan = true;
        Scanned.clear();
        Scanner sc;
        try {
            sc = new Scanner(ParseSelect.file);
        } catch(NullPointerException e){
            System.out.println("No File Selected");
            return;
        }

        int n = 3;
        scan(ParseSelect.file, sc);

        String[] array = Scanned.get(14);
        String[] previous = Scanned.get(13);

        // Format of prosoft text files is as follows:
        // Line 1: Blank
        // Line 2: "Log Start"
        // Line 3: (begin with request
        // purpose of the following IF is to ensure that a proper text file is selected
        // if "Log" is not present on line 2 then throw an exception to tell the user why.

        if(!previous[0].equals("Log"))
            throw new Exception("File Selected not a proper ProSoft Log file");
        while (HasLine) {

            if(previous.length > 1) {
                if (previous[1].equals("Start")) {
                    ParseStringRequest.InitializeMap();
                    ArrPos = 0;
                    BegLog = true;
                    FirstReq = false;
                    while (ArrPos < array.length && !FirstReq) {
                        FinishedReq = false;
                        ParseStringRequest.IncLine(n);
                        ParseStringRequest.ParseStringRequest(array, ArrPos);
                        while(!FinishedReq){
                            n++;
                            ArrPos = 0;
                            ParseStringRequest.ClrError();
                            scan(ParseSelect.file, sc);
                            array = Scanned.get(14);
                            ParseStringRequest.ParseStringRequest(array, ArrPos);
                        }
                        PrintReq.ReqPrint();
                        FirstReq = true;
                    }
                    BegLog = false;
                    FirstReq = false;

                }
            }
            if (!(array[0].equals("")) && !(array[0].equals("Log") && !(FirstReq))) {
                ParseStringRequest.InitializeMap();
                ArrPos = 0;

                while(ArrPos < array.length){
                    FinishedReq = false;

                    switch (array[ArrPos]) {
                        case "R-":
                            ParseStringRequest.IncLine(n);
                            ParseStringRequest.ParseStringRequest(array,ArrPos);
                            while(!FinishedReq){
                                n++;
                                ArrPos = 0;
                                ParseStringRequest.ClrError();
                                scan(ParseSelect.file, sc);
                                array = Scanned.get(14);
                                if (!array[0].equals("") && !(array[0].equals("Log"))) {
                                    ParseStringRequest.ParseStringRequest(array, ArrPos);
                                } else {
                                    FinishedReq = true;
                                }
                            }
                            if(!Iterables.getLast(ParseStringRequest.ParsedReq.get("fcn")).equals(0))
                                PrintReq.ReqPrint();
                            ParseStringRequest.InitializeMap();
                            break;

                        default:
                            ArrPos++;
                            break;
                    }

                }
                n++;
                ArrPos = 0;
                ParseStringRequest.ClrError();
                scan(ParseSelect.file, sc);
                ParseStringRequest.InitializeMap();
                array = Scanned.get(14);
                previous = Scanned.get(13);

            }else{
                n++;
                ArrPos = 0;
                ParseStringRequest.ClrError();
                scan(ParseSelect.file, sc);
                ParseStringRequest.InitializeMap();
                array = Scanned.get(14);
                previous = Scanned.get(13);
            }

        }
        sc.reset();
        sc.close();
        HasLine = true;
    }

}

