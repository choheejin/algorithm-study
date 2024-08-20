import sys

N = int(sys.stdin.readline())

five = 0
three = 0

if N % 5 == 0:
  print(N//5)

else:
  while N%5 != 0 and N >=0:
    N = N -3
    three = three + 1
  if(N<0):
    print("-1")
  else:
    print(N//5 + three)