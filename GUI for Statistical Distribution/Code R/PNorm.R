PNorm <- function(h,...){
  pel <- gwindow("Peluang Normal")
  Big <- ggroup(container = pel,horizontal = F)
  g1 <- gframe("Nilai:",container = Big,expand=F)
  g2 <- gframe("Rata-rata:",container = Big,expand=F)
  g3 <- gframe("Standar Deviasi:",container = Big,expand=F)
  nilai <- gedit(container = g1)
  rata <- gedit(container = g2,coerce.with = as.numeric)
  SD <- gedit(container = g3,coerce.with = as.numeric)
  avail <- c(LowerTail=1,UpperTail=2)
  poin <- gradio(names(avail),horizontal = F,container = Big)
  Big1 <- ggroup(container = Big)
  canc=gbutton("Cancel",cont=Big1,expand=T)
  addHandlerChanged(canc, handler= function(h,...){
    visible(pel)= FALSE
    focus (main)
  })
  ok=gbutton("OK",cont=Big1,expand=T)
  addHandlerChanged(ok,handler=function(h,...){
    x <- get(svalue(nilai), mode = "numeric")
    if(avail[svalue(poin)]==1) { pelu <- pnorm(svalue(x),svalue(rata),svalue(SD))}
    if(avail[svalue(poin)]==2) { pelu <- pnorm(svalue(x),svalue(rata),svalue(SD),lower.tail = F)}
    insert(Output,"")
    insert(Output,capture.output(cbind(X=x,Peluang=pelu)))
    
    visible(pel)= FALSE
    focus(main)
  })
}

