import java.util.Scanner;
public class Main{
    public static void main(String[] args )throws InterruptedException{
        Scanner sc = new Scanner(System.in);
        int vidaPlayer = 100;
        int danoPlayer = 20;
        int cura = 30;
        int quantidadeCura = 4;

        int vidaEsqueleto = 60;
        int danoEsqueleto = 15;

        System.out.println("\033[1;35m🎮⚔️ \033[1;31mBLOODIVINE \033[1;33mINICIANDO...\033[0m \033[1;36mPrepare-se para a batalha! ⚔️🎮\033[0m");
        Thread.sleep(3000);
        System.out.println("Apareceu um Esqueleto!!");


        while(vidaPlayer > 0 && vidaEsqueleto > 0){
            System.out.println("--------------------");
            System.out.println("\033[32m❤\uFE0FSua Vida: " + vidaPlayer + "\033[0m");
            System.out.println("\033[31m\uD83D\uDC7EVida Esqueleto: " + vidaEsqueleto + "\033[0m");
            System.out.println("\033[34m\uD83D\uDCE6Curas disponiveis: " + quantidadeCura + "\033[0m");
            System.out.println("--------------------");
            System.out.println("Escolha sua ação:");
            System.out.println("1) Atacar⚔\uFE0F");
            System.out.println("2) Curar\uD83E\uDDEA");
            System.out.println("Opção:");

            if (!sc.hasNextInt()){
                System.out.println("\u274CEscolha Inválida");
                sc.next();
                continue;
            }


            int escolha = sc.nextInt();

            if (escolha == 1 ){
                vidaEsqueleto -= danoPlayer;
                vidaPlayer -= danoEsqueleto;
                System.out.println("⚔\uFE0F Você atacou o Esqueleto!");
                System.out.println("\uD83D\uDCA5 O Esqueleto contra-atacou!");
            } else if (escolha == 2) {
                if (quantidadeCura <= 0){
                    System.out.println("Você não tem mais curas");
                    continue;
                }

                vidaPlayer += cura;
                vidaPlayer -= danoEsqueleto;
                quantidadeCura--;
                System.out.println("\uD83D\uDC9AVocê se curou!");
                System.out.println("O Esqueleto te atacou");
                if (vidaPlayer > 100){
                    vidaPlayer = 100;
                }
            }else {
                System.out.println("\u274CEscolha inválida");
            }
            Thread.sleep(2500);

            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }


            if (vidaPlayer <= 0){
                System.out.println("\uD83D\uDC80Você morreu");
            }else{
                System.out.println("--------------------");
                System.out.println("\uD83C\uDFC6Você venceu");
                System.out.println("------Fim de Jogo-----");
            }


        sc.close();

    }
}

