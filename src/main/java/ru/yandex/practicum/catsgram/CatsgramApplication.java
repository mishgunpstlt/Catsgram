package ru.yandex.practicum.catsgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatsgramApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatsgramApplication.class, args);
        /*final Gson gson = new Gson();
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Введите JSON => ");
        final String input = scanner.nextLine();
        try {
            String str = gson.fromJson(input, Map.class).toString();
            System.out.println("Был введён корректный JSON");
            System.out.println(str);
        } catch (JsonSyntaxException exception) {
            System.out.println("Был введён некорректный JSON");
        }*/
    }
}
