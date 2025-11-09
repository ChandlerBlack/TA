#include <stdio.h>
int main() {
  double x, y, z;
  x = 0;
  x = x;

  x = -((5));
  printf("%g\n", (double)(-((5))));
  y = -(x) + 1;
  printf("%g\n", (double)(-(x) + 1));
  z = -((1 + 2)) * (-(3));
  printf("%g\n", (double)(-((1 + 2)) * (-(3))));
  return 0;
}
