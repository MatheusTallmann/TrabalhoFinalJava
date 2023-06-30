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

                EfetuarJogadaComputador(jogador, tabuleiro, computador);
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
        for (int i = 0; i < 6; i++) 
        {
            for (int j = 0; j < 7; j++) 
            {
                tabuleiro[i][j] = 'B';
            }
        }
    }

    /* Método de Impressão do Tabuleiro contendo a numeração das colunas */
    public void ImprimirTabuleiro(char[][] tabuleiro) 
    {
        for (int i = 0; i < 6; i++) 
        {
            if (i > 0) 
            {
                System.out.println();
            }
            for (int j = 0; j < 7; j++) 
            {
                System.out.print(tabuleiro[i][j] + " ");
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

    /* Método de ação da jogada */
    public void EfetuarJogada(Scanner teclado, char[][] tabuleiro, char jogador) 
    {

        System.out.println("Escolha um número de 0 a 6 para fazer uma jogada:");

        int coluna = 0;
        String jogada = "";
        char jogadaNumero = ' ';
        

        boolean jogadaValida = true;

        do 
        {        
            jogada = teclado.next();
            jogadaNumero = jogada.charAt(0);    

            while((jogada.length() != 1) || (jogadaNumero != '0' && jogadaNumero != '1' && jogadaNumero != '2' && jogadaNumero != '3' && jogadaNumero != '4' && jogadaNumero != '5' && jogadaNumero != '6'))
            {
                System.out.println("Favor insira somente um único número entre 0 e 6:");                    
                
                jogada = teclado.next();
                jogadaNumero = jogada.charAt(0);
            }

            coluna = Integer.parseInt(jogada);

            boolean espaçoOcupado = VerificaColunaPreenchida(tabuleiro, coluna);

            if (espaçoOcupado == true) 
            {
                System.out.println("Não há espaço na coluna escolhida, escolha outra:");
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

    /* Método de validação de espaços vazios nas colunas */
    public int VerificarEspaco(char[][] tabuleiro, int coluna) 
    {
        int espaço = 0;
        for (int i = 0; i < 6; i++) 
        {
            if (tabuleiro[i][coluna] != 'B') 
            {
                espaço = i - 1;
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

    /* Método de verificação de vitória */
    public boolean VerificarVitoria(char[][] tabuleiro, char jogador, char computador) 
    {
        boolean vencedor = true; 
    
        /* Verificação de vitória na horizontal */
        for(int i = 0; i <= 5; i++) 
        {
            for(int j = 0; j <= 3; j++) 
            {
                if(tabuleiro[i][j] == jogador && tabuleiro[i][j + 1] == jogador && tabuleiro[i][j + 2] == jogador && tabuleiro[i][j + 3] == jogador) 
                {
                    System.out.println("Você venceu!");
                    vencedor = true;
                    return vencedor;
                }
                if(tabuleiro[i][j] == computador && tabuleiro[i][j + 1] == computador && tabuleiro[i][j + 2] == computador && tabuleiro[i][j + 3] == computador) 
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
        for(int i = 0; i <= 6; i++) 
        {
            for(int j = 0; j <= 2; j++) 
            {
                if(tabuleiro[j][i] == jogador && tabuleiro[j + 1][i] == jogador && tabuleiro[j + 2][i] == jogador && tabuleiro[j + 3][i] == jogador) 
                {
                    System.out.println("Você venceu!");
                    vencedor = true;
                    return vencedor;
                }
                if(tabuleiro[j][i] == computador && tabuleiro[j + 1][i] == computador && tabuleiro[j + 2][i] == computador && tabuleiro[j + 3][i] == computador) 
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
        for(int i = 0; i < tabuleiro.length - 3; i++) 
        {
            for(int j = 0; j < tabuleiro[i].length - 3; j++) 
            {
                if(tabuleiro[i][j] == jogador && tabuleiro[i + 1][j + 1] == jogador && tabuleiro[i + 2][j + 2] == jogador && tabuleiro[i + 3][j + 3] == jogador) 
                {
                    System.out.println("Você venceu");
                    vencedor = true;
                    return vencedor;
                }
                if(tabuleiro[i][j] == computador && tabuleiro[i + 1][j + 1] == computador && tabuleiro[i + 2][j + 2] == computador && tabuleiro[i + 3][j + 3] == computador) 
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
        for(int i = 0; i < tabuleiro.length - 3; i++) 
        {
            for(int j = 3; j < tabuleiro[i].length - 3; j++) 
            {
                if(tabuleiro[i][j] == jogador && tabuleiro[i + 1][j - 1] == jogador && tabuleiro[i + 2][j - 2] == jogador && tabuleiro[i + 3][j - 3] == jogador) 
                {
                    System.out.println("Você venceu!");
                    vencedor = true;
                    return vencedor;
                }
                if(tabuleiro[i][j] == computador && tabuleiro[i + 1][j - 1] == computador && tabuleiro[i + 2][j - 2] == computador && tabuleiro[i + 3][j - 3] == computador) 
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
        for(int i = 0; i <= 5; i++) 
        {
            for(int j = 0; j <= 6; j++) 
            {    
                if(tabuleiro[i][j] != 'B') 
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
