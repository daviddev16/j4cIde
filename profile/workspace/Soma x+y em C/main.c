
#include <stdio.h>

int main() {

while(1) {
	int x;
	int y;
	
	printf("Digite o valor de X:");
	scanf("%d", &x);
	printf("Digite o valor de Y:");
	scanf("%d", &y);
	
	if (x == -1) {
		break;
	}
	
	int sum = x + y;
	printf("Sum: %d.\n", sum);
}
