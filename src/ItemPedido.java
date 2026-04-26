import java.math.BigDecimal;

/**
 * Representa um item incluído em um pedido.
 *
 * O item relaciona um {@link Produto} com a quantidade escolhida pelo
 * cliente e calcula o subtotal correspondente.
 */
public class ItemPedido {
    private final Produto produto;
    private final int quantidade;

    /**
     * Cria um item de pedido.
     *
     * @param produto produto escolhido
     * @param quantidade quantidade solicitada
     * @throws IllegalArgumentException se o produto for nulo ou a quantidade não for positiva
     */
    public ItemPedido(Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("O produto deve ser informado.");
        }

        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        this.produto = produto;
        this.quantidade = quantidade;
    }

    /**
     * Retorna o produto deste item.
     *
     * @return produto escolhido
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * Retorna a quantidade deste item.
     *
     * @return quantidade solicitada
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Calcula o subtotal do item.
     *
     * @return preço unitário do produto multiplicado pela quantidade
     */
    public BigDecimal calcularSubtotal() {
        return produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }

    @Override
    public String toString() {
        return quantidade + "x " + produto.getNome() + " = " + Moeda.formatar(calcularSubtotal());
    }
}
