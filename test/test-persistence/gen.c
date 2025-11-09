#include <stdio.h>
int main() {
  double x, y;
  x = 0;
  x = x;

  x = 10;
  printf("%g\n", (double)(10));
  y = x * 2;
  printf("%g\n", (double)(x * 2));
  x = y + 1;
  printf("%g\n", (double)(y + 1));
  return 0;
}
