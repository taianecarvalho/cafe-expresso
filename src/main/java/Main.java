import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Menu de console do sistema Café Expresso.
 *
 * O console separa a interface do cliente, usada no totem, da interface do
 * atendente, usada no balcão/cozinha.
 */
public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Cardapio CARDAPIO = new Cardapio();
    private static final List<Pedido> PEDIDOS = new ArrayList<>();
    private static Pedido pedidoDoCliente = new Pedido();

    /**
     * Executa o menu principal do sistema.
     *
     * @param args argumentos de linha de comando não utilizados
     */
    public static void main(String[] args) {
        cadastrarProdutosIniciais();

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opção: ");
            executarOpcaoPrincipal(opcao);
        } while (opcao != 0);

        System.out.println("Sistema encerrado.");
        SCANNER.close();
    }

    private static void cadastrarProdutosIniciais() {
        CARDAPIO.cadastrarProduto("Café Expresso", new BigDecimal("5.50"));
        CARDAPIO.cadastrarProduto("Cappuccino", new BigDecimal("8.50"));
        CARDAPIO.cadastrarProduto("Pão de queijo", new BigDecimal("4.00"));
    }

    private static void exibirMenuPrincipal() {
        System.out.println();
        System.out.println("=== Café Expresso ===");
        System.out.println("1 - Interface Cliente/Totem");
        System.out.println("2 - Interface Atendente/Cozinha");
        System.out.println("0 - Sair");
    }

    private static void executarOpcaoPrincipal(int opcao) {
        switch (opcao) {
            case 1:
                executarMenuCliente();
                break;
            case 2:
                executarMenuAtendente();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private static void executarMenuCliente() {
        int opcao;
        do {
            exibirMenuCliente();
            opcao = lerInteiro("Escolha uma opção: ");
            executarOpcaoCliente(opcao);
        } while (opcao != 0);
    }

    private static void exibirMenuCliente() {
        System.out.println();
        System.out.println("=== Cliente/Totem ===");
        System.out.println("1 - Consultar cardápio");
        System.out.println("2 - Adicionar item ao pedido");
        System.out.println("3 - Ver pedido atual");
        System.out.println("4 - Pagar pedido");
        System.out.println("5 - Novo pedido");
        System.out.println("0 - Voltar");
    }

    private static void executarOpcaoCliente(int opcao) {
        try {
            switch (opcao) {
                case 1:
                    listarCardapio();
                    break;
                case 2:
                    adicionarItemAoPedido();
                    break;
                case 3:
                    exibirPedido("Pedido atual", pedidoDoCliente);
                    break;
                case 4:
                    pagarPedidoDoCliente();
                    break;
                case 5:
                    pedidoDoCliente = new Pedido();
                    System.out.println("Novo pedido iniciado.");
                    break;
                case 0:
                    break;
                default:
                System.out.println("Opção inválida.");
                    break;
            }
        } catch (IllegalArgumentException | IllegalStateException erro) {
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private static void executarMenuAtendente() {
        int opcao;
        do {
            exibirMenuAtendente();
            opcao = lerInteiro("Escolha uma opção: ");
            executarOpcaoAtendente(opcao);
        } while (opcao != 0);
    }

    private static void exibirMenuAtendente() {
        System.out.println();
        System.out.println("=== Atendente/Cozinha ===");
        System.out.println("1 - Cadastrar produto");
        System.out.println("2 - Ver fila de pedidos");
        System.out.println("3 - Iniciar preparo de pedido");
        System.out.println("4 - Finalizar pedido");
        System.out.println("0 - Voltar");
    }

    private static void executarOpcaoAtendente(int opcao) {
        try {
            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarPedidos();
                    break;
                case 3:
                    alterarPedidoParaPreparo();
                    break;
                case 4:
                    finalizarPedido();
                    break;
                case 0:
                    break;
                default:
                System.out.println("Opção inválida.");
                    break;
            }
        } catch (IllegalArgumentException | IllegalStateException erro) {
            System.out.println("Erro: " + erro.getMessage());
        }
    }

    private static void cadastrarProduto() {
        String nome = lerTexto("Nome do produto: ");
        BigDecimal preco = lerDecimal("Preço do produto: ");

        Produto produto = CARDAPIO.cadastrarProduto(nome, preco);
        System.out.println("Produto cadastrado: " + produto);
    }

    private static void listarCardapio() {
        if (CARDAPIO.listarProdutos().isEmpty()) {
            System.out.println("Cardápio vazio.");
            return;
        }

        System.out.println("Cardápio:");
        for (Produto produto : CARDAPIO.listarProdutos()) {
            System.out.println("- " + produto);
        }
    }

    private static void adicionarItemAoPedido() {
        String nome = lerTexto("Nome do produto: ");
        Optional<Produto> produto = CARDAPIO.buscarPorNome(nome);

        if (!produto.isPresent()) {
            System.out.println("Produto não encontrado no cardápio.");
            return;
        }

        int quantidade = lerInteiro("Quantidade: ");
        pedidoDoCliente.adicionarItem(produto.get(), quantidade);
        System.out.println("Item adicionado ao pedido.");
    }

    private static void pagarPedidoDoCliente() {
        pedidoDoCliente.registrarPagamento();
        PEDIDOS.add(pedidoDoCliente);

        System.out.println("Pagamento registrado.");
        System.out.println("Pedido " + PEDIDOS.size() + " enviado para a cozinha.");

        pedidoDoCliente = new Pedido();
    }

    private static void listarPedidos() {
        if (PEDIDOS.isEmpty()) {
            System.out.println("Nenhum pedido na fila.");
            return;
        }

        for (int i = 0; i < PEDIDOS.size(); i++) {
            exibirPedido("Pedido " + (i + 1), PEDIDOS.get(i));
        }
    }

    private static void alterarPedidoParaPreparo() {
        Pedido pedido = selecionarPedido();
        pedido.iniciarPreparo();
        System.out.println("Pedido enviado para preparo.");
    }

    private static void finalizarPedido() {
        Pedido pedido = selecionarPedido();
        pedido.finalizar();
        System.out.println("Pedido finalizado.");
    }

    private static Pedido selecionarPedido() {
        listarPedidos();

        if (PEDIDOS.isEmpty()) {
            throw new IllegalStateException("Não há pedidos disponíveis.");
        }

        int numeroPedido = lerInteiro("Número do pedido: ");

        if (numeroPedido < 1 || numeroPedido > PEDIDOS.size()) {
            throw new IllegalArgumentException("Pedido não encontrado.");
        }

        return PEDIDOS.get(numeroPedido - 1);
    }

    private static void exibirPedido(String titulo, Pedido pedido) {
        System.out.println();
        System.out.println(titulo);
        System.out.println("Status: " + pedido.getStatus());

        if (pedido.getItens().isEmpty()) {
            System.out.println("Pedido sem itens.");
        } else {
            System.out.println("Itens:");
            for (ItemPedido item : pedido.getItens()) {
                System.out.println("- " + item);
            }
        }

        System.out.println("Total: " + Moeda.formatar(pedido.calcularTotal()));
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return SCANNER.nextLine();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);

            try {
                return Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException erro) {
                System.out.println("Informe um número inteiro válido.");
            }
        }
    }

    private static BigDecimal lerDecimal(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String valor = SCANNER.nextLine();

            try {
                return Moeda.converterParaBigDecimal(valor);
            } catch (NumberFormatException erro) {
                System.out.println("Informe um valor monetário válido.");
            }
        }
    }
}
