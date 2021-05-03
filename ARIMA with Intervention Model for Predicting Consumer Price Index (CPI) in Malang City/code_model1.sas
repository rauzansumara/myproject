/*Import data*/
proc import out=work.Intervensi datafile='D:\Arena Statistics\Analisis Data\2017\Waiting List\Skripsi Mitha (Model Intervensi)\DATA IHK.xlsx' 
dbms=xlsx replace;
sheet='sheet1';
getnames=yes;
run;

/*tampilkan data*/
proc print data=Intervensi;
run;

/*plot data time series*/
proc gplot data=Intervensi;
symbol1 i=splines v=dot;
plot IHK*Date;
run;

/*manipulasi data*/
data Intervensi;
set Intervensi;
z=0;
*t_data1=log(data1);
run;

/*Stasioneritas ragam*/
proc transreg maxiter=0 nozeroconstant;
      model BoxCox(data1) = identity(z);
      output;
run;

/*Stasioneritas rata-rata*/
proc arima data=Intervensi;
identify var=data1(1) nlag=40 stationarity=(adf);
run;

/*Mendapatkan model ARIMA + peramalan*/
proc arima data=Intervensi;
identify var=data1(1) nlags=25;
run;
estimate p=1 method=ml;
run;
forecast out=ramalan lead=67 printall;
run;

/*uji normalitas*/
proc univariate normaltest;
var residual;
run;

/*tampilkan ramalan*/
proc print data=ramalan;
run;
