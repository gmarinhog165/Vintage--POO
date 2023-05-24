import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SapatilhaPremium extends Sapatilha implements Premium {

    public SapatilhaPremium(){
        super();
    }

    /**
     * Construtor para Sapatilha Premium que são novas.
     * @param novo
     * @param desc
     * @param marca
     * @param preco_base
     * @param transportadora
     * @param tamanho
     * @param atacadores
     * @param cor
     * @param colecao
     */
    public SapatilhaPremium(boolean novo, String desc, String marca, double preco_base, Transportadora transportadora, double tamanho, boolean atacadores, String cor, LocalDate colecao){
        super(novo, desc, marca,preco_base,transportadora, tamanho, atacadores,cor,colecao);
        setPreco_curr(calculaPreco());
    }

    /**
     * Construtor para Sapatilha Premium que não são novas.
     * @param novo
     * @param n_donos
     * @param estado
     * @param desc
     * @param marca
     * @param preco_base
     * @param transportadora
     * @param tamanho
     * @param atacadores
     * @param cor
     * @param colecao
     */
    public SapatilhaPremium(boolean novo, int n_donos, Estado estado, String desc, String marca, double preco_base, Transportadora transportadora, double tamanho, boolean atacadores, String cor, LocalDate colecao) {
        super(novo, n_donos, estado, desc, marca, preco_base,transportadora,tamanho,atacadores,cor,colecao);
        setPreco_curr(calculaPreco());
    }

    public SapatilhaPremium(SapatilhaPremium o){
        super(o);
        setPreco_curr(calculaPreco());
    }

    public SapatilhaPremium clone(){
        return new SapatilhaPremium(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getCodAlfaNum() + "--");
        sb.append("Sapatilha Premium " + this.getMarca());
        sb.append(", Novo: " + this.isNovo());
        if(!isNovo()) {
            sb.append(", Estado: " + this.getEstado());
            sb.append(", Nº de donos: " + this.getN_donos());
        }
        sb.append(", Preço Base: " + this.getPreco_base());
        sb.append(", Descrição: " + this.getDesc());
        sb.append(", Tamanho: " + this.getTamanho());
        sb.append(", Atacadores: " + this.isAtacadores());
        sb.append(", Cor: " + this.getCor());
        sb.append(", Coleção: " + this.getColecao());
        sb.append(", Preço Atual: " + this.getPreco_curr());
        return sb.toString();
    }

    /**
     * Método que calcula o preço de uma Sapatilha Premium.
     * @return preço atual da Sapatilha Premium.
     */
    public double calculaPreco() {
        double preco = this.getPreco_base();
        long year_interval = ChronoUnit.YEARS.between(this.getColecao(), LocalDate.now());
        preco += (double)year_interval * 0.1 * preco;

        return preco;
    }
}
