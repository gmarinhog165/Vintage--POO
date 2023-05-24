import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.spi.LocaleServiceProvider;

public class View {
    /**
     * Log in.
     * @return opção escolhida.
     */
    public int logInMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Vintage--------");
        System.out.println("1. Login/Sign-in");
        System.out.println("0. Terminar Programa");

        int option = 0;
        do{
            option = leInteiro();
            if(option != 1 && option != 0)
                System.out.println("Introduza o valor 0 ou 1.");
        } while(option != 1 && option != 0);

        return option;
    }

    /**
     * Recolhe o email no login
     * @return email.
     */
    public String logIn() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Vintage--------");
        System.out.println("Insira o seu email:");

        String option = sc.next();
        return option;
    }

    /**
     * Recolha de informação dos campos dum utilizador.
     * @return array de strings com os campos.
     */
    public String[] accountCreation() {
        Scanner scanner = new Scanner(System.in);
        String[] utilizadorInput = new String[3];
        System.out.println("Please type in your account data!");


        System.out.print("Enter your name: ");
        utilizadorInput[0] = scanner.nextLine();

        System.out.print("Enter your home adress ");
        utilizadorInput[1] = scanner.nextLine();

        System.out.print("Enter your nif: ");
        utilizadorInput[2] = Integer.toString(leInteiro());
        return utilizadorInput;
    }

    /**
     * Menu principal.
     * @return opção escolhida.
     */
    public String menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------Vintage--------");
        System.out.println("1 - Criar Artigo");
        System.out.println("2 - Criar Transportadora");
        System.out.println("3 - Criar Utilizador");
        System.out.println("4 - Fazer Encomenda");
        System.out.println("5 - Devolver Encomenda");
        System.out.println("6 - Mudar Data");
        System.out.println("7 - Guardar num ficheiro de objetos");
        System.out.println("8 - Escreve ficheiro de txt");
        System.out.println("9 - Calcular Estatísticas");
        System.out.println("0 - Terminar Sessão");
        System.out.println("Indique a opcao: ");

        String option = sc.next();
        return option;
    }

    /**
     * Recolhe as horas que o utilizador quer avançar.
     * @return horas fornecidas.
     */
    public int avancaData(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique quantas horas pretende avançar:");
        int opt = 0;
        do{
            opt = leInteiro();

        } while(opt < 0);
        return opt;
    }

    /**
     * Recolha de informação do modo de loading.
     * @return opção escolhida.
     */
    public int txtOrObject() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique como pretende carregar os dados:");
        System.out.println("1. Popular com os dados de teste");
        System.out.println("2. Ficheiro de Objetos");


        int opt = 0;
        do{
            opt = leInteiro();
            if(opt != 1 && opt !=2)
                System.out.println("Introduza um inteiro com o valor 1 ou 2.");
        } while(opt != 1 && opt != 2);

        return opt;
    }

    /**
     * Recolha de informação sobre o artigo a ser criado.
     * @param prem
     * @return opção escolhida.
     */
    public int tipoArtigoCriacao(boolean prem) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Que tipo de artigo queres criar?");
        System.out.println("1. Mala");
        System.out.println("2. Sapatilha");
        if(!prem){
            System.out.println("3. Tshirt");
        }
        System.out.println("0 - Voltar ao Menu Inicial");

        int option = 0;
        do{
            option = leInteiro();
            if((option < 0 || option > 3) && !prem)
                System.out.println("Introduza o valor 0, 1, 2 ou 3.");
            else if ((option < 0 || option > 2) && prem)
                System.out.println("Introduza o valor 0, 1, 2.");
        } while(((option < 0 || option > 3) && !prem) || ((option < 0 || option > 2) && prem));

        return option;
    }

    /**
     * Recolha de dados relativos à criação dos artigos.
     * @return array de string com os campos.
     */
    public String[] artigoCreation(){
        Scanner scanner = new Scanner(System.in);
        String[] utilizadorInput = new String[6];
        System.out.println("Por favor escreva os dados relativos ao seu Artigo!");

        System.out.println("Indique se o artigo é novo/usado (true/false): ");
        utilizadorInput[0] = leBoolean();

        if (utilizadorInput[0].equalsIgnoreCase("false")) {
            System.out.println("Em que estado se encontra o artigo(PESSIMO/MAU/RAZOAVEL/BOM/MUITO_BOM)? ");
            utilizadorInput[1] = scanner.nextLine();

            System.out.println("Número de donos: ");
            utilizadorInput[2] = Integer.toString(leInteiro());
        }
        else{
            utilizadorInput[1] = "freepass";
        }
        System.out.println("Descrição do Artigo: ");
        utilizadorInput[3] = scanner.nextLine();
        System.out.println("Marca: ");
        utilizadorInput[4] = scanner.nextLine();
        System.out.println("Preco Base: ");
        utilizadorInput[5] = Double.toString(leDouble());

        return utilizadorInput;
    }

    /**
     * Recolha de dados relativos à criação de malas.
     * @return array de string com os campos.
     */
    public String[] malaCreation(){
        Scanner scanner = new Scanner(System.in);
        String[] utilizadorInput = new String[3];
        System.out.println("Por favor escreva os dados relativos à sua Mala!");

        System.out.println("Indique a dimensão da Mala (PEQUENO/MEDIO/GRANDE): ");
        utilizadorInput[0] = scanner.nextLine();
        System.out.println("Indique o seu material: ");
        utilizadorInput[1] = scanner.nextLine();
        System.out.println("Indique no formato aaaa-mm-dd a data da sua coleção: ");
        utilizadorInput[2] = leLocalDate();

        return utilizadorInput;
    }

    /**
     * Recolha de dados relativos à criação de tshirts.
     * @return array de string com os campos.
     */
    public String[] tshirtCreation(){
        Scanner scanner = new Scanner(System.in);
        String[] utilizadorInput = new String[2];
        System.out.println("Por favor escreva os dados relativos à sua T-Shirt!");

        System.out.println("Indique o Tamanho da Tshirt (S/M/L/XL): ");
        utilizadorInput[0] = scanner.nextLine();
        System.out.println("Indique o Padrao da Tshirt (RISCAS/LISO/PALMEIRAS): ");
        utilizadorInput[1] = scanner.nextLine();
        return utilizadorInput;
    }

    /**
     * Recolha de dados relativos à criação de sapatilhas.
     * @return array de string com os campos.
     */
    public String[] sapatilhaCreation(){
        Scanner scanner = new Scanner(System.in);
        String[] utilizadorInput = new String[4];
        System.out.println("Por favor escreva os dados relativos às suas Sapatilhas!");

        System.out.println("Indique o Tamanho da Sapatilha: ");
        utilizadorInput[0] = Double.toString(leDouble());
        System.out.println("Indique se tem atacadores (true/false): ");
        utilizadorInput[1] = leBoolean();
        System.out.println("Indique a sua cor: ");
        utilizadorInput[2] = scanner.nextLine();
        System.out.println("Indique no formato aaaa-mm-dd a data da sua coleção: ");
        utilizadorInput[3] = leLocalDate();

        return utilizadorInput;
    }

    /**
     * Recolha de campos relativos à transportadora.
     * @return array de strings com esses campos.
     */
    public String[] transportadoraCreation() {
        String[] utilizadorInput = new String[5];
        Scanner sc = new Scanner(System.in);

        System.out.println("Qual o nome da transportadora?:");
        utilizadorInput[0] = sc.nextLine();

        System.out.println("Indique a taxa de lucro da transportadora (entre 0.00 e 1.00):");
        utilizadorInput[1] = Double.toString(leDouble());

        System.out.println("A transportadora é premium? (true/false):");
        utilizadorInput[2] = leBoolean();

        return utilizadorInput;
    }

    /**
     * Recolhe informação relativa ao proximo passo que um utilizador que dar numa encomenda.
     * @return opçao escolhida.
     */
    public int OpcaoEncomenda() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Qual das seguintes opções pretende?");
        System.out.println("1. Adicionar Artigo");
        System.out.println("2. Remover Artigo");
        System.out.println("3. Finalizar Encomenda");
        System.out.println("4. Cancelar Encomenda");
        int opt = 0;
        do{
            opt = leInteiro();
            if((opt < 1 || opt > 4)){
                System.out.println("Introduza o valor 1, 2, 3 ou 4.");
            }
        } while(opt < 1 || opt > 4);
        return opt;
    }

    /**
     * recolhe o código de uma encomenda.
     * @return código encomenda.
     */
    public int codEncomenda() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique o código da encomenda que pretende devolver:");
        int enc = leInteiro();
        return enc;
    }

    /**
     * Dá um print informativo.
     */
    public void encomendaNaoExistente() {
        System.out.println("Não há nenhuma encomenda atribuida a este utilizador.");
    }

    /**
     * Recolhe o código alfanumérico de um artigo que pretende remover de uma encomenda.
     * @return código alfanumérico do artigo.
     */
    public String removeArtigo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Indique o código alfanumérico do artigo que pretende remover da sua encomenda:");
        String artigo = sc.nextLine();
        return artigo;
    }

    /**
     * Dá um print informativo.
     * @param email
     */
    public void encomendaNaoAssociada(String email){
        System.out.println("Essa encomenda não está associada a " + email);
    }

    /**
     * Recolhe o código alfanumerico do artigo que quer adicionar à encomenda.
     * @return código alfanumérico do artigo.
     */
    public String encomendaCreation() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o código alfanumérico do Artigo que pretende:");
        String artigo = sc.nextLine();

        return artigo;
    }

    /**
     * Dá um print informativo.
     */
    public void artigoRepetido(){
        System.out.println("O código do artigo que inseriu já se encontra na encomenda, pelo que não foi adicionado.");
    }

    /**
     * Recolhe os campos relativos à criação de um utilizador.
     * @return array de strings com esses campos.
     */
    public String[] userCreation() {
        String[] utilizadorInput = new String[4];
        Scanner sc = new Scanner(System.in);

        System.out.println("Indique o seu email: ");
        utilizadorInput[0] = sc.nextLine();
        System.out.println("Indique o seu nome: ");
        utilizadorInput[1] = sc.nextLine();
        System.out.println("Indique a sua morada: ");
        utilizadorInput[2] = sc.nextLine();
        System.out.println("Indique o seu NIF: ");
        utilizadorInput[3] = Integer.toString(leInteiro());

        return utilizadorInput;
    }

    /**
     * Recolhe o nome da transportadora que o utilizador pretende.
     * @return nome escolhido.
     */
    public String escolheTransportadora() {
        Scanner sc = new Scanner(System.in);
        System.out.println("De entre as Transportadoras anteriores escreva o nome da que pretende: ");

        String ret = sc.nextLine();
        return ret;
    }

    /**
     * Dá um print informativo.
     */
    public void transpNaoCorresponde(){
        System.out.println("O nome que escreveu não corresponde a nenhum dos que foi exposto.\n");
    }

    /**
     * Recolhe informação sobre se o utilizador pretende criar um artigo normal ou um artigo premium.
     * @return opção escolhida.
     */
    public int artPremium(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual das seguintes opções representa o seu artigo?");
        System.out.println("1. Artigo Normal");
        System.out.println("2. Artigo Premium");

        int opt = 0;
        do{
            opt = leInteiro();
            if(opt != 1 && opt !=2)
                System.out.println("Introduza um valor igual a 1 ou 2.");
        } while (opt != 1 && opt != 2);

        return opt;
    }

    /**
     * Método que garante apenas a leitura de um Inteiro.
     * @return int.
     */
    public int leInteiro(){
        Scanner sc = new Scanner(System.in);

        boolean loop = false;
        int option = 0;
        do{
            try{
                option = sc.nextInt();
                loop = true;
            } catch (Exception e){
                if(sc.hasNext()) sc.next();
                System.out.println("Parâmetro introduzido incorreto! Por favor introduza um inteiro.");
            }
        } while(!loop);

        return option;
    }

    /**
     * Método que garante apenas a leitura de um Double.
     * @return double.
     */
    public double leDouble(){
        Scanner sc = new Scanner(System.in);

        boolean loop = false;
        double option = 0;
        do{
            try{
                option = sc.nextDouble();
                loop = true;
            } catch (Exception e){
                if(sc.hasNext()) sc.next();
                System.out.println("Parâmetro introduzido incorreto! Por favor introduza um double.");
            }
        } while(!loop);

        return option;
    }

    /**
     * Método que garante apenas a leitura de um Boolean.
     * @return boolean.
     */
    public String leBoolean(){
        Scanner sc = new Scanner(System.in);
        boolean loop = false, opt = false;
        do{
            try{
                opt = sc.nextBoolean();
                loop = true;
            } catch (Exception e){
                if(sc.hasNext()) sc.next();
                System.out.println("Parâmetro introduzido incorreto! Por favor introduza um boolean.");
            }
        } while(!loop);
        return Boolean.toString(opt);
    }

    /**
     * Método que garante apenas a leitura de um LocalDate.
     * @return LocalDate.
     */
    public String leLocalDate(){
        Scanner sc = new Scanner(System.in);
        String ret = null;
        boolean flag = true;
        do{
            ret = sc.nextLine();
            try{
                LocalDate test = LocalDate.parse(ret);
                flag = false;
            } catch (Exception e){
                System.out.println("Parâmetro introduzido incorreto! Por favor introduza o formato dum LocalDate como foi mencionado.");
            }
        } while(flag);
        return ret;
    }

    /**
     * Menu das estatísticas.
     * @return opção escolhida.
     */
    public int menuEstatisticas() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual a estatística que pretende calcular?");
        System.out.println("1 - Qual é o vendedor que mais facturou num período ou desde sempre");
        System.out.println("2 - Qual o transportador com maior volume de facturação");
        System.out.println("3 - listar as encomendas emitidas por um vendedor");
        System.out.println("4 - Fornecer uma ordenação dos maiores compradores/vendedores do sistema durante um período a determinar");
        System.out.println("5 - Determinar quanto dinheiro ganhou o Vintage no seu funcionamento");

        System.out.println("0 - Voltar ao Menu Inicial");

        int opt = 0;
        do{
            opt = leInteiro();
            if(opt < 0 || opt > 5)
                System.out.println("Introduza um valor igual a 0, 1, 2, 3, 4 ou 5.");
        } while(opt < 0 || opt > 5);

        return opt;
    }

    /**
     * Recolhe um intervalo de tempo positivo.
     * @return array com os dois LocalDates correspondentes ao intervalo de tempo.
     */
    public LocalDate[] intervaloTempo(){
        LocalDate datas[] = new LocalDate[2];

        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("Introduza a data inicial: (yyyy-mm-dd)");
            datas[0]=LocalDate.parse(leLocalDate());
            System.out.println("Introduza a data final: (yyyy-mm-dd)");
            datas[1]=LocalDate.parse(leLocalDate());

            if(datas[0].isAfter(datas[1]))
                System.out.println("Por favor introduza uma data inicial inferior ou igual à data superior.");
        } while(datas[0].isAfter(datas[1]));

        return datas;
    }

    /**
     * Imprime um Double.
     * @param a
     */
    public void imprimeDouble(double a){
        System.out.println(a);
    }

    /**
     * Recolhe o email de um utilizador.
     * @return string desse email.
     */
    public String leEmail(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o email do utilizador que pretende efetuar a estatística.");
        String email = sc.nextLine();
        return email;
    }

    /**
     * Imprime de forma ordenada os maiores compradores.
     * @param chavesOrdenadas
     */
    public void imprimeMaioresCompradores(List<String> chavesOrdenadas){
        System.out.print("Maiores compradores:");
        System.out.println(chavesOrdenadas.toString());
    }

    /**
     * Imprime de forma ordenada os maiores vendedores.
     * @param chavesOrdenadas
     */
    public void imprimeMaioresVendedores(List<String> chavesOrdenadas){
        System.out.print("Maiores vendedores:");

        System.out.println(chavesOrdenadas.toString());
    }

    /**
     * Imprime uma string.
     * @param def
     */
    public void imprimeString(String def){
        System.out.println(def);
    }

    /**
     * Imprime o parametro que estava errado.
     * @param a
     */
    public void parametrosErrados(String a){
        System.out.println("Os parâmetros passados em " + a + " não se encontram corretos!");
    }
    
}