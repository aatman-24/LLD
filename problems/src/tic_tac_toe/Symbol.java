package tic_tac_toe;

public enum Symbol {
    X("X"),
    O("0");

    private final String sign;

    Symbol(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }
}
