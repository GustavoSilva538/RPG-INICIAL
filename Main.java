import java.math.BigInteger;
import java.util.Scanner;
public class Main{
    public static void digitar(String texto) throws InterruptedException {
        for (int i = 0; i < texto.length(); i++) {
            System.out.print(texto.charAt(i));
            Thread.sleep(50);
        }
        System.out.println();
    }


    public static void main(String[] args )throws InterruptedException{

        Scanner sc = new Scanner(System.in);
        int vidaPlayer = 100;
        int vidaMaximaPlayer = 100;
        int danoPlayer = 20;
        int cura = 30;
        int quantidadeCura = 4;

        int vidaEsqueleto = 60;
        int danoEsqueleto = 15;
        int qunadoTomarDanoEsqueleto = danoEsqueleto;
        int round = 1;
        boolean tomouDano = false;

        digitar("\033[1;35m🎮⚔️ \033[1;31mBLOODIVINE \033[1;33mINICIANDO...\033[0m \033[1;36mPrepare-se para a batalha! ⚔️🎮\033[0m");
        Thread.sleep(3000);
        digitar("Apareceu um Esqueleto!!");


        while(vidaPlayer > 0 && vidaEsqueleto > 0){
            System.out.println("Round: " + round);
            System.out.println("--------------------");
            if (tomouDano){
                System.out.println("\033[32m❤\uFE0FSua Vida: " + vidaPlayer + "/" + vidaMaximaPlayer + "\033[31m" + " -" +  + qunadoTomarDanoEsqueleto + "\033[0m");
            }else{
                System.out.println("\033[32m❤\uFE0FSua Vida: " + vidaPlayer + "/" + vidaMaximaPlayer + "\033[0m");
            }

            System.out.println("\033[31m\uD83D\uDC7EVida Esqueleto: " + vidaEsqueleto );
            System.out.println("\033[34m\uD83D\uDCE6Curas disponiveis: " + quantidadeCura + "\033[0m");
            System.out.println("--------------------");
            System.out.println("Escolha sua ação:");
            System.out.println("1) ⚔\uFE0FAtacar");
            System.out.println("2) \uD83E\uDDEACurar");
            System.out.println("Opção:");

            if (!sc.hasNextInt()){
                for (int i = 0; i < 50; i++) {
                System.out.println();
            }
                digitar("\u274CEscolha Inválida");
                tomouDano = false;
                sc.next();
                Thread.sleep(1000);
                for (int i = 0; i < 50; i++) {
                    System.out.println();
                }
                continue;
            }



            int escolha = sc.nextInt();

            if (escolha == 1 ){
                vidaEsqueleto -= danoPlayer;
                vidaPlayer -= danoEsqueleto;
                tomouDano = true;
                digitar("⚔\uFE0F Você atacou o Esqueleto!");
                digitar("\uD83D\uDCA5 O Esqueleto contra-atacou!");
            } else if (escolha == 2) {
                if (quantidadeCura <= 0){
                    digitar("Você não tem mais curas");
                    continue;
                }

                vidaPlayer += cura;
                vidaPlayer -= danoEsqueleto;
                tomouDano = true;
                quantidadeCura--;
                digitar("\uD83D\uDC9AVocê se curou!");
                digitar("\uD83D\uDCA5O Esqueleto te atacou");
                if (vidaPlayer > 100){
                    vidaPlayer = 100;
                }
                round++;
            }else {
                for (int i = 0; i < 50; i++) {
                    System.out.println();
                }
                digitar("\u274CEscolha inválida");
                tomouDano = false;
                digitar("Digite o número da ação que vc deseja executar");
            }
            Thread.sleep(2500);

            for (int i = 0; i < 50; i++) {
                System.out.println();
            }

        }


        if (vidaPlayer <= 0){
            digitar("\uD83D\uDC80Você morreu");
        }else{
            System.out.println("--------------------");
            digitar("\uD83C\uDFC6Você venceu");
            digitar("------Fim de Jogo-----");
        }


        sc.close();

    }
}
