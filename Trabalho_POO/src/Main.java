import java.util.Scanner;
import Entities.Sistema;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sistema sistema = new Sistema();

        while (true) {
            mostrarMenu();
            try {
                System.out.println("Qual opção você quer?: ");
                int opcao = Integer.parseInt(sc.nextLine());
                System.out.println("\n");

                switch (opcao) {
                    case 0:
                        System.out.println("Saindo do programa...");
                        sc.close();
                        return;
                    case 1:
                        sistema.AdicionarPalestrante(sc);
                        break;
                    case 2:
                        sistema.RemoverPalestrante(sc);
                        break;
                    case 3:
                        sistema.EditarPalestrante(sc);
                        break;
                    case 4:
                        sistema.AdicionarParticipante(sc);
                        break;
                    case 5:
                        sistema.RemoverParticipante(sc);
                        break;
                    case 6:
                        sistema.EditarParticipante(sc);
                        break;
                    case 7:
                        sistema.AdicionarLocal(sc);
                        break;
                    case 8:
                        sistema.RemoverLocal(sc);
                        break;
                    case 9:
                        sistema.EditarLocal(sc);
                        break;
                    case 10:
                        sistema.AdicionarPalestra(sc);
                        break;
                    case 11:
                        sistema.RemoverPalestra(sc);
                        break;
                    case 12:
                        sistema.EditarPalestra(sc);
                        break;
                    case 13:
                        sistema.Inscricao(sc);
                        break;
                    case 14:
                        sistema.RemoverInscricao(sc);
                        break;
                    case 15:
                        sistema.ListaPresenca(sc);
                        break;
                    case 16:
                        sistema.ListaLocalEPalestras();
                        break;
                    case 17:
                        sistema.PalestrasDeParticipante(sc);
                        break;
                    case 18:
                        sistema.EmitirCertificados(sc);
                        break;
                    default:
                        System.out.println("Digite um valor válido");
                        break;
                }
            } catch (Exception exception) {
                System.out.println("\n" + exception.getMessage() + "\n");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("0 - Sair do programa");
        System.out.println("1 - Adicionar palestrante");
        System.out.println("2 - Remover palestrante");
        System.out.println("3 - Editar palestrante");
        System.out.println("4 - Adicionar participante");
        System.out.println("5 - Remover participante");
        System.out.println("6 - Editar participante");
        System.out.println("7 - Adicionar Local");
        System.out.println("8 - Remover Local");
        System.out.println("9 - Editar Local");
        System.out.println("10 - Adicionar palestra");
        System.out.println("11 - Remover palestra");
        System.out.println("12 - Editar palestra");
        System.out.println("13 - Inscrever participante em palestra");
        System.out.println("14 - Desinscrever participante em palestra");
        System.out.println("15 - Gerar lista de presença");
        System.out.println("16 - Listar locais e suas palestras");
        System.out.println("17 - Listar palestras de um participante");
        System.out.println("18 - Emitir certificados de uma palestra");
    }
}