clear all, close all

x = linspace(-100, 100);
b = 5;
y = x.^2 + b;
plot(x, y);
title("Originial Function");
xlabel("x");
ylabel("y");
hold on;

saltFactor = 25;
salt = saltFactor * randn(size(y));
ySalted = y + salt;
plot(x, ySalted);
title("Salted Data");
xlabel("x");
ylabel("y");
hold on;

smoothRange = 6;
smoothed = movmean(ySalted, smoothRange);
plot(x, smoothed);
title("Smoothed Data");
xlabel("x");
ylabel("y");
hold on;
