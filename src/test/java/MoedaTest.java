import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class MoedaTest {

	@Test
    void deveConverterComVirgula() {
        assertEquals(new BigDecimal("5.50"), Moeda.converterParaBigDecimal("5,50"));
    }

    @Test
    void deveConverterComMilhar() {
        assertEquals(new BigDecimal("1234.56"), Moeda.converterParaBigDecimal("1.234,56"));
    }

    @Test
    void deveConverterComSimboloDeReal() {
        assertEquals(new BigDecimal("8.90"), Moeda.converterParaBigDecimal("R$ 8,90"));
    }

    @Test
    void deveConverterComPonto() {
        assertEquals(new BigDecimal("5.50"), Moeda.converterParaBigDecimal("5.50"));
    }

    @Test
    void deveFormatarValorDecimal() {
        assertEquals("R$ 5,50", Moeda.formatar(new BigDecimal("5.50")));
    }

    @Test
    void deveFormatarValorInteiro() {
        assertEquals("R$ 10,00", Moeda.formatar(new BigDecimal("10")));
    }

    @Test
    void deveRejeitarTextoVazio() {
        assertThrows(NumberFormatException.class, () -> Moeda.converterParaBigDecimal(""));
    }

    @Test
    void deveRejeitarTextoInvalido() {
        assertThrows(NumberFormatException.class, () -> Moeda.converterParaBigDecimal("abc"));
    }

}
