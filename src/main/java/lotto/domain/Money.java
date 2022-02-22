package lotto.domain;

public class Money {

    private final int amount;

    public Money(final int amount) {
        this.amount = amount;
    }

    public int calculateLottoCount() {
        return amount / 1000;
    }
}
