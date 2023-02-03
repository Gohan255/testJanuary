package task2.services;

import task2.interfaces.LotTicketService;

import java.math.BigDecimal;
import java.util.List;

public class TripCostsCalculator {

    private LotTicketService ticketService;

    public TripCostsCalculator(LotTicketService ticketService) {
        this.ticketService = ticketService;
    }

    public BigDecimal calculate(List<String> countries) {
        if (countries == null || countries.isEmpty()) {
            throw new IllegalArgumentException("List is null or empty");
        }
        BigDecimal cost = BigDecimal.ZERO;
        for (int i = 0; i < countries.size() - 1; i++) {
            cost = cost.add(ticketService.getPrice(countries.get(i), countries.get(i + 1)));
        }
        cost = cost.add(ticketService.getPrice(countries.get(countries.size() - 1), countries.get(0)));
        return cost;
    }

}
