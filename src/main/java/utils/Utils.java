package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Utils {

    private static <T, R extends Comparable<? super R>> boolean isSorted(List<T> list, Function<T, R> function) {
        Comparator<T> comp = Comparator.comparing(function);
        for (int i = 0; i < list.size() - 1; ++i) {
            T left = list.get(i);
            T right = list.get(i + 1);
            if (comp.compare(left, right) >= 0) {
                return false;
            }
        }

        return true;
    }


    public static WebDriver getWebDriver() {
        return new ChromeDriver();
    }
}
