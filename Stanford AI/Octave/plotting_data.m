t=[0:0.01:0.98]
y1=sin(2*pi*4*t);
y2=cos(2*pi*4*t);
plot(t,y2)
hold on;
plot(t,y1)
xlabel('time')
ylabel('value')
legend('sin','cos')
title('my plot')
%-save file
print -dpng 'myPlot.png'
close