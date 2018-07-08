package main;

import javafx.beans.property.SimpleObjectProperty;


public class SerialData {
    public final SimpleObjectProperty Line;
    public final SimpleObjectProperty ID;
    public final SimpleObjectProperty Code;
    public final SimpleObjectProperty Address;
    public final SimpleObjectProperty Amounts;
    public final SimpleObjectProperty Status;
    public final SimpleObjectProperty Value;
    public final SimpleObjectProperty Bytes;
    public final SimpleObjectProperty Bits;
    public final SimpleObjectProperty Values;

    public SerialData(Object line, Object id, Object code, Object address, Object amounts, Object status, Object value, Object bytes, Object bits, Object values){
        this.Line = new SimpleObjectProperty(line);
        this.ID = new SimpleObjectProperty(id);
        this.Code = new SimpleObjectProperty(code);
        this.Address = new SimpleObjectProperty(address);
        this.Amounts = new SimpleObjectProperty(amounts);
        this.Status = new SimpleObjectProperty(status);
        this.Value = new SimpleObjectProperty(value);
        this.Bytes = new SimpleObjectProperty(bytes);
        this.Bits = new SimpleObjectProperty(bits);
        this.Values = new SimpleObjectProperty(values);
    }
    public Object getLine(){
        return Line.get();
    }
    public void setLine(int line){
        Line.set(line);
    }
    public Object getID(){
        return ID.get();
    }
    public void setID(int id){
        ID.set(id);
    }
    public Object getCode(){
        return Code.get();
    }
    public void setCode(int code){
        Code.set(code);
    }
    public Object getAddress(){
        return Address.get();
    }
    public void setAddress(int address){
        Address.set(address);
    }
    public Object getAmounts(){
        return Amounts.get();
    }
    public void setAmounts(int amounts){
        Amounts.set(amounts);
    }
    public Object getStatus(){
        return Status.get();
    }
    public void setStatus(String status){
        Status.set(status);
    }
    public Object getValue(){
        return Value.get();
    }
    public void setValue(int value){
        Value.set(value);
    }
    public Object getBytes(){
        return Bytes.get();
    }
    public void setBytes(int bytes){
        Bytes.set(bytes);
    }
    public Object getBits(){
        return Bits.get();
    }
    public void setBits(String bits){
        Bits.set(bits);
    }
    public Object getValues(){
        return Values.get();
    }
    public void setValues(int values){
        Values.set(values);
    }
}

