import com.google.common.collect.Iterables;

import java.util.ArrayList;

public class PrintResp {


    final static void RespPrint() {
        ParseStringResponse PSR = new ParseStringResponse();
        ArrayList<String> Parsed = new ArrayList<String>();
        ArrayList<String> extras = new ArrayList<String>();
        int size;


        switch(Iterables.getLast(PSR.ParsedResp.get("fcn"))){
            default:
                Parsed.clear();
                extras.clear();
                size = PSR.ParsedResp.get("bit").size();

                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("line"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("id"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("fcn"))));
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("bytes"))));
                Parsed.add(String.format("%8s",Integer.toBinaryString(Iterables.get(PSR.ParsedResp.get("bit"), 1))).replace(" ", "0"));
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
                    extras.add(String.format("%8s",Integer.toBinaryString(Iterables.get(PSR.ParsedResp.get("bit"), i))).replace(" ", "0"));
                    extras.add("");
                }
                break;

            case 3:
                Parsed.clear();
                extras.clear();
                size = PSR.ParsedResp.get("values").size();

                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("line"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("id"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("fcn"))));
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("bytes"))));
                Parsed.add("");
                Parsed.add(Integer.toString(Iterables.get(PSR.ParsedResp.get("values"), 1)));

                for(int i = 2; i < size; i++) {
                    extras.clear();
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add(Integer.toString(Iterables.get(PSR.ParsedResp.get("values"), i)));
                    parseUI.data.add(new SerialData(
                            Integer.parseInt(Parsed.get(0)),
                            Integer.parseInt(Parsed.get(1)),
                            Integer.parseInt(Parsed.get(2)),
                            extras.get(3),
                            extras.get(4),
                            extras.get(5),
                            extras.get(6),
                            extras.get(7),
                            extras.get(8),
                            Parsed.get(9)
                    ));
                }
                break;

            case 4:
                Parsed.clear();
                extras.clear();
                size = PSR.ParsedResp.get("values").size();

                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("line"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("id"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("fcn"))));
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("bytes"))));
                Parsed.add("");
                Parsed.add(Integer.toString(Iterables.get(PSR.ParsedResp.get("values"), 1)));

                for(int i = 2; i < size; i++) {
                    extras.clear();
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add("");
                    extras.add(Integer.toString(Iterables.get(PSR.ParsedResp.get("values"), i)));
                    parseUI.data.add(new SerialData(
                            Integer.parseInt(Parsed.get(0)),
                            Integer.parseInt(Parsed.get(1)),
                            Integer.parseInt(Parsed.get(2)),
                            extras.get(3),
                            extras.get(4),
                            extras.get(5),
                            extras.get(6),
                            Parsed.get(7),
                            extras.get(8),
                            extras.get(9)
                    ));
                }
                break;
            case 5:
                Parsed.clear();
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("line"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("id"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("fcn"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("add"))));
                Parsed.add("");
                if(Iterables.getLast(PSR.ParsedResp.get("status")).equals(0)){
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
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("line"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("id"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("fcn"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("add"))));
                Parsed.add("");
                Parsed.add("");
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("value"))));
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                break;
                
            case 15:
                Parsed.clear();
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("line"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("id"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("fcn"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("add"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("amounts"))));
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                break;

            case 16:
                Parsed.clear();
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("line"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("id"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("fcn"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("add"))));
                Parsed.add(Integer.toString(Iterables.getLast(PSR.ParsedResp.get("amounts"))));
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");
                Parsed.add("");

                break;

        }

        parseUI.data.add(new SerialData(
                Integer.parseInt(Parsed.get(0)),
                Integer.parseInt(Parsed.get(1)),
                Integer.parseInt(Parsed.get(2)),
                Parsed.get(3),
                Parsed.get(4),
                Parsed.get(5),
                Parsed.get(6),
                Parsed.get(7),
                Parsed.get(8),
                Parsed.get(9)
        ));

        for(String v: Parsed){
            System.out.format("%-10s", "| "+ v);




        }

        System.out.print("\n");




        int i = 0;
        //noinspection Duplicates
        switch(Iterables.getLast(PSR.ParsedResp.get("fcn"))){


            case 1:
                //noinspection Duplicates
                for(String v: extras){
                    System.out.format("%-10s", "| "+ v);
                    i++;
                    if((i%10) == 0 && i !=0){
                        System.out.print("\n");
                    }
                }
                break;

            case 2:
                //noinspection Duplicates
                for(String v: extras){
                    System.out.format("%-10s", "| "+ v);
                    i++;

                    if((i%10) == 0 && i !=0){
                        System.out.print("\n");
                    }
                }
                break;

            case 3:
                //noinspection Duplicates
                for(String v: extras){
                    System.out.format("%-10s", "| "+ v);
                    i++;
                    if((i%10) == 0 && i !=0){
                        System.out.print("\n");
                    }
                }
                break;

            case 4:
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
    @SuppressWarnings("Duplicates")
    final static void startTable(){
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
