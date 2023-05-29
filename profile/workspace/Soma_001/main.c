
#include <stdio.h>
#include <stdlib.h>

/* vetores e matrizes */

/*
24 - 1) Fazer um programa para ler 10 números e exibi-los na tela em seguida.
*/
void vintequartro_questao_um() {
    int tamanho = 10;
    int valores[tamanho];
    for (int i = 0; i < tamanho; i++) {
        printf("Digite um valor: \n");
        scanf("%d", &valores[i]);
    }
    printf("Os valores sao: ");
    for (int i = 0; i < tamanho; i++) {
        printf("%d ", valores[i]);
    }
}

/*
24 - 2) Fazer um programa para ler 20 números e, em seguida, informar o menor 
valor e a sua posição no vetor. 
*/
void vintequartro_questao_dois() {
    int tamanho = 10;
    int ultimo_menor_valor = INT_MAX;
    for (int i = 0; i < tamanho; i++) {
        int valor = 0;
        printf("Digite um valor: \n");
        scanf("%d", &valor);
        if (valor < ultimo_menor_valor) {
            ultimo_menor_valor = valor;
        }
    }
    printf("O menor valor e: %d", ultimo_menor_valor);
    
}

/*
24 - 5) Criar um programa para ler 20 números e informar os
números acima e abaixo da média.
*/
void vintequartro_questao_cinco() {
    int tamanho = 20;
    int media = 0;
    int notas[tamanho];
    
    printf("Informe a media: ");
    scanf("%d", &media);
    
    for (int i = 0; i < tamanho; i++) {
        printf("Informe uma nota:");
        scanf("%d", &notas[i]);
    }
    
    for (int i = 0; i < tamanho; i++) {
        if (notas[i] > media) {
            printf("%d esta acima da media.\n");
        } else if (notas[i] < media) {
            printf("%d esta abaixo da media.\n");
        } else {
            printf("%d esta na media.\n");
        }
    }
}

/*
25 - 8) Fazer um programa para ler dois vetores de 10 posições,
multiplicar o valor das posições correspondentes dos vetores
e colocar os resultados nas mesmas posições de um terceiro
vetor. Mostrar os valores dos três vetores.

*/
void vintecinco_questao_oito() {
    int tamanho = 3;
    
    int vet_0[tamanho];
    int vet_1[tamanho];
    int vet_3[tamanho];
    
    printf("Preencha os valores do vetor 1:\n");
    for (int i = 0; i < tamanho; i++) {
        printf("Informe um valor:");
        scanf("%d", &vet_0[i]);
    }
    
    printf("Preencha os valores do vetor 2:\n");
    for (int i = 0; i < tamanho; i++) {
        printf("Informe um valor:");
        scanf("%d", &vet_1[i]);
    }
    
    printf("Processando...\n");
    for (int i = 0; i < tamanho; i++) {
        vet_3[i] = vet_0[i] * vet_1[i];
    }
    
    printf("Valores finais do vetor 3\n");
    for (int i = 0; i < tamanho; i++) {
        printf("%d ", vet_3[i]);
    }
}

/*
38 - 10) Criar um programa para ler a quantidade vendida de até 100 produtos e
informar os produtos que possuem mais de 100 unidades vendidas. Cada
posição do vetor corresponde a um produto.
*/
void trintaeoito_questao_dez() {
    
    int tamanho_maximo = 100;
    int posicao = -1;
    int vendas[tamanho_maximo];
    
    do {
        
        int valor;
        printf("Quantidade vendida do produto %d.\n", (posicao + 2));
        scanf("%d", &valor);
        
        if (valor <= -1){
            break;
        }
        
        posicao++;
        vendas[posicao] = valor;
        
    } while (posicao < tamanho_maximo);
    
    for (int i = 0; i < posicao + 1; i++) {
        if (vendas[i] > 100){ 
            printf("O produto %d possui mais de 100 unidades vendidas [%d].\n", (i + 1), vendas[i]);
        }
    }   
}

/*
38 - 12) Criar um programa para ler o salário inicial e o salário final de até 50
funcionários e listar o percentual de aumento de cada um deles. Não
deixar que o segundo valor informado pelo usuário seja menor que o
salário anterior.
*/
void trintaeoito_questao_doze() {}

/*
39 - 15) Criar um programa para ler o salário inicial e o salário final de até 50
funcionários e listar o percentual de aumento de cada um deles. Não
deixar que o segundo valor informado pelo usuário seja menor que o
salário anterior.
*/
void trintaenove_questao_quinze() {}

/*
50 - 16) Criar um programa para ler 20 números e listá-los em ordem
decrescente.
*/
void cinquenta_questao_dezesseis() {}

/*
57 - 21) Preencha uma matriz 4 x 4 com valores informados pelo usuário. Em
seguida, o programa deve exibir o número da linha e da coluna onde um
número informado pelo usuário encontra-se. Se o valor não for achado,
mostrar uma mensagem informando o usuário.
*/
void cinquentaesete_questao_vinteum() {}


int main()
{
    //vintequartro_questao_um();
    //vintequartro_questao_dois();
    //vintequartro_questao_cinco();
    vintecinco_questao_oito();
    //trintaeoito_questao_dez();
    //trintaeoito_questao_doze();
    //trintaenove_questao_quinze();
    //cinquenta_questao_dezesseis();
    //cinquentaesete_questao_vinteum();
    
    return 0;
}

