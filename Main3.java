import java.util.Scanner;

public class Main3 {

    public static void digitar(String texto) throws InterruptedException {
        for (int i = 0; i < texto.length(); i++) {
            System.out.print(texto.charAt(i));
            Thread.sleep(50);
        }
        System.out.println();
    }

    public static  void mostrarMenu(Inimigo inimigo, int quantidadeCura) {
        System.out.println(cores.vermelho + "\uD83D\uDC7E Vida" + " " + inimigo.nome  + " " + inimigo.vida + cores.reset);
        System.out.println(cores.azul + "\uD83D\uDCE6 Curas disponiveis: " + quantidadeCura + "\033[0m" + cores.reset);
        System.out.println("--------------------");
        System.out.println("Escolha sua ação:");
        System.out.println("1) ⚔\uFE0F Atacar");
        System.out.println("2) \uD83E\uDDEACurar");
        System.out.println("3) Status");
        System.out.println("Opção:");
    }

	public static  void mostrarMenu2(Inimigo inimigo, int quantidadeCura, int round, Player player) {
	    System.out.println("Round: " + round);
        System.out.println("--------------------");
        System.out.println(cores.verde + "\uFE0F Sua Vida: " + player.vida + "/" + player.vidaMax + cores.vermelho + " -" + inimigo.dano + cores.reset);
        System.out.println(cores.vermelho + "\uD83D\uDC7E Vida" + " " + inimigo.nome  + " " + inimigo.vida + cores.reset);
        System.out.println(cores.azul + "\uD83D\uDCE6 Curas disponiveis: " + quantidadeCura + "\033[0m" + cores.reset);
        System.out.println("--------------------");
        System.out.println("Escolha sua ação:");
        System.out.println("1) ⚔\uFE0F Atacar");
        System.out.println("2) \uD83E\uDDEA Curar");
        System.out.println("3) Status");
        System.out.println("Opção:");
    }

    public static void limparTela() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    public static class cores {
        static final String verde = "\033[32m";
        static final String vermelho = "\033[31m";
        static final String azul = "\033[34m";
        static final String vermelhoForte = "\033[1;31m";
        static final String amareloForte = "\033[1;33m";
        static final String cianoForte = "\033[1;36m";
        static final String reset = "\033[0m";
    }

    public static class Player {
        int vida;
        int vidaMax;
        int dano;
        int cura;
        int quantidadeCura;
        String classe;

        public void atacar(Inimigo inimigo) {
            inimigo.vida -= dano;
        }

        public void curar() {
            if (quantidadeCura > 0) {
                vida += cura;
                if (vida > vidaMax) 
									vida = vidaMax;
                quantidadeCura--;
								System.out.println(cores.verde + "\u2728 Você se curou! Vida atual: " + vida + "/" + vidaMax + cores.reset);
            }
						else {
								System.out.println(cores.amareloForte + "\\u26A0\\uFE0F Você não tem mais curas disponíveis!" + cores.reset);
								limparTela();
						}
        }

        public void receberDano(int dano) {
            vida -= dano;
        }

        public static Player criarPlayer(String classeEscolhida) {
            if (classeEscolhida.equals(rpgClasses.GUERREIRO)) {
                Player p = new Player();
                p.vida = 100;
                p.vidaMax = 100;
                p.dano = 20;
                p.cura = 30;
                p.quantidadeCura = 4;
                p.classe = rpgClasses.GUERREIRO;
                return p;
            } else if (classeEscolhida.equals(rpgClasses.ASSASSINO)) {
                Player p = new Player();
                p.vida = 90;
                p.vidaMax = 90;
                p.dano = 15;
                p.cura = 30;
                p.quantidadeCura = 3;
                p.classe = rpgClasses.ASSASSINO;
                return p;
            } else if (classeEscolhida.equals(rpgClasses.TANK)) {
                Player p = new Player();
                p.vida = 150;
                p.vidaMax = 150;
                p.dano = 15;
                p.cura = 40;
                p.quantidadeCura = 5;
                p.classe = rpgClasses.TANK;
                return p;
            }
            return null;
        }
    }

