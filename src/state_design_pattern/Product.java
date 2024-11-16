package 
state_design_pattern;

public class Product {

    String code;
    int value;
    boolean isSoldOut;

    public Product() {
    }

    public Product(int value, String code) {
        this.value = value;
        this.code = code;
        this.isSoldOut = false;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public void setSoldOut(boolean soldOut) {
        isSoldOut = soldOut;
    }
}
