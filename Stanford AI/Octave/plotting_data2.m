t=[0:0.01:0.98]
y1=sin(2*pi*4*t);
y2=cos(2*pi*4*t);
figure(1); plot(t,y1);
figure(2); plot(t,y2);

subplot(1,2,1); %create a grid 1x2 accessing the first el
plot(t,y1);
subplot(1,2,2);
plot(t,y2)
axis([0.5 1 -1 1]) % set scale
clf; % clear screen

A=magic(5)
imagesc(A) % create a color image based on matrix
imagesc(A), colorbar, colormap gray;
%do a colorbar showing all the gradients and set the coloring
%to grayscale (viridis is default one)

imagesc(magic(15)), colorbar, colormap gray; % of magic 15x15