import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Mala extends Artigo {
    public enum Dim{
        PEQUENO,
        MEDIO,
        GRANDE
    }

    private Dim dimensao;
    private String material;
    private LocalDate colecao;
    private double preco_curr;

    public Mala(){
        this.dimensao = Dim.PEQUENO;
        this.material = "";
        this.colecao = LocalDate.now();
        this.preco_curr = this.getPreco_base();
    }

    /**
     * Construtor para Malas que são novas.
     * @param novo
     * @param desc
     * @param marca
     * @param preco_base
     * @param transportadora
     * @param dimensao
     * @param material
     * @param colecao
     */
    public Mala(boolean novo, String desc, String marca , double preco_base, Transportadora transportadora, Dim dimensao, String material, LocalDate colecao) {
        super(novo, desc, marca, preco_base,transportadora);
        this.dimensao = dimensao;
        this.material = material;
        this.colecao = colecao;
        this.preco_curr = calculaPrecoDesconto();
    }

    /**
     * Construtor para Malas que não são novas.
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
    public Mala(boolean novo, int n_donos, Estado estado, String desc, String marca, double preco_base, Transportadora transportadora, Dim dimensao, String material, LocalDate colecao) {
        super(novo, n_donos, estado, desc, marca, preco_base,transportadora);
        this.dimensao = dimensao;
        this.material = material;
        this.colecao = colecao;
        this.preco_curr = calculaPrecoDesconto();
    }

    public Mala(Mala o) {
        super(o);
        this.dimensao = o.getDimensao();
        this.material = o.getMaterial();
        this.colecao = o.getColecao();
        this.preco_curr = o.getPreco_curr();
    }

    public Dim getDimensao() {
        return this.dimensao;
    }

    public void setDimensao(Dim dimensao) {
        this.dimensao = dimensao;
    }

    public String getMaterial() {
        return this.material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public LocalDate getColecao() {
        return this.colecao;
    }

    public void setColecao(LocalDate colecao) {
        this.colecao = colecao;
    }

    public double getPreco_curr() {
        return this.preco_curr;
    }

    public void setPreco_curr(double preco_curr) {
        this.preco_curr = preco_curr;
    }

    /**
     * Método que calcula o preço de venda da Mala tendo como ponto de partida o valor base da Mala.
     * @return
     */
    private double calculaPrecoDesconto(){
        double preco = this.getPreco_base();
        long year_interval = ChronoUnit.YEARS.between(this.colecao, LocalDate.now());
            switch (this.dimensao) {
                case PEQUENO:
                    preco -= preco * 0.25;
                    break;
                case MEDIO:
                    preco -= preco * 0.15;
                    break;
                case GRANDE:
                    preco -= preco * 0.1;
                    break;
            }
        return preco;
    }

    public Mala clone(){
        return new Mala(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getCodAlfaNum()+"--");
        sb.append("Mala " + this.getMarca());
        sb.append(", Novo: " + isNovo());
        if(!isNovo()) {
            sb.append(", Estado: " + this.getEstado());
            sb.append(", Nº de donos: " + this.getN_donos());
        }
        sb.append(", Preço Base: " + this.getPreco_base());
        sb.append(", Descrição: " + this.getDesc());
        sb.append(", Dimensão: " + this.dimensao);
        sb.append(", Material: " + this.material);
        sb.append(", Coleção: " + this.colecao);
        sb.append(", Preço Atual: " + this.preco_curr);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        if (!super.equals(o)) return false;
        Mala mala = (Mala) o;
        return Double.compare(mala.getPreco_curr(), preco_curr) == 0 &&
                this.colecao.equals(mala.getColecao()) &&
                this.dimensao == mala.getDimensao() &&
                this.material.equals(mala.getMaterial());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((dimensao == null) ? 0 : dimensao.hashCode());
        result = prime * result + ((material == null) ? 0 : material.hashCode());
        result = prime * result + ((colecao == null) ? 0 : colecao.hashCode());
        long temp = Double.doubleToLongBits(preco_curr);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Método que cria uma String para escrever o objeto Mala à venda em ficheiro de texto.
     * @return String com todos os campos.
     */
    public String toLogVender() {

        if (super.isNovo() == true) {
            return ("Mala_vender:" + super.isNovo() + "," + this.getDesc() + "," + this.getMarca() + "," +  this.getPreco_base()  + "," +  this.getDimensao()  + "," +  this.getMaterial()  + "," +  this.getColecao() + "," +  this.getTransp().getNome());
        } else {
            return ("Mala_vender:" + super.isNovo() + "," + this.getN_donos() + "," + this.getEstado() + "," + this.getDesc() + "," + this.getMarca() + "," + this.getPreco_base() + "," + this.getDimensao() + "," + this.getMaterial() + "," + this.getColecao() + "," + this.getTransp().getNome());
        }
    }

    /**
     * Método que cria uma String para escrever o objeto Mala vendida em ficheiro de texto.
     * @return String com todos os campos.
     */
    public String toLogVendidos() {

        if (super.isNovo() == true) {
            return ("Mala_vendida:" + super.isNovo() + "," + this.getDesc() + "," + this.getMarca() + "," +  this.getPreco_base()  + "," +  this.getDimensao()  + "," +  this.getMaterial()  + "," +  this.getColecao() + "," +  this.getTransp().getNome());
        } else {
            return ("Mala_vendida:" + super.isNovo() + "," + this.getN_donos() + "," + this.getEstado() + "," + this.getDesc() + "," + this.getMarca() + "," + this.getPreco_base() + "," + this.getDimensao() + "," + this.getMaterial() + "," + this.getColecao() + "," + this.getTransp().getNome());
        }
    }
}
