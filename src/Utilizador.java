

import java.io.Serializable;
import java.util.*;

public class Utilizador implements Serializable {
    private int code;
    private String email;
    private String nome;
    private String morada;
    private int nif;
    private Map<String, Artigo> vendido;
    private Set<String> para_vender;
    private Set<Encomenda> encomendas_compradas;
    private Set<Encomenda> encomendas_vendidas;
    private double dinheiro_vendas;
    private double dinheiro_compras;

    public Utilizador(){
        this.email = "";
        this.morada = "";
        this.nome = "";
        this.nif = 0;
        this.vendido = new HashMap<>();
        this.para_vender = new HashSet<>();
        this.encomendas_compradas = new HashSet<>();
        this.encomendas_vendidas = new HashSet<>();
        this.dinheiro_compras = 0;
        this.dinheiro_vendas = 0;
    }

    public Utilizador(String email, String nome, String morada, int nif) {
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.vendido = new HashMap<>();
        this.para_vender = new HashSet<>();
        this.encomendas_compradas = new HashSet<>();
        this.encomendas_vendidas = new HashSet<>();
        this.dinheiro_vendas = 0.0;
        this.dinheiro_compras = 0.0;
    }

    public Utilizador(String email, String nome, String morada, int nif, double dinheiro_vendas, double dinheiro_compras) {
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        this.vendido = new HashMap<>();
        this.para_vender = new HashSet<>();
        this.encomendas_compradas = new HashSet<>();
        this.encomendas_vendidas = new HashSet<>();
        this.dinheiro_vendas = dinheiro_vendas;
        this.dinheiro_compras = dinheiro_compras;
    }
    public Utilizador(String email, String nome, String morada, int nif, Map<String, Artigo> vendido, Set<String> para_vender, double dinheiro_vendas, double dinheiro_compras) {
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.nif = nif;
        setVendido(vendido);
        setPara_vender(para_vender);
        this.encomendas_compradas = new HashSet<>();
        this.encomendas_vendidas = new HashSet<>();
        this.dinheiro_vendas = dinheiro_vendas;
        this.dinheiro_compras = dinheiro_compras;
    }

    public Utilizador(Utilizador o){
        this.code = o.getCode();
        this.email = o.getEmail();
        this.nome = o.getNome();
        this.morada = o.getMorada();
        this.nif = o.getNif();
        this.vendido = o.getVendido();
        this.para_vender = o.getPara_vender();
        this.dinheiro_compras = o.getDinheiro_compras();
        this.dinheiro_vendas = o.getDinheiro_vendas();
        this.encomendas_compradas = o.getEncomendasCompradas();
        this.encomendas_vendidas = o.getEncomendas_vendidas();
    }

    public Set<Encomenda> getEncomendas_vendidas() {
        Set<Encomenda> novo = new HashSet<>();
        for(Encomenda i : this.encomendas_vendidas){
            novo.add(i.clone());
        }
        return novo;
    }

