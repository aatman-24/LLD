package 
flyweight_design_pattern;

public class Robot {

        int coordinateX;    // 4 bytes
        int coordinateY;    // 4 bytes
        String type;        // 50 Bytes (1 byte * 50 char length)
        String body;        // 2d bitmap, 31 KB             // EST: 31 KB

    public Robot(int coordinateX, int coordinateY, String type, String body) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.type = type;
        this.body = body;
    }
}
