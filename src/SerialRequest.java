import com.google.common.collect.Iterables;
import java.io.File;
import java.util.*;

@SuppressWarnings("Duplicates")
public class SerialRequest {
    static ParseSelect PS = new ParseSelect();
    static boolean FinishedReq = false;

    private static ParseStringRequest PSR = new ParseStringRequest();
    private static ArrayList<String[]> Scanned = new ArrayList<String[]>();

    static boolean FirstScan = true;
    static boolean HasLine = true;
    static int LinesScanned = 0;


    private static void scan(File file, Scanner sc) throws Exception {


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

        Scanner sc;
        try {
           sc = new Scanner(PS.file);
        } catch(NullPointerException e){
            System.out.println("No File Selected");
            return;
        }

        int n = 3;
        int ArrPos = 14;
        PSR.InitializeMap();
        scan(PS.file, sc);
        while (HasLine || ArrPos < 14) {


            if (ArrPos < 14) {
                ArrPos++;
                n++;
            }
            String[] array = Scanned.get(ArrPos);

            if (n == 3) {
                PSR.IncAtmpt();
                PSR.ParseStringRequest(array, 0);
                PSR.ClrError();
                scan(PS.file, sc);
                ArrPos--;
                array = Scanned.get(14);
                PSR.ParseStringRequest(array, 0);
                PSR.IncLine(n);
                PrintReq.startTable();
                PrintReq.ReqPrint();
                PSR.InitializeMap();
                n++;
                FinishedReq = false;


            } else if (!array[0].equals("") && !(array[0].equals("Log"))) {

                for (int i = 0; i < array.length; i++) {
                    boolean breakSwitch = false;
                    FinishedReq = false;
                    switch (array[i]) {
                        case "R-":
                            while (!FinishedReq && !breakSwitch) {
                                PSR.ParseStringRequest(array, i);
                                while (Iterables.getLast(PSR.ParsedReq.get("Error")).equals(1)) {
                                    PSR.ClrError();
                                    scan(PS.file, sc);
                                    ArrPos--;
                                    array = Scanned.get(14);
                                    if (!array[0].equals("Log") && !array[0].equals("")) {
                                        PSR.ParseStringRequest(array, 0);
                                    } else {
                                        PSR.InitializeMap();
                                        scan(PS.file, sc);
                                        breakSwitch = true;
                                    }
                                }
                                if (FinishedReq) {
                                    PSR.IncLine(n);
                                    PrintReq.ReqPrint();
                                    PSR.InitializeMap();
                                    array = Scanned.get(ArrPos);


                                }
                            }
                        default:
                            break;
                    }
                }
                n++;

            }else{
                n++;
            }
            scan(PS.file, sc);
        }

    }

}

