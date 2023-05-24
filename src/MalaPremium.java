import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MalaPremium extends Mala implements Premium{

    public MalaPremium(){
        super();
    }

    /**
     * Construtor para Mala Premium que são novas.
     * @param novo
     * @param desc
     * @param marca
     * @param preco_base
     * @param transportadora
     * @param dimensao
     * @param material
     * @param colecao
     */
    public MalaPremium(boolean novo, String desc, String marca , double preco_base, Transportadora transportadora, Dim dimensao, String material, LocalDate colecao){
        super(novo, desc, marca, preco_base,transportadora,dimensao,material,colecao);
        setPreco_curr(calculaPreco());
    }

    /**
     * Construtor para Mala Premium que não são novas.
     * @param novo
     * @param n_donos
     * @param estado
     * @param desc
     * @param marca
     * @param preco_base
     * @param transportadora
     * @param dimensao
     * @param material
     * @param colecao
     */
    public MalaPremium(boolean novo, int n_donos, Estado estado, String desc, String marca, double preco_base, Transportadora transportadora, Dim dimensao, String material, LocalDate colecao){
        super(novo, n_donos, estado, desc, marca, preco_base,transportadora,dimensao,material,colecao);
        setPreco_curr(calculaPreco());
    }

    public MalaPremium(MalaPremium o){
        super(o);
        setPreco_curr(calculaPreco());
    }

    public MalaPremium clone(){
        return new MalaPremium(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getCodAlfaNum()+"--");
        sb.append("MalaPremium " + this.getMarca());
        sb.append(", Novo: " + isNovo());
        if(!isNovo()) {
            sb.append(", Estado: " + this.getEstado());
            sb.append(", Nº de donos: " + this.getN_donos());
        }
        sb.append(", Preço Base: " + this.getPreco_base());
        sb.append(", Descrição: " + this.getDesc());
        sb.append(", Dimensão: " + this.getDimensao());
        sb.append(", Material: " + this.getMaterial());
        sb.append(", Coleção: " + this.getColecao());
        sb.append(", Preço Atual: " + this.getPreco_curr());
        return sb.toString();
    }

    /**
     * Método que calcula o preço de uma Mala Premium.
     * @return preço atual da Mala Premium.
     */
    public double calculaPreco() {
        double preco = this.getPreco_base();
        long year_interval = ChronoUnit.YEARS.between(this.getColecao(), LocalDate.now());
        preco += (double)year_interval * 0.1 * preco;
        return preco;
    }
}
