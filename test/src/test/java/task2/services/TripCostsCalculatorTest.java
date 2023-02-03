package task2.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import task2.interfaces.LotTicketService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class TripCostsCalculatorTest {

    @Mock
    LotTicketService lotTicketServiceMock;

    TripCostsCalculator tripCostsCalculator;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
        tripCostsCalculator = new TripCostsCalculator(lotTicketServiceMock);
    }

    @Test
    public void shouldReturnCorrectValueOfTripCostsOnCalculate() {
        Mockito.when(lotTicketServiceMock.getPrice("Polska", "Niemcy")).thenReturn(BigDecimal.valueOf(100));
        Mockito.when(lotTicketServiceMock.getPrice("Niemcy", "Grecja")).thenReturn(BigDecimal.valueOf(100));
        Mockito.when(lotTicketServiceMock.getPrice("Grecja", "Chorwacja")).thenReturn(BigDecimal.valueOf(100));
        Mockito.when(lotTicketServiceMock.getPrice("Chorwacja", "Japonia")).thenReturn(BigDecimal.valueOf(100));
        Mockito.when(lotTicketServiceMock.getPrice("Japonia", "Polska")).thenReturn(BigDecimal.valueOf(200));

        List<String> countries = List.of("Polska", "Niemcy", "Grecja", "Chorwacja", "Japonia");

        assertEquals(BigDecimal.valueOf(600), tripCostsCalculator.calculate(countries));
    }
}