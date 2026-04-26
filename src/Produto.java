import java.math.BigDecimal;
import java.util.Objects;

/**
 * Representa um produto disponível no cardápio da cafeteria.
 *
 * Um produto possui nome e preço unitário. A classe valida os dados de
 * entrada para impedir produtos sem nome ou com preço inválido.
 */
public class Produto {
    private final String nome;
    private final BigDecimal preco;

    /**
     * Cria um produto com nome e preço unitário.
     *
     * @param nome nome do produto
     * @param preco preço unitário do produto
     * @throws IllegalArgumentException se o nome estiver vazio ou o preço não for maior que zero
     */
    public Produto(String nome, BigDecimal preco) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto deve ser informado.");
        }

        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do produto deve ser maior que zero.");
        }

        this.nome = nome.trim();
        this.preco = preco;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna o preço unitário do produto.
     *
     * @return preço unitário
     */
    public BigDecimal getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return nome + " - " + Moeda.formatar(preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, preco);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Produto)) {
            return false;
        }

        Produto other = (Produto) obj;
        return Objects.equals(nome, other.nome) && Objects.equals(preco, other.preco);
    }
}
