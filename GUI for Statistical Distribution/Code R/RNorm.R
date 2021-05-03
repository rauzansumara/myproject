RNorm <- function(h,...){
  kua <- gwindow("Bangkitan Normal")
  Big <- ggroup(container = kua,horizontal = F)
  Big1 <- ggroup(container = kua,horizontal = F)
  
  g1 <- gframe("Jumlah:",container = Big,expand=F)
  g2 <- gframe("Rata-rata:",container = Big,expand=F)
  g3 <- gframe("Standar Deviasi:",container = Big,expand=F)
  
  g4 <- gframe("Nilai X:",container=Big1,expand=F)
  
  avail <- c(LowerTail=1,UpperTail=2)
  poin <- gradio(names(avail),horizontal = F,container = Big1)
  
  nilai <- gedit(container = g1,coerce.with = as.numeric)
  rata <- gedit(container = g2,coerce.with = as.numeric)
  SD <- gedit(container = g3,coerce.with = as.numeric)
  input <- gedit(container = g4,coerce.with = as.numeric)
  
  canc=gbutton("Cancel",cont=Big,expand=T)
  addHandlerChanged(canc, handler= function(h,...){
    visible(kua)= FALSE
    focus (main)
  })
  ok=gbutton("OK",cont=Big,expand=T)
  addHandlerChanged(ok,handler=function(h,...){
    pelu <- rnorm(svalue(nilai),svalue(rata),svalue(SD))
    insert(Output,"")
    insert(Output,capture.output(cbind(X=pelu)))
  
    xv <- sort(pelu)
    x <- svalue(input)
    yv <- dnorm(xv,svalue(rata),svalue(SD))
    
    win.graph()
    plotDistr(xv,yv,cdf = F,xlab="X",ylab="Densitas",
         main=paste('Distribusi Z : n =',svalue(nilai),'~N(',svalue(rata),',',svalue(SD),')'))

    maxx <- max(xv)
    minn <- min(xv) 
    
    if(avail[svalue(poin)]==1) { 
      
      p <- round(pnorm(x,svalue(rata),svalue(SD)),3)
      xx <- seq(minn,x,0.01)
      yy <- dnorm(xx,svalue(rata),svalue(SD))
      cord.x <- c(minn,xx,x) 
      cord.y <- c(0,yy,0) 
      polygon(cord.x,cord.y,col='skyblue') 
      legend('topright',legend =c(paste('Nilai X =',x),paste('p-value =',p)),bty = 'n')      
    }
    if(avail[svalue(poin)]==2) { 
      
      p <- round(pnorm(x,svalue(rata),svalue(SD),lower.tail = F),3)
      xx <- seq(x,maxx,0.01)
      yy <- dnorm(xx,svalue(rata),svalue(SD))
      cord.x <- c(x,xx,maxx) 
      cord.y <- c(0,yy,0) 
      polygon(cord.x,cord.y,col='skyblue') 
      legend('topright',legend =c(paste('Nilai X =',x),paste('p-value =',p)),bty = 'n')
    }
    
    visible(kua)= FALSE
    focus(main)
  })
}