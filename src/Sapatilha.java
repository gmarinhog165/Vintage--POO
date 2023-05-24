import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Sapatilha extends Artigo {
    private double tamanho;
    private boolean atacadores;
    private String cor;
    private LocalDate colecao;
    private double preco_curr;

    public Sapatilha(){
        super();
        this.tamanho = 0;
        this.atacadores = false;
        this.cor = "";
        this.colecao = LocalDate.now();
        this.preco_curr = getPreco_base();
    }

    /**
     * Construtor para Sapatilhas que são novas.
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
    public Sapatilha(boolean novo, String desc, String marca, double preco_base, Transportadora transportadora, double tamanho, boolean atacadores, String cor, LocalDate colecao) {
        super(novo, desc, marca,preco_base,transportadora);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.colecao = colecao;
        this.preco_curr = calculaPrecoDesconto();
    }

    /**
     * Construtor para Sapatilhas que não são novas.
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
    public Sapatilha(boolean novo, int n_donos, Estado estado, String desc, String marca, double preco_base, Transportadora transportadora, double tamanho, boolean atacadores, String cor, LocalDate colecao) {
        super(novo, n_donos, estado, desc, marca, preco_base,transportadora);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.colecao = colecao;
        this.preco_curr = calculaPrecoDesconto();
    }

    public Sapatilha(Sapatilha o) {
        super(o);
        this.tamanho = o.getTamanho();
        this.atacadores = o.isAtacadores();
        this.cor = o.getCor();
        this.colecao = o.getColecao();
        this.preco_curr = o.getPreco_curr();
    }

    public double getPreco_curr() {
        return this.preco_curr;
    }

    public void setPreco_curr(double preco_curr) {
        this.preco_curr = preco_curr;
    }

    public double getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isAtacadores() {
        return this.atacadores;
    }

    public void setAtacadores(boolean atacadores) {
        this.atacadores = atacadores;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public LocalDate getColecao() {
        return this.colecao;
    }

    public void setColecao(LocalDate colecao) {
        this.colecao = colecao;
    }

    /**
     * Método que calcula o preço de venda da Sapatilha tendo como ponto de partida o valor base da Sapatilha.
     * @return preço base se não entrar nas condições ou preço com desconto caso sim.
     */
    private double calculaPrecoDesconto() {
        double preco = this.getPreco_base();
        int donos = this.getN_donos();
        long year_interval = ChronoUnit.YEARS.between(this.colecao, LocalDate.now());
        if (year_interval >= 1 && this.getEstado() != null) {
            switch (this.getEstado()) {
                case PESSIMO:
                    preco -= (preco/donos)*(1.0/5.0);
                    break;
                case MAU:
                    preco -= (preco/donos)*(1.0/4.0);
                    break;
                case RAZOAVEL:
                    preco -= (preco/donos)*(1.0/3.0);
                    break;
                case BOM:
                    preco -= (preco/donos)*(1.0/2.0);
                    break;
                case MUITO_BOM:
                    preco -= (preco/donos);
                    break;
            }
        }
        else if(this.tamanho > 45 && this.getEstado() == null){
            preco -= preco*(2.0/3.0);
        }
        return preco;
    }

    public Sapatilha clone(){
        return new Sapatilha(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getCodAlfaNum() + "--");
        sb.append("Sapatilha " + this.getMarca());
        sb.append(", Novo: " + this.isNovo());
        if(!isNovo()) {
            sb.append(", Estado: " + this.getEstado());
            sb.append(", Nº de donos: " + this.getN_donos());
        }
        sb.append(", Preço Base: " + this.getPreco_base());
        sb.append(", Descrição: " + this.getDesc());
        sb.append(", Tamanho: " + this.tamanho);
        sb.append(", Atacadores: " + this.atacadores);
        sb.append(", Cor: " + this.cor);
        sb.append(", Coleção: " + this.colecao);
        sb.append(", Preço Atual: " + this.preco_curr);
        return sb.toString();
    }
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        if (!super.equals(o)) return false;
        Sapatilha sapatilha = (Sapatilha) o;
        return Double.compare(sapatilha.tamanho, tamanho) == 0 &&
                atacadores == sapatilha.atacadores &&
                Double.compare(sapatilha.preco_curr, preco_curr) == 0 &&
                this.cor.equals(sapatilha.getCor()) &&
                this.colecao.equals(sapatilha.getColecao());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(tamanho);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (atacadores ? 1 : 0);
        result = prime * result + ((cor == null) ? 0 : cor.hashCode());
        result = prime * result + ((colecao == null) ? 0 : colecao.hashCode());
        temp = Double.doubleToLongBits(preco_curr);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Método que cria uma String para escrever o objeto Sapatilha à venda em ficheiro de texto.
     * @return String com todos os campos.
     */
    public String toLogVender(){
        if(super.isNovo()==true){
            return("Sapatilha_vender:" + super.isNovo() + "," + this.getDesc() + "," + this.getMarca() + "," + this.getPreco_base() + "," +this.getTamanho() + "," + this.isAtacadores() + "," + this.getCor() + "," + this.getColecao() + "," + this.getTransp().getNome());
        }
        else{
            return("Sapatilha_vender:" + super.isNovo() + "," + this.getN_donos() + "," + this.getEstado() + "," + this.getDesc() + "," + this.getMarca() + "," + this.getPreco_base() + "," + this.getTamanho() + "," + this.isAtacadores() + "," + this.getCor() + "," + this.getColecao() + "," + this.getTransp().getNome());
        }
    }

    /**
     * Método que cria uma String para escrever o objeto Sapatilha vendida em ficheiro de texto.
     * @return String com todos os campos.
     */
    public String toLogVendidos(){
        if(super.isNovo()==true){
            return("Sapatilha_vendida:" + super.isNovo() + "," + this.getDesc() + "," + this.getMarca() + "," + this.getPreco_base() + "," +this.getTamanho() + "," + this.isAtacadores() + "," + this.getCor() + "," + this.getColecao() + "," + this.getTransp().getNome());
        }
        else{
            return("Sapatilha_vendida:" + super.isNovo() + "," + this.getN_donos() + "," + this.getEstado() + "," + this.getDesc() + "," + this.getMarca() + "," + this.getPreco_base() + "," + this.getTamanho() + "," + this.isAtacadores() + "," + this.getCor() + "," + this.getColecao() + "," + this.getTransp().getNome());
        }
    }
}
