import sys

n = int(sys.stdin.readline())

room = []

for i in range(n):
  x, y = map(int, sys.stdin.readline().split())
  room.append([x,y])

room.sort(key=lambda x:x[0])
room.sort(key=lambda x:x[1])

pre = 0
count = 0

for i in room:
  if(pre <= i[0]):
    count = count + 1
    pre = i[1]
    
print(count)