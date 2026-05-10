import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class CardapioTest {

    @Test
    void deveCadastrarComNome() {

    	Cardapio cardapio = new Cardapio();

        Produto produto = cardapio.cadastrarProduto("Cappuccino", new BigDecimal("8.50"));

        assertEquals("Cappuccino", produto.getNome());

    }

    @Test
    void deveCadastrarComPreco() {

    	Cardapio cardapio = new Cardapio();

        Produto produto = cardapio.cadastrarProduto("Cappuccino", new BigDecimal("8.50"));

        assertEquals(new BigDecimal("8.50"), produto.getPreco());

    }

    @Test
    void deveAdicionarProdutoNaLista() {

    	Cardapio cardapio = new Cardapio();

        cardapio.cadastrarProduto("Cappuccino", new BigDecimal("8.50"));

        assertEquals(1, cardapio.listarProdutos().size());

    }

    @Test
    void deveBuscarIgnorandoMaiusculasEAcentos() {

    	Cardapio cardapio = new Cardapio();
        cardapio.cadastrarProduto("Café Expresso", new BigDecimal("5.50"));

        assertTrue(cardapio.buscarPorNome("cafe expresso").isPresent());

    }

    @Test
    void deveRetornarProdutoEncontrado() {

    	Cardapio cardapio = new Cardapio();
        Produto produto = cardapio.cadastrarProduto("Café Expresso", new BigDecimal("5.50"));

        assertEquals(produto, cardapio.buscarPorNome("CAFÉ EXPRESSO").get());

    }

    @Test
    void deveRetornarVazioQuandoNaoEncontra() {

    	Cardapio cardapio = new Cardapio();
        cardapio.cadastrarProduto("Café Expresso", new BigDecimal("5.50"));

        assertFalse(cardapio.buscarPorNome("Chá").isPresent());

    }

    @Test
    void deveRetornarVazioParaNomeEmBranco() {

    	Cardapio cardapio = new Cardapio();
        cardapio.cadastrarProduto("Café Expresso", new BigDecimal("5.50"));

        assertFalse(cardapio.buscarPorNome("").isPresent());

    }

    @Test
    void deveRetornarVazioParaNomeNulo() {

    	Cardapio cardapio = new Cardapio();
        cardapio.cadastrarProduto("Café Expresso", new BigDecimal("5.50"));

        assertFalse(cardapio.buscarPorNome(null).isPresent());

    }

    @Test
    void deveImpedirAlteracaoDaLista() {

    	Cardapio cardapio = new Cardapio();
        cardapio.cadastrarProduto("Café Expresso", new BigDecimal("5.50"));

        assertThrows(UnsupportedOperationException.class, () -> cardapio.listarProdutos().clear());

    }

    @Test
    void devePreservarListaAposTentativaDeLimpar() {

    	Cardapio cardapio = new Cardapio();
        cardapio.cadastrarProduto("Café Expresso", new BigDecimal("5.50"));

        try {
            cardapio.listarProdutos().clear();
        } catch (UnsupportedOperationException ignored) {
        }

        assertEquals(1, cardapio.listarProdutos().size());

    }

}
