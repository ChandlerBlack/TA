#include <stdio.h>
int main() {
  double x, y;
  x = 0;
  x = x;

  x = 1.5;
  printf("%g\n", (double)(x));

  y = x * 2.0;
  printf("%g\n", (double)(y));

  return 0;
}
