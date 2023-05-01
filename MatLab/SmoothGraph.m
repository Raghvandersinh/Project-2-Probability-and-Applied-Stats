x = 1:1:100;
y = x.^2 + x;
tempY = x.^2 +x;
for i = 1:1000
   for j = 1:1
       smooth = movmean(tempY,[2,2]);
   end
   tempY = smooth;
end
hold on
plot(x,y)
plot(x,smooth)

