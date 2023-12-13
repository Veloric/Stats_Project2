clear all, close all
x1 = -100:0.1:100;
y1 = -x1 .^ 2;
plot(x1, y1);
title("Initial Data Points");
xlabel("x");
ylabel("y");

listOfY1 = [-x1 .^ 2];
i = 1;
while (i < size(listOfY1))
   randomInt = randi([2, 500]);
   listOfY1(i) = listOfY1(i) + randomInt;
   i++;
endwhile
plot(x1, listOfY1);
title("Salted Data");

x2 = linspace(-100, 100);
y2 = x1 .^ 2;
y3 = spline(x1, y2, x2);
plot(x2, y3);
title("Smoothed Data");
