package lotto.domain;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoMachine {

    private static final List<LottoNumber> lottoNumbers;

    static {
        lottoNumbers = IntStream.rangeClosed(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toList());
    }

    public static Set<LottoNumber> createRandomLottoNumbers() {
        Collections.shuffle(lottoNumbers);
        return IntStream.rangeClosed(0, 5)
                .boxed()
                .map(lottoNumbers::get)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
