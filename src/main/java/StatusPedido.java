/**
 * Define os status possíveis para o ciclo de vida de um pedido.
 */
public enum StatusPedido {
    /**
     * Pedido criado e ainda não pago.
     */
    PENDENTE,

    /**
     * Pagamento registrado e pedido liberado para preparo.
     */
    PAGO,

    /**
     * Pedido em preparo pela cafeteria.
     */
    EM_PREPARO,

    /**
     * Pedido finalizado e entregue ao cliente.
     */
    FINALIZADO
}
