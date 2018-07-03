import com.google.common.collect.Iterables;
import javafx.application.Application;
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

        scan(PS.file, sc);
        String[] array = Scanned.get(14);
        PrintResp.startTable();
        while (HasLine) {
            if (!array[0].equals("") && !(array[0].equals("Log"))) {
                PSR.InitializeMap();
                ArrPos = 0;
                while(ArrPos < array.length){
                    FinishedReq = false;

                    switch (array[ArrPos]) {
                        case "R+":
                            PSR.IncLine(n);
                            PSR.ParseStringResponse(array,ArrPos);
                            while(!FinishedReq){
                                n++;
                                ArrPos = 0;
                                PSR.ClrError();
                                scan(PS.file, sc);
                                array = Scanned.get(14);
                                PSR.ParseStringResponse(array, ArrPos);
                            }
                            PR.RespPrint();
                            PSR.InitializeMap();
                            break;

                        default:
                            ArrPos++;
                            break;
                    }

                }
                n++;
                ArrPos = 0;
                PSR.ClrError();
                scan(PS.file, sc);
                PSR.InitializeMap();
                array = Scanned.get(14);

            }else{
                n++;
                ArrPos = 0;
                PSR.ClrError();
                scan(PS.file, sc);
                PSR.InitializeMap();
                array = Scanned.get(14);
            }

        }

    }

}

