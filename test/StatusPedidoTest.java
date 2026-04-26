import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StatusPedidoTest {
    @Test
    void deveConterStatusNaOrdemDoFluxoDoPedido() {
        StatusPedido[] status = StatusPedido.values();

        assertEquals(StatusPedido.PENDENTE, status[0]);
        assertEquals(StatusPedido.PAGO, status[1]);
        assertEquals(StatusPedido.EM_PREPARO, status[2]);
        assertEquals(StatusPedido.FINALIZADO, status[3]);
    }

    @Test
    void deveConverterTextoParaStatus() {
        assertEquals(StatusPedido.PENDENTE, StatusPedido.valueOf("PENDENTE"));
        assertEquals(StatusPedido.PAGO, StatusPedido.valueOf("PAGO"));
        assertEquals(StatusPedido.EM_PREPARO, StatusPedido.valueOf("EM_PREPARO"));
        assertEquals(StatusPedido.FINALIZADO, StatusPedido.valueOf("FINALIZADO"));
    }
}
