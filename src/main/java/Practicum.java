import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

public class Practicum {

    @Data
    @Builder
    public static class House {
        @NonNull
        private Integer wallWidth;
        @NonNull
        private Integer wallLength;
        @NonNull
        private String wallsColor;

        @Builder.Default
        private Integer wallHeight = 2500;
        @Builder.Default
        private Integer numberOfWindows = 6;
        @Builder.Default
        private Integer numberOfFloors = 1;

        public int calculateTotalArea() {
            return wallWidth * wallLength * numberOfFloors;
        }
    }

    public static void main(String[] args) {
        final House house = House.builder()
                .wallWidth(5)
                .wallLength(12)
                .wallsColor("red")
                .numberOfFloors(2)
                .build();

        System.out.println(house);
        System.out.println("Общая площадь = " + house.calculateTotalArea());
    }
}