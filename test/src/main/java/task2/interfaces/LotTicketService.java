package task2.interfaces;

import java.math.BigDecimal;

public interface LotTicketService {

    BigDecimal getPrice(String departureCountry, String arrivalContury);

}
