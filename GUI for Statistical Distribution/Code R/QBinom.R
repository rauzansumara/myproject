QBinom <- function(h,...){
  kua <- gwindow("Quantile Binomial")
  Big <- ggroup(container = kua,horizontal = F)
  g1 <- gframe("Nilai Peluang:",container = Big,expand=F)
  g2 <- gframe("Banyaknya Percobaan:",container = Big,expand=F)
  g3 <- gframe("Peluang Sukses:",container = Big,expand=F)
  nilai <- gedit(container = g1)
  coba <- gedit(container = g2,coerce.with = as.numeric)
  sukses <- gedit(container = g3,coerce.with = as.numeric)
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
    if(avail[svalue(poin)]==1) { pelu <- qbinom(svalue(x),svalue(coba),svalue(sukses))}
    if(avail[svalue(poin)]==2) {  pelu <- qbinom(svalue(x),svalue(coba),svalue(sukses),lower.tail = F)}
    insert(Output,"")
    insert(Output,capture.output(cbind(Peluang=x,Quantile=pelu)))
    
    visible(kua)= FALSE
    focus(main)
  })
}

