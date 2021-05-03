#Import Data
ReadFile <- function(h,...) {
  windopen=gwindow("File Open",visible = TRUE, expand = TRUE)
  focus(windopen)
  g1= ggroup(cont= windopen,horizontal = F)
  g2= ggroup(cont = g1)
  glabel("Search In :", cont= g2)
  pil = gedit(intial.msg = "select a directory", cont= g2,expand=T)
  
  #browse button
  brow= gbutton("Browse",cont= g2,expand=T)
  addHandlerChanged(brow,handler= function(h,...){
    Y= gfile("Select File",type = "open",
             filter=list("All files" = list (patterns = c("*"))))
    svalue(pil)= Y})
  
  g3= ggroup(cont= g1)
  glabel("Tanda Hubung :",cont=g3)
  
  #pilihan separator
  g4=ggroup(cont=g3)
  se=gedit(initial.msg= "tipe pemisah", width= 1,cont= g4)
  
  #header
  headr= gcheckbox("header",cont = g4)
  
  #ok &cancel
  g5= ggroup(cont= g1)
  canc = gbutton("Cancel", cont = g5,expand=T)
  addHandlerChanged(canc, handler= function(h,...){
    visible(windopen)= FALSE
    focus (main)
  })
  ok = gbutton("OK",cont=g5,expand=T)
  addHandlerChanged(ok,handler=function(h,...){
    the.name = svalue(pil)
    the.sep = svalue(se)
    the.header = svalue(headr)
    x <- read.table(the.name,sep=the.sep,header=the.header)
    assign(svalue(Data),x[,1],inherits = T)

    visible(windopen)= FALSE
    focus(main)
  })

}