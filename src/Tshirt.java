public class Tshirt extends Artigo{
    public enum Tamanho{
        S,
        M,
        L,
        XL
    }
    public enum Padrao{
        RISCAS,
        LISO,
        PALMEIRAS
    }

    private Tamanho tamanho;
    private Padrao padrao;
    private double preco_curr;

    public Tshirt(){
        super();
        this.tamanho = Tamanho.S;
        this.padrao = Padrao.RISCAS;
        this.preco_curr = getPreco_base();
    }

    /**
     * Construtor para T-Shirts que são novas.
     * @param novo
     * @param desc
     * @param marca
     * @param preco_base
     * @param transportadora
     * @param tamanho
     * @param padrao
     */
    public Tshirt(boolean novo, String desc, String marca, double preco_base, Transportadora transportadora, Tamanho tamanho, Padrao padrao) {
        super(novo, desc, marca, preco_base,transportadora);
        this.tamanho = tamanho;
        this.padrao = padrao;
        this.preco_curr = calculaPrecoDesconto();
    }


    /**
     * Construtor para T-Shirts que não são novas.
     * @param novo
     * @param n_donos
     * @param estado
     * @param desc
     * @param marca
     * @param preco_base
     * @param transportadora
     * @param tamanho
     * @param padrao
     */
    public Tshirt(boolean novo, int n_donos, Estado estado, String desc, String marca, double preco_base, Transportadora transportadora, Tamanho tamanho, Padrao padrao) {
        super(novo, n_donos, estado, desc, marca, preco_base,transportadora);
        this.tamanho = tamanho;
        this.padrao = padrao;
        this.preco_curr = calculaPrecoDesconto();
    }

    public Tshirt(Tshirt o) {
        super(o);
        this.tamanho = o.getTamanho();
        this.padrao = o.getPadrao();
        this.preco_curr = o.getPreco_curr();
    }

    public Tamanho getTamanho() {
        return this.tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Padrao getPadrao() {
        return this.padrao;
    }

    public void setPadrao(Padrao padrao) {
        this.padrao = padrao;
    }

    public double getPreco_curr() {
        return this.preco_curr;
    }

    public void setPreco_curr(double preco_curr) {
        this.preco_curr = preco_curr;
    }

    /**
     * Método que calcula o preço de venda da T-Shirt tendo como ponto de partida o valor base da T-Shirt.
     * @return preço base se não entrar nas condições ou preço com desconto caso sim.
     */
    private double calculaPrecoDesconto(){
        double preco = this.getPreco_base();
        if(this.padrao != Padrao.LISO && !this.isNovo()){
            preco *= 0.5;
        }
        return preco;
    }

    public Tshirt clone(){
        return new Tshirt(this);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getCodAlfaNum() + "--");
        sb.append("T-shirt ");
        sb.append(getMarca());
        sb.append(", Novo: " + isNovo());
        if(!isNovo()) {
            sb.append(", Estado: " + getEstado());
            sb.append(", Nº de donos: " + getN_donos());
        }
        sb.append(", Preço Base: " + getPreco_base());
        sb.append(", Descrição: " + getDesc());
        sb.append(", Tamanho: " + this.tamanho);
        sb.append(", Padrão: " + this.padrao);
        sb.append(", Preço Atual: " + this.preco_curr);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;
        if (!super.equals(o)) return false;
        Tshirt tshirt = (Tshirt) o;
        return Double.compare(tshirt.preco_curr, preco_curr) == 0 &&
                this.padrao == tshirt.getPadrao() &&
                this.tamanho == tshirt.getTamanho();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode(); // código hash da classe superior
        result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
        result = prime * result + ((padrao == null) ? 0 : padrao.hashCode());
        long temp = Double.doubleToLongBits(preco_curr);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Método que cria uma String para escrever o objeto T-Shirt à venda em ficheiro de texto.
     * @return String com todos os campos.
     */
    public String toLogVender(){
        if(super.isNovo()==true){
            return("Tshirt_vender:" + super.isNovo() + "," + this.getDesc() + "," + this.getMarca() + "," + this.getPreco_base() + "," + this.tamanho + "," + this.getPadrao() + "," + this.getTransp().getNome());
        }
        else{
            return("Tshirt_vender:" + super.isNovo() + "," + this.getN_donos() + "," + this.getEstado() + "," + this.getDesc() + "," + this.getMarca() + "," + this.getPreco_base() + "," + this.getTransp().getNome() + "," + this.getTamanho() + "," + this.getPadrao());
        }
    }

    /**
     * Método que cria uma String para escrever o objeto T-Shirt vendida em ficheiro de texto.
     * @return String com todos os campos.
     */
    public String toLogVendidos(){
        if(super.isNovo()==true){
            return("Tshirt_vendida:" + super.isNovo() + "," + this.getDesc() + "," + this.getMarca() + "," + this.getPreco_base() + "," + this.tamanho + "," + this.getPadrao() + "," + this.getTransp().getNome());
        }
        else{
            return("Tshirt_vendida:" + super.isNovo() + "," + this.getN_donos() + "," + this.getEstado() + "," + this.getDesc() + "," + this.getMarca() + "," + this.getPreco_base() + "," + this.getTransp().getNome() + "," + this.getTamanho() + "," + this.getPadrao());
        }
    }
}