    public static class rpgClasses {
        public static final String GUERREIRO = "GUERREIRO";
        public static final String ASSASSINO = "ASSASSINO";
        public static final String TANK = "TANK";
    }

    public static class Inimigos {
        public static final String ESQUELETO = "ESQUELETO";
        public static final String GOBLIN = "GOBLIN";
    }

    public static class Inimigo {
        int vida;
        int dano;
        String nome;

        public void atacar(Player player) {
            player.vida -= dano;
        }

        public static Inimigo gerarInimigo(String nomeInimigo) {
            if (nomeInimigo.equals(Inimigos.ESQUELETO)) {
                Inimigo i = new Inimigo();
                i.vida = 60;
                i.dano = 15;
                i.nome = Inimigos.ESQUELETO;
                return i;
            } else if (nomeInimigo.equals(Inimigos.GOBLIN)) {
                Inimigo i = new Inimigo();
                i.vida = 50;
                i.dano = 10;
                i.nome = Inimigos.GOBLIN;
                return i;
            }
            return null;
        }
    }



    public static void combate(Player player, Inimigo inimigo, Scanner sc) throws InterruptedException {
        int round = 1;
				boolean tomouDano = false;
				int danoAcumulado = 0;

        digitar("Apareceu um " + inimigo.nome + "!!");

        while (player.vida > 0 && inimigo.vida > 0) {

						if (tomouDano) {
							mostrarMenu2(inimigo, danoAcumulado, round, player);
						}
						else {
            mostrarMenu(inimigo, player.quantidadeCura);
						}

            if (!sc.hasNextInt()) {
                limparTela();
                digitar("\u274CEscolha Inválida. Digite um número de 1 a 3.");
                sc.next();
                Thread.sleep(1000);
                limparTela();
                continue;
            }

            int escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    player.atacar(inimigo);
                    inimigo.atacar(player);
										danoAcumulado += inimigo.dano;
										tomouDano = true;
                    digitar("⚔\uFE0F Você atacou o " + inimigo.nome + "!");
                    digitar("\uD83D\uDCA5 O " + inimigo.nome + " contra-atacou!");
                    digitar("\uD83E\uDE78Dano recebido:" + cores.vermelho + " -" + inimigo.dano + cores.reset);
                    Thread.sleep(2500);
										round++;
										limparTela();
                    break;
                case 2:
                    player.curar();
                    inimigo.atacar(player);
										danoAcumulado += inimigo.dano;
										tomouDano = true;
										digitar("\uD83D\uDCA5O " + inimigo.nome + " te atacou!");
                    digitar("\uD83E\uDE78Dano recebido: " + cores.vermelho + " -" + inimigo.dano + cores.reset);
                    Thread.sleep(2500);
										round++;
										limparTela();
                    break;
                case 3:
                   limparTela();
                    System.out.println("------Status------");
                    System.out.println("Dano básico: " + player.dano);
                    System.out.println("Vida: " + player.vida + "/" + player.vidaMax);
                    System.out.println("Total dano recebido: " + danoAcumulado);
                    Thread.sleep(3000);
                    limparTela();
                    break;
            }
            limparTela();
        }

        if (player.vida <= 0) {
          digitar("\uD83D\uDC80Você morreu");
        } else {
              System.out.println("--------------------");
                digitar("\uD83C\uDFC6 Você venceu");
                digitar("------Fim de Jogo-----");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        Player player = Player.criarPlayer(rpgClasses.GUERREIRO);
        Inimigo inimigo = Inimigo.gerarInimigo(Inimigos.ESQUELETO);

        digitar(" " + cores.vermelhoForte + "BLOODIVINE " + cores.amareloForte + "INICIANDO... " + cores.cianoForte + "Prepare-se para a batalha! " + cores.reset);
        Thread.sleep(3000);
        combate(player, inimigo, sc);

        sc.close();
    }
}
