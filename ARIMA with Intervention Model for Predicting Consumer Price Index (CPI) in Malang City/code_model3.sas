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
proc arima data=Intervensi plots = all;
identify var=data3(1) crosscorr=(inter_1 inter_2);
estimate p=1 input=(0 $ (1) / (0) inter_1 0 $ (1) / (0) inter_2) method=ml;
forecast out=ramalan3 lead=12;
run;

/*uji normalitas*/
proc univariate normaltest;
var residual;
run;

/*Print data*/
proc print data=ramalan3;
run;
