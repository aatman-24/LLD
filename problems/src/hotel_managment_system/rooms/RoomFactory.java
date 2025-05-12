package hotel_managment_system.rooms;

public class RoomFactory {

    public static Room createRoom(int roomNumber, RoomType roomType) {
        switch (roomType) {
            case SINGLE:
                return new SingleRoom(roomNumber);
            case DOUBLE:
                return new DoubleRoom(roomNumber);
            case SUITE:
                return new SuiteRoom(roomNumber);
            case DELUXE:
                return new DeluxeRoom(roomNumber);
            default:
                return null;
        }
    }
}
