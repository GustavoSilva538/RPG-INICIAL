import java.util.Scanner;
public class Main{

    public static void digitar(String texto) throws InterruptedException {
        for (int i = 0; i < texto.length(); i++) {
            System.out.print(texto.charAt(i));
            Thread.sleep(50);
        }
        System.out.println();
    }

    public static  void mostrarMenu(int vidaEsqueleto, int quantidadeCura) {
        System.out.println(cores.vermelho + "\uD83D\uDC7EVida Esqueleto: " + vidaEsqueleto + cores.reset);
        System.out.println(cores.azul + "\uD83D\uDCE6Curas disponiveis: " + quantidadeCura + "\033[0m" + cores.reset);
        System.out.println("--------------------");
        System.out.println("Escolha sua ação:");
        System.out.println("1) ⚔\uFE0FAtacar");
        System.out.println("2) \uD83E\uDDEACurar");
        System.out.println("Opção:");
    }

    public static void limparTela(){
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public class cores{
        static final String verde = "\033[32m";
        static final String vermelho = "\033[31m";
        static final String azul = "\033[34m";
        static final String vermelhoForte = "\033[1;31m";
        static final String amareloForte = "\033[1;33m";
        static final String cianoForte = "\033[1;36m";
        static final String reset = "\033[0m";
    }

    public static void main(String[] args )throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        int vidaPlayer = 100;
        int vidaMaximaPlayer = 100;
        int danoPlayer = 20;
        int cura = 30;
        int quantidadeCura = 4;

        int vidaEsqueleto = 60;
        int danoEsqueleto = 15;
        int round = 1;
        boolean tomouDano = false;


        digitar("🎮⚔️ " + cores.vermelhoForte + "BLOODIVINE " + cores.amareloForte + "INICIANDO... " + cores.cianoForte + "Prepare-se para a batalha! ⚔️🎮" + cores.reset);
        Thread.sleep(3000);
        digitar("Apareceu um Esqueleto!!");

        while (vidaPlayer > 0 && vidaEsqueleto > 0) {
            System.out.println("Round: " + round);
            System.out.println("--------------------");
            if (tomouDano) {
                System.out.println(cores.verde + "❤\uFE0FSua Vida: " + vidaPlayer + "/" + vidaMaximaPlayer + cores.vermelho + " -" + danoEsqueleto + cores.reset);
            } else {
                System.out.println(cores.verde + "❤\uFE0FSua Vida: " + vidaPlayer + "/" + vidaMaximaPlayer + cores.reset);
            }

            mostrarMenu(vidaEsqueleto, quantidadeCura);

            if (!sc.hasNextInt()) {
                limparTela();
                digitar("\u274CEscolha Inválida");
                tomouDano = false;
                sc.next();
                Thread.sleep(1000);
                limparTela();
                continue;
            }

            int escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    vidaEsqueleto -= danoPlayer;
                    vidaPlayer -= danoEsqueleto;
                    tomouDano = true;
                    digitar("⚔\uFE0F Você atacou o Esqueleto!");
                    digitar("\uD83D\uDCA5 O Esqueleto contra-atacou!");
                    digitar("\uD83E\uDE78Dano recebido:" + cores.vermelho + " -" + danoEsqueleto + cores.reset);
                    Thread.sleep(2500);
                    round++;
                    limparTela();
                    break;
                case 2:
                    if (quantidadeCura <= 0) {
                        digitar("\u274CVocê não tem mais curas");
                        Thread.sleep(2500);
                        limparTela();
                        continue;
                    }

                    vidaPlayer += cura;
                    vidaPlayer -= danoEsqueleto;
                    tomouDano = true;
                    quantidadeCura--;
                    digitar("\uD83D\uDC9AVocê se curou! " + cores.verde + "+" + cura + cores.reset);
                    digitar("\uD83D\uDCA5O Esqueleto te atacou!");
                    digitar("\uD83E\uDE78Dano recebido: " + cores.vermelho + " -" + danoEsqueleto + cores.reset);
                    if (vidaPlayer > 100) {
                        vidaPlayer = 100;
                    }
                    Thread.sleep(2500);
                    round++;
                    limparTela();
                    break;

                default:
                    limparTela();
                    digitar("\u274CEscolha inválida");
                    tomouDano = false;
                    digitar("Digite o número da ação que vc deseja executar");
                    Thread.sleep(2500);
                    round++;
                    limparTela();
            }
        }

        if (vidaPlayer <= 0 || vidaEsqueleto <= 0)
            if (vidaPlayer <= 0) {
                digitar("\uD83D\uDC80Você morreu");
            } else {
                System.out.println("--------------------");
                digitar("\uD83C\uDFC6Você venceu");
                digitar("------Fim de Jogo-----");
            }
        
            sc.close();


    }
}
