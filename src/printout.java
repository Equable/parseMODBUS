import com.google.common.collect.Iterables;
import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;

public class printout {

    protected final static void ReqPrint() {
        ParseStringRequest PSR = new ParseStringRequest();
        ArrayList<String> Parsed = new ArrayList<String>();
        ArrayList<String> extras = new ArrayList<String>();
        int size;


        switch(Iterables.getLast(PSR.ParsedReq.get("fcn"))){
            default:
                Parsed.clear();
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("line"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("id"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("fcn"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("add"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("amounts"))));
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                break;

            case 5:
                Parsed.clear();
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("line"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("id"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("fcn"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("add"))));
                Parsed.add("");
                if(Iterables.getLast(PSR.ParsedReq.get("status")).equals(0)){
                    Parsed.add("OFF");
                }else{
                    Parsed.add("ON");
                }
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                break;

            case 6:
                Parsed.clear();
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("line"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("id"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("fcn"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("add"))));
                Parsed.add("");
                Parsed.add("");
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("value"))));
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                break;

            case 15:
                Parsed.clear();
                extras.clear();
                size = PSR.ParsedReq.get("bit").size();

                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("line"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("id"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("fcn"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("add"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("amounts"))));
                Parsed.add("");
                Parsed.add("");
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("bytes"))));
                Parsed.add(String.format("%8s",Integer.toBinaryString(Iterables.get(PSR.ParsedReq.get("bit"), 1))).replace(" ", "0"));
                Parsed.add("");

                for(int i = 2; i < size; i++) {
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add(String.format("%8s",Integer.toBinaryString(Iterables.get(PSR.ParsedReq.get("bit"), i))).replace(" ", "0"));
                    extras.add("");
                }
                break;

            case 16:
                Parsed.clear();
                extras.clear();
                size = PSR.ParsedReq.get("values").size();

                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("line"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("id"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("fcn"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("add"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("amounts"))));
                Parsed.add("");
                Parsed.add("");
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedReq.get("bytes"))));
                Parsed.add("");
                Parsed.add(Integer.toString(Iterables.get(PSR.ParsedReq.get("values"), 1)));

                for(int i = 2; i < size; i++) {
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add(Integer.toString(Iterables.get(PSR.ParsedReq.get("values"), i)));
                }
                break;

        }

        for(String v: Parsed){
            System.out.format("%-10s", "| "+ v);

        }
        System.out.print("\n");
        int i = 0;
        switch(Iterables.getLast(PSR.ParsedReq.get("fcn"))){


            case 15:
                //noinspection Duplicates
                for(String v: extras){
                    System.out.format("%-10s", "| "+ v);
                    i++;
                    if((i%10) == 0 && i !=0){
                        System.out.print("\n");
                    }
                }
                break;

            case 16:
                //noinspection Duplicates
                for(String v: extras){
                    System.out.format("%-10s", "| "+ v);
                    i++;
                    if((i%10) == 0 && i !=0){
                        System.out.print("\n");
                    }
                }
                break;

            default:
                break;
        }


    }
    protected final static void startTable(){
        //
        //PRINT TABLE FORMAT: SlaveID fcn add amounts status value bytes bit values
        //
        System.out.format("%-10s","| " + "Line");
        System.out.format("%-10s", "| "+ "SlaveID");
        System.out.format("%-10s", "| "+ "Fcn Code");
        System.out.format("%-10s", "| "+ "Address");
        System.out.format("%-10s", "| "+ "Amounts");
        System.out.format("%-10s", "| "+ "Status");
        System.out.format("%-10s", "| "+ "Value");
        System.out.format("%-10s", "| "+ "Bytes");
        System.out.format("%-10s", "| "+ "Bits");
        System.out.format("%-10s", "| "+ "Values");
        System.out.print("\n");
        String Div = new String(new char[100]).replace("\0", "-");
        System.out.println(Div);
    }
}

