package Application;

import Entities.Missao;
import Entities.MundoVirtual;
import Models.Habilidade;
import Models.Inimigo;
import Models.Item;
import Models.Personagem;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jogo {
    public static void iniciarJogo() {
            Scanner scanner = new Scanner(System.in);
            Random random = new Random();

            boolean jogoAtivo = true;

            Item i1 = new Item("Ak-47", "arma que atira", "rifle", "Sangramento");
            Item i3 = new Item("Poção de velocidade", "Poções", "Rapida", "Velocidade anormal");
            Inimigo orc = new Inimigo("Orc", 10, 100, 100);
            Missao m1 = new Missao("Orc maldito", "Mate 1 orc que está perturbando a região", "Mate 1 orc");
            Habilidade h2 = new Habilidade("Rajada de tiro", "muito tiro", 10, 40);
            Habilidade h1 = new Habilidade("Rugir", "Som alto", 5, 10);
            Habilidade h3 = new Habilidade("Machadada violenta","machadada forte",10,13);
            Habilidade h4 = new Habilidade("Bola de Fogo", "Lança uma bola de fogo", 15, 55);
            Habilidade h5 = new Habilidade("Veneno Mortal", "Envenena o alvo", 8, 20);
            Habilidade h7 = new Habilidade("Ataque Duplo", "Realiza dois ataques em sequência", 12, 30);
            Habilidade h8 = new Habilidade("Soco poderoso","um soco bem forte",30,10);

            orc.getHabilidadesDeCombate().add(h2);
            orc.getHabilidadesDeCombate().add(h3);
            orc.getHabilidadesDeCombate().add(h1);
            Personagem personagem = null;

            MundoVirtual mundo = new MundoVirtual(10, 10);

            mundo.getInimigos().add(orc);
            mundo.getMissoesAtivas().add(m1);


            System.out.println("Bem-vindo ao Jogo de RPG!");
            System.out.println("1. Iniciar Jogo");
            System.out.println("0. Sair");

            int escolhaMenuInicial = scanner.nextInt();

            if (escolhaMenuInicial == 1) {

                personagem = new Personagem("Cocudo",200,200,100);
                System.out.print("O nome do personagem é  " + personagem.getNome() + ", ");
                personagem.getHabilidades().add(h4);
                personagem.getHabilidades().add(h5);
                personagem.getHabilidades().add(h7);
                System.out.println(" Posição Inicial: (" + personagem.getPosicaoX() + ", " + personagem.getPosicaoY() + ")");
            } else if (escolhaMenuInicial == 0) {

                System.out.println("Obrigado por jogar!");
                jogoAtivo = false;
            } else {
                System.out.println("Opção inválida. O jogo será encerrado.");
                jogoAtivo = false;
            }


            while (jogoAtivo) {

                int escolha = Menu.mostrarMenu();


                switch (escolha) {
                    case 1:
                        mundo.inicarMissao(m1, personagem);
                        break;
                    case 2:
                        mundo.caminhar(personagem, 0, -1);
                        break;
                    case 3:
                        mundo.caminhar(personagem, 0, 1);
                        break;
                    case 4:
                        mundo.caminhar(personagem, -1, 0);
                        break;
                    case 5:
                        mundo.caminhar(personagem, 1, 0);
                        break;
                    case 6:
                        personagem.getItens().add(i1);
                        mundo.pegarItemPersonagem(personagem, i1);
                        break;
                    case 7:
                        personagem.getItens().add(i3);
                        mundo.usarItemPersonagem(personagem, i3);
                        break;
                    case 8:
                        mundo.escolherHabilidadePersonagem(personagem,orc);
                        if (orc.getSaude() <= 0) {
                            mundo.morrerInimigo(orc);
                        } else {
                            mundo.escolherHabilidadeInimigo(orc,personagem);
                        }
                        break;
                    case 9:
                        mundo.completarMissao(m1, personagem);
                        m1.getRecompensas().add(new Item("Poção de cura", "Poção que restaura a vida.", "Poção", "Cura"));
                        System.out.println("Recompensa: Poção de cura");
                        break;
                    case 0:

                        System.out.println("Obrigado por jogar!");
                        jogoAtivo = false;
                        break;
                    default:
                        System.out.println("Opção inválida. Escolha novamente.");
                        break;
                }
                System.out.println("Posição Atual: (" + personagem.getPosicaoX() + ", " + personagem.getPosicaoY() + ")");
            }
            scanner.close();
        }
}

