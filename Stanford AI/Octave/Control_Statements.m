v=zeros(10,1)

for i=1:10,
  v(i)=2^i;
end;
v

indices=1:10;
indices
for i=indices,
  disp(i);
end;

i=1;
while i<=5,
  v(i)=100;
  i=i+1;
end;
v

i=1;
while true,
  v(i)=999;
  i=i+1;
  if i==6,
    break;
    end;
  end;
v

v(1)=2;
if v(1)==1,
  disp('The value is one');
elseif v(1)==2,
  disp('The value is two');
else
  disp('Something else');
end;

%addpath('D:\Github\StanfordAI\Octave') would add the
%search path to the octave directories
squareThis(5)
[a,b] = squareAndCube(5) % is like passing parameters

X= [1 1; 1 2; 1 3];
y= [1; 2; 3];
theta=[0;1];
j=costFunction(X,y,theta)