function [X_norm, mu, sigma] = featureNormalize(X)
%FEATURENORMALIZE Normalizes the features in X 
%   FEATURENORMALIZE(X) returns a normalized version of X where
%   the mean value of each feature is 0 and the standard deviation
%   is 1. This is often a good preprocessing step to do when
%   working with learning algorithms.

%earlier solution
%mu = [mean(X(:,1)) mean(X(:,2))];
%sigma = [max(X(:,1))-min(X(:,1)) max(X(:,2))-min(X(:,2))];
%X_norm=[(X(:,1)-mu(1))/sigma(1) (X(:,2)-mu(2))/sigma(2)]

% You need to set these values correctly
%later soulution
mu = mean(X);
sigma = std(X);
X_norm = (X - mu)./sigma;

% ====================== YOUR CODE HERE ======================
% Instructions: First, for each feature dimension, compute the mean
%               of the feature and subtract it from the dataset,
%               storing the mean value in mu. Next, compute the 
%               standard deviation of each feature and divide
%               each feature by it's standard deviation, storing
%               the standard deviation in sigma. 
%
%               Note that X is a matrix where each column is a 
%               feature and each row is an example. You need 
%               to perform the normalization separately for 
%               each feature. 
%
% Hint: You might find the 'mean' and 'std' functions useful.
%       



% ============================================================

end
