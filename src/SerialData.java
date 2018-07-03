import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SerialData {
    public final SimpleIntegerProperty Line;
    public final SimpleIntegerProperty ID;
    public final SimpleIntegerProperty Code;
    public final SimpleStringProperty Address;
    public final SimpleStringProperty Amounts;
    public final SimpleStringProperty Status;
    public final SimpleStringProperty Value;
    public final SimpleStringProperty Bytes;
    public final SimpleStringProperty Bits;
    public final SimpleStringProperty Values;

    public SerialData(int line, int id, int code, String address, String amounts, String status, String value, String bytes, String bits, String values){
        this.Line = new SimpleIntegerProperty(line);
        this.ID = new SimpleIntegerProperty(id);
        this.Code = new SimpleIntegerProperty(code);
        this.Address = new SimpleStringProperty(address);
        this.Amounts = new SimpleStringProperty(amounts);
        this.Status = new SimpleStringProperty(status);
        this.Value = new SimpleStringProperty(value);
        this.Bytes = new SimpleStringProperty(bytes);
        this.Bits = new SimpleStringProperty(bits);
        this.Values = new SimpleStringProperty(values);
    }
    public int getLine(){
        return Line.get();
    }
    public void setLine(int line){
        Line.set(line);
    }
    public int getID(){
        return ID.get();
    }
    public void setID(int id){
        ID.set(id);
    }
    public int getCode(){
        return Code.get();
    }
    public void setCode(int code){
        Code.set(code);
    }
    public String getAddress(){
        return Address.get();
    }
    public void setAddress(String address){
        Address.set(address);
    }
    public String getAmounts(){
        return Amounts.get();
    }
    public void setAmounts(String amounts){
        Amounts.set(amounts);
    }
    public String getStatus(){
        return Status.get();
    }
    public void setStatus(String status){
        Status.set(status);
    }
    public String getValue(){
        return Value.get();
    }
    public void setValue(String value){
        Value.set(value);
    }
    public String getBytes(){
        return Bytes.get();
    }
    public void setBytes(String bytes){
        Bytes.set(bytes);
    }
    public String getBits(){
        return Bits.get();
    }
    public void setBits(String bits){
        Bits.set(bits);
    }
    public String getValues(){
        return Values.get();
    }
    public void setValues(String values){
        Values.set(values);
    }
}

