import java.util.Scanner;

public class TrabalhoFinalJava 
{
    /* MÉTODO PRINCIPAL */
    public TrabalhoFinalJava() 
    {
        Scanner teclado = new Scanner(System.in);
        char[][] tabuleiro = new char[6][7];
        char resposta = 'S';

        do 
        {
            System.out.println("Início do Jogo!");
            System.out.println();
            
            DefinirTabuleiro(tabuleiro);

            char jogador = SelecionarCor(teclado);
            char computador;
            
            if(jogador == 'V') 
            {
                computador = 'A';
            }
            else 
            {
                computador = 'V';
            }

            ImprimirTabuleiro(tabuleiro);
            
            boolean vencedor = false;
            
            do 
            {
                EfetuarJogada(teclado, tabuleiro, jogador);
                vencedor = VerificarVitoria(tabuleiro, jogador, computador);
                
                if(vencedor == true) 
                {
                    break;
                }

                EfetuarJogada(teclado, tabuleiro, computador);
                vencedor = VerificarVitoria(tabuleiro, jogador, computador);
            } 
            while(vencedor == false);

            System.out.println("Deseja jogar novamente? S/N");
            resposta = teclado.next().charAt(0);
            resposta = Character.toUpperCase(resposta);
        }     
        while(resposta == 'S');
    }

    /* Método de criação e alimentação da Matriz com valores "B" */
    public void DefinirTabuleiro(char[][] tabuleiro) 
    {
        for (int y = 0; y < 6; y++) 
        {
            for (int w = 0; w < 7; w++) 
            {
                tabuleiro[y][w] = '.';
            }
        }
    }

    /* Método de Impressão do Tabuleiro contendo a numeração das colunas */
    public void ImprimirTabuleiro(char[][] tabuleiro) 
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
        System.out.println("\n-------------\n0 1 2 3 4 5 6");
        System.out.println();
    }

    /* Métodos de seleção da cor para efetuar as jogadas */
    public char SelecionarCor(Scanner teclado) 
    {
        System.out.println("Escolha a cor V - Vermelho/ A - Azul");        
        char cor = ' ';
        String input = teclado.next();              

        cor = input.charAt(0);
        cor = Character.toUpperCase(cor);

        while((input.length() != 1) || (cor != 'V' && cor != 'A'))
        {
            while(input.length() != 1)
            {
                System.out.println("Por favor, insira somente 1 caracter, sendo V para vermelho ou A para azul");   
                input = teclado.next();
            }

            cor = input.charAt(0);
            cor = Character.toUpperCase(cor);

            while (cor != 'V' && cor != 'A') 
            {
                System.out.println("A cor não é válida, escolha V para vermelho ou A para azul");
                input = teclado.next();

                cor = input.charAt(0);
                cor = Character.toUpperCase(cor);        
            } 
        }

        System.out.println();

        return cor;
    }

    /* Método de validação de espaços vazios nas colunas */
    public int VerificarEspaco(char[][] tabuleiro, int coluna) 
    {
        int espaço = 0;
        for (int y = 0; y < 6; y++) 
        {
            if (tabuleiro[y][coluna] != '.') 
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

    /* Método para verificar se determinada coluna já foi completamente preenchida */
    public boolean VerificaColunaPreenchida(char[][] tabuleiro, int coluna)
    {
        boolean espaçoOcupado = false;
        if (tabuleiro[0][coluna] == '.') 
        {
            espaçoOcupado = false;
        } 
        else 
        {
            espaçoOcupado = true;
        }

        return espaçoOcupado;
    }

    /* Método de ação da jogada */
    public void EfetuarJogada(Scanner teclado, char[][] tabuleiro, char jogador) 
    {

        System.out.println("Escolha um número de 0 a 6 para fazer uma jogada:");

        int coluna = teclado.nextInt();
        boolean jogadaValida = true;

        do 
        {
            if (coluna < 0 || coluna > 6) 
            {
                System.out.println("Insira um número de 0 a 6");
                coluna = teclado.nextInt();
            }

            boolean espaçoOcupado = VerificaColunaPreenchida(tabuleiro, coluna);

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

        int espaço = VerificarEspaco(tabuleiro, coluna);

        tabuleiro[espaço][coluna] = jogador;
        ImprimirTabuleiro(tabuleiro);
        System.out.println();
    }

    /* Método de jogada do computador */
    public void EfetuarJogadaComputador(int coluna, char[][] tabuleiro, char computador) 
    {
        boolean espaçoOcupado = true;
        
        do 
        {
            coluna = (int) (Math.random() * 10);

            if(coluna <= 6) 
            {
                espaçoOcupado = VerificaColunaPreenchida(tabuleiro, coluna);
            }
        } 
        while(coluna > 6 && espaçoOcupado == true);

        int espaço = VerificarEspaco(tabuleiro, coluna);

        tabuleiro[espaço][coluna] = computador;

        ImprimirTabuleiro(tabuleiro);

        System.out.println("Computador jogou na coluna " + coluna);
        System.out.println();
    }    

    /* Método de verificação de vitória */
    public boolean VerificarVitoria(char[][] tabuleiro, char jogador, char computador) 
    {
        boolean vencedor = true; 
    
        /* Verificação de vitória na horizontal */
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
    
        /* Verificação de vitória na vertical */
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
    
        /* Verificação de vitória na diagonal, da esquerda para a direita */
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
    
        /* Verificação de vitória na diagonal, da direita para a esquerda */
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
    
        /* Caso não tenha vitória nas verificações acima, ele varre a matriz analisando se há espaços em branco.
           Se não houver espaços em branco, ele retorna o empate! */
        for(int y = 0; y <= 5; y++) 
        {
            for(int w = 0; w <= 6; w++) 
            {
    
                if(tabuleiro[y][w] != '.') 
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
