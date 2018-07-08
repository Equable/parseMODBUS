package main;

import java.io.File;
import java.util.*;

@SuppressWarnings("Duplicates")
public class SerialResponse {


    static int ArrPos = 0;
    static ParseSelect PS = new ParseSelect();
    static boolean FinishedReq = false;

    private static ParseStringResponse PSR = new ParseStringResponse();
    private static PrintResp PR = new PrintResp();
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
            if (!array[0].equals("") && !(array[0].equals("Log"))) {
                ParseStringResponse.InitializeMap();
                ArrPos = 0;
                while(ArrPos < array.length){
                    FinishedReq = false;

                    switch (array[ArrPos]) {
                        case "R+":
                            ParseStringResponse.IncLine(n);
                            ParseStringResponse.ParseStringResponse(array,ArrPos);
                            while(!FinishedReq){
                                n++;
                                ArrPos = 0;
                                ParseStringResponse.ClrError();
                                scan(ParseSelect.file, sc);
                                array = Scanned.get(14);
                                ParseStringResponse.ParseStringResponse(array, ArrPos);
                            }
                            PrintResp.RespPrint();
                            ParseStringResponse.InitializeMap();
                            break;

                        default:
                            ArrPos++;
                            break;
                    }

                }
                n++;
                ArrPos = 0;
                ParseStringResponse.ClrError();
                scan(ParseSelect.file, sc);
                ParseStringResponse.InitializeMap();
                array = Scanned.get(14);

            }else{
                n++;
                ArrPos = 0;
                ParseStringResponse.ClrError();
                scan(ParseSelect.file, sc);
                ParseStringResponse.InitializeMap();
                array = Scanned.get(14);
            }

        }
        sc.reset();
        sc.close();
        HasLine = true;

    }

}

