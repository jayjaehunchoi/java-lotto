package lotto.domain.lotto;

import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.exception.DuplicateLottoNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class WinningLottoTest {
    private Lotto lotto;

    @BeforeEach
    void setUp() {
        lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    void 생성자_확인() {
        assertThat(new WinningLotto(lotto, 7))
                .isExactlyInstanceOf((WinningLotto.class));
    }

    @Test
    void 생성자_확인_보너스_숫자와_이미_입력된_로또숫자가_중복되는지_확인() {
        assertThatThrownBy(() -> new WinningLotto(lotto, 1))
                .isExactlyInstanceOf(DuplicateLottoNumberException.class);
    }

    @Test
    void 당첨로또_결과를_잘_생성하는지_확인() {
        LottoRepository lottoRepository = new LottoRepository();
        lottoRepository.addManualLotto(LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 6))));
        lottoRepository.addManualLotto(LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 7))));
        lottoRepository.addManualLotto(LottoGenerator.create(new ManualLottoGeneratingStrategy(Arrays.asList(1, 2, 3, 4, 5, 9))));

        LottoTickets lottoTickets = new LottoTickets(lottoRepository);
        WinningLotto winningLotto = new WinningLotto(lotto, 7);
        Result result = winningLotto.match(lottoTickets);

        assertThat(result.get(Rank.FIRST)).isEqualTo(1);
        assertThat(result.get(Rank.SECOND)).isEqualTo(1);
        assertThat(result.get(Rank.THIRD)).isEqualTo(1);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(result.get(Rank.FIFTH)).isEqualTo(0);
        assertThat(result.get(Rank.MISS)).isEqualTo(0);
    }
}