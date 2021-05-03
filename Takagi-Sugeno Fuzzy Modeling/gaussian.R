-----------------------------------------------------------------------
#Fungsi Mencari Parameter Gaussian
#Keterangan :
#Data -> Data time series berupa suatu vektor
#k -> Banyaknya himpunan fuzzy berupa suatu skalar
-----------------------------------------------------------------------
Par.gauss <- function(Data,k){ 
Maxx <- ceiling(max(Data))
Minn <- ceiling(min(Data))
r <- (Maxx-Minn)
s <- r/(k-1)
mu <- matrix(0,k,2)
zikma <- (s/2)/1.1774
for (i in 1:k){
    	mu[i,1] <- zikma
	mu[i,2] <- Minn + (i-1)*s
  }
Par.gauss <- mu
}


-----------------------------------------------------------------------
#Fungsi Mencari Derajat Keanggotaan
#Keterangan :
#Ser -> Data time series berupa suatu vektor
#Par -> Parameter dari fungsi keanggotaan gaussian sigma, mean and tinggi berupa suatu matriks
-----------------------------------------------------------------------
DKeanggotaan <- function(Ser,Par){
m <- nrow(Par)
myMF <- NULL
for(i in 1:m) {
	myDM <- gaussMF("myDM",Ser,c(Par[i,],1))$mfVals
	myMF <- cbind(myMF,myDM)
	}
colnames(myMF) <- NULL
DKeanggotaan <- myMF
}

----------------------------------------------------------------------
#Mencari Letak Himpunan Fuzzy Untuk Setiap Data
#Keterangan :
#DK -> Derajat keanggotaan data yang berupa suatu matriks
----------------------------------------------------------------------
Pilih.FK <- function(DK) {
n <- nrow(DK)
c <- NULL
s <- NULL
for(i in 1:n) {
	c[i] <- max(DK[i,])
	s[i] <- which.max(DK[i,])
	}
Pilih.FK <- cbind(fuzzy.set=s,Degree=c)
}

------------------------------------------------------------------------
#Mencari Rule dari Pasangan Input Output 
#Keterangan :
#FK -> Himpunan fuzzy yang mewakili suatu data yang berupa suatu vektor
#DK -> Derajat keanggotaan dari data yang berupa suatu vektor
#m -> Banyaknya input yang berpengaruh yang berupa suatu sekalar
------------------------------------------------------------------------
Pas.IO <- function(FK,DK,m) {
n <- length(FK)
Pasangan <- matrix(0,(n-m),(m+1))
Degree <- NULL
for(i in 1:(n-m)) {
    	s <- i
	for(j in 1:(m+1)) {
		Pasangan[i,j] = FK[s]
		s <- s+1
		}
    	Degree[i] <- prod(DK[i:(m+i)]) 
   }
Pas.IO <- cbind(Pasangan,Degree)
}

-----------------------------------------------------------------------------
#Mencari Rule Base
#Keterangan :
#Pasangan -> Pasangan input ouput dengan tambahan derajat keanggotaan yang berupa suatu matriks
----------------------------------------------------------------------------
Rule.Base <- function(Pasangan) {
n <- nrow(Pasangan)
m <- ncol(Pasangan)
KR <- Pasangan[order(Pasangan[,m],decreasing=T),]
KRt <- KR[,1:(m-2)]
for(i in 1:(n-1)) {
	for(j in (i+1):n) {
		in.cond <- all(KRt[i,]==KRt[j,])
		if(in.cond==TRUE) {
			KR[j,] <- 0
		}
         }
}
rule <- KR[which(KR[,1]!=0),]
Rule.Base=rule
}	

---------------------------------------------------------------------
#memasukan data pada rule base
#keterangan :
#Data -> Data input yang berupa suatu matriks
#Par -> Parameter fungsi keanggotaan yang berupa suatu matriks
#Rule -> Rule base atau basis kaidah yang berupa suatu matriks
--------------------------------------------------------------------
hasil <- function(Data,Par,Rule) {
k <- ncol(Data)
l <- nrow(Rule)
n <- nrow(Par)
envi <- list()
for(i in 1:l) {
	myMF <- NULL
	for(j in 1:k) {
		s <- Rule[i,j]
		myDM <- gaussMF("",Data[,j],c(Par[s,],1))$mfVals
		myMF <- cbind(myMF,myDM)
		}
colnames(myMF) <- NULL
envi[[i]] <- myMF
	}
hasil <- envi
}

----------------------------------------------------------------------
#mencari alfa cut atau fire strength
#Keterangan :
#ipt -> nilai derajat keanggotaan setiap rule base dari data input yang berupa suatu list
----------------------------------------------------------------------
alfa <- function(ipt) {
n <- nrow(ipt[[1]])
m <- length(ipt)
out <- matrix(0,n,m)
for(i in 1:m) {
	for(j in 1:n) {
		out[j,i] <- prod(ipt[[i]][j,])
		}
	}
alfa <- out
}

------------------------------------------------------------------------
#Normalisasi fire strength
#Keterangan :
#alfa -> nilai alfa cut atau fire strength yang berupa suatu matriks
------------------------------------------------------------------------
normfire <- function(alfa) {
n <- nrow(alfa)
m <- ncol(alfa)
out <- matrix(0,n,m)
for(i in 1:n) {
	jum <- sum(alfa[i,])
	for(j in 1:m) {		
		out[i,j] <- alfa[i,j]/jum
		}
	}
normfire <- out
}

--------------------------------------------------------------------------
#Mencari Teta
#Keterangan :
#normalfa -> nilai fire strength yang telah dinormalisasi yang berupa suatu matriks
#Data -> data output yang berupa suatu matriks
--------------------------------------------------------------------------
Teta <- function(normalfa,Data) {
n <- length(Data)
X <- normalfa
Teta <- solve(crossprod(X,X))%*%t(X)%*%Data
}

ts.plot(Output,main = 'Data Vs. Fitted',ylab ='')
lines(Fitted,col=2)
legend('topright',legend = c('Data','Fitted'),col=c(1,2), lty = 1,bty = 'n')
