PGeo <- function(h,...){
  pel <- gwindow("Peluang Geometrik")
  Big <- ggroup(container = pel,horizontal = F)
  g1 <- gframe("Nilai:",container = Big,expand=F)
  g2 <- gframe("Peluang Sukses:",container = Big,expand=F)
  nilai <- gedit(container = g1)
  lam <- gedit(container = g2,coerce.with = as.numeric)
  avail <- c(Poin=1,LowerTail=2,UpperTail=3)
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
    if(avail[svalue(poin)]==1) { pelu <- dgeom(svalue(x),svalue(lam))}
    if(avail[svalue(poin)]==2) { pelu <- pgeom(svalue(x),svalue(lam))}
    if(avail[svalue(poin)]==3) { pelu <- pgeom(svalue(x),svalue(lam),lower.tail = F)}
    insert(Output,"")
    insert(Output,capture.output(cbind(X=x,Peluang=pelu)))
    
    visible(pel)= FALSE
    focus(main)
  })
}

