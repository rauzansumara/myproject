#Eksport Data
SaveFile <- function(h,...) {
  windopen=gwindow("Save File",visible = TRUE,expand=T)
  focus(windopen)
  g1= ggroup(cont= windopen,horizontal = F)
  glabel("Search In :", cont= g1)
  #browse button
  start_dir <- gfilebrowse(text = "Select a directory ..." ,
                               quote = FALSE , type = "selectdir" , cont = g1)
  
  #ok & cancel
  g2= ggroup(cont= g1)
  canc = gbutton("Cancel", cont = g2,expand=T)
  addHandlerChanged(canc, handler= function(h,...){
    visible(windopen)= FALSE
    focus (main)
  })
  ok = gbutton("OK",cont=g2,expand=T)
  addHandlerChanged(ok,handler=function(h,...){
    write.table(svalue(Output),svalue(start_dir))
  
    visible(windopen)= FALSE
    focus(main)
  })
  
}