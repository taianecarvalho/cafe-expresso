import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ProdutoTest {

	@Test
    void deveGuardarNome() {

		Produto produto = new Produto("Café Expresso", new BigDecimal("5.50"));

        assertEquals("Café Expresso", produto.getNome());

    }

    @Test
    void deveGuardarPreco() {

    	Produto produto = new Produto("Café Expresso", new BigDecimal("5.50"));

        assertEquals(new BigDecimal("5.50"), produto.getPreco());

    }

    @Test
    void deveRemoverEspacosDoNome() {

    	Produto produto = new Produto("  Café Expresso  ", new BigDecimal("5.50"));

        assertEquals("Café Expresso", produto.getNome());

    }

    @Test
    void deveRejeitarNomeEmBranco() {
        assertThrows(IllegalArgumentException.class, () -> new Produto(" ", new BigDecimal("5.00")));
    }

    @Test
    void deveRejeitarNomeNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Produto(null, new BigDecimal("5.00")));
    }

    @Test
    void deveRejeitarPrecoZero() {
        assertThrows(IllegalArgumentException.class, () -> new Produto("Café", BigDecimal.ZERO));
    }

    @Test
    void deveRejeitarPrecoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Produto("Café", new BigDecimal("-1.00")));
    }

    @Test
    void deveRejeitarPrecoNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Produto("Café", null));
    }

    @Test
    void deveSerIgualPorNomeEPreco() {

    	Produto produto = new Produto("Café Expresso", new BigDecimal("5.50"));
        Produto outro = new Produto("Café Expresso", new BigDecimal("5.50"));

        assertEquals(produto, outro);

    }

    @Test
    void deveTerMesmoHashCodeQuandoIguais() {

    	Produto produto = new Produto("Café Expresso", new BigDecimal("5.50"));
        Produto outro = new Produto("Café Expresso", new BigDecimal("5.50"));

        assertEquals(produto.hashCode(), outro.hashCode());

    }

}
