package repository;

import model.GiftCardPayment;

import java.util.HashMap;
import java.util.Map;

public class GiftCardRepository {
    private final Map<String, GiftCardPayment> giftCards = new HashMap<>();

    public void addGiftCard(GiftCardPayment giftCard) {
        if (giftCard == null) {
            throw new IllegalArgumentException("Provide a valid gift card!");
        }
        if (giftCards.containsKey(giftCard.getGiftCardNumber())) {
            throw new IllegalArgumentException("Gift Card with number " + giftCard.getGiftCardNumber() + " already exists!");
        }
        giftCards.put(giftCard.getGiftCardNumber(), giftCard);
    }

    public GiftCardPayment findByGiftCardNumber(String giftCardNumber) {
        if (giftCardNumber == null || giftCardNumber.isBlank()) {
            throw new IllegalArgumentException("Provide a valid gift card number!");
        }
        return giftCards.get(giftCardNumber);
    }
}
