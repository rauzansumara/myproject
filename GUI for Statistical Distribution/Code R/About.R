About <- function(h,...) {
  
  win=gwindow('About',visible = TRUE,expand=T)
  g1= ggroup(cont= win,horizontal = F)
  
  glabel('Creator By:',container = g1)
  glabel('Rauzan Sumara, Student of Statistics, Brawijaya University',container = g1)
  glabel('',container = g1)
  glabel('',container = g1)
  glabel('Email :',container = g1)
  glabel('rauzan.sumara@yahoo.com',container = g1)
  glabel('Phone :',container = g1)
  glabel('+62 819 0911 1417',container = g1)
  
}