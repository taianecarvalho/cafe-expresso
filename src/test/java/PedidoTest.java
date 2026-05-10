import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class PedidoTest {

	@Test
    void deveCalcularTotal() {

		Pedido pedido = new Pedido();
        pedido.adicionarItem(new Produto("Café Expresso", new BigDecimal("5.50")), 2);
        pedido.adicionarItem(new Produto("Pão de queijo", new BigDecimal("4.00")), 1);

        assertEquals(new BigDecimal("15.00"), pedido.calcularTotal());

    }

    @Test
    void deveIniciarComoPendente() {

    	Pedido pedido = criarPedidoComItem();

        assertEquals(StatusPedido.PENDENTE, pedido.getStatus());

    }

    @Test
    void deveRegistrarPagamento() {

    	Pedido pedido = criarPedidoComItem();

        pedido.registrarPagamento();

        assertEquals(StatusPedido.PAGO, pedido.getStatus());

    }

    @Test
    void deveIniciarPreparo() {

    	Pedido pedido = criarPedidoComItem();
        pedido.registrarPagamento();

        pedido.iniciarPreparo();

        assertEquals(StatusPedido.EM_PREPARO, pedido.getStatus());

    }

    @Test
    void deveFinalizarPedido() {

    	Pedido pedido = criarPedidoComItem();
        pedido.registrarPagamento();
        pedido.iniciarPreparo();

        pedido.finalizar();

        assertEquals(StatusPedido.FINALIZADO, pedido.getStatus());

    }

    @Test
    void deveImpedirPreparoSemPagamento() {

    	Pedido pedido = criarPedidoComItem();

        assertThrows(IllegalStateException.class, () -> pedido.iniciarPreparo());

    }

    @Test
    void deveImpedirFinalizarSemPreparar() {

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
    void deveRejeitarStatusNulo() {

    	Pedido pedido = criarPedidoComItem();

        assertThrows(IllegalArgumentException.class, () -> pedido.alterarStatus(null));

    }

    @Test
    void deveRejeitarMesmoStatus() {

    	Pedido pedido = criarPedidoComItem();

        assertThrows(IllegalStateException.class, () -> pedido.alterarStatus(StatusPedido.PENDENTE));

    }

    @Test
    void deveImpedirAlterarStatusAposFinalizar() {

    	Pedido pedido = pedidoFinalizado();

        assertThrows(IllegalStateException.class, () -> pedido.alterarStatus(StatusPedido.PAGO));

    }

    @Test
    void deveImpedirAdicionarItemAposFinalizar() {

    	Pedido pedido = pedidoFinalizado();

        assertThrows(IllegalStateException.class,
                () -> pedido.adicionarItem(new Produto("Bolo", new BigDecimal("7.00")), 1));

    }

    @Test
    void deveImpedirAlteracaoDaListaDeItens() {

    	Pedido pedido = criarPedidoComItem();

        assertThrows(UnsupportedOperationException.class, () -> pedido.getItens().clear());

    }

    @Test
    void devePreservarItensAposTentativaDeLimpar() {

    	Pedido pedido = criarPedidoComItem();

        try {
            pedido.getItens().clear();
        } catch (UnsupportedOperationException ignored) {
        }

        assertEquals(1, pedido.getItens().size());

    }

    private Pedido criarPedidoComItem() {
        Pedido pedido = new Pedido();
        pedido.adicionarItem(new Produto("Café Expresso", new BigDecimal("5.50")), 1);
        return pedido;
    }

    private Pedido pedidoFinalizado() {
        Pedido pedido = criarPedidoComItem();
        pedido.registrarPagamento();
        pedido.iniciarPreparo();
        pedido.finalizar();
        return pedido;
    }

}
