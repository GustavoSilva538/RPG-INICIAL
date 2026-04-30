
import java.util.Scanner;
public class Main6 {

    public static void digitar(String texto) throws InterruptedException {
        for (int i = 0; i < texto.length(); i++) {
            System.out.print(texto.charAt(i));
            Thread.sleep(20);
        }
        System.out.println();
    }

    public static Player escolhaClasse() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Player player = Player.criarPlayer(rpgClasses.GUERREIRO);

        digitar("Sua história no RPG de " + cores.vermelhoForte + "BLOODVINE" + " INICIOU!!!" + cores.reset);

        Thread.sleep(1500);
        limparTela();

        while (true) {

            digitar("Digite 1, 2 ou 3 para escolher sua classe no RPG de " + cores.vermelhoForte + "BLOODVINE" + cores.reset);
            System.out.println("1) ⚔️" + rpgClasses.GUERREIRO);
            System.out.println("2) 🗡️" + rpgClasses.ASSASSINO);
            System.out.println("3) 🛡️" + rpgClasses.TANK);

            if (!sc.hasNextInt()) {
                limparTela();
                digitar("❌Escolha Inválida. Digite um número de 1 a 3.");
                sc.next();
                Thread.sleep(1000);
                limparTela();
                continue;
            }

            int numClasse = sc.nextInt();

            if (numClasse == 1) {
                player = Player.criarPlayer(rpgClasses.GUERREIRO);
                digitar("Sua escolha foi a classe " + rpgClasses.GUERREIRO);
                Thread.sleep(2000);
                limparTela();
                return player;

            } else if (numClasse == 2) {
                player = Player.criarPlayer(rpgClasses.ASSASSINO);
                digitar("Sua escolha foi a classe " + rpgClasses.ASSASSINO);
                Thread.sleep(2000);
                limparTela();
                return player;

            } else if (numClasse == 3) {
                player = Player.criarPlayer(rpgClasses.TANK);
                digitar("Sua escolha foi a classe " + rpgClasses.TANK);
                Thread.sleep(2000);
                limparTela();
                return player;
            }

            if (numClasse > 3 || numClasse < 1) {
                limparTela();
                digitar("❌Escolha Inválida. Digite um número de 1 a 3.");
                Thread.sleep(1000);
                limparTela();
            }
        }
    }

    public static void mostrarMenu(Inimigo inimigo, Player player, int quantidadeCura, int round) {
        System.out.println("Round: " + round);
        System.out.println("========================");
        System.out.println(cores.verde + "❤️ Sua Vida: " + player.vida + "/" + player.vidaMax + cores.reset);
        System.out.println(cores.vermelho + "👾 Vida " + inimigo.nome + ": " + inimigo.vida + cores.reset);
        System.out.println(cores.azul + "📦 Curas disponiveis: " + quantidadeCura + "\033[0m" + cores.reset);
        System.out.println("========================");
        System.out.println("Escolha sua ação:");
        System.out.println("1) ⚔️ Atacar");
        System.out.println("2) 🧪 Curar");
        System.out.println("3) 📊 Status");
        System.out.print("Opção:");
    }

    public static void mostrarMenu2(Inimigo inimigo, int quantidadeCura, int round, Player player) {
        System.out.println("Round: " + round);
        System.out.println("========================");
        System.out.println(cores.verde + "❤️ Sua Vida: " + player.vida + "/" + player.vidaMax + cores.vermelho + " -" + inimigo.dano + cores.reset);
        System.out.println(cores.vermelho + "👾 Vida " + inimigo.nome + ": " + inimigo.vida + cores.reset);
        System.out.println(cores.azul + "📦 Curas disponiveis: " + quantidadeCura + "\033[0m" + cores.reset);
        System.out.println("========================");
        System.out.println("Escolha sua ação:");
        System.out.println("1) ⚔️ Atacar");
        System.out.println("2) 🧪 Curar");
        System.out.println("3) 📊 Status");
        System.out.print("Opção:");
    }

    public static void limparTela() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    public static class cores {
        static final String verde = "\033[32m";
        static final String vermelho = "\033[31m";
        static final String azul = "\033[34m";
        static final String roxo = "\033[35m";
        static final String amareloForte = "\033[1;33m";
        static final String cianoForte = "\033[1;36m";
        static final String vermelhoForte = "\033[1;31m";
        static final String negrito = "\033[1m";
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

                System.out.println("✨ Você se curou! Vida atual: " + cores.verde + vida + "/" + vidaMax + cores.reset);
            } else {
                System.out.println(cores.amareloForte + "❌Você não tem mais curas disponíveis!" + cores.reset);
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
        public static final String GUERREIRO = cores.negrito + cores.vermelhoForte + "GUERREIRO" + cores.reset;
        public static final String ASSASSINO = cores.negrito + cores.roxo + "ASSASSINO" + cores.reset;
        public static final String TANK = cores.negrito + cores.azul + "TANK" + cores.reset;
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
                mostrarMenu2(inimigo, player.quantidadeCura, round, player);
            } else {
                mostrarMenu(inimigo, player, player.quantidadeCura, round);
            }

            if (!sc.hasNextInt()) {
                limparTela();
                digitar("❌Escolha Inválida. Digite um número de 1 a 3.");
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

                    digitar("⚔️ Você atacou o " + inimigo.nome + "!");
                    digitar("💥 O " + inimigo.nome + " contra-atacou!");
                    digitar("🩸Dano recebido:" + cores.vermelho + " -" + inimigo.dano + cores.reset);

                    Thread.sleep(2500);
                    round++;
                    limparTela();
                    break;

                case 2:
                    player.curar();
                    inimigo.atacar(player);
                    danoAcumulado += inimigo.dano;
                    tomouDano = true;

                    digitar("💥O " + inimigo.nome + " te atacou!");
                    digitar("🩸Dano recebido: " + cores.vermelho + " -" + inimigo.dano + cores.reset);

                    Thread.sleep(2500);
                    round++;
                    limparTela();
                    break;

                case 3:
                    if (tomouDano) {
                        limparTela();
                        System.out.println("======Status======");
                        System.out.println("Dano básico: " + cores.vermelhoForte + player.dano + cores.reset);
                        System.out.println("Vida: " + cores.verde + player.vida + "/" + player.vidaMax + cores.reset);
                        System.out.println("Total dano recebido: " + cores.vermelho + "-" + danoAcumulado + cores.reset);
                        Thread.sleep(3000);

                    } else {
                        limparTela();
                        System.out.println("======Status======");
                        System.out.println("Dano básico: " + cores.vermelhoForte + player.dano + cores.reset);
                        System.out.println("Vida: " + cores.verde + player.vida + "/" + player.vidaMax + cores.reset);
                        Thread.sleep(3000);
                        limparTela();
                    }
                    break;
            }

            limparTela();
        }

        if (player.vida <= 0) {
            digitar("☠️Você morreu");
        } else {
            System.out.println("==========================");
            digitar("=======🎖️Você venceu======");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);

        Player player = escolhaClasse();
        Inimigo inimigo = Inimigo.gerarInimigo(Inimigos.ESQUELETO);

        digitar("🎮⚔️ " + cores.negrito + cores.vermelhoForte + "BLOODIVINE " + cores.amareloForte + "INICIANDO... " + cores.cianoForte + "Prepare-se para a batalha! 🎮⚔️" + cores.reset);

        Thread.sleep(3000);

        combate(player, inimigo, sc);

        sc.close();
    }
}




