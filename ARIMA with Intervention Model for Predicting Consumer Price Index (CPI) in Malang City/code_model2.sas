/*Import data*/
proc import out=work.Intervensi datafile='D:\Arena Statistics\Analisis Data\2017\Waiting List\Skripsi Mitha (Model Intervensi)\DATA IHK.xlsx' 
dbms=xlsx replace;
sheet='sheet1';
getnames=yes;
run;

/*tampilkan data*/
proc print data=Intervensi;
run;

/*Pembentukan model ARIMA + Intervensi*/
proc arima data=Intervensi;
identify var=data2(1) crosscorr=(inter_1);
estimate p=1 input=(0 $ (1) / (0) inter_1) method=ml;
forecast out=ramalan2 lead=42;
run;

/*uji normalitas*/
proc univariate normaltest;
var residual;
run;

/*Print data*/
proc print data=ramalan2;
run;
