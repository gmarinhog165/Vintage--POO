import UserExceptions.ArtigoDoesntExistException;
import UserExceptions.TransportadoraDoesntExistException;
import UserExceptions.UserDoesntExistException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Populator {

    /**
     * Método que funciona como script para popular a Vintage como ponto de partida do programa.
     * @return Vintage populada.
     */
    public static Vintage populateData() throws UserDoesntExistException, ArtigoDoesntExistException, TransportadoraDoesntExistException {
        Vintage vintage = new Vintage();
        // cria transportadoras
        Transportadora transp1 = new Transportadora(0.05,"gls",0.0);
        Transportadora transp2 = new Transportadora(0.07,"ctt",0.0);
        Transportadora transp3 = new Transportadora(0.06,"dhl",0.0);
        Transportadora transp4 = new TransportadoraPremium(0.15,"mrw premium",0.0);

        // insere na vintage transportadoras
        vintage.addTransportadora(transp1);
        vintage.addTransportadora(transp2);
        vintage.addTransportadora(transp3);
        vintage.addTransportadora(transp4);

        // cria utilizadores
        Utilizador user1 = new Utilizador("henrique@vaz.pt","Henrique Vaz","Rua das cenas, n12",260485489,12.5,11.2);
        Utilizador user2 = new Utilizador("goncalo@goncalves.pt","Gonçalo Gonçalves","Rua das batatas, n15",123456789,145.4,151.1);
        Utilizador user3 = new Utilizador("user1@gmail.com", "João Silva", "Rua A, nº 10", 123456789, 0, 0);
        Utilizador user4 = new Utilizador("user2@hotmail.com", "Ana Santos", "Rua B, nº 20", 987654321, 0, 0);
        Utilizador user5 = new Utilizador("user3@gmail.com", "Pedro Oliveira", "Rua C, nº 30", 456789123, 0, 0);
        Utilizador user6 = new Utilizador("user4@yahoo.com", "Mariana Santos", "Rua D, nº 40", 789123456, 0, 0);
        Utilizador user7 = new Utilizador("user5@gmail.com", "Bruno Oliveira", "Rua E, nº 50", 321654987, 0, 0);
        Utilizador user8 = new Utilizador("user6@hotmail.com", "Catarina Silva", "Rua F, nº 60", 654987321, 0, 0);
        Utilizador user9 = new Utilizador("user7@yahoo.com", "Ricardo Pereira", "Rua G, nº 70", 159357246, 0, 0);
        Utilizador user10 = new Utilizador("user8@gmail.com", "Marta Santos", "Rua H, nº 80", 753159246, 0, 0);
        Utilizador admin = new Utilizador("asd", "Inês Silva", "Rua J, nº 100", 147258369, 0, 0);

        // insere na vintage users
        vintage.addUser(user1);
        vintage.addUser(user2);
        vintage.addUser(user3);
        vintage.addUser(user4);
        vintage.addUser(user5);
        vintage.addUser(user6);
        vintage.addUser(user7);
        vintage.addUser(user8);
        vintage.addUser(user9);
        vintage.addUser(user10);
        vintage.addUser(admin);

        // cria artigos
        Artigo art1 =new Tshirt(true,"Tshirt nova","Ferrari",110,transp1,Tshirt.Tamanho.XL,Tshirt.Padrao.LISO);
        Artigo art2 =new Tshirt(true,"Tshirt nova","Dragon Lore",99,transp1,Tshirt.Tamanho.L,Tshirt.Padrao.LISO);
        Artigo art3 =new Tshirt(true,"Tshirt nova","Lacoste",90,transp2,Tshirt.Tamanho.L,Tshirt.Padrao.PALMEIRAS);
        Artigo art4 =new Tshirt(true,"Tshirt nova","Puma",60,transp1,Tshirt.Tamanho.XL,Tshirt.Padrao.LISO);
        Artigo art5 =new Tshirt(true,"Tshirt nova","Nude project",150,transp2,Tshirt.Tamanho.L,Tshirt.Padrao.PALMEIRAS);
        Artigo art6 =new Tshirt(false, 5, Artigo.Estado.BOM, "T-shirt branca lisa", "Nike", 30.0, transp1, Tshirt.Tamanho.L, Tshirt.Padrao.LISO);
        Artigo art7 =new Tshirt(false, 2, Artigo.Estado.RAZOAVEL, "T-shirt preta estampada", "Adidas", 25.0, transp4, Tshirt.Tamanho.M, Tshirt.Padrao.PALMEIRAS);
        Artigo art8 =new Tshirt(false, 3, Artigo.Estado.MUITO_BOM, "T-shirt azul com listras", "Puma", 20.0, transp2, Tshirt.Tamanho.XL, Tshirt.Padrao.LISO);
        Artigo art9 =new Tshirt(false, 3, Artigo.Estado.MAU, "T-shirt cinza com logotipo", "Fila", 35.0, transp3, Tshirt.Tamanho.S, Tshirt.Padrao.PALMEIRAS);
        Artigo art10 =new Tshirt(false, 2, Artigo.Estado.PESSIMO, "T-shirt vermelha com padrão", "Under Armour", 40.0, transp2, Tshirt.Tamanho.S, Tshirt.Padrao.RISCAS);

        Artigo art11 = new Mala(true,"Artigo novo","prada",245,transp3, Mala.Dim.GRANDE,"pele sintética",LocalDate.parse("2023-12-12"));
        Artigo art12 = new Mala(true,"Artigo novo","Tous",40,transp3, Mala.Dim.GRANDE,"pele sintética",LocalDate.parse("2023-12-12"));
        Artigo art13 = new Mala(true, "Mala de viagem nova", "Samsonite", 300.0, transp1, Mala.Dim.PEQUENO, "Couro sintético", LocalDate.parse("2019-06-30"));
        Artigo art14 = new Mala(true, "Mala de rodinhas usada", "Delsey", 200.0, transp1, Mala.Dim.MEDIO, "Poliéster", LocalDate.parse("2022-05-15"));
        Artigo art15 = new Mala(true, "Mala executiva nova", "Tumi", 400.0, transp2, Mala.Dim.GRANDE, "Couro", LocalDate.parse("2021-07-31"));
        Artigo art16 = new Mala(false, 5, Artigo.Estado.BOM, "Mala de mão usada", "Louis Vuitton", 800.0, transp1, Mala.Dim.PEQUENO, "Couro", LocalDate.parse("2022-12-31"));
        Artigo art17 = new Mala(false, 3, Artigo.Estado.RAZOAVEL, "Mala de viagem grande nova", "Rimowa", 1200.0, transp4, Mala.Dim.GRANDE, "Poliéster", LocalDate.parse("2024-05-30"));
        Artigo art18 = new Mala(false, 2, Artigo.Estado.MUITO_BOM, "Mala de rodinhas usada", "Tumi", 500.0, transp4, Mala.Dim.MEDIO, "Nylon", LocalDate.parse("2023-11-15"));
        Artigo art19 = new Mala(false, 4, Artigo.Estado.MAU, "Mala de mão premium nova", "Prada", 1000.0, transp1, Mala.Dim.PEQUENO, "Couro", LocalDate.parse("2023-08-31"));
        Artigo art20 = new Mala(false, 2, Artigo.Estado.PESSIMO, "Mala executiva usada", "Montblanc", 900.0, transp2, Mala.Dim.MEDIO, "Couro", LocalDate.parse("2020-09-30"));
        Artigo art32 = new MalaPremium(false, 2, Artigo.Estado.PESSIMO, "Mala executiva usada", "Montblanc", 900.0, transp4, Mala.Dim.MEDIO, "Couro", LocalDate.parse("2020-09-30"));

        Artigo art21 = new Sapatilha(true, "Sapatilha feminina de tecido", "Nike", 200.0, transp1, 36.5, true, "Cinza", LocalDate.parse("2022-08-15"));
        Artigo art22 = new Sapatilha(true, "Sapatilha masculina de couro", "Adidas", 250.0, transp2, 42.0, false, "Preto", LocalDate.parse("2021-04-01"));
        Artigo art23 = new Sapatilha(true, "Sapatilha infantil de tecido", "Puma", 100.0, transp3, 28.0, true, "Rosa", LocalDate.parse("2019-06-30"));
        Artigo art24 = new Sapatilha(true, "Sapatilha unissex de couro", "Converse", 150.0, transp4, 39.5, false, "Branco", LocalDate.parse("2009-12-31"));
        Artigo art25 = new Sapatilha(true, "Sapatilha feminina de camurça", "Vans", 180.0, transp1, 38.5, true, "Roxo", LocalDate.parse("2013-09-30"));
        Artigo art26 = new Sapatilha(false, 1, Artigo.Estado.BOM, "Sapatilha em ótimo estado", "Adidas", 129.99, transp1, 37.5, true, "Branca", LocalDate.parse("2020-03-01"));
        Artigo art27 = new Sapatilha(false, 3, Artigo.Estado.RAZOAVEL, "Sapatilha nova na caixa", "Nike", 159.99, transp2, 40.0, true, "Vermelha", LocalDate.parse("2021-01-01"));
        Artigo art28 = new Sapatilha(false, 3, Artigo.Estado.MUITO_BOM, "Sapatilha com uso moderado", "Puma", 89.99, transp3, 39.0, false, "Preta", LocalDate.parse("2022-09-01"));
        Artigo art29 = new Sapatilha(false, 2, Artigo.Estado.MAU, "Sapatilha em bom estado", "Reebok", 99.99, transp2, 38.5, true, "Azul", LocalDate.parse("2022-06-01"));
        Artigo art30 = new Sapatilha(false, 5, Artigo.Estado.PESSIMO, "Sapatilha de lançamento", "Under Armour", 179.99, transp4, 41.0, true, "Verde", LocalDate.parse("2017-02-01"));
        Artigo art31 = new SapatilhaPremium(false, 5, Artigo.Estado.PESSIMO, "Sapatilha de lançamento", "Under Armour", 179.99, transp4, 41.0, true, "Verde", LocalDate.parse("2017-02-01"));

        // insere na vintage artigos
        vintage.addArtigoVenda(user1.getEmail(),art1);
        vintage.addArtigoVenda(user1.getEmail(),art11);
        vintage.addArtigoVenda(user1.getEmail(),art21);

        vintage.addArtigoVenda(user2.getEmail(),art2);
        vintage.addArtigoVenda(user2.getEmail(),art12);
        vintage.addArtigoVenda(user2.getEmail(),art22);

        vintage.addArtigoVenda(user3.getEmail(),art23);
        vintage.addArtigoVenda(user3.getEmail(),art13);
        vintage.addArtigoVenda(user3.getEmail(),art3);

        vintage.addArtigoVenda(user4.getEmail(),art4);
        vintage.addArtigoVenda(user4.getEmail(),art14);
        vintage.addArtigoVenda(user4.getEmail(),art24);

        vintage.addArtigoVenda(user5.getEmail(),art5);
        vintage.addArtigoVenda(user5.getEmail(),art15);
        vintage.addArtigoVenda(user5.getEmail(),art25);

        vintage.addArtigoVenda(user6.getEmail(),art6);
        vintage.addArtigoVenda(user6.getEmail(),art16);
        vintage.addArtigoVenda(user6.getEmail(),art26);

        vintage.addArtigoVenda(user7.getEmail(),art7);
        vintage.addArtigoVenda(user7.getEmail(),art17);
        vintage.addArtigoVenda(user7.getEmail(),art27);

        vintage.addArtigoVenda(user8.getEmail(),art8);
        vintage.addArtigoVenda(user8.getEmail(),art18);
        vintage.addArtigoVenda(user8.getEmail(),art28);

        vintage.addArtigoVenda(user9.getEmail(),art9);
        vintage.addArtigoVenda(user9.getEmail(),art19);
        vintage.addArtigoVenda(user9.getEmail(),art29);

        vintage.addArtigoVenda(user10.getEmail(),art10);
        vintage.addArtigoVenda(user10.getEmail(),art20);
        vintage.addArtigoVenda(user10.getEmail(),art30);
        vintage.addArtigoVenda(user10.getEmail(),art31);
        vintage.addArtigoVenda(user10.getEmail(),art32);

        // cria encomenda
        
        Encomenda encDoUser1 = new Encomenda(LocalDateTime.now());
        Encomenda encDoUser2 = new Encomenda(LocalDateTime.now());
        Encomenda encDoUser3 = new Encomenda(LocalDateTime.now());
        Encomenda encDoUser4 = new Encomenda(LocalDateTime.now());
        Encomenda encDoUser5 = new Encomenda(LocalDateTime.now());

        // adiciona artigos encomenda
        encDoUser1.addArtEncomenda(art4);
        encDoUser1.addArtEncomenda(art5);
        encDoUser2.addArtEncomenda(art2);
        encDoUser2.addArtEncomenda(art25);
        encDoUser3.addArtEncomenda(art27);
        encDoUser4.addArtEncomenda(art18);
        encDoUser4.addArtEncomenda(art7);
        encDoUser4.addArtEncomenda(art19);
        encDoUser5.addArtEncomenda(art1);
        encDoUser5.addArtEncomenda(art12);

        // insere na vintage encomendas
        encDoUser1.setEstado(Encomenda.State.Finalizada);
        vintage.addEncomenda(user1.getEmail(),encDoUser1);
        encDoUser2.setEstado(Encomenda.State.Finalizada);
        vintage.addEncomenda(user2.getEmail(),encDoUser2);
        encDoUser3.setEstado(Encomenda.State.Finalizada);
        vintage.addEncomenda(user3.getEmail(),encDoUser3);
        encDoUser4.setEstado(Encomenda.State.Finalizada);
        vintage.addEncomenda(user4.getEmail(),encDoUser4);
        encDoUser5.setEstado(Encomenda.State.Finalizada);
        vintage.addEncomenda(user5.getEmail(),encDoUser5);

        return vintage;
    }

}

