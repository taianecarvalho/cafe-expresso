import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa um pedido realizado no sistema de autoatendimento.
 *
 * O pedido funciona como um carrinho de compras: armazena itens, calcula o
 * valor total e controla o ciclo de status do atendimento.
 */
public class Pedido {
    private final List<ItemPedido> itens;
    private StatusPedido status;

    /**
     * Cria um pedido vazio com status inicial.
     */
    public Pedido() {
        this.itens = new ArrayList<>();
        this.status = StatusPedido.PENDENTE;
    }

    /**
     * Adiciona um produto ao pedido com a quantidade informada.
     *
     * @param produto produto a ser adicionado
     * @param quantidade quantidade desejada
     * @throws IllegalStateException se o pedido não estiver pendente
     * @throws IllegalArgumentException se o produto ou a quantidade forem inválidos
     */
    public void adicionarItem(Produto produto, int quantidade) {
        if (status != StatusPedido.PENDENTE) {
            throw new IllegalStateException("Não é possível adicionar itens após o pagamento.");
        }

        itens.add(new ItemPedido(produto, quantidade));
    }

    /**
     * Calcula o valor total do pedido.
     *
     * @return soma dos subtotais de todos os itens
     */
    public BigDecimal calcularTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (ItemPedido item : itens) {
            total = total.add(item.calcularSubtotal());
        }

        return total;
    }

    /**
     * Registra o pagamento do pedido.
     *
     * @throws IllegalStateException se o pedido não tiver itens ou se a transição de status for inválida
     */
    public void registrarPagamento() {
        if (itens.isEmpty()) {
            throw new IllegalStateException("Não é possível pagar um pedido sem itens.");
        }

        alterarStatus(StatusPedido.PAGO);
    }

    /**
     * Altera o pedido para o status de preparo.
     *
     * @throws IllegalStateException se o pedido ainda não estiver pago
     */
    public void iniciarPreparo() {
        alterarStatus(StatusPedido.EM_PREPARO);
    }

    /**
     * Finaliza o pedido após o preparo.
     *
     * @throws IllegalStateException se o pedido não estiver em preparo
     */
    public void finalizar() {
        alterarStatus(StatusPedido.FINALIZADO);
    }

    /**
     * Altera o status do pedido respeitando o fluxo definido pelo sistema.
     *
     * @param novoStatus novo status desejado
     * @throws IllegalArgumentException se o novo status for nulo
     * @throws IllegalStateException se a transição de status for inválida
     */
    public void alterarStatus(StatusPedido novoStatus) {
        if (novoStatus == null) {
            throw new IllegalArgumentException("O novo status deve ser informado.");
        }

        if (!podeAlterarPara(novoStatus)) {
            throw new IllegalStateException("Transição de status inválida: " + status + " para " + novoStatus + ".");
        }

        this.status = novoStatus;
    }

    /**
     * Verifica se o pedido pode mudar do status atual para o status informado.
     *
     * @param novoStatus status de destino
     * @return true se a transição for permitida; caso contrário, false
     */
    public boolean podeAlterarPara(StatusPedido novoStatus) {
        if (novoStatus == null || novoStatus == status) {
            return false;
        }

        switch (status) {
            case PENDENTE:
                return novoStatus == StatusPedido.PAGO;
            case PAGO:
                return novoStatus == StatusPedido.EM_PREPARO;
            case EM_PREPARO:
                return novoStatus == StatusPedido.FINALIZADO;
            case FINALIZADO:
                return false;
            default:
                return false;
        }
    }

    /**
     * Retorna os itens do pedido.
     *
     * A lista retornada não pode ser alterada diretamente.
     *
     * @return lista imutável de itens
     */
    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    /**
     * Retorna o status atual do pedido.
     *
     * @return status atual
     */
    public StatusPedido getStatus() {
        return status;
    }
}
