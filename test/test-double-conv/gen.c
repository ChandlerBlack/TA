#include <stdio.h>
int main() {
  double x, y;
  x = 0;
  x = x;

  x = 1.5;
  printf("%g\n", (double)(1.5));
  y = x * 2.0;
  printf("%g\n", (double)(x * 2.0));
  return 0;
}
