# Trabalho Final Java

## Jogo Liga 4

- o único import que deve ser usado é *java.util.Scanner*;  
- o construtor declara a única ocorrência do objeto *teclado* da classe *Scanner* para permitir ler dados do console usando o teclado;
- o método main só instância o construtor desta classe;  
- o construtor declara a matriz do tipo char que representa o *tabuleiro  
- a matriz *tabuleiro* tem o tamanho 6 por 7;
- o jogo será implementado para ser jogado entre um jogador e o computador;
- devem ser implementados métodos que por sua vez serão chamados no construtor. Esses métodos serão a sua escolha, bem como os tipos de retorno ou parâmetros;
- não poderá ser declarados atributos de classe.

**OBS:** caso um dos itens acima não seja atendido, será descontado 0,5 pontos por item não atendido. <br />

## Regras do Jogo Liga-4
O Liga-4 é um jogo de estratégia em que dois jogadores alternam em colocar discos em uma grade vertical de 6 linhas por 7 colunas. <br />
O objetivo do jogo é ser o primeiro a formar uma linha contínua de quatro discos da mesma cor, seja horizontal, vertical ou diagonalmente.  <br />
O jogo é bastante simples, mas requer planejamento e antecipação para bloquear os movimentos do oponente enquanto tenta formar sua própria linha de quatro discos.  <br />
O jogador precisa estar atento às possibilidades de seu oponente e tomar decisões estratégicas para garantir a vitória.  <br />

Implemente o jogo, seguindo os seguintes Requisitos Funcionais (RF):  
RF01 - o tabuleiro deve começar com todas as casas em branco representando pelo valor "B" **(0,5)** <br />
RF02 - o jogador deve escolher a sua cor entre V (vermelho) ou A (azul)  **(0,5)** <br />
RF03 - na sua vez, o jogador deve informar uma posição de coluna para jogar. A peça ficará posicionada na primeira linha disponível daquela coluna (considerar a linha mais abaixo possível) **(1,0)** <br />
RF04 - após o jogador executar sua jogada, o computador deve executar sua jogada de forma aleatória (utilizar o método Math.random para sortear a coluna) **(1,0)** <br />
RF05 - deve ser possível imprimir o tabuleiro a qualquer momento **(1,0)** <br />
RF06 - quando o jogador ou o computador ganhar, deve-se imprimir quem foi o vencedor (considerar vitória para 4 cores posicionadas linearmente na horizontal, vertical ou diagonal). **(4,0 -- 1,0 para cada posição - horizontal, vertical, diagonal para direita, diagonal para esquerda)** -  <br /> 
RF07 - se o jogador ou o computador informar uma coluna que está com todas as linhas preenchidas, deve-se solicitar novamente que faça sua jogada. **(0,5)** <br />
RF08 - se o tabuleiro estiver completo e não houver vitória deve-se imprimir EMPATE **(0,5)** <br />
RF09 - quando um jogador vence ou há empate, deve-se perguntar se deseja jogar novamente e, caso afirmativo, deve-se reiniciar a partida. **(1,0)** <br />