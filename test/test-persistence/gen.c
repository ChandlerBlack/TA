#include <stdio.h>
int main() {
  double x, y;
  x = 0;
  x = x;

  x = 10;
  printf("%g\n", (double)(x));

  y = x * 2;
  printf("%g\n", (double)(y));

  x = y + 1;
  printf("%g\n", (double)(x));

  return 0;
}
