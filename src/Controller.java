import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import UserExceptions.*;

public class Controller {
    private View view;
    private Vintage vintage;
    private String current_user;
    private boolean run;
    private boolean logged;
    private LocalDateTime data;

    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.loadFileMenu();

        do {
            if (!controller.logged)
                controller.logIn();

            if (controller.logged)
                controller.displayMenu();

        } while (controller.run);
    }

    public Controller(){
        this.data = LocalDateTime.now();
        this.view = new View();
        this.vintage = new Vintage();
        this.run = true;
        this.logged = false;
    }

    /**
     * Método que popula o programa através de um script ou de um ficheiro de objetos.
     * @return
     */
    public void loadFileMenu() {
        boolean flag = true;
        while(flag){
            int opt = this.view.txtOrObject();
            switch(opt){
                case 1:
                    try{
                        this.vintage = Populator.populateData();
                    } catch(UserDoesntExistException udee){
                        udee.getMessage();
                    } catch(ArtigoDoesntExistException adee){
                        adee.getMessage();
                    } catch(TransportadoraDoesntExistException tdee){
                        tdee.getMessage();
                    }
                    flag = false;
                    break;
                case 2:
                    try{
                        loadFromObjectFile();
                        flag = false;
                    } catch (FileNotFoundException fne){
                        fne.getMessage();
                    } catch (IOException io){
                        io.getMessage();
                    } catch (ClassNotFoundException cnfe){
                        cnfe.getMessage();
                    }
                    break;
            }
        }
    }

    /**
     * Método responsável pela execução das componentes do menu.
     */
    public void displayMenu(){
        try{
            int op2 = Integer.parseInt(this.view.menu());
            switch(op2){
                case 0:
                    this.logged = false;
                    return;
                case 1:
                    try{
                        createArtigo(); //falta adicionar À struct etc
                    } catch (Exception e){
                        e.getMessage();
                    }
                    break;
                case 2:
                    createTransportadora();
                    break;
                case 3:
                    criaUtilizador();
                    break;
                case 4:
                    criaEncomenda();
                    break;
                case 5:
                    Set<Encomenda> encs = this.vintage.getEncomendasUser(this.current_user);
                    for(Encomenda e : encs){
                        if(e.isRefundable(this.data)){
                            this.view.imprimeString(e.toDevolver());
                        }

                    }
                    int quantos = encs.size();
                    if(quantos != 0){
                        int cod = this.view.codEncomenda();
                        String nome;
                        try{
                            nome = this.vintage.getUserFromEncomenda(cod);
                            if(nome.equals(this.current_user)){
                                this.vintage.devolveEncomenda(cod,current_user, this.data);
                            }
                            else
                                this.view.encomendaNaoAssociada(this.current_user);
                        } catch (EncomendaDoesntExistException e){
                            e.getMessage();
                        } catch (UserDoesntExistException udee){
                            udee.getMessage();
                        } catch (EncomendaNotRefundableException enre){
                            enre.getMessage();
                        }
                        break;
                    }
                    else{
                        this.view.encomendaNaoExistente();
                    }
                    break;
                case 6:
                    avancaData(); //para ja o tempo para passar de finalizada a expedida é de 5h
                    break;
                case 7:
                    writeToObjectFile();
                    break;
                default:
                    break;
                case 8:
                    try{
                        writeToLog();
                    }
                    catch (IOException fne){
                        fne.getMessage();
                    }
                    break;
                case 9:
                    calculaEstatisticas();
            }
        } catch(Exception e){
            this.view.imprimeString("Erro no load de ficheiros!");
        }
    }

    /**
     * Método responsável pelo Log/in caso exista utilizador, ou criação de conta caso contrário.
     */
    public void logIn() {
        int op1 = this.view.logInMenu();
        switch (op1) {
            case 0:
                this.run = false;
                return;
            case 1: {
                String username;
                boolean flag = true;
                while (flag) {
                    username = this.view.logIn();
                    if (this.vintage.userExists(username)) {
                        try{ // ISTO ESTÁ MUITO À TROLHA, REVER!!
                            current_user = this.vintage.getUserEspecifico(username).getEmail();
                        } catch (UserDoesntExistException e){
                            e.getMessage();
                        }
                        this.logged = true;
                        flag = false;
                    } else {
                        String[] tokens = this.view.accountCreation();
                        Utilizador aux = new Utilizador(username, tokens[0], tokens[1], Integer.parseInt(tokens[2]), 0.0, 0.0);
                        this.vintage.addUser(aux);
                        current_user = aux.getEmail();
                        this.logged = true;
                        flag = false;
                    }
                }
                break;
            }
        }
    }

    /**
     * Método responsável pela criação de artigos.
     */
    public void createArtigo(){ //adaptar para premium
        boolean flag = true;
        boolean flag2 = true;
        Transportadora c = null;
        int premium = this.view.artPremium();
        boolean prem = (premium == 1) ? false : true;
        while(flag){
            Set<Transportadora> transpSet = this.vintage.getListaTransportadoras(prem);
            for(Transportadora t :transpSet){
                this.view.imprimeString(t.toString());
            }
            String transp = this.view.escolheTransportadora();

            try{
                c = this.vintage.getTransportadoraEspecifico(transp);
                if(transpSet.contains(c))
                    flag = false;
                else
                    this.view.transpNaoCorresponde();

            } catch (TransportadoraDoesntExistException e){
                e.getMessage();
            }

            while(flag2 && !flag){
                String[] tokens = null;
                String[] tokens2 = null;
                int artcode = this.view.tipoArtigoCriacao(prem);
                if(artcode == 0) return;
                boolean ciclo1 = true;
                while(ciclo1){
                    tokens = this.view.artigoCreation();
                    if(pertenceEstado(tokens[1]) || tokens[1].equals("freepass")) ciclo1 = false;
                    else this.view.parametrosErrados("Estado");
                }
                Artigo art1 = null;
                switch(artcode){
                    case 1:
                        while(!ciclo1){
                            tokens2 = this.view.malaCreation();
                            if(pertenceDimensao(tokens2[0])) ciclo1 = true;
                            else this.view.parametrosErrados("Dimensao");
                        }
                        if(!prem){
                            if(tokens[0].toLowerCase().equals("false")){
                                art1 = new Mala(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1].toUpperCase()), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Mala.Dim.valueOf(tokens2[0].toUpperCase()), tokens2[1], LocalDate.parse(tokens2[2]));
                            }
                            else{
                                art1 = new Mala(Boolean.parseBoolean(tokens[0]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Mala.Dim.valueOf(tokens2[0].toUpperCase()), tokens2[1], LocalDate.parse(tokens2[2]));
                            }
                        }
                        else{
                            if(tokens[0].toLowerCase().equals("false")){
                                art1 = new MalaPremium(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1].toUpperCase()), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Mala.Dim.valueOf(tokens2[0].toUpperCase()), tokens2[1], LocalDate.parse(tokens2[2]));
                            }
                            else{
                                art1 = new MalaPremium(Boolean.parseBoolean(tokens[0]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Mala.Dim.valueOf(tokens2[0].toUpperCase()), tokens2[1], LocalDate.parse(tokens2[2]));
                            }
                        }

                        break;
                    case 2:
                        tokens2 = this.view.sapatilhaCreation();
                        if(!prem){
                            if(tokens[0].toLowerCase().equals("false")){
                                art1 = new Sapatilha(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1].toUpperCase()), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Double.parseDouble(tokens2[0]), Boolean.parseBoolean(tokens2[1]), tokens2[2], LocalDate.parse(tokens2[3]));
                            }
                            else{
                                art1 = new Sapatilha(Boolean.parseBoolean(tokens[0]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Double.parseDouble(tokens2[0]), Boolean.parseBoolean(tokens2[1]), tokens2[2], LocalDate.parse(tokens2[3]));
                            }
                        }
                        else{
                            if(tokens[0].toLowerCase().equals("false")){
                                art1 = new SapatilhaPremium(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1].toUpperCase()), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Double.parseDouble(tokens2[0]), Boolean.parseBoolean(tokens2[1]), tokens2[2], LocalDate.parse(tokens2[3]));
                            }
                            else{
                                art1 = new SapatilhaPremium(Boolean.parseBoolean(tokens[0]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Double.parseDouble(tokens2[0]), Boolean.parseBoolean(tokens2[1]), tokens2[2], LocalDate.parse(tokens2[3]));
                            }
                        }
                        break;

                    case 3:
                        while(!ciclo1){
                            boolean tamanho = false;
                            boolean padrao = false;
                            tokens2 = this.view.tshirtCreation();
                            if(!pertenceTamanho(tokens2[0])) this.view.parametrosErrados("Tamanho");
                            else tamanho = true;
                            if(!pertencePadrao(tokens2[1])) this.view.parametrosErrados("Padrão");
                            else padrao = true;
                            if(padrao && tamanho) ciclo1 = true;
                        }
                        if(!prem){
                            if(tokens[0].toLowerCase().equals("false")){
                                art1 = new Tshirt(Boolean.parseBoolean(tokens[0]), Integer.parseInt(tokens[2]), Artigo.Estado.valueOf(tokens[1].toUpperCase()), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Tshirt.Tamanho.valueOf(tokens2[0].toUpperCase()), Tshirt.Padrao.valueOf(tokens2[1].toUpperCase()));
                            }
                            else{
                                art1 = new Tshirt(Boolean.parseBoolean(tokens[0]), tokens[3], tokens[4], Double.parseDouble(tokens[5]), c, Tshirt.Tamanho.valueOf(tokens2[0].toUpperCase()), Tshirt.Padrao.valueOf(tokens2[1].toUpperCase()));
                            }
                        }
                        break;
                    case 0:
                        return;
                }
                try{
                    this.vintage.addArtigoVenda(this.current_user, art1); //composição no artigo -> agregação artigo entre vintage e encomendas
                    flag2 = false;
                } catch (UserDoesntExistException udee){
                    udee.getMessage();
                } catch (Exception e){
                    e.getMessage();
                }
            }
        }
    }

    /**
     * Método responsável pela criação de transportadoras.
     */
    public void createTransportadora(){ // adaptar para premium tmb
        Transportadora transportadora = null;
        String tokens[] = this.view.transportadoraCreation();
        if(tokens[2].equalsIgnoreCase("false")) {
            transportadora = new Transportadora(tokens[0], Double.parseDouble(tokens[1]));
        }
        else{
            transportadora = new TransportadoraPremium(tokens[0], Double.parseDouble(tokens[1]));
        }
        this.vintage.addTransportadora(transportadora);
    }

    /**
     * Método responsável pela criação de uma encomenda.
     */
    public void criaEncomenda(){
        Encomenda encomenda_atual = new Encomenda(this.data);
        boolean flag = true;
        String artigo;
        while(flag){
            int opt = this.view.OpcaoEncomenda();
            switch(opt){
                case 1:
                    this.view.imprimeString(this.vintage.setToString(this.vintage.getListaArtigos(this.current_user)));
                    artigo = this.view.encomendaCreation();
                    try{
                        Artigo art = this.vintage.findArtigo(artigo);
                        if(!encomenda_atual.contem(art)){
                            encomenda_atual.addArtEncomenda(art);
                        }
                        else
                            this.view.artigoRepetido();
                    } catch (ArtigoDoesntExistException e){
                        e.getMessage();
                    }
                    this.view.imprimeString(encomenda_atual.showPrecoAtual());
                    break;
                case 2:
                    this.view.imprimeString(this.vintage.setToString(encomenda_atual.getArtigos()));
                    artigo = this.view.removeArtigo();
                    try{
                        encomenda_atual.removeArtEncomenda(this.vintage.findArtigo(artigo));
                    } catch (ArtigoDoesntExistException e){
                        e.getMessage();
                    }
                    encomenda_atual.showPrecoAtual();
                    break;
                case 3:
                    if(encomenda_atual.getArtigos().size() != 0) {
                        encomenda_atual.atualizaEncomenda(this.data);
                        try {
                            this.vintage.addEncomenda(this.current_user, encomenda_atual);
                        } catch (UserDoesntExistException udee) {
                            udee.getMessage();
                        } catch (ArtigoDoesntExistException adee) {
                            adee.getMessage();
                        } catch (TransportadoraDoesntExistException tdee) {
                            tdee.getMessage();
                        }
                    }
                    flag = false;
                    break;
                case 4:
                    flag = false;
                    break;
            }
        }
    }

    /**
     * Método responsável pela criação de um utilizador.
     */
    public void criaUtilizador(){
        String tokens[] = this.view.userCreation();
        Utilizador utilizador = new Utilizador(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]));
        this.vintage.addUser(utilizador);
    }

    /**
     * Método responsável pelas estatísticas.
     */
    public void calculaEstatisticas() {
        boolean flag = true;
        LocalDate datas[];
        while (flag) {
            try {
                int op2 = this.view.menuEstatisticas();
                switch (op2) {
                    case 0:
                        flag = false; // Exit loop
                        break;
                    case 1:
                        datas = this.view.intervaloTempo();
                        maiorVendedorIntervaloTempo(datas[0],datas[1]);
                        break;
                    case 2:
                        melhorTransportadora();
                        break;
                    case 3:
                        String nome = this.view.leEmail();
                        try{
                            encomendasEmitidasVendedor(nome);
                        } catch (UserDoesntExistException e){
                            e.getMessage();
                        } catch (EncomendasNonExistentException enee){
                            enee.getMessage();
                        }
                        break;
                    case 4:
                        datas = this.view.intervaloTempo();
                        maioresCompradores(datas[0],datas[1]);
                        maioresVendedores(datas[0],datas[1]);
                        break;
                    case 5:
                        dinheiroVintage();
                        break;
                    default:
                        this.view.imprimeString("Opção Inválida!");
                        break;
                }
            } catch(Exception e) {
                this.view.imprimeString(e.getMessage());
            }
        }
    }

    /**
     * Método responsável por escrever o estado do nosso programa num ficheiro de objetos.
     * @throws IOException
     */
    public void writeToObjectFile() throws IOException{
        FileOutputStream fos = new FileOutputStream("state.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.vintage);
        oos.write(Encomenda.getCount());
        oos.flush();
        oos.close();
    }

    /**
     * Método responsável por dar load de um estado do nosso programa guardado num ficheiro de objetos.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadFromObjectFile() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("state.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.vintage = (Vintage) ois.readObject();
        Encomenda.setCount(ois.read());
        ois.close();
    }

    /**
     * Método responsável por escrever o estado do nosso programa para um ficheiro de texto
     * de forma a tornar fácil a visualização e debug.
     * @throws IOException
     */
    public void writeToLog() throws IOException {
        String name = "./src/log.txt";

        File file = new File(name);
        FileOutputStream fos = new FileOutputStream(file);
        try (PrintWriter pw = new PrintWriter(fos)) {

            List<String> lista;

            lista = this.vintage.toLog();

            for (String s : lista) {
                pw.println(s);
            }

        }
        fos.close();
    }

    /**
     * Método responsável por atualizar a data do nosso programa e devidas alterações associadas.
     * @throws IOException
     */
    public void avancaData() throws IOException{
        int nrHoras = this.view.avancaData();

        this.data = this.data.plusHours(nrHoras);

        Map<Integer,String> recibos = this.vintage.atualizaEncomendas(this.data,nrHoras);

        for(Map.Entry<Integer,String> a : recibos.entrySet()){
            int cod = a.getKey();
            String recib = a.getValue();
            String nome = "./src/Recibos/" + cod + ".txt";
            File file = new File(nome);
            FileOutputStream fos = new FileOutputStream(file);

            try (PrintWriter pw = new PrintWriter(fos)){
                pw.println(recib);
            }
            fos.close();
        }
        this.view.imprimeString("Foram avançadas " + nrHoras + " horas");
    }

    /**
     * Método responsável pela 1ª estatística.
     * @param start
     * @param end
     */
    public void maiorVendedorIntervaloTempo(LocalDate start, LocalDate end){
        Map<String,List<Encomenda>> encs = this.vintage.getEncomendasPeriodo(start,end,true);

        String nome = encs.entrySet().stream()
                .max(Comparator.comparingDouble(entry -> this.vintage.calculaTotalGanhoUser(entry.getKey(), entry.getValue())))
                .map(Map.Entry::getKey)
                .orElse(null);
        try{
            Utilizador melhor = this.vintage.getUserEspecifico(nome);
            this.view.imprimeString(melhor.toString());
        }
        catch (UserDoesntExistException e){
            e.getMessage();
        }
    }

    /**
     * Método responsável pela 5ª estatística.
     */
    public void dinheiroVintage(){
        double total = this.vintage.calculaDinheiroVintage();
        this.view.imprimeDouble(total);
    }

    /**
     * Método responsável pela 2ª estatística.
     */
    public void melhorTransportadora(){
        String transportadora = this.vintage.maiorFaturacaoTransportadora().toString();
        this.view.imprimeString(transportadora);
    }

    /**
     * Método responsável pela 3ª estatistica.
     * @param email
     * @throws UserDoesntExistException
     * @throws EncomendasNonExistentException
     */
    public void encomendasEmitidasVendedor(String email) throws UserDoesntExistException, EncomendasNonExistentException{
        Utilizador user = this.vintage.getUserEspecifico(email);
        Set<Encomenda> aux = user.getEncomendas_vendidas();
        if(aux.size() == 0)
            throw new EncomendasNonExistentException(user.getEmail());
        for(Encomenda c : aux){
            this.view.imprimeString(c.toString());
        }
    }

    /**
     * Método integrante da 4ª estatistica.
     * @param start
     * @param end
     */
    public void maioresCompradores(LocalDate start, LocalDate end){
        Map<String,List<Encomenda>> encs = this.vintage.getEncomendasPeriodo(start,end,false);

        Map<String,Double> gastos = new HashMap<>();

        for(Map.Entry<String,List<Encomenda>> c : encs.entrySet()){
            String email = c.getKey();
            List<Encomenda> aux = c.getValue();

            double total_gasto = this.vintage.calculaTotalGastoUsers(aux);

            gastos.put(email,total_gasto);
        }

        List<String> chavesOrdenadas = gastos.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        this.view.imprimeMaioresCompradores(chavesOrdenadas);
    }

    /**
     * Método integrante da 4ª estatística.
     * @param start
     * @param end
     */
    public void maioresVendedores(LocalDate start, LocalDate end){
        Map<String,List<Encomenda>> encs = this.vintage.getEncomendasPeriodo(start,end,true);

        Map<String,Double> ganhos = new HashMap<>();

        for(Map.Entry<String,List<Encomenda>> c : encs.entrySet()){
            String email = c.getKey();
            List<Encomenda> aux = c.getValue();

            double total_ganho = this.vintage.calculaTotalGanhoUser(email,aux);

            ganhos.put(email,total_ganho);
        }

        List<String> chavesOrdenadas = ganhos.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        this.view.imprimeMaioresVendedores(chavesOrdenadas);
    }

    /**
     * Método que verifica se uma string pertence ao enum Estado.
     * @param c
     * @return true/false
     */
    public boolean pertenceEstado(String c){
        for(Artigo.Estado a : Artigo.Estado.values()){
            if(a.name().equalsIgnoreCase(c)) return true;
        }
        return false;
    }

    /**
     * Método que verifica se uma string pertence ao enum Dimensao.
     * @param c
     * @return true/false
     */
    public boolean pertenceDimensao(String c){
        for(Mala.Dim a : Mala.Dim.values()){
            if(a.name().equalsIgnoreCase(c)) return true;
        }
        return false;
    }

    /**
     * Método que verifica se uma string pertence ao enum Padrao.
     * @param c
     * @return true/false
     */
    public boolean pertencePadrao(String c){
        for(Tshirt.Padrao a : Tshirt.Padrao.values()){
            if(a.name().equalsIgnoreCase(c)) return true;
        }
        return false;
    }

    /**
     * Método que verifica se uma string pertence ao enum Tamanho.
     * @param c
     * @return true/false
     */
    public boolean pertenceTamanho(String c){
        for(Tshirt.Tamanho a : Tshirt.Tamanho.values()){
            if(a.name().equalsIgnoreCase(c)) return true;
        }
        return false;
    }


}
