#include <stdio.h>
int main() {
  double x;
  x = 0;
  x = x;

  scanf("%lf", &x);
  if (x > 0) {
    printf("%g\n", (double)(x));
  } else {
    printf("%g\n", (double)(-(x)));
  }

  return 0;
}
