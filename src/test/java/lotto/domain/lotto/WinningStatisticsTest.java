package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

import java.util.Arrays;
import lotto.domain.number.PayOut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningStatisticsTest {

    @Test
    @DisplayName("수익률 계산")
    void getYield() {
        WinningNumber winningNumber = WinningNumber.valueOf("1, 2, 3, 4, 5, 6", "7");
        LottoGroup lottoGroup = new LottoGroup(Arrays.asList(
            LottoNumbers.valueOf("1,2,3,4,5,6"),
            LottoNumbers.valueOf("1,2,3,4,5,34"),
            LottoNumbers.valueOf("1,2,3,4,5,7")
        ));
        WinningStatistics result = winningNumber.getResult(lottoGroup, PayOut.valueOf("3000"));
        assertThat(2031500000D / 3000D).isEqualTo(result.getYield(), withPrecision(2d));
    }
}