    public void setEncomendas_vendidas(Set<Encomenda> a) {
        this.encomendas_vendidas = new HashSet<>();
        for(Encomenda c : a){
            this.encomendas_vendidas.add(c.clone());
        }
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Encomenda> getEncomendasCompradas() {
        Set<Encomenda> novo = new HashSet<>();
        for(Encomenda i : this.encomendas_compradas){
            novo.add(i.clone());
        }
        return novo;
    }

    public void setEncomendasCompradas(Set<Encomenda> a){
            this.encomendas_compradas = new HashSet<>();
            for(Encomenda c : a){
                this.encomendas_compradas.add(c.clone());
            }

    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getNif() {
        return this.nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public HashMap<String, Artigo> getVendido() {
        HashMap<String, Artigo> novo = new HashMap<>();
        for(Map.Entry<String, Artigo> c : this.vendido.entrySet()){
            String key = c.getKey();
            Artigo clone = c.getValue().clone();
            novo.put(key,clone);
        }
        return novo;
    }

    public void setVendido(Map<String, Artigo> vendido) {
        this.vendido = new HashMap<>();
        for(Map.Entry<String,Artigo> c : vendido.entrySet()){
            String key = c.getKey();
            Artigo clone = c.getValue().clone();
            this.vendido.put(key,clone);
        }
    }

    public Set<String> getPara_vender() {
        Set<String> novo = new HashSet<>();

        for(String c : this.para_vender){
            novo.add(c);
        }
        return novo;
    }

    public void setPara_vender(Set<String> a) {
        this.para_vender = new HashSet<>();
        for(String c : a){
            this.para_vender.add(c);
        }
    }

    public double getDinheiro_vendas() {
        return this.dinheiro_vendas;
    }

    public void setDinheiro_vendas(double dinheiro_vendas) {
        this.dinheiro_vendas = dinheiro_vendas;
    }

    public double getDinheiro_compras() {
        return this.dinheiro_compras;
    }

    public void setDinheiro_compras(double dinheiro_compras) {
        this.dinheiro_compras = dinheiro_compras;
    }

    public Utilizador clone(){
        return new Utilizador(this);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Código no Sistema:: " + this.code + "\n");
        sb.append("Email:: " + this.email + "\n");
        sb.append("Nome:: " + this.nome + "\n");
        sb.append("Morada:: " + this.morada + "\n");
        sb.append("NIF:: " + this.nif + "\n");
        sb.append("Artigos Vendidos:: [");
        for(Artigo c : this.vendido.values()){
            sb.append(c + "\n");
        }
        sb.append("]\n");
        sb.append("Artigos à Venda:: [");
        for(String c : this.para_vender){
            sb.append(c + "\n");
        }
        sb.append("]\n");
        sb.append("Dinheiro Feito em Vendas:: " + this.dinheiro_vendas + "\n");
        sb.append("Dinheiro Gasto em Compras:: " + this.dinheiro_compras + "\n");

        return sb.toString();
    }

    public boolean equals(Object o){
        if (this==o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        Utilizador l = (Utilizador) o;
        return  this.code == l.getCode() &&
                this.email.equals(l.getEmail()) &&
                this.nome.equals(l.getNome()) &&
                this.morada.equals(l.getMorada()) &&
                this.nif == l.getNif() &&
                Double.compare(this.dinheiro_compras, l.getDinheiro_compras()) == 0 &&
                Double.compare(this.dinheiro_vendas, l.getDinheiro_vendas()) == 0 &&
                this.vendido.equals(l.getVendido()) &&
                this.para_vender.equals(l.para_vender) &&
                this.encomendas_vendidas.equals(l.getEncomendas_vendidas()) &&
                this.encomendas_compradas.equals(l.getEncomendasCompradas());
    }

    /**
     * Método que adiciona um Artigo à coleção dos artigos vendidos
     * @param c
     */
    public void addArtigoVendido(Artigo c){
        this.vendido.put(c.getCodAlfaNum(), c);
    }

    /**
     * Método que adiciona um Artigo à coleção dos artigos para vender
     * @param c
     */
    public void addArtigoParaVender(String c){
        this.para_vender.add(c);
    }

    /**
     * Método que remove um Artigo à coleção dos artigos para vender
     * @param c
     */
    public void removeArtigoParaVender(String c){
        this.para_vender.remove(c);
    }

    /**
     * Método que remove um Artigo à coleção dos artigos vendidos
     * @param c
     */
    public void removeArtigoVendido(String c){this.vendido.remove(c);}

    /**
     * Método que adiciona uma Encomenda à coleção das encomendas compradas
     * @param i
     */
    public void addEncomendaComprada(Encomenda i){
        this.encomendas_compradas.add(i);
    }

    /**
     * Método que adiciona uma Encomenda à coleção das encomendas onde o utilizador vendeu algum dos seus artigos.
     * @param i
     */
    public void addEncomendaVendida(Encomenda i){
        this.encomendas_vendidas.add(i);
    }

    /**
     * Método que remove uma Encomenda da coleção das encomendas compradas.
     * @param i
     */
    public void removeEncomendaComprada(Encomenda i){
        this.encomendas_compradas.remove(i);
    }

    /**
     * Método que remove uma Encomenda da coleção das encomendas vendidas.
     * @param i
     */
    public void  removeEncomendaVendida(Encomenda i){
        this.encomendas_vendidas.remove(i);
    }

    /**
     * Método que cria uma String para escrever o objeto Utilizador em ficheiro de texto.
     * @return String com todos os campos.
     */
    public String toLog(){
        return ("Utilizador:" + this.getEmail() + "," + this.getNome() + "," + this.getMorada() + "," + this.getNif()+ "," + this.getDinheiro_vendas() + "," + this.getDinheiro_compras());
    }
}
