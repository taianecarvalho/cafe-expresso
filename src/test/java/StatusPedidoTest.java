import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StatusPedidoTest {

	@Test
    void devePendenteSerOPrimeiro() {
        assertEquals(StatusPedido.PENDENTE, StatusPedido.values()[0]);
    }

    @Test
    void devePagoSerOSegundo() {
        assertEquals(StatusPedido.PAGO, StatusPedido.values()[1]);
    }

    @Test
    void deveEmPreparoSerOTerceiro() {
        assertEquals(StatusPedido.EM_PREPARO, StatusPedido.values()[2]);
    }

    @Test
    void deveFinalizadoSerOQuarto() {
        assertEquals(StatusPedido.FINALIZADO, StatusPedido.values()[3]);
    }

    @Test
    void deveConverterTextoParaPendente() {
        assertEquals(StatusPedido.PENDENTE, StatusPedido.valueOf("PENDENTE"));
    }

    @Test
    void deveConverterTextoParaPago() {
        assertEquals(StatusPedido.PAGO, StatusPedido.valueOf("PAGO"));
    }

    @Test
    void deveConverterTextoParaEmPreparo() {
        assertEquals(StatusPedido.EM_PREPARO, StatusPedido.valueOf("EM_PREPARO"));
    }

    @Test
    void deveConverterTextoParaFinalizado() {
        assertEquals(StatusPedido.FINALIZADO, StatusPedido.valueOf("FINALIZADO"));
    }

}
