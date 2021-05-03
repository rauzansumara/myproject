#tsDyn packages

#input data
granger <- read.table("clipboard")
granger <- ts(granger,frequency = 4,start = c(1958,1))

#plot data
plot(granger,main="Granger",ylab="r short")

#Uji ADF
adf.test(granger)

#differensi pertama
dgranger <- diff(granger)
plot(dgranger,main="Granger",ylab="r short")
adf.test(dgranger)

#Model ARI(2,1)
model.ar2 <- arima(granger, order = c(2,1,0))

#Mencari delay
delay1 <- arima(granger, order = c(2,1,0),fixed = c(NA,0))
delay2 <- arima(granger, order = c(2,1,0),fixed = c(0,NA))

#membentuk model TAR, trashold = 0
model.tar <- setar(dgranger,m=2,thDelay = 1,th=0,include = "none",model = "TAR")
model.tar <- setar(dgranger,m=2,mL=2,mH=2,thDelay = 1,th=0,include = "none",model = "TAR")

#Plot aktual vs. fitted
plot(dgranger,main="dGranger vs. fitted",ylab="r short")
lines(fitted.values(model.tar),col=2)


y <- dgranger
model = ts.intersect(y, lag1y=lag(,-1), lag2y=lag(y, -2), lag3y=lag(y,-3), lag4y=lag(y, -4))
x = model[,1]
P = model[,2:5]
c = .0 #Threshold value