#include <stdio.h>
int main() {
  double x;
  x = 0;
  x = x;

  x = 1;
  while (x <= 5) {
    printf("%g\n", (double)(x));
    x = x + 1;
  }

  return 0;
}
