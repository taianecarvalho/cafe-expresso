import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Representa o cardápio de produtos disponíveis na cafeteria.
 *
 * Permite cadastrar produtos, listar os produtos cadastrados e buscar um
 * produto pelo nome.
 */
public class Cardapio {
    private final List<Produto> produtos;

    /**
     * Cria um cardápio vazio.
     */
    public Cardapio() {
        this.produtos = new ArrayList<>();
    }

    /**
     * Cadastra um novo produto no cardápio.
     *
     * @param nome nome do produto
     * @param preco preço unitário do produto
     * @return produto cadastrado
     * @throws IllegalArgumentException se os dados do produto forem inválidos
     */
    public Produto cadastrarProduto(String nome, BigDecimal preco) {
        Produto produto = new Produto(nome, preco);
        produtos.add(produto);
        return produto;
    }

    /**
     * Lista os produtos cadastrados.
     *
     * A lista retornada não pode ser alterada diretamente.
     *
     * @return lista imutável de produtos
     */
    public List<Produto> listarProdutos() {
        return Collections.unmodifiableList(produtos);
    }

    /**
     * Busca um produto pelo nome, desconsiderando diferenças entre letras
     * maiúsculas, minúsculas e acentos.
     *
     * @param nome nome a ser pesquisado
     * @return produto encontrado, ou vazio se nenhum produto corresponder ao nome
     */
    public Optional<Produto> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return Optional.empty();
        }

        String nomeNormalizado = normalizarNome(nome);

        for (Produto produto : produtos) {
            if (normalizarNome(produto.getNome()).equals(nomeNormalizado)) {
                return Optional.of(produto);
            }
        }

        return Optional.empty();
    }

    private String normalizarNome(String nome) {
        return Normalizer.normalize(nome.trim(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }
}
