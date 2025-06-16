package car_rental_system_latest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    private String city;    // as of now, using city, but in real world it could be more complex
}
