input = int(input())

cnt = 0
n = input
while 1:
      n1 = int(n / 10)
      n2 = n % 10
      
      sum = n1 + n2

      n = (n2 * 10) + (sum % 10)
      
      cnt += 1

      if input == n:
            break

print(cnt)