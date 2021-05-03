QPoi <- function(h,...){
  kua <- gwindow("Quantile Poisson")
  Big <- ggroup(container = kua,horizontal = F)
  g1 <- gframe("Nilai Peluang:",container = Big,expand=F)
  g2 <- gframe("Lambda:",container = Big,expand=F)
  nilai <- gedit(container = g1)
  lam <- gedit(container = g2,coerce.with = as.numeric)
  avail <- c(LowerTail=1,UpperTail=2)
  poin <- gradio(names(avail),horizontal = F,container = Big)
  Big1 <- ggroup(container = Big)
  canc=gbutton("Cancel",cont=Big1,expand=T)
  addHandlerChanged(canc, handler= function(h,...){
    visible(kua)= FALSE
    focus (main)
  })
  ok=gbutton("OK",cont=Big1,expand=T)
  addHandlerChanged(ok,handler=function(h,...){
    x <- get(svalue(nilai), mode = "numeric")
    if(avail[svalue(poin)]==1) { pelu <- qpois(svalue(x),svalue(lam))}
    if(avail[svalue(poin)]==2) {  pelu <- qpois(svalue(x),svalue(lam),lower.tail = F)}
    insert(Output,"")
    insert(Output,capture.output(cbind(Peluang=x,Quantile=pelu)))
    
    visible(kua)= FALSE
    focus(main)
  })
}

