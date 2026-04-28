import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class CardapioTest {
    @Test
    void deveCadastrarProdutoNoCardapio() {
        Cardapio cardapio = new Cardapio();

        Produto produto = cardapio.cadastrarProduto("Cappuccino", new BigDecimal("8.50"));

        assertEquals("Cappuccino", produto.getNome());
        assertEquals(new BigDecimal("8.50"), produto.getPreco());
        assertEquals(1, cardapio.listarProdutos().size());
    }

    @Test
    void deveBuscarProdutoPorNomeIgnorandoMaiusculasEAcentos() {
        Cardapio cardapio = new Cardapio();
        Produto produto = cardapio.cadastrarProduto("Café Expresso", new BigDecimal("5.50"));

        assertTrue(cardapio.buscarPorNome("cafe expresso").isPresent());
        assertEquals(produto, cardapio.buscarPorNome("CAFÉ EXPRESSO").get());
    }

    @Test
    void deveRetornarVazioAoBuscarProdutoInexistenteOuNomeInvalido() {
        Cardapio cardapio = new Cardapio();
        cardapio.cadastrarProduto("Café Expresso", new BigDecimal("5.50"));

        assertFalse(cardapio.buscarPorNome("Chá").isPresent());
        assertFalse(cardapio.buscarPorNome("").isPresent());
        assertFalse(cardapio.buscarPorNome(null).isPresent());
    }

    @Test
    void deveProtegerListaDeProdutosContraAlteracaoExterna() {
        Cardapio cardapio = new Cardapio();
        cardapio.cadastrarProduto("Café Expresso", new BigDecimal("5.50"));

        assertThrows(UnsupportedOperationException.class, () -> cardapio.listarProdutos().clear());
        assertEquals(1, cardapio.listarProdutos().size());
    }
}
