import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ProdutoTest {
    @Test
    void deveCriarProdutoValido() {
        Produto produto = new Produto("Café Expresso", new BigDecimal("5.50"));

        assertEquals("Café Expresso", produto.getNome());
        assertEquals(new BigDecimal("5.50"), produto.getPreco());
    }

    @Test
    void deveRemoverEspacosDoNome() {
        Produto produto = new Produto("  Café Expresso  ", new BigDecimal("5.50"));

        assertEquals("Café Expresso", produto.getNome());
    }

    @Test
    void deveImpedirNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> new Produto(" ", new BigDecimal("5.00")));
        assertThrows(IllegalArgumentException.class, () -> new Produto(null, new BigDecimal("5.00")));
    }

    @Test
    void deveImpedirPrecoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Produto("Café", BigDecimal.ZERO));
        assertThrows(IllegalArgumentException.class, () -> new Produto("Café", new BigDecimal("-1.00")));
        assertThrows(IllegalArgumentException.class, () -> new Produto("Café", null));
    }

    @Test
    void deveCompararProdutosPorNomeEPreco() {
        Produto produto = new Produto("Café Expresso", new BigDecimal("5.50"));
        Produto outroProdutoIgual = new Produto("Café Expresso", new BigDecimal("5.50"));

        assertEquals(produto, outroProdutoIgual);
        assertEquals(produto.hashCode(), outroProdutoIgual.hashCode());
    }
}
