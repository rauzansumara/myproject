RPoi <- function(h,...){
  kua <- gwindow("Bangkitan Poisson")
  Big <- ggroup(container = kua,horizontal = F)
  g1 <- gframe("Jumlah:",container = Big,expand=F)
  g2 <- gframe("Lambda:",container = Big,expand=F)
  nilai <- gedit(container = g1,coerce.with = as.numeric)
  lam <- gedit(container = g2,coerce.with = as.numeric)
  Big1 <- ggroup(container = Big)
  canc=gbutton("Cancel",cont=Big1,expand=T)
  addHandlerChanged(canc, handler= function(h,...){
    visible(kua)= FALSE
    focus (main)
  })
  ok=gbutton("OK",cont=Big1,expand=T)
  addHandlerChanged(ok,handler=function(h,...){
    pelu <- rpois(svalue(nilai),svalue(lam))
    insert(Output,"")
    insert(Output,capture.output(cbind(X=pelu)))
    
	win.graph()
	plotDistr(pelu, dpois(pelu,svalue(lam)),
	xlab="X",ylab="Peluang",main="Distribusi Poisson",discrete = T)
	
	legend('topright',legend =c(paste('Sampel =',svalue(nilai)),
								paste('Lambda =',svalue(lam))),bty = 'n')
	
    visible(kua)= FALSE
    focus(main)
  })
}

