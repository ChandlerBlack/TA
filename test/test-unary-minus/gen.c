#include <stdio.h>
int main() {
  double x, y, z;
  x = 0;
  x = x;

  x = -((5));
  printf("%g\n", (double)(x));

  y = -(x) + 1;
  printf("%g\n", (double)(y));

  z = -((1 + 2)) * (-(3));
  printf("%g\n", (double)(z));

  return 0;
}
