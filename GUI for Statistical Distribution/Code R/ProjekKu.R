options(guiToolkit="RGtk2")
require(gWidgets)
require(splines)
require(car)
require(RcmdrMisc)
require(RGtk2)

#mambuat bar menu
Menu <- list()

Binomial <- list()
Poisson <- list()
Geometrik <- list()
"Binomial Negatif" <- list()
Normal <- list()
"t-students" <- list()
"Chi-Square" <- list()
Fisher <- list()

#Panggil Fungsi Sebaran Diskrit
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/PBinom.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/QBinom.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/RBinom.R')

source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/PPoi.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/QPoi.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/RPoi.R')

source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/PGeo.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/QGeo.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/RGeo.R')

source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/PNBinom.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/QNBinom.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/RNBinom.R')

#Panggil Fungsi Sebaran Kontinu
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/PNorm.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/RNorm.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/QNorm.R')

source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/Pt.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/Qt.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/Rt.R')

source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/PChi.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/QChi.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/RChi.R')

source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/Pf.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/Qf.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/Rf.R')

#Fungsi Import/Export Data
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/ReadFile.R')
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/SaveFile.R')

#Fungsi Tampilkan Data
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/Tamp.R')

#Fungsi Hapus Output
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/Hapus.R')

#Fungsi About
source('D:/Materi Kuliah/Semester 6/Komputasi Statistika Lanjt/Tugas Akhir/About.R')

#Sub menu pada "File"
Menu$File$Import$handler <- ReadFile
Menu$File$Eksport$handler <- SaveFile
Menu$File$Keluar$handler <- function(h,...){dispose(main)}

#Sub menu pada "Sebaran Diskrit"
Menu$"Sebaran Diskrit"$Binomial$Peluang$handler <- PBinom 
Menu$"Sebaran Diskrit"$Binomial$Quantile$handler <- QBinom
Menu$"Sebaran Diskrit"$Binomial$"Bangkitkan Data"$handler <- RBinom

Menu$"Sebaran Diskrit"$Poisson$Peluang$handler <- PPoi
Menu$"Sebaran Diskrit"$Poisson$Quantile$handler <- QPoi
Menu$"Sebaran Diskrit"$Poisson$"Bangkitkan Data"$handler <- RPoi

Menu$"Sebaran Diskrit"$Geometrik$Peluang$handler <- PGeo
Menu$"Sebaran Diskrit"$Geometrik$Quantile$handler <- QGeo
Menu$"Sebaran Diskrit"$Geometrik$"Bangkitkan Data"$handler <- RGeo

Menu$"Sebaran Diskrit"$"Binomial Negatif"$Peluang$handler <- PNBinom
Menu$"Sebaran Diskrit"$"Binomial Negatif"$Quantile$handler <- QNBinom
Menu$"Sebaran Diskrit"$"Binomial Negatif"$"Bangkitkan Data"$handler <- RNBinom

#Sub menu pada "Sebaran Kontinu"
Menu$"Sebaran Kontinu"$Normal$Peluang$handler <- PNorm
Menu$"Sebaran Kontinu"$Normal$Quantile$handler <- QNorm
Menu$"Sebaran Kontinu"$Normal$"Bangkitkan Data"$handler <- RNorm

Menu$"Sebaran Kontinu"$"t-students"$Peluang$handler <- Pt
Menu$"Sebaran Kontinu"$"t-students"$Quantile$handler <- Qt
Menu$"Sebaran Kontinu"$"t-students"$"Bangkitkan Data"$handler <- Rt

Menu$"Sebaran Kontinu"$"Chi-Square"$Peluang$handler <- PChi
Menu$"Sebaran Kontinu"$"Chi-Square"$Quantile$handler <- QChi
Menu$"Sebaran Kontinu"$"Chi-Square"$"Bangkitkan Data"$handler <- RChi

Menu$"Sebaran Kontinu"$Fisher$Peluang$handler <- Pf
Menu$"Sebaran Kontinu"$Fisher$Quantile$handler <- Qf
Menu$"Sebaran Kontinu"$Fisher$"Bangkitkan Data"$handler <- Rf

#Sub menu pada "Bantuan"
Menu$Help$About$handler <- About

#Membuat Window
main <- gwindow("Sebaran Statistik")
size(main)<- c(600,500)

#membuat isi dalam window
Group <- ggroup(cont = main,horizontal = F)

mb <- gmenu(Menu,container = main)

Group1 <- ggroup(cont = Group)
glabel("Data Set: ",cont=Group1)

Data <- gedit(initial.msg="Nama Data",container = Group1)

Tampilkan.Data <- gbutton("Tampilkan Data",cont=Group1,handler = Tamp)

Group2 <- ggroup(container = Group,horizontal = F,expand=T)
Frame <- gframe("Output",container = Group2,expand=T,horizontal = F)
Output <- gtext(container = Frame,expand=T)

Hapus.Ouput <- gbutton("Hapus Output",cont=Group2,handler = Hapus)