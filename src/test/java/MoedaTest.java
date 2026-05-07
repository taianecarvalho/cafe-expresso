import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class MoedaTest {
    @Test
    void deveConverterValorNoPadraoBrasileiro() {
        assertEquals(new BigDecimal("5.50"), Moeda.converterParaBigDecimal("5,50"));
        assertEquals(new BigDecimal("1234.56"), Moeda.converterParaBigDecimal("1.234,56"));
        assertEquals(new BigDecimal("8.90"), Moeda.converterParaBigDecimal("R$ 8,90"));
    }

    @Test
    void deveConverterValorComPontoDecimal() {
        assertEquals(new BigDecimal("5.50"), Moeda.converterParaBigDecimal("5.50"));
    }

    @Test
    void deveFormatarValorNoPadraoBrasileiro() {
        assertEquals("R$ 5,50", Moeda.formatar(new BigDecimal("5.50")));
        assertEquals("R$ 10,00", Moeda.formatar(new BigDecimal("10")));
    }

    @Test
    void deveImpedirValorInvalido() {
        assertThrows(NumberFormatException.class, () -> Moeda.converterParaBigDecimal(""));
        assertThrows(NumberFormatException.class, () -> Moeda.converterParaBigDecimal("abc"));
    }
}
