import java.io.Serializable;

public class Transportadora implements Serializable {
    public static final double PEQUENAS = 3.99;
    public static final double MEDIAS = 5.99;
    public static final double GRANDES = 10.99;
    public static final double IMPOSTO = 0.15;
    private double lucro;
    private String nome;
    private double dinheiro_feito;

    public Transportadora() {
        this.nome = "boua";
        this.lucro = 0;
        this.dinheiro_feito = 0;
    }

    /**
     * Construtor para criar uma Transportadora nova.
     * @param nome
     * @param lucro
     */
    public Transportadora(String nome, double lucro) {
        this.nome = nome;
        this.lucro = lucro;
        this.dinheiro_feito = 0.0;
    }

    /**
     * Construtor para criar uma Transportadora que já tenha atividade na Vintage.
     * @param lucro
     * @param nome
     * @param dinheiro_feito
     */
    public Transportadora(double lucro, String nome, double dinheiro_feito) {
        this.nome = nome;
        this.lucro = lucro;
        this.dinheiro_feito = dinheiro_feito;
    }

    public Transportadora(Transportadora o) {
        this.lucro = o.getLucro();
        this.nome = o.getNome();
        this.dinheiro_feito = o.getDinheiro_feito();
    }


    public double getDinheiro_feito() {
        return dinheiro_feito;
    }

    public void setDinheiro_feito(double dinheiro_feito) {
        this.dinheiro_feito = dinheiro_feito;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
    }

    /**
     * Método que calcula o preço de transporte da transportadora para a encomenda.
     * @param n_transp_apareceu -> número de vezes que dada transportadora apareceu numa encomenda.
     * @return preço de transporte
     */
    public double precoTransporte(int n_transp_apareceu) {
        double base = 0;
        if (n_transp_apareceu == 1) base = PEQUENAS;
        else if (n_transp_apareceu >= 2 && n_transp_apareceu <= 5) base = MEDIAS;
        else if (n_transp_apareceu > 5) base = GRANDES;
        double preco = base * (1.0 + this.lucro) * (1.0 + IMPOSTO) * 0.9;
        return preco;
    }

    public Transportadora clone() {
        return new Transportadora(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transportadora :: " + this.nome);
        sb.append(", Lucro: " + this.lucro);
        sb.append(", Dinheiro em Vendas: " + this.dinheiro_feito);
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        Transportadora l = (Transportadora) o;
        return Double.compare(this.lucro, l.getLucro()) == 0 &&
                this.nome.equals(l.getNome()) &&
                Double.compare(this.dinheiro_feito, l.getDinheiro_feito()) == 0;

    }

    public String toLog() {
        return ("Transportadora:" + this.getNome() + "," + this.getLucro() + "," + this.getDinheiro_feito());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(dinheiro_feito);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lucro);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    /**
     * Método que atualiza as receitas da transportadora.
     * @param n -> número de vezes que dada transportadora apareceu numa encomenda.
     */
    public void addLucro(int n){
        this.dinheiro_feito += precoTransporte(n);
    }
}
