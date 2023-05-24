import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Random;

public abstract class Artigo implements Serializable {

    public enum Estado {
        PESSIMO,
        MAU,
        RAZOAVEL,
        BOM,
        MUITO_BOM
    };

    private boolean novo;
    private int n_donos;
    private Estado estado;
    private String desc;
    private String marca;
    private String codAlfaNum;
    private double preco_base;
    private Transportadora transp;

    public Artigo(){
        this.novo = false;
        this.n_donos = 0;
        this.estado = Estado.PESSIMO;
        this.marca = "";
        this.desc = "";
        this.codAlfaNum = "";
        this.preco_base = 0;
        this.transp = new Transportadora();
    }

    /**
     * Construtor para Artigos que são novos.
     * @param novo
     * @param desc
     * @param marca
     * @param preco_base
     * @param transportadora
     */
    public Artigo(boolean novo, String desc, String marca, double preco_base,Transportadora transportadora) {
        this.novo = novo;
        this.desc = desc;
        this.marca = marca;
        this.codAlfaNum = alfanumericalGenerator();
        this.preco_base = preco_base;
        this.transp = transportadora.clone();
    }

    /**
     * Construtor para Artigos que não são novos.
     * @param novo
     * @param n_donos
     * @param estado
     * @param desc
     * @param marca
     * @param preco_base
     * @param transportadora
     */
    public Artigo(boolean novo, int n_donos, Estado estado, String desc,String marca, double preco_base, Transportadora transportadora) {
        this.novo = novo;
        this.n_donos = n_donos;
        this.estado = estado;
        this.desc = desc;
        this.marca = marca;
        this.codAlfaNum = alfanumericalGenerator();
        this.preco_base = preco_base;
        this.transp = transportadora.clone();
    }

    public Artigo(Artigo o){
        this.novo = o.isNovo();
        this.n_donos = o.getN_donos();
        this.estado = o.getEstado();
        this.desc = o.getDesc();
        this.marca = o.getMarca();
        this.codAlfaNum = o.getCodAlfaNum();
        this.preco_base = o.getPreco_base();
        this.transp = o.getTransp();
    }

    public Transportadora getTransp() {
        return this.transp.clone();
    }

    public void setTransp(Transportadora transp) {
        this.transp = transp.clone();
    }

    public int getN_donos() {
        return this.n_donos;
    }

    public void setN_donos(int n_donos) {
        this.n_donos = n_donos;
    }

    public boolean isNovo() {
        return this.novo;
    }

    public void setNovo(boolean novo) {
        this.novo = novo;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodAlfaNum() {
        return this.codAlfaNum;
    }

    public void setCodAlfaNum(String codAlfaNum) {
        this.codAlfaNum = codAlfaNum;
    }

    public double getPreco_base() {
        return this.preco_base;
    }

    public void setPreco_base(double preco_base) {
        this.preco_base = preco_base;
    }

    /**
     * Métodos abstratos para serem implementados nas suas subclasses.
     */
    public abstract Artigo clone();
    public abstract String toLogVendidos();
    public abstract String toLogVender();
    public abstract String toString();
    public abstract double getPreco_curr();


    /**
     * Método que gera um código alfanumérico aleatório
     * @return código alfanumérico
     */
    public String alfanumericalGenerator() {

        String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int LENGTH = 12;


        StringBuilder sb = new StringBuilder(LENGTH);
        Random random = new SecureRandom();
        random.setSeed(System.currentTimeMillis());

        for (int i = 0; i < LENGTH; i++) {
            sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }

        return sb.toString();
    }


    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        Artigo l = (Artigo) o;
        return  this.novo == l.isNovo() &&
                this.estado == l.getEstado() &&
                this.n_donos == l.getN_donos() &&
                Double.compare(this.preco_base, l.getPreco_base()) == 0 &&
                this.desc.equals(l.getDesc()) &&
                this.marca.equals(l.getMarca()) &&
                this.codAlfaNum.equals(l.getCodAlfaNum()) &&
                this.transp.equals(l.getTransp());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 17;
        result = prime * result + (novo ? 1 : 0);
        result = prime * result + n_donos;
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result + ((desc == null) ? 0 : desc.hashCode());
        result = prime * result + ((marca == null) ? 0 : marca.hashCode());
        result = prime * result + ((codAlfaNum == null) ? 0 : codAlfaNum.hashCode());
        long temp = Double.doubleToLongBits(preco_base);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((transp == null) ? 0 : transp.hashCode());
        return result;
    }
}
