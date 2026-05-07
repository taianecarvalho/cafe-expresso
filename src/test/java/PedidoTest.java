import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class PedidoTest {
    @Test
    void deveCalcularTotalDoPedido() {
        Pedido pedido = new Pedido();
        pedido.adicionarItem(new Produto("Café Expresso", new BigDecimal("5.50")), 2);
        pedido.adicionarItem(new Produto("Pão de queijo", new BigDecimal("4.00")), 1);

        assertEquals(new BigDecimal("15.00"), pedido.calcularTotal());
    }

    @Test
    void deveSeguirFluxoValidoDeStatus() {
        Pedido pedido = criarPedidoComItem();

        assertEquals(StatusPedido.PENDENTE, pedido.getStatus());

        pedido.registrarPagamento();
        assertEquals(StatusPedido.PAGO, pedido.getStatus());

        pedido.iniciarPreparo();
        assertEquals(StatusPedido.EM_PREPARO, pedido.getStatus());

        pedido.finalizar();
        assertEquals(StatusPedido.FINALIZADO, pedido.getStatus());
    }

    @Test
    void deveImpedirPreparoSemPagamento() {
        Pedido pedido = criarPedidoComItem();

        assertThrows(IllegalStateException.class, () -> pedido.iniciarPreparo());
    }

    @Test
    void deveImpedirPularDiretoParaFinalizado() {
        Pedido pedido = criarPedidoComItem();

        assertThrows(IllegalStateException.class, () -> pedido.finalizar());
    }

    @Test
    void deveImpedirPagamentoSemItens() {
        Pedido pedido = new Pedido();

        assertThrows(IllegalStateException.class, () -> pedido.registrarPagamento());
    }

    @Test
    void deveImpedirAdicionarItemAposPagamento() {
        Pedido pedido = criarPedidoComItem();
        pedido.registrarPagamento();

        assertThrows(IllegalStateException.class,
                () -> pedido.adicionarItem(new Produto("Bolo", new BigDecimal("7.00")), 1));
    }

    @Test
    void deveImpedirAlterarStatusParaNuloOuMesmoStatus() {
        Pedido pedido = criarPedidoComItem();

        assertThrows(IllegalArgumentException.class, () -> pedido.alterarStatus(null));
        assertThrows(IllegalStateException.class, () -> pedido.alterarStatus(StatusPedido.PENDENTE));
    }

    @Test
    void deveImpedirAlteracaoAposFinalizacao() {
        Pedido pedido = criarPedidoComItem();
        pedido.registrarPagamento();
        pedido.iniciarPreparo();
        pedido.finalizar();

        assertThrows(IllegalStateException.class, () -> pedido.alterarStatus(StatusPedido.PAGO));
        assertThrows(IllegalStateException.class,
                () -> pedido.adicionarItem(new Produto("Bolo", new BigDecimal("7.00")), 1));
    }

    @Test
    void deveProtegerListaDeItensContraAlteracaoExterna() {
        Pedido pedido = criarPedidoComItem();

        assertThrows(UnsupportedOperationException.class, () -> pedido.getItens().clear());
        assertEquals(1, pedido.getItens().size());
    }

    private Pedido criarPedidoComItem() {
        Pedido pedido = new Pedido();
        pedido.adicionarItem(new Produto("Café Expresso", new BigDecimal("5.50")), 1);
        return pedido;
    }
}
