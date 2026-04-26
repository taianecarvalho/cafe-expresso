import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class ItemPedidoTest {
    @Test
    void deveCalcularSubtotal() {
        Produto produto = new Produto("Pão de queijo", new BigDecimal("4.00"));
        ItemPedido item = new ItemPedido(produto, 3);

        assertEquals(new BigDecimal("12.00"), item.calcularSubtotal());
    }

    @Test
    void deveImpedirQuantidadeInvalida() {
        Produto produto = new Produto("Café", new BigDecimal("5.00"));

        assertThrows(IllegalArgumentException.class, () -> new ItemPedido(produto, 0));
        assertThrows(IllegalArgumentException.class, () -> new ItemPedido(produto, -1));
    }

    @Test
    void deveImpedirProdutoNulo() {
        assertThrows(IllegalArgumentException.class, () -> new ItemPedido(null, 1));
    }
}
