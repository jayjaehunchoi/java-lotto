package lotto.domain;

import java.util.List;
import java.util.Map;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public LottoResult createResult(WinLotto winLotto) {
        Map<Rank, Integer> resultMap = Rank.initResultMap();
        lottos.stream()
                .map(winLotto::matchResult)
                .forEach(result -> resultMap.put(result, resultMap.get(result) + 1));
        return new LottoResult(resultMap);
    }
}
