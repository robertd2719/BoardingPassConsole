package flight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Carrier {
    ArrayList<String> carriers;
    public Carrier(){
        carriers = new ArrayList<>(Arrays.asList("United","SouthWest","United","JetBlue"));

    }
    public String getCarrier(){
        return this.carriers.get(new Random().nextInt(carriers.size()));
    }
}
