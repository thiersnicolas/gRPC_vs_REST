package be.hogent.thiersn;

import java.util.Collection;
import java.util.List;

public interface People5Service<T> {
    Collection<Integer> clearData();

    List<T> people5(int amount);

    Collection<Integer> supportedAmounts();
}
