package utils;

import pages.CruiseItem;
import java.util.function.Predicate;

public interface CruiseCriteria {

    Predicate<CruiseItem> moreOrEqual6Nights = t -> t.getNumberOfNights() >= 6;
    Predicate<CruiseItem> lessOrEqual8Nights = t -> t.getNumberOfNights() <= 8;
}
