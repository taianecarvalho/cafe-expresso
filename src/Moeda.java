import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utilitário para converter e exibir valores monetários no padrão brasileiro.
 */
public class Moeda {
    private Moeda() {
    }

    /**
     * Converte um texto monetário para {@link BigDecimal}.
     *
     * Aceita valores como 5,50, 5.50, R$ 5,50 e 1.234,56.
     *
     * @param valor texto informado pelo usuário
     * @return valor convertido
     * @throws NumberFormatException se o texto não representar um valor monetário válido
     */
    public static BigDecimal converterParaBigDecimal(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new NumberFormatException("Valor vazio.");
        }

        String valorNormalizado = valor.trim()
                .replace("R$", "")
                .replace(" ", "");

        if (valorNormalizado.contains(",")) {
            valorNormalizado = valorNormalizado.replace(".", "").replace(",", ".");
        }

        return new BigDecimal(valorNormalizado);
    }

    /**
     * Formata um valor monetário no padrão brasileiro.
     *
     * @param valor valor a ser formatado
     * @return valor formatado com R$ e virgula decimal
     */
    public static String formatar(BigDecimal valor) {
        if (valor == null) {
            return "R$ 0,00";
        }

        return "R$ " + valor.setScale(2, RoundingMode.HALF_UP).toPlainString().replace(".", ",");
    }
}
