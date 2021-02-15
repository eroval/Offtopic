load featuresX.txt
load satY.txt

size(featuresX)
size(satY)

v=satY(1:10)
save hi.mat v;

%clear - deletes all vars in the space
%clear x - deletes x
%whos - display all variables

%eye(m) - mxm Identity matrix
%ones(m) - mxm ones matrix
%zeros(m) - mxm zeroes matrix
%A=[1 2; 3 4; 5 6] - 1 2 matrix[3x2]
%                    3 4
%                    5 6
%A=[1 2 3] - 1x3 matrix
%size(A) - display size of matrix
%disp(sprintf(A)) - display A

%save filename.dat v -ascii - save 'filename.dat' using var v under ASCII encoding
%load data.dat - loads the file as its name


A=[1 2;3 4; 5 6]

whos

%display the element on 3th row and 2nd column
A(3,2)

%display everything on second column
A(:,2)

%display everything on first row
A(1,:)

%display all the data from 1st and 3rd row
A([1,3],:)

%display all the data from first and second row and second column
A([1,3],[2])

%assign 10,11,12 to the element in the second column
A(:,2)=[10;11;12]

%append another column to the right ([100;101;102],A for left append) 
A=[A,[100;101;102]]

%append another row on the bottom
A=[A;100 101 102]

%append row at the top
A=[100 101 102;A]

%put all elements into a single vector
A(:)

%concatanate two matrices
A=[1 2;3 4;5 6]
B=[7 8;9 10;11 12]

C=[A B]



A = [1 2; 3 4; 5 6]
B=[11 12; 13 14; 15 16]
C=[1 1; 2 2]

%multiply two matrices
A*C

%multiply each element by the element in the second matrix (they must be of equal size)
A.*B

%multiply by scalar
A.*2

%power of matrix
A.^2

%recprocal matrix
1./A

%log
log(A)

%exponent
exp(A)

%absolute values
abs(A)

%increment each element by one
A+ones(length(A),1)

%the simpler way
A+1
A+4

%transpose a matrix
A'

A=[1 2;3 4;5 6]
a=[1 15 2 0.5]

%max in array
max(a)

%with index
[val,ind]=max(a)

%check array
a<3

%output all element which answer to a specific case
find(a<3)

sum(a)
floor(a)
ceil(a)

A=[1 2 3;4 5 6;7 8 9]
[r,c]=find(A>=7)

rand(3)

max(rand(3),rand(3))

%column-wise maximum
max(A,[],1)

%row-wise maximum
max(A,[],2)