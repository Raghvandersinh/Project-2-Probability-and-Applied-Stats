x = 1:1:100;
y = x.^2 + x;
r = randi([-1000,1000],1,100);
salt = 1:1:100;
for i = 1:100
  salt(i) = y(i) + r(i);
end
hold on
plot(x,y)
plot(x,salt)
