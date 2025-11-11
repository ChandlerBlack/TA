#include <stdio.h>
int main() {
  double x;
  x = 0;
  x = x;

  x = 1 + 2;
  printf("%g\n", (double)(x));

  x = x + 3;
  printf("%g\n", (double)(x));

  return 0;
}
