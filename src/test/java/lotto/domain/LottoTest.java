package lotto.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @DisplayName("구매 로또 생성시 숫자가 6개가 입력되지 않으면 에러 발생")
    @Test
    void lottoCreateExceptionBySize() {
        final List<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoNumbers))
                .withMessage("[ERROR] 로또 넘버는 6개가 입력되어야 합니다.");
    }

    @DisplayName("구매 로또가 중복될 경우 예외가 발생해야 한다.")
    @Test
    void duplicateLottoException() {
        final List<LottoNumber> lottoNumbers = Stream.of(1, 1, 1, 1, 1, 1)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Lotto(lottoNumbers))
                .withMessage("[ERROR] 로또 넘버는 중복될 수 없습니다.");
    }

    @Nested
    @DisplayName("번호를 포함하는지 확인할 수 있다.")
    class ContainNumber {

        private Lotto lotto;

        @BeforeEach
        void setUp() {
            lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                    .map(LottoNumber::new)
                    .collect(Collectors.toList()));
        }

        @DisplayName("보너스 넘버를 포함하는 경우")
        @Test
        void containNumberTrue() {
            final LottoNumber lottoNumber = new LottoNumber(1);
            assertTrue(lotto.containNumber(lottoNumber));
        }

        @DisplayName("보너스 넘버를 포함하지 않는 경우")
        @Test
        void containNumberFalse() {
            final LottoNumber lottoNumber = new LottoNumber(7);
            assertFalse(lotto.containNumber(lottoNumber));
        }
    }

    @Test
    void matchLotto() {
        final Lotto lotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 6)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));

        final Lotto compareLotto = new Lotto(Stream.of(1, 2, 3, 4, 5, 7)
                .map(LottoNumber::new)
                .collect(Collectors.toList()));

        assertThat(lotto.match(compareLotto)).isEqualTo(5);
    }
}
