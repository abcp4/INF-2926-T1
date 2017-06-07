require("ggplot2")

polynomial<-read.csv("PolynomialMultiplication.csv")

ggplot() + geom_line(aes(x=polynomial$N, y=polynomial$CPU_NSquare, color="O(n^2")) +
  
  geom_line(aes(x=polynomial$N, y=polynomial$CPU_NLog3, color="O(n^log3)")) + 
  
  geom_line(aes(x=polynomial$N, y=polynomial$CPU_NLogN, color="O(nlogn)")) +
  
  ggtitle("\t\t\tComparação em tempo de CPU\t\t\t") +
  
  labs(x="N",y="Log(CPU)")