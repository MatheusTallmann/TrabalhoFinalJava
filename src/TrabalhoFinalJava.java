import java.util.Scanner;

public class TrabalhoFinalJava 
{

    public TrabalhoFinalJava() 
    {
        Scanner teclado = new Scanner(System.in);
        char[][] tabuleiro = new char[6][7];
        char resposta = 'S';

        do 
        {
            System.out.println("Bem vindo ao Liga-4");

            definirTabuleiro(tabuleiro);

            char jogador = escolherCor(teclado);
            char computador;
            
            if(jogador == 'V') 
            {
                computador = 'A';
            }
            else 
            {
                computador = 'V';
            }

            imprimirTabuleiro(tabuleiro);
            boolean vencedor = false;
            
            do 
            {
                jogadaPessoa(teclado, tabuleiro, jogador);
                vencedor = verificarVitória(tabuleiro, jogador, computador);
                
                if(vencedor == true) 
                {
                    break;
                }

                jogadaComputador(jogador, tabuleiro, computador);
                vencedor = verificarVitória(tabuleiro, jogador, computador);
            } 
            while(vencedor == false);

            System.out.println("Deseja jogar novamente? S/N");
            resposta = teclado.next().charAt(0);
            resposta = Character.toUpperCase(resposta);
        }     
        while(resposta == 'S');
    }

    public void definirTabuleiro(char[][] tabuleiro) 
    {
        for (int y = 0; y < 6; y++) 
        {
            for (int w = 0; w < 7; w++) 
            {
                tabuleiro[y][w] = 'B';
            }
        }
    }

    public void imprimirTabuleiro(char[][] tabuleiro) 
    {
        for (int x = 0; x < 6; x++) 
        {
            if (x > 0) 
            {
                System.out.println();
            }
            for (int y = 0; y < 7; y++) 
            {
                System.out.print(tabuleiro[x][y] + " ");
            }
        }

        System.out.println();
    }

    public char escolherCor(Scanner teclado) 
    {
        System.out.println("Escolha a cor V - Vermelho/ A - Azul");
        char jogador = teclado.next().charAt(0);

        jogador = Character.toUpperCase(jogador);

        while (jogador != 'V' && jogador != 'A') 
        {
            System.out.println("A cor não é válida, escolha V para vermelho ou A para azul");
            jogador = teclado.next().charAt(0);
            jogador = Character.toUpperCase(jogador);
        
        } 
        
        return jogador;
    }

    public int verificarEspaço(char[][] tabuleiro, int coluna) 
    {
        int espaço = 0;
        for (int y = 0; y < 6; y++) 
        {
            if (tabuleiro[y][coluna] != 'B') 
            {
                espaço = y - 1;
                break;
            }
            else 
            {
                espaço = 5;
            }
        }

        return espaço;
    }

    public boolean verificarEspaçoOcupado(char[][] tabuleiro, int coluna) 
    {
        boolean espaçoOcupado = false;
        if (tabuleiro[0][coluna] == 'B') 
        {
            espaçoOcupado = false;
        } 
        else 
        {
            espaçoOcupado = true;
        }

        return espaçoOcupado;
    }

    public void jogadaPessoa(Scanner teclado, char[][] tabuleiro, char jogador) 
    {

        System.out.println("Escolha a coluna para fazer a jogada:");

        int coluna = teclado.nextInt();
        boolean jogadaValida = true;

        do 
        {
            if (coluna < 0 || coluna > 6) 
            {
                System.out.println("Insira um número de 0 a 6");
                coluna = teclado.nextInt();
            }

            boolean espaçoOcupado = verificarEspaçoOcupado(tabuleiro, coluna);

            if (espaçoOcupado == true) {
                System.out.println("Não há espaço na coluna escolhida, escolha outra:");
                coluna = teclado.nextInt();
            }
            else 
            {
                jogadaValida = false;
            }
        } 
        while (jogadaValida);

        int espaço = verificarEspaço(tabuleiro, coluna);

        tabuleiro[espaço][coluna] = jogador;
        imprimirTabuleiro(tabuleiro);
        System.out.println();
    }

