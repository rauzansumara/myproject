#Plot Data
plot(Profits,ylim=c(0,250),lwd=3,col=4,ylab='',main="Profits & Dividends")
lines(Dividends,col=2,lwd=3)
legend('topleft',legend = c('Profits','Dividends'),col=c(4,2), lty = 1,bty = 'n',lwd = 3)

#Box Cox
boxcox(Profits~1)
powerTransform(Profits)
boxcox(Dividends~1)
powerTransform(Dividends)

#Transformasi Log
LDividends <- log(Dividends)
LProfits <- log(Profits)

#Plot Data yang telah di transformasi log
plot(LProfits,ylim=c(3,6),lwd=3,col=4,ylab='',main="log(Profits) & log(Dividends)")
lines(LDividends,col=2,lwd=3)
legend('topleft',legend = c('log(Profits)','log(Dividends)'),col=c(4,2), lty = 1,bty = 'n',lwd = 3)

#Model Y vs. X
model1 <- lm(LDividends~LProfits)
summary(model1)

#Proses differensi
dLDividends <- diff(LDividends)
dLProfits <- diff(LProfits)

#Plot data hasil differensi
plot(dLProfits,lwd=3,col=4,ylab='',main="diff(log(Profits)) & diff(log(Dividends))")
lines(dLDividends,col=2,lwd=3)
legend('bottomleft',legend = c('diff(LProfits)','diff(LDividends)'),col=c(4,2), lty = 1,bty = 'n',lwd = 3)

#model dY vs. dX
model2 <- lm(dLDividends~dLProfits)
summary(model2)

#Uji ADF sisaan
adf.test(model1$residuals)

#Plot Sisaan
ts.plot(model1$residuals,main="Sisaan",xlab="Time")

#Uji Angel Granger
egcm(LProfits,LDividends,i1test = 'adf',urtest = 'adf')

#Model Dinamis
model3 <- dynlm(LDividends ~ L(LDividends,1) + LProfits + L(LProfits,1))
summary(model3)