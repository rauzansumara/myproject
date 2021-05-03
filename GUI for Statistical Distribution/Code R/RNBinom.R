RNBinom <- function(h,...){
  kua <- gwindow("Bangkitan Binomial Negatif")
  Big <- ggroup(container = kua,horizontal = F)
  g1 <- gframe("Jumlah:",container = Big,expand=F)
  g2 <- gframe("Banyaknya Percobaan:",container = Big,expand=F)
  g3 <- gframe("Peluang Sukses:",container = Big,expand=F)
  nilai <- gedit(container = g1,coerce.with = as.numeric)
  coba <- gedit(container = g2,coerce.with = as.numeric)
  sukses <- gedit(container = g3,coerce.with = as.numeric)
  Big1 <- ggroup(container = Big)
  canc=gbutton("Cancel",cont=Big1,expand=T)
  addHandlerChanged(canc, handler= function(h,...){
    visible(kua)= FALSE
    focus (main)
  })
  ok=gbutton("OK",cont=Big1,expand=T)
  addHandlerChanged(ok,handler=function(h,...){
    pelu <- rnbinom(svalue(nilai),svalue(coba),svalue(sukses))
    insert(Output,"")
    insert(Output,capture.output(cbind(X=pelu)))
    
	win.graph()
	plotDistr(pelu, dnbinom(pelu,svalue(coba),svalue(sukses)),
	xlab="Banyaknya Gagal Hingga Mencapai Target Sukses",ylab="Peluang",main="Distribusi Binomial Negatif",discrete = T)
	
	legend('topright',legend =c(paste('Sampel =',svalue(nilai)),
								paste('Banyak Percobaan =',svalue(coba)),
								paste('Peluang Sukses =',svalue(sukses))),bty = 'n')
	
    visible(kua)= FALSE
    focus(main)
  })
}