    public void jogadaComputador(int coluna, char[][] tabuleiro, char computador) 
    {

        boolean espaçoOcupado = true;
        
        do 
        {
            coluna = (int) (Math.random() * 10);

            if(coluna <= 6) 
            {
                espaçoOcupado = verificarEspaçoOcupado(tabuleiro, coluna);
            }
        } 
        while(coluna > 6 && espaçoOcupado == true);

        int espaço = verificarEspaço(tabuleiro, coluna);

        tabuleiro[espaço][coluna] = computador;

        imprimirTabuleiro(tabuleiro);

        System.out.println("Computador jogou na coluna " + coluna);
        System.out.println();
    }    
    
    public boolean verificarVitória(char[][] tabuleiro, char jogador, char computador) 
    {
        boolean vencedor = true; 

        for(int y = 0; y <= 5; y++) 
        {
            for(int w = 0; w <= 3; w++) 
            {
                if(tabuleiro[y][w] == jogador && tabuleiro[y][w + 1] == jogador && tabuleiro[y][w + 2] == jogador && tabuleiro[y][w + 3] == jogador) 
                {
                    System.out.println("Você venceu!");
                    vencedor = true;
                    return vencedor;
                }
                if(tabuleiro[y][w] == computador && tabuleiro[y][w + 1] == computador && tabuleiro[y][w + 2] == computador && tabuleiro[y][w + 3] == computador) 
                {
                    System.out.println("Computador venceu!");
                    vencedor = true;
                    return vencedor;
                }
                else 
                {
                    vencedor = false;
                }
            }
        }

        for(int y = 0; y <= 6; y++) 
        {
            for(int w = 0; w <= 2; w++) 
            {
                if(tabuleiro[w][y] == jogador && tabuleiro[w + 1][y] == jogador && tabuleiro[w + 2][y] == jogador && tabuleiro[w + 3][y] == jogador) 
                {
                    System.out.println("Você venceu!");
                    vencedor = true;
                    return vencedor;
                }
                if(tabuleiro[w][y] == computador && tabuleiro[w + 1][y] == computador && tabuleiro[w + 2][y] == computador && tabuleiro[w + 3][y] == computador) 
                {
                    System.out.println("Computador venceu!");
                    vencedor = true;
                    return vencedor;
                }
                else 
                {
                    vencedor = false;
                }
            }

        }

        for(int y = 0; y < tabuleiro.length - 3; y++) 
        {
            for(int w = 0; w < tabuleiro[y].length - 3; w++) 
            {
                if(tabuleiro[y][w] == jogador && tabuleiro[y + 1][w + 1] == jogador && tabuleiro[y + 2][w + 2] == jogador && tabuleiro[y + 3][w + 3] == jogador) 
                {
                    System.out.println("Você venceu");
                    vencedor = true;
                    return vencedor;
                }
                if(tabuleiro[y][w] == computador && tabuleiro[y + 1][w + 1] == computador && tabuleiro[y + 2][w + 2] == computador && tabuleiro[y + 3][w + 3] == computador) 
                {
                    System.out.println("Computador venceu!");
                    vencedor = true;
                    return vencedor;
                }
                else 
                {
                    vencedor = false;
                }
            }

        }

        for(int y = 0; y < tabuleiro.length - 3; y++) 
        {
            for(int w = 3; w < tabuleiro[y].length - 3; w++) 
            {
                if(tabuleiro[y][w] == jogador && tabuleiro[y + 1][w - 1] == jogador && tabuleiro[y + 2][w - 2] == jogador && tabuleiro[y + 3][w - 3] == jogador) 
                {
                    System.out.println("Você venceu!");
                    vencedor = true;
                    return vencedor;
                }
                if(tabuleiro[y][w] == computador && tabuleiro[y + 1][w - 1] == computador && tabuleiro[y + 2][w - 2] == computador && tabuleiro[y + 3][w - 3] == computador) 
                {
                    System.out.println("Computador venceu!");
                    vencedor = true;
                    return vencedor;
                }
                else 
                {
                    vencedor = false;
                }
            }
        } 

        
        for(int y = 0; y <= 5; y++) 
        {
            for(int w = 0; w <= 6; w++) 
            {
                if(tabuleiro[y][w] != 'B') 
                {
                    vencedor = true;
                }
                else 
                {
                    vencedor = false;
                    return vencedor;
                }
            }
        }

        if(vencedor == true) 
        {
            System.out.println("Empate!");
        } 

        return vencedor;

    }

        

    public static void main(String[] args) 
    {
        new TrabalhoFinalJava();
    }
}